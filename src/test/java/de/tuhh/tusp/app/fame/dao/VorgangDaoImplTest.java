package de.tuhh.tusp.app.fame.dao;

import static de.tuhh.tusp.app.fame.dao.FameTestDaoConfig.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchparameter;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class VorgangDaoImplTest {

	@Autowired
	private VorgangDao vorgangDao;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private FameTestDaoConfig testRepo;

	@Test
	public void crudTest() {

		Vorgang pojo = new Vorgang();
		pojo.setId(0L);
		pojo.setWorkflowInstanceId(workflowInstanceId);
		pojo.setStatusId(statusId);
		pojo.setConnectorKey("");
		pojo.setGesperrtDurch("");
		pojo.setGesperrtDatum(new Date());
		pojo.setWarn("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(vorgangDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}

	@Test
	public void search_finds() throws Exception {

		Input i1 = testRepo.manufactureInput("i1", workflowId, inputTypeId, 1L);
		Input i2 = testRepo.manufactureInput("i2", workflowId, inputTypeId, 2L);

		Vorgang v1 = testRepo.manufactureVorgang("v1", workflowInstanceId, statusId);
		testRepo.manufactureValue("test", i1.getId(), v1.getId());

		Vorgang v2 = testRepo.manufactureVorgang("v2", workflowInstanceId, statusId);
		testRepo.manufactureValue("muster", i1.getId(), v2.getId());
		testRepo.manufactureValue("test", i2.getId(), v2.getId());

		Map<Long, String> searched = new HashMap<>();
		searched.put(i1.getId(), "test");

		List<WorkflowInstance> wis = Arrays.asList(workflowInstanceDao.fetchById(workflowInstanceId));

		List<Map<String, String>> result = vorgangDao.search(wis, searched);

		assertEquals(1, result.size());
		assertEquals(v1.getId() + "", result.get(0).get("id"));
	}

	@Test
	public void search_handlesUnsafeConditions() {

		assertNull(vorgangDao.search(null, new HashMap<>()));
		assertNull(vorgangDao.search(Arrays.asList(), new HashMap<>()));
		assertNull(vorgangDao.search(Arrays.asList(new WorkflowInstance()), null));
		assertNull(vorgangDao.search(Arrays.asList(new WorkflowInstance()), new HashMap<>()));
	}

	@Test
	public void search2() throws Exception {

		Input i1 = testRepo.manufactureInput("i1", workflowId, inputTypeId, 1L);
		Input i2 = testRepo.manufactureInput("i2", workflowId, inputTypeId, 2L);

		Vorgang v1 = testRepo.manufactureVorgang("v1", workflowInstanceId, statusId);
		testRepo.manufactureValue("test", i1.getId(), v1.getId());

		Vorgang v2 = testRepo.manufactureVorgang("v2", workflowInstanceId, statusId);
		testRepo.manufactureValue("muster", i1.getId(), v2.getId());
		testRepo.manufactureValue("test", i2.getId(), v2.getId());

		List<Suchparameter> params = new ArrayList<>();
		params.add(new Suchparameter(i1, "est"));

		// Findet nur i1 test
		List<Suchergebnis> result = vorgangDao.search(params);
		assertEquals(1, result.size());
		assertEquals(i1.getId(), result.get(0).getValue().getInputId());
		assertEquals("test", result.get(0).getValue().getEingabe());

		// Findet auch i2 test
		params.add(new Suchparameter(i2, "es"));
		result = vorgangDao.search(params);
		assertEquals(2, result.size());

		// findet auch i1 muster
		params.add(new Suchparameter(i1, "ust"));
		result = vorgangDao.search(params);
		assertEquals(3, result.size());
	}
}
