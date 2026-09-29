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
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RightsAndRolesDaoImplTest {

	@Autowired
	RightsAndRolesDao rightsAndRolesDao;

	@Test
	public void crudTest() {

		RightsAndRoles pojo = new RightsAndRoles();

		pojo.setRoleId(0L);
		pojo.setDefaulttext("neue Rolle");
		pojo.setLongtext("neue Rolle");
		pojo.setShorttext("neue Rolle");
		pojo.setUniquename("neue Rolle");
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getRoleId" };

		testUtils.simpleCrudTestForDao(	rightsAndRolesDao, pojo, ignoreGetters,
										new Change("setDefaulttext", "geänderte Rolle"));

	}

	@Test
	public void fetchByUniqueName_existing() {

		RightsAndRoles rar = TestUtils.manufacturePojo(RightsAndRoles.class);
		rar.setUniquename("F");
		rar.setRoleId(rightsAndRolesDao.insert(rar));

		assertEquals(rar.getId(), rightsAndRolesDao.fetchByUniqename("F").getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void fetchByUniqueName_notExisting() {

		rightsAndRolesDao.fetchByUniqename("M");
	}
}
