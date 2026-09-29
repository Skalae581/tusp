package de.tuhh.tusp.stu.timsl.dao;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, TimSonderStudentDaoImpl.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TimSonderlStudnetDaoImplTest {

	@Autowired
	private TimSonderStudentDaoImpl historyDao;

	@Test
	public void crudTest() throws ParseException {

		TimSonderStudent pojo = new TimSonderStudent();
		pojo.setId(0L);
		pojo.setMtknr(666L);
		pojo.setStartdate(CalendarUtils.stringToDate("2020-04-20"));
		pojo.setEnddate(CalendarUtils.stringToDate("2020-04-30"));

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	historyDao, pojo, ignoreGetters,
										new Change("setStartdate", CalendarUtils.stringToDate("2020-04-21")));

	}
}
