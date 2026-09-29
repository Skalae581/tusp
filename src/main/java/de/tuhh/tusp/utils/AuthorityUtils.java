package de.tuhh.tusp.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import de.tuhh.tusp.utils.auth.HISinOneAuthority;

/**
 * Klasse soll die Nutzung der HIS-Rollen in Verbindung mit Spring erleichtern.
 *
 * @author rztjm
 *
 */
public interface AuthorityUtils {

	/**
	 * Gibt die Admin-Rolle in einer Liste zurück
	 *
	 * @return Die Rollen
	 */
	List<String> getAdminRoles();

	/**
	 * Gibt den Anzeigenamen des aktuellen Benutzers zurück oder den Benutzernamen, wenn nichts anderes da ist
	 *
	 * @return Den Displayname
	 */
	String getCurrentUserDisplayName();

	/**
	 * Gibt den Benutzernamen des aktuellen Benutzers zurück
	 *
	 * @return Den Nutzernamen
	 */
	String getCurrentUserName();

	/**
	 * Gibt die Authorities des Benutzers umgewandelt in Rollen-Strings
	 *
	 * Für HISinOneAuthority-Elemente werden vorhandene Rechteeinschränkungen mit angefügt (siehe auch
	 * {@link HISinOneAuthority}).
	 *
	 * @return Die Rollennamen
	 */
	List<String> getRoles();

	/**
	 * Holt die Authorities eines Benutzers
	 *
	 * @return Die Authorities
	 */
	Collection<? extends GrantedAuthority> getUserAuthorities();
}
