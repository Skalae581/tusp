package de.tuhh.tusp.config;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.auth.CustomAuthenticationProvider;
import de.tuhh.tusp.utils.auth.CustomAuthenticationProviderImpl;
import de.tuhh.tusp.utils.auth.HisInOneAuthException;
import de.tuhh.tusp.utils.auth.ShibbolethAuthException;
import de.tuhh.tusp.utils.auth.ShibbolethAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	@Autowired
	private AuthenticationManagerBuilder auth;

	@Autowired
	private ConfigService configurationService;

	/**
	 * Erzeugt einen Filter, der Spring sagt, dass die Anfrage UTF-8 kodiert ist
	 *
	 * @return filter
	 */
	private Filter characterEncodingFilter() {

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return encodingFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// UTF-8 filter für Spring
		http.addFilterBefore(characterEncodingFilter(), ChannelProcessingFilter.class);

		// Loginverhalten definieren
		http.formLogin().disable();
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.sessionManagement().invalidSessionUrl("/login?message=ses");
		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {

				String loginUrl = request.getContextPath() + "/login";
				if (authException instanceof HisInOneAuthException
						|| authException instanceof ShibbolethAuthException) {
					// Anmeldung hat nicht funktioniert
					loginUrl += "?message=env";
				} else if (authException instanceof BadCredentialsException) {
					// Benutzername oder PWD falsch
					loginUrl += "?message=cred";
				} else if (authException instanceof InsufficientAuthenticationException) {

					// Ist ne Session da? dann ist sie abgelaufen
					boolean ok = false;
					for (javax.servlet.http.Cookie c : request.getCookies()) {
						if ("JSESSIONID".equals(c.getName())) {
							loginUrl += "?message=ses";
							ok = true;
							break;
						}
					}
					if (!ok) {
						// Benutzer muss sich erst anmelden
						loginUrl += "?message=req";
					}
				}

				response.sendRedirect(loginUrl);
			}
		});

		// Freigaben suchen
		List<AccessConfig> accesses = new ArrayList<>();
		Reflections ref = new Reflections("de.tuhh.tusp", new MethodAnnotationsScanner(), new TypeAnnotationsScanner(),
				new SubTypesScanner());
		for (Class<?> cl : ref.getTypesAnnotatedWith(AccessConfig.class)) {
			AccessConfig findable = cl.getAnnotation(AccessConfig.class);
			logger.debug("Class annotation " + cl.getSimpleName() + " for path " + findable.path());
			accesses.add(findable);
		}
		for (Method cl : ref.getMethodsAnnotatedWith(AccessConfig.class)) {
			AccessConfig findable = cl.getAnnotation(AccessConfig.class);
			logger.debug("Method annotation " + cl.getName() + " for path " + findable.path());
			accesses.add(findable);
		}

		// Die Reihenfolge muss stimmen:
		// "/fame/admin/** require roles: ADMIN" muss vor "/fame/** permitAll"
		accesses.sort(new Comparator<AccessConfig>() {

			@Override
			public int compare(AccessConfig o1, AccessConfig o2) {

				return o1.path().compareTo(o2.path()) * -1;
			}
		});

		// Rechte anwenden
		for (AccessConfig c : accesses) {
			grantAccess(c, http);
		}

		// Startseite darf jeder sehen
		http.authorizeRequests().antMatchers("/").permitAll();

		// Shibboleth darf jeder nutzen
		http.authorizeRequests().antMatchers("/shib").permitAll();
		http.authorizeRequests().antMatchers("/shiberror").permitAll();
		http.authorizeRequests().antMatchers("/shibstart").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/accessDenied").permitAll();

		// Logout darf jeder nutzen
		http.authorizeRequests().antMatchers("/logout").permitAll();

		// Ressourcen darf jeder sehen
		http.authorizeRequests().antMatchers("/resources/**").permitAll();

		// Alles was nicht erlaubt ist ist *verboten*
		http.authorizeRequests().anyRequest().denyAll();

		// add Shibboleth AuthentifikationFilter
		http.addFilterBefore(new ShibbolethAuthenticationFilter("/shib", auth.getObject(), configurationService),
				UsernamePasswordAuthenticationFilter.class);

		// Hiermit werden die Security-Header (und andere) eingeschaltet.
		// Es bietet aber keine vollständige Sicherheit!!!
		// Strict-Transport-Security braucht hier nicht eingeschaltet werden
		// macht die Proxy
		HeadersConfigurer<HttpSecurity> headers = http.headers();
		headers.contentSecurityPolicy("script-src 'self'"); // CSP
		headers.contentTypeOptions(); // immer aktiv
		headers.xssProtection().xssProtectionEnabled(true);// X-XSS-Protection
															// wird vom Firefox
															// nicht supportet!
		headers.frameOptions().sameOrigin(); // Wir wollen nur in unsere frames!
		// Es gibt einen XSS-Filter der vor jedem Request ausgeführt wird. Suche
		// die
		// Klasse XssFilter
	}

	@Bean
	public CustomAuthenticationProvider getCustomAuthenticationProviderImpl() {

		return new CustomAuthenticationProviderImpl();
	}

	/**
	 * Gibt einen Pfad für entsprechend der Config frei
	 *
	 * @param config
	 * @param http
	 * @throws Exception ?
	 */
	private void grantAccess(AccessConfig config, HttpSecurity http) throws Exception {

		String rolesString = "";
		for (Rollen rolle : config.access()) {

			if (rolle.equals(Rollen.PUBLIC)) {
				logger.debug("Path " + config.path() + " permitAll");
				http.authorizeRequests().antMatchers(config.path()).permitAll();
				return;
			}

			logger.debug("Grant Access Role: " + rolle.toString());
			rolesString += "," + rolle.toString();
		}

		rolesString = rolesString.substring(1);

		logger.debug("Path " + config.path() + " require roles: " + rolesString);

		http.authorizeRequests().antMatchers(config.path()).hasAnyRole(rolesString);

	}

	/**
	 * Registriert den AuthentificationProvider
	 *
	 * @param auth
	 * @param authProvider
	 * @throws Exception ?
	 */
//	@Autowired
//	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth,
//			CustomAuthenticationProvider authProvider) throws Exception {
//
//		auth.authenticationProvider(authProvider);
//	}
	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		// Änderung
		auth.authenticationProvider(getCustomAuthenticationProviderImpl());
	}
}
