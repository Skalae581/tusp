package de.tuhh.tusp.app.fame.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.pojo.Log;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class LogDaoImplTest {

	@Autowired
	private LogDao logDao;

	@Test
	public void crudTest() {

		Log pojo = new Log();
		pojo.setId(0L);
		pojo.setVorgangId(FameTestDaoConfig.vorgangId);
		pojo.setValueId(FameTestDaoConfig.valueId);
		pojo.setChangeTime(new Date());
		pojo.setSubject("Test");
		pojo.setUser("Mustermann");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(logDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}

}