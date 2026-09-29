package de.tuhh.tusp.global;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.global.dao.ConfigDao;
import net.jcip.annotations.NotThreadSafe;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, Config.class })
@RunWith(SpringJUnit4ClassRunner.class)
@NotThreadSafe
public class TransactionIT {

	private static long id = 0;

	@Autowired
	private ConfigDao dao;

	@Test
	@Transactional
	public void isTransaktionManagementEnabled() {

		assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
	}

	public void test() {

		if (id == 0) {
			// Der erste Lauf legt ein Element an, dass weggeräumt werden muss
			de.tuhh.tusp.global.pojo.Config obj = new de.tuhh.tusp.global.pojo.Config();
			obj.setKey("TEST_KEY");
			obj.setValue("TEST_VALUE");
			id = dao.insert(obj);
		}
		else {
			// Der zweite Lauf stellt sicher, dass das Element weg ist
			try {
				dao.fetchById(id);
				fail("Das TransactionManagement hat kein Rollback gemacht");
			}
			catch (NoSuchElementException e) {}
		}

	}

	@Test
	@Transactional
	public void test1() {

		test();
	}

	@Test
	@Transactional
	public void test2() {

		test();
	}
}
