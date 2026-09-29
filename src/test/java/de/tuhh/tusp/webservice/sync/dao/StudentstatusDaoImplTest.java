package de.tuhh.tusp.webservice.sync.dao;

import java.text.ParseException;

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
import de.tuhh.tusp.webservice.sync.pojo.Studentstatus;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentstatusDaoImplTest {

	@Autowired
	StudentstatusDao degreeDao;

	@Test
	public void crudTest() throws ParseException {

		Studentstatus pojo = new Studentstatus();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Studentstatus");
		pojo.setDefaulttextEn("Test Studentstatus");
		pojo.setLongtextDe("Test Studentstatus");
		pojo.setLongtextEn("Test Studentstatus");
		pojo.setShorttextDe("Test Studentstatus");
		pojo.setShorttextEn("Test Studentstatus");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setUniquename("unique");
		pojo.setValidTo(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setValidFrom(CalendarUtils.stringToDate("1978-01-01"));
		pojo.setPriority(2L);
		pojo.setAstat("astat");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
