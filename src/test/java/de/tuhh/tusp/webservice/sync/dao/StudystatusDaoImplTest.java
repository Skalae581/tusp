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
import de.tuhh.tusp.webservice.sync.pojo.Studystatus;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudystatusDaoImplTest {

	@Autowired
	StudystatusDao degreeDao;

	@Test
	public void crudTest() throws ParseException {

		Studystatus pojo = new Studystatus();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Studystatus");
		pojo.setDefaulttextEn("Test Studystatus");
		pojo.setLongtextDe("Test Studystatus");
		pojo.setLongtextEn("Test Studystatus");
		pojo.setShorttextDe("Test Studystatus");
		pojo.setShorttextEn("Test Studystatus");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setUniquename("unique");
		pojo.setPriority(2L);
		pojo.setAstat("astat");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
