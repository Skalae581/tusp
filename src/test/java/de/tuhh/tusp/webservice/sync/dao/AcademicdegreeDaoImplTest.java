package de.tuhh.tusp.webservice.sync.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class AcademicdegreeDaoImplTest {

	@Autowired
	private AcademicdegreeDao dao;

	@Test
	public void crudTest() {

		Academicdegree pojo = new Academicdegree();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Degree");
		pojo.setDefaulttextEn("Test Degree");
		pojo.setLongtextDe("Test Degree");
		pojo.setLongtextEn("Test Degree");
		pojo.setShorttextDe("Test Degree");
		pojo.setShorttextEn("Test Degree");
		pojo.setValid(true);
		pojo.setUniquename("asdf");
		pojo.setSortorder("123");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
