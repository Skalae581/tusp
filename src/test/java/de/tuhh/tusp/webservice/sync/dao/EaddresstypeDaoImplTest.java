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
import de.tuhh.tusp.webservice.sync.pojo.Eaddresstype;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class EaddresstypeDaoImplTest {

	@Autowired
	EaddresstypeDao dao;

	@Test
	public void crudTest() {

		Eaddresstype pojo = new Eaddresstype();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Eaddresstype");
		pojo.setDefaulttextEn("Test Eaddresstype");
		pojo.setLongtextDe("Test Eaddresstype");
		pojo.setLongtextEn("Test Eaddresstype");
		pojo.setShorttextDe("Test Eaddresstype");
		pojo.setShorttextEn("Test Eaddresstype");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setAddresstype("Test val");
		pojo.setUniquename("Unique");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
