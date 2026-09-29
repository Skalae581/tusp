package de.tuhh.tusp.app.fame.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class InputBerechtigungDaoImplTest {

	@Autowired
	InputBerechtigungDao inputBerechtigungDao;

	@Test
	public void crudTest() {

		InputBerechtigung pojo = new InputBerechtigung();
		pojo.setId(0L);
		pojo.setKommentar("");
		pojo.setInputId(FameTestDaoConfig.inputId);
		pojo.setRolleId(FameTestDaoConfig.rolleId);
		pojo.setStatusId(FameTestDaoConfig.statusId);
		pojo.setRecht(InputRechteEnum.READ);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	inputBerechtigungDao, pojo, ignoreGetters,
										new Change("setKommentar", "Kommentar wurde geändert"));

	}
}
