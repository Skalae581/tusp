package de.tuhh.tusp.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;

/**
 * Klasse um mit dem LDAP zu kommunizieren.
 *
 * @author rztjm
 *
 */
@Component
public class LdapDaoImpl implements LdapDao {

	private static Logger logger = LogManager.getLogger(LdapDaoImpl.class);

	@Autowired
	private ConfigService config;

	private DirContext context;

	@Override
	public boolean auth(String username, String password) {

		try {
			String baseDn = config.get("de.tuhh.tusp.utils.ldap.baseDn", "", true);
			DirContext tmpCtx = createContext("uid=" + username + ",ou=People," + baseDn, password);
			tmpCtx.close();
			return true;
		} catch (NamingException e) {
			if (!e.getMessage().contains("Invalid Credentials")) {
				logger.error(e);
			}
		}
		return false;
	}

	@Override
	public void closeContext() {

		if (context == null) {
			return;
		}

		try {
			context.close();
			context = null;
		} catch (Exception e) {
			logger.warn("Konnte Ldap-Context nicht schließen", e);
		}
	}

	/**
	 * Übernimmt die eigentliche Contexterzeugung. Durch diese Auslagerung kann die
	 * auth()-Methode einen Bind machen ohne den Klassen-Context zu ändern.
	 *
	 * @param username
	 * @param password
	 * @return die Connection
	 * @throws NamingException Wenn der Verbindungsaufbau fehlschlägt
	 */
	private DirContext createContext(String bindDn, String password) throws NamingException {

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, getConnectionString());

		env.put(Context.SECURITY_PROTOCOL, "ssl");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, bindDn);
		env.put(Context.SECURITY_CREDENTIALS, password);

		// Debugging output
		// env.put("com.sun.jndi.ldap.trace.ber", System.err);

		// Create the initial context
		return new InitialDirContext(env);
	}

	/**
	 * Erzeugt den Connection-String
	 *
	 * @return Connection-String
	 */
	private String getConnectionString() {

//		String protokoll = config.get("de.tuhh.tusp.utils.ldap.protokoll", "ldaps", true);
//		String server = config.get("de.tuhh.tusp.utils.ldap.servername", "", true);
//		String port = config.get("de.tuhh.tusp.utils.ldap.port", "636", true);
//		String baseDn = config.get("de.tuhh.tusp.utils.ldap.baseDn", "", true);

		String protokoll = "ldaps";
		String server = "ldap.rz.tu-harburg.de";
		String port = "636";
		String baseDn = "dc=tu-harburg,dc=de";

		if (server.isEmpty() || baseDn.isEmpty()) {
			throw new RuntimeException("LDAP ist nicht konfiguriert: de.tuhh.tusp.utils.ldap.*");
		}

		// String test = protokoll + "://" + server + ":" + port + "/" + baseDn;

		return protokoll + "://" + server + ":" + port + "/" + baseDn;
	}

	@Override
	public SearchResult getLdapPerson(String filter) {

		try {
			if (context == null) {
				initContext();
			}
			List<SearchResult> result = searchPerson(filter);
			if (result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (Exception e) {
			logger.error("Konnte Benuter nicht aus LDAP laden", e);
			return null;
		}
	}

	@Override
	public String getStringAttribute(SearchResult result, String name) {

		try {
			List<String> strings = getStringListAttribute(result, name);
			if (strings == null || strings.size() == 0) {
				return null;
			}
			return strings.get(0);
		} catch (Exception e) {
			// TODO hier sollte eine Exception fliegen!
			logger.error("Konnte Attribut nicht laden", e);
			return null;
		}
	}

	public List<String> getStringListAttribute(SearchResult result, String name) {

		if (result == null) {
			return null;
		}

		Attribute res = result.getAttributes().get(name);
		if (res == null) {
			return null;
		}

		List<String> ret = new ArrayList<>();
		try {
			for (int i = 0; i < res.size(); i++) {
				ret.add((String) res.get(i));
			}
			return ret;
		} catch (NamingException e) {
			throw new RuntimeException("Konnte Benuter nicht aus LDAP laden", e);
		}
	}

	@Override
	public void initContext() throws NamingException {

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, getConnectionString());

		env.put(Context.SECURITY_PROTOCOL, "ssl");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "none");

		// Debugging output
		// env.put("com.sun.jndi.ldap.trace.ber", System.err);

		// Create the initial context
		this.context = new InitialDirContext(env);
	}

	@Override
	public void initContext(String username, String password) throws NamingException {

		String baseDn = config.get("de.tuhh.tusp.utils.ldap.baseDn", null, true);
		this.context = createContext("uid=" + username + ",ou=People," + baseDn, password);
	}

	@Override
	public void initContextWithDn(String bindDn, String password) throws NamingException {

		this.context = createContext(bindDn, password);
	}

	public LdapPerson resultToPerson(SearchResult result) {

		LdapPerson pers = new LdapPerson();
		pers.setUid(getStringAttribute(result, "uid"));
		pers.setDisplayName(getStringAttribute(result, "displayName"));
		String gebDat = getStringAttribute(result, "hhEduPersonDateOfBirth");
		try {
			if (gebDat != null) {
				pers.setDateOfBirth(CalendarUtils.stringToDate(gebDat));
			}
		} catch (ParseException e) {
			logger.error("Date " + gebDat + " konnte nicht geparst werden", e);
		}
		pers.setGivenName(getStringAttribute(result, "givenName"));
		pers.setSurname(getStringAttribute(result, "sn"));
		pers.setMail(getStringAttribute(result, "mail"));
		pers.setOrgUnit(getStringAttribute(result, "ou"));
		pers.setGender(getStringAttribute(result, "hhEduPersonGender"));
		pers.setPostalCode(getStringAttribute(result, "postalCode"));
		pers.setRoomNumber(getStringAttribute(result, "roomNumber"));
		pers.setStreet(getStringAttribute(result, "street"));
		pers.setTelefonNumber(getStringAttribute(result, "telephoneNumber"));
		pers.setScapedAffiliations(getStringListAttribute(result, "tuhhEduPersonScopedAffiliation"));
		return pers;
	}

	@Override
	public List<SearchResult> search(String searchFilter, String searchScope) throws NamingException {

		logger.debug("Executed query '" + searchFilter + "' with scope '" + searchScope + "'");

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<SearchResult> results = context.search(searchScope, searchFilter, searchControls);
		ArrayList<SearchResult> ret = new ArrayList<>();
		while (results.hasMoreElements()) {
			ret.add(results.nextElement());
		}

		logger.debug("Got " + ret.size() + " entries.");

		return ret;
	}

	@Override
	public List<LdapPerson> searchLdapPerson(String searchFilter) throws NamingException {

		List<LdapPerson> ret = new ArrayList<>();
		List<SearchResult> results = search(searchFilter, "ou=People");
		for (SearchResult searchResult : results) {
			ret.add(resultToPerson(searchResult));
		}
		return ret;
	}

	@Override
	public List<SearchResult> searchPerson(String searchFilter) throws NamingException {

		return search(searchFilter, "ou=People");
	}
}
