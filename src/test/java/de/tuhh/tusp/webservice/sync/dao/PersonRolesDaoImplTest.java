package de.tuhh.tusp.webservice.sync.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.PersonRoles;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonRolesDaoImplTest {

	@Autowired
	PersonRolesDao personRolesDao;

	@Test
	public void crudTest() {

		PersonRoles pojo = new PersonRoles();

		pojo.setId(0L);
		pojo.setOrgunitLid(2L);
		pojo.setPersonId(6L);
		pojo.setRoleId(196L);
		pojo.setValidFrom(new Date());
		pojo.setValidTo(new Date());
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getValidFrom", "getValidTo" };

		testUtils.simpleCrudTestForDao(personRolesDao, pojo, ignoreGetters, new Change("setPersonId", 7L));

	}
}
