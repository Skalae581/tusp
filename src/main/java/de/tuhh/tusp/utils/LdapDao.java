package de.tuhh.tusp.utils;
//TODO move to package de.tuhh.tusp.utils.net;

import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import lombok.Data;

/**
 * Das LdapDao stellt die Schnittstelle zwischen der Anwendung und dem TU-LDAP
 * dar.
 *
 * @author rztjm
 *
 */
public interface LdapDao {

	@Data
	public class LdapPerson {

		private String uid;
		private String displayName;
		private String surname;
		private String givenName;
		private Date dateOfBirth;
		private String mail;
		private String orgUnit;
		private String gender;
		private List<String> scapedAffiliations;
		private String postalCode;
		private String roomNumber;
		private String street;
		private String telefonNumber;

		public String toShortString() {

			return uid + "(" + displayName + ")";
		}
	}

	/**
	 * Versucht einen LDAP-Bind mit den Credentials zu machen.
	 *
	 * @param username
	 * @param password
	 * @return true wenn Credentials gültig sind
	 */
	boolean auth(String username, String password);

	/**
	 * Schließt den LDAP-Context der Instanz.
	 */
	void closeContext();

	SearchResult getLdapPerson(String filter);

	String getStringAttribute(SearchResult result, String name);

	/**
	 * Erzeugt einen annonymen LDAP-Context für die Instanz.
	 *
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	void initContext() throws NamingException;

	/**
	 * Erzeugt mit den Credentials einen neuen LDAP-Context für die Instanz.
	 *
	 * @param username
	 *            eine Benutzerkennung aus der OU People
	 * @param password
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	void initContext(String username, String password) throws NamingException;

	/**
	 * Erzeugt mit den Credentials einen neuen LDAP-Context für die Instanz.
	 *
	 * @param dn
	 *            die vollständige dn des Benutzers
	 * @param password
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	void initContextWithDn(String dn, String password) throws NamingException;

	/**
	 * Sucht mit Hilfe des Filters im LDAP, unterhalb des angegebenen Scope.
	 * Vorher muss initContext() aufgerufen worden sein!
	 *
	 * @param searchFilter
	 * @return Liste mit den {@link SearchResult}
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	List<SearchResult> search(String searchFilter, String searchScope) throws NamingException;

	/**
	 * Sucht mit Hilfe des Filters im LDAP, unterhalb der ou People. Vorher muss
	 * initContext() aufgerufen worden sein!
	 *
	 * @param searchFilter
	 * @return Liste mit den {@link SearchResult}
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	List<LdapPerson> searchLdapPerson(String searchFilter) throws NamingException;

	/**
	 * Sucht mit Hilfe des Filters im LDAP, unterhalb der ou People. Vorher muss
	 * initContext() aufgerufen worden sein!
	 *
	 * @deprecated
	 * @param searchFilter
	 * @return Liste mit den {@link SearchResult}
	 * @throws NamingException
	 *             wenn es LDAP-Fehler gibt
	 */
	@Deprecated
	List<SearchResult> searchPerson(String searchFilter) throws NamingException;

}