package de.tuhh.tusp.webservice.sync.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;
import de.tuhh.tusp.webservice.sync.pojo.Person;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoImplTest {

	@Autowired
	PersonDao personDao;

	@Autowired
	AcademicdegreeDao acadDao;

	@Test
	public void crudTest() {

		Academicdegree degree = new Academicdegree();
		degree.setId(3L);
		acadDao.insert(degree);

		Person pojo = new Person();

		pojo.setId(0L);
		pojo.setFirstname("Test Person");
		pojo.setSurname("Test Person");
		pojo.setAcademicdegreeId(degree.getId());
		pojo.setAcademicdegreeSuffix("");
		pojo.setUpdatedAt(new Date());
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getUpdatedAt" };
		testUtils.simpleCrudTestForDao(personDao, pojo, ignoreGetters, new Change("setSurname", "Name wurde geändert"));
	}

	@Test
	public void getMaxUpdated_mitDaten() throws ParseException {

		Person p1 = new Person();
		p1.setId(1L);
		p1.setFirstname("");
		p1.setSurname("");
		p1.setUpdatedAt(CalendarUtils.stringToDate("01.01.2010"));
		personDao.insert(p1);

		Person p2 = new Person();
		p2.setId(2L);
		p2.setFirstname("");
		p2.setSurname("");
		personDao.insert(p2);

		Person p3 = new Person();
		p3.setId(3L);
		p3.setFirstname("");
		p3.setSurname("");
		p3.setUpdatedAt(CalendarUtils.stringToDate("31.12.2005"));
		personDao.insert(p3);

		Date date = personDao.getMaxUpdated();

		assertEquals("Fri Jan 01 00:00:00 CET 2010", date.toString());
	}

	@Test
	public void getMaxUpdated_ohneDaten() {

		Date date = personDao.getMaxUpdated();

		assertNull(date);
	}
}
