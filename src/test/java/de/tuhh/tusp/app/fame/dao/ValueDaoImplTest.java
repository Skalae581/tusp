package de.tuhh.tusp.app.fame.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class, FameTestDaoConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ValueDaoImplTest {

	@Autowired
	ValueDao valueDao;

	@Test
	public void crudTest() {

		Value pojo = new Value();
		pojo.setId(0L);
		pojo.setInputId(1L);
		pojo.setVorgangId(1L);
		pojo.setEingabe("");
		pojo.setCreateTime(new Date());

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(valueDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}
}
