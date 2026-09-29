package de.tuhh.tusp.utils.auth;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import de.tuhh.tusp.global.service.ConfigService;

public class ShibbolethAuthenticationFilter extends GenericFilterBean {

	private static Logger logger = LogManager.getLogger(ShibbolethAuthenticationFilter.class);

	private ConfigService configService;

	/**
	 *
	 */
	public ShibbolethAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager,
			ConfigService configService) {
		super();

		this.configService = configService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		// Wenn Cast nicht möglich geht es nicht
		if (!(request instanceof HttpServletRequest)) {
			filterChain.doFilter(request, response);
			return;
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.getSession().setMaxInactiveInterval(36000); // = 10h

		// Wenn der Benutzer angemeldet ist, gibt es nichts zu tun
		SecurityContext sci = (SecurityContext) httpRequest.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (sci != null && sci.getAuthentication() != null && sci.getAuthentication().isAuthenticated()) {
			filterChain.doFilter(request, response);
			return;
		}

		String path = httpRequest.getRequestURI();
		String basePath = httpRequest.getContextPath();
		UsernamePasswordAuthenticationToken authRequest = null;

		// Wenn uid-Parameter gesetzt und nicht auf DEV, TST und PROD dann nimm die uid
		String username = request.getParameter("uid");
		Boolean enableBackDoor = configService.get("de.tuhh.tusp.utils.auth.shibboleth.enable_backdoor", "false", true)
				.equals("true");
		if (enableBackDoor) {
			logger.warn("Backdoor ist aktiv!");
			if (username != null) {
				logger.warn("Benutzer " + username + " wird über Backdoor angemeldet.");
				authRequest = new UsernamePasswordAuthenticationToken(username, new ShibbolethTooken());
			}
		}

		// Shibboleth-Seite macht einen Anmeldeversuch
		if (Pattern.matches(".*/shib$", path)) {

			// Wenn Benutzer-Variable nicht gesetz => redirect to shiberror
			username = httpRequest.getHeader(configService.get("de.tuhh.tusp.global.shibboleth_variable", "uid", true));
			if (username == null || username.isEmpty()) {
				logger.warn("Redirect to shiberror wegen fehlender Shibboleth authentifizierung");
				((HttpServletResponse) response).sendRedirect(basePath + "/login?message=env");
				return;
			}
			authRequest = new UsernamePasswordAuthenticationToken(username, new ShibbolethTooken());
		}

		// Loginformular verarbeiten
		if ("true".equals(configService.get("de.tuhh.tusp.enable_loginform", "false", true))
				&& Pattern.matches(".*/login$", path) && httpRequest.getMethod().toLowerCase().equals("post")) {
			username = httpRequest.getParameter("benutzername");
			String passwort = httpRequest.getParameter("passwort");
			authRequest = new UsernamePasswordAuthenticationToken(username, passwort);
		}

		if (authRequest != null) {
			// Wenn auth-Requeste, dann weiterleiten
			SecurityContextHolder.getContext().setAuthentication(authRequest);
		}

		// im Zweifelsfall weitermachen als wäre nichts passiert
		filterChain.doFilter(request, response);
	}
}
