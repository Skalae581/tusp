package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class LdapDaoImplIT {

	@Autowired
	LdapDao ldapDao;

	@Test
	public void authTest() {

		// Fehlschlagende Anmeldung:
		boolean result = ldapDao.auth("rztjm", "dasPwdIstUngültig");

		assertFalse("Benutzer hätte nicht angemeldet sein dürfen!!!!!!!", result);
	}

	@Test
	public void initAndCloseContextTest() {

		try {
			ldapDao.initContext();
		}
		catch (NamingException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		finally {
			ldapDao.closeContext();
		}
	}

	void initContextTest() {
		// Kann aktuell nicht getestet werden, da wir keine eigenen Kredentioals
		// haben
	}

	@Test
	public void searchPersonTest() {

		try {
			ldapDao.initContext();

			List<SearchResult> persons = ldapDao.searchPerson("uid=rzt*m");

			assertTrue("Zu wenige LDAP-User gefunden", persons.size() > 1);

		}
		catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally {
			ldapDao.closeContext();
		}
	}

	@Test
	public void searchTest() {

		try {
			ldapDao.initContext();

			List<SearchResult> persons = ldapDao.search("uid=rztjm", "ou=People");

			assertEquals(	"Falsche Person im LDAP gefunden.", "Jan",
							persons.get(0).getAttributes().get("givenName").get());

		}
		catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally {
			ldapDao.closeContext();
		}
	}
}
