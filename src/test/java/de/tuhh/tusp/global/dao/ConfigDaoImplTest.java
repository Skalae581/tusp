package de.tuhh.tusp.global.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import net.jcip.annotations.NotThreadSafe;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, de.tuhh.tusp.global.Config.class })
@RunWith(SpringJUnit4ClassRunner.class)
@NotThreadSafe
public class ConfigDaoImplTest {

	@Autowired
	ConfigDaoImpl dao;

	@Autowired
	private BasicDataSource dataSource;

	@After
	public void after() {

		if (!dataSource.isClosed()) {
			dao.deleteAll();
		}
	}

	@Before
	public void before() {

		Config c1 = new Config();
		c1.setKey("de.tuhh.tusp.test.some_key");
		c1.setValue("blob1");
		c1.setId(dao.insert(c1));

		Config c2 = new Config();
		c2.setKey("de.tuhh.tusp.test2.some_key");
		c2.setValue("blob2");
		c2.setId(dao.insert(c2));

		Config c3 = new Config();
		c3.setKey("de.tuhh.tusp.test3.some_key");
		c3.setValue("blob3");
		c3.setId(dao.insert(c3));
	}

	@Test
	public void crudTest() {

		Config pojo = new Config();
		pojo.setKey("de.tuhh.tusp.neuer_key");
		pojo.setValue("blob3");
		pojo.setId(dao.insert(pojo));

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		// Beißt sich sonst mit dem Unique-Constraint
		pojo.setKey("de.tuhh.tusp.neuer_key2");

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setValue", "Wert wurde geändert"));

	}

	@Test
	public void fetchByKeyTest() {

		Config c = dao.fetchByKey("");
		assertNull(c);

		c = dao.fetchByKey("de.tuhh.tusp.test.some_key");
		assertEquals("blob1", c.getValue());
	}

	@Test
	public void findByKeyPrefix() {

		// Finde alle wenn kein Suchbefriff
		List<Config> items = dao.findByKeyPrefix(null);
		assertEquals(3, items.size());

		// Finde alle wenn leerer Suchbefriff
		items = dao.findByKeyPrefix("");
		assertEquals(3, items.size());

		// Wie erwartet
		items = dao.findByKeyPrefix("de.tuhh.tusp.test2");
		assertEquals(1, items.size());
	}

	@Test
	public void testConnectionTest() throws SQLException {

		// geht
		dao.testConnection();

		// geht nicht
		dataSource.close(); // Datenbankverbindung beenden
		try {
			dao.testConnection();
			fail();
		}
		catch (Exception e) {}
	}
}
