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
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DegreeDaoImplTest {

	@Autowired
	DegreeDao degreeDao;
	private Degree degree1;
	private Degree degree2;

	@Before
	public void before() {

		degree1 = TestUtils.manufacturePojo(Degree.class);
		degree1.setId(degreeDao.insert(degree1));

		degree2 = TestUtils.manufacturePojo(Degree.class);
		degree2.setId(degreeDao.insert(degree2));
	}

	@Test
	public void crudTest() throws ParseException {

		Degree pojo = new Degree();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Degree");
		pojo.setDefaulttextEn("Test Degree");
		pojo.setLongtextDe("Test Degree");
		pojo.setLongtextEn("Test Degree");
		pojo.setShorttextDe("Test Degree");
		pojo.setShorttextEn("Test Degree");
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

		Degree degree = degreeDao.fetchByUniquename(degree2.getUniquename());

		assertEquals(degree2.getId(), degree.getId());
	}

	@Test
	public void fetchByUniquename_nonExisting() {

		Degree degree = degreeDao.fetchByUniquename("kein degree");

		assertNull(degree);
	}
}
