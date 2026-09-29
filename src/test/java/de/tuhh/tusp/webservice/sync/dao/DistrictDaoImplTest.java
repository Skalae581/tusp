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
import de.tuhh.tusp.webservice.sync.pojo.District;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DistrictDaoImplTest {

	@Autowired
	DistrictDao degreeDao;

	private District district1;

	private District district2;

	@Before
	public void before() {

		district1 = TestUtils.manufacturePojo(District.class);
		district1.setId(degreeDao.insert(district1));

		district2 = TestUtils.manufacturePojo(District.class);
		district2.setId(degreeDao.insert(district2));
	}

	@Test
	public void crudTest() throws ParseException {

		District pojo = new District();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test District");
		pojo.setDefaulttextEn("Test District");
		pojo.setLongtextDe("Test District");
		pojo.setLongtextEn("Test District");
		pojo.setShorttextDe("Test District");
		pojo.setShorttextEn("Test District");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setLid(1L);
		pojo.setUniquename("unique");
		pojo.setValidTo(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setValidFrom(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setAstat("astat");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}

	@Test
	public void fetchByUniquename_existing() {

		District district = degreeDao.fetchByUniquename(district2.getUniquename());

		assertEquals(district2.getId(), district.getId());
	}

	@Test
	public void fetchByUniquename_nonExisting() {

		District district = degreeDao.fetchByUniquename("komischer name");

		assertNull(district);
	}
}
