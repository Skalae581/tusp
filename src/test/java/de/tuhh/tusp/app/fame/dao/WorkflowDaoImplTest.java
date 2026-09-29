package de.tuhh.tusp.app.fame.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkflowDaoImplTest {

	@Autowired
	WorkflowDao workflowDao;

	@Test
	public void crudTest() {

		Workflow pojo = new Workflow();
		pojo.setId(0L);
		pojo.setName("Test-Workflow");
		pojo.setConnector("");
		pojo.setKommentar("");
		pojo.setInputValidationCode("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getParent" };
		testUtils.simpleCrudTestForDao(workflowDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));
	}
}
