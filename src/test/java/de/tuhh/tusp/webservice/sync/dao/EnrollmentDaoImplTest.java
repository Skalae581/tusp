package de.tuhh.tusp.webservice.sync.dao;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Enrollment;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class EnrollmentDaoImplTest {

	@Autowired
	EnrollmentDao degreeDao;

	@Test
	public void crudTest() throws ParseException {

		Enrollment pojo = new Enrollment();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Enrollment");
		pojo.setDefaulttextEn("Test Enrollment");
		pojo.setLongtextDe("Test Enrollment");
		pojo.setLongtextEn("Test Enrollment");
		pojo.setShorttextDe("Test Enrollment");
		pojo.setShorttextEn("Test Enrollment");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setUniquename("unique");
		pojo.setAstat("astat");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
