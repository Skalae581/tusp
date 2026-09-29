package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.wsoutput.pojo.TuHHStg;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, TuHHStgDaoImplTest.class })
@ComponentScan(basePackages = "de.tuhh.tusp.webservice.wsoutput.dao", lazyInit = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class TuHHStgDaoImplTest {

	@Autowired
	private TuHHStgDao dao;

	@Test
	public void crudTest() {

		TuHHStg pojo = new TuHHStg();
		pojo.setId(0L);
		pojo.setAstatTuhh("1234");
		pojo.setAstatUnihh("2345");
		pojo.setDtxt("Test");
		pojo.setStg("TST");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setStg", "Tst"));

	}

	@Test
	public void getStgByAstatUni_existingTest() {

		TuHHStg stg1 = new TuHHStg();
		stg1.setAstatTuhh("1234");
		stg1.setAstatUnihh("2345");
		stg1.setDtxt("Test");
		stg1.setStg("stg1");
		stg1.setId(dao.insert(stg1));

		TuHHStg stg2 = new TuHHStg();
		stg2.setAstatTuhh("3456");
		stg2.setAstatUnihh("4567");
		stg2.setDtxt("Test");
		stg2.setStg("stg2");
		stg2.setId(dao.insert(stg2));

		String result = dao.getStgByAstatUni(stg1.getAstatUnihh());

		assertEquals(stg1.getStg(), result);
	}

	@Test
	public void getStgByAstatUni_notExistingTest() {

		String result = dao.getStgByAstatUni("bla");

		assertNull(result);
	}
}
