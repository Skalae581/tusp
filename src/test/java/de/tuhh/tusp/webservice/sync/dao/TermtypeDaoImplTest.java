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
import de.tuhh.tusp.webservice.sync.pojo.Termtype;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TermtypeDaoImplTest {

	@Autowired
	TermtypeDao degreeDao;

	@Test
	public void crudTest() throws ParseException {

		Termtype pojo = new Termtype();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Termtype");
		pojo.setDefaulttextEn("Test Termtype");
		pojo.setLongtextDe("Test Termtype");
		pojo.setLongtextEn("Test Termtype");
		pojo.setShorttextDe("Test Termtype");
		pojo.setShorttextEn("Test Termtype");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setUniquename("unique");
		pojo.setTermCategory(1L);
		pojo.setTermNumber(2L);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
