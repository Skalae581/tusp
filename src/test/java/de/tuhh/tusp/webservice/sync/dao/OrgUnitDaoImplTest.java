package de.tuhh.tusp.webservice.sync.dao;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OrgUnitDaoImplTest {

	@Autowired
	OrgUnitDao orgUnitDao;

	@Test
	public void crudTest() {

		OrgUnit pojo = new OrgUnit();

		pojo.setLid(0L);
		pojo.setId(0L);
		pojo.setParentId(1L);
		pojo.setDisplaytextDe("setDisplaytextDe");
		pojo.setType(1L);
		pojo.setLongtextDe("setLongtextDe");
		pojo.setShorttextDe("setShorttextDe");
		pojo.setUniquename("setUniquename");
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getLid" };
		testUtils.simpleCrudTestForDao(	orgUnitDao, pojo, ignoreGetters,
										new Change("setDisplaytextDe", "Name wurde geändert"));

	}

	@Test
	public void fetchByShorttextDe_existing() {

		OrgUnit ou = TestUtils.manufacturePojo(OrgUnit.class);
		ou.setShorttextDe("MyOU");
		ou.setId(orgUnitDao.insert(ou));

		assertEquals(ou.getId(), orgUnitDao.fetchByShorttextDe("MyOU").getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void fetchByShorttextDe_notExisting() {

		orgUnitDao.fetchByShorttextDe("MyOU2");
	}
}
