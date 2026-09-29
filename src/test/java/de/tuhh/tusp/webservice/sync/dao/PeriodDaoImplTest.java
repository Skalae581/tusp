package de.tuhh.tusp.webservice.sync.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Period;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class PeriodDaoImplTest {

	@Autowired
	PeriodDao periodDao;

	@Test
	public void crudTest() {

		Period pojo = new Period();

		pojo.setId(0L);
		pojo.setEnddate(new Date(0));
		pojo.setStartdate(new Date(0));
		pojo.setUniquename("WS2017");
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getLid" };
		testUtils.simpleCrudTestForDao(periodDao, pojo, ignoreGetters, new Change("setUniquename", "new name"));

	}
}
