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
import de.tuhh.tusp.webservice.sync.pojo.Addresstag;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddresstagDaoImplTest {

	@Autowired
	AddresstagDao dao;

	@Test
	public void crudTest() {

		Addresstag pojo = new Addresstag();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Addresstag");
		pojo.setDefaulttextEn("Test Addresstag");
		pojo.setLongtextDe("Test Addresstag");
		pojo.setLongtextEn("Test Addresstag");
		pojo.setShorttextDe("Test Addresstag");
		pojo.setShorttextEn("Test Addresstag");
		pojo.setValid(true);
		pojo.setUniquename("Unique");
		pojo.setSortorder("1");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	dao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
