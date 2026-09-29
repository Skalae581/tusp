package de.tuhh.tusp.utils.auth;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import de.tuhh.tusp.utils.LdapDao;

/**
 * Implementiert die TU eigenen Authetifizierungsmechanismen und läd die
 * entsprechenden Rollen
 *
 * @author rztjm
 *
 */
public class CustomAuthenticationProviderImpl implements CustomAuthenticationProvider {

	private static Logger logger = LogManager.getLogger(CustomAuthenticationProviderImpl.class);

	@Autowired
	private LdapDao ldapDao;

	@Autowired
	private RoleFetcher roleFetcher;

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {

		try {
			return this.doAuthentication(arg0);
		} catch (AuthenticationException e) {
			throw e;
		} catch (Exception e) {
			throw new AuthenticationServiceException("Athentification faild", e);
		}
	}

	/**
	 * Führt die Authentification durch
	 *
	 * @param arg0
	 * @return das Authentification Result
	 * @throws AuthenticationException Wenn Authentification fehlschlägt
	 */
	private Authentication doAuthentication(Authentication arg0) throws AuthenticationException {

		String username;
		AuthTypes authType = AuthTypes.NULL;

		// Wenn Umgebungsvariable gesetzt, darüber authentifizieren
		if (arg0.getCredentials() instanceof ShibbolethTooken) {
			if (arg0.getPrincipal() == null) {
				throw new ShibbolethAuthException("No username supplied!");
			}

			username = (String) arg0.getPrincipal();
			authType = AuthTypes.SHIBBOLETH;
			logger.debug("User " + username + " über Shibboleth authentifiziert.");
		}

		// Sonst über Benutzername und Passwort
		else {
			try {
				username = (String) arg0.getPrincipal();
				String password = (String) arg0.getCredentials();

				if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
					String msg = "Username and passwort are required!";
					logger.info("BadCredentialsException: " + msg);
					throw new BadCredentialsException(msg);
				}

				// TODO authentifizierung auf HIO und nicht auf LDAP machen
				if (!ldapDao.auth(username, password)) {
					throw new BadCredentialsException(arg0.getPrincipal() + " wrong username or password!");
				}

				// Authentifizierung erfolgreich
				authType = AuthTypes.HIO;
			} catch (ClassCastException e) {
				String msg = "Wrong username or password!";
				logger.info("BadCredentialsException: " + msg, e);
				throw new BadCredentialsException(msg, e);
			}
		}

		Collection<GrantedAuthority> authorities = null;
		try {
			authorities = roleFetcher.getAuthoritiersByUsername(username, authType);
			for (Iterator<GrantedAuthority> iterator = authorities.iterator(); iterator.hasNext();) {
				GrantedAuthority type = iterator.next();
				logger.debug("Authorities||||||||||||||||:" + type.getAuthority());
			}

		} catch (Exception e) {
			String error = "Konnte HISinOne-Rollen nicht laden für " + username;
			logger.error(error, e);
			throw new HisInOneAuthException(error, e);
		}

		/**
		 * All Known Implementing Classes: AbstractAuthenticationToken,
		 * AnonymousAuthenticationToken, CasAssertionAuthenticationToken,
		 * CasAuthenticationToken, JaasAuthenticationToken, OpenIDAuthenticationToken,
		 * PreAuthenticatedAuthenticationToken, RememberMeAuthenticationToken,
		 * RunAsUserToken, TestingAuthenticationToken,
		 * UsernamePasswordAuthenticationToken
		 */
		Authentication result = new PreAuthenticatedAuthenticationToken(arg0.getPrincipal(), arg0.getCredentials(),
				authorities);

		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return arg0.equals(org.springframework.security.authentication.AbstractAuthenticationToken.class)
				|| arg0.equals(org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class);
	}

}
