package de.tuhh.tusp.utils.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import de.his.ws.rightsandrolesservice.ServiceFault_Exception;

public interface RoleFetcher {

	/**
	 * holt die Authorities anhand des Benutzernamens
	 *
	 * @param username
	 * @param authType
	 * @return die Authorities
	 * @throws ServiceFault_Exception
	 *             bei IO-Problemen mit dem Webservice
	 */
	Collection<GrantedAuthority> getAuthoritiersByUsername(	String username,
															AuthTypes authType) throws ServiceFault_Exception;
}
