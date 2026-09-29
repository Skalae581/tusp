package de.tuhh.tusp.webservice.sync.dao;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
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
import de.tuhh.tusp.webservice.sync.pojo.Subject;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectDaoImplTest {

	@Autowired
	private SubjectDao degreeDao;
	private Subject subject1;
	private Subject subject2;

	@Before
	public void before() {

		subject1 = TestUtils.manufacturePojo(Subject.class);
		subject1.setId(degreeDao.insert(subject1));

		subject2 = TestUtils.manufacturePojo(Subject.class);
		subject2.setId(degreeDao.insert(subject2));
	}

	@Test
	public void crudTest() throws ParseException {

		Subject pojo = new Subject();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Subject");
		pojo.setDefaulttextEn("Test Subject");
		pojo.setLongtextDe("Test Subject");
		pojo.setLongtextEn("Test Subject");
		pojo.setShorttextDe("Test Subject");
		pojo.setShorttextEn("Test Subject");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setLid(1L);
		pojo.setUniquename("unique");
		pojo.setValidTo(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setValidFrom(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setAstatBund("");
		pojo.setAstatLand("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}

	@Test
	public void fetchByUniquename_existing() {

		Subject subject = degreeDao.fetchByUniquename(subject2.getUniquename());

		assertEquals(subject2.getId(), subject.getId());
	}

	@Test
	public void fetchByUniquename_nonExisting() {

		Subject subject = degreeDao.fetchByUniquename("gibts nicht");

		assertNull(subject);
	}
}
