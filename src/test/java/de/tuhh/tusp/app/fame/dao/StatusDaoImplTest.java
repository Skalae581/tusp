package de.tuhh.tusp.app.fame.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StatusDaoImplTest {

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private VorgangDao vorgangDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private RollenDao rollenDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;
	@Autowired
	private InputDao inputDao;

	@Test
	public void crudTest() {

		Status pojo = new Status();
		pojo.setId(0L);
		pojo.setName("Test-Status");
		pojo.setWorkflowId(FameTestDaoConfig.workflowId);
		pojo.setKommentar("");
		pojo.setPosition(0L);
		pojo.setHidden(0);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(statusDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}

	@Test
	public void findByWorkflowTest() {

		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		w1.setId(workflowDao.insert(w1));

		Status s1 = TestUtils.manufacturePojo(Status.class);
		s1.setWorkflowId(w1.getId());
		s1.setPosition(2L);
		s1.setId(statusDao.insert(s1));

		Status s2 = TestUtils.manufacturePojo(Status.class);
		s2.setWorkflowId(w1.getId());
		s2.setPosition(1L);
		s2.setId(statusDao.insert(s2));

		// Schaue ob beide Status in richtiger (umgedrehtern) Reihenfolge gefunden werden
		List<Status> list = statusDao.findByWorkflow(w1.getId(), false);
		assertEquals(2, list.size());
		assertEquals(s1.getName(), list.get(1).getName());
		assertEquals(s2.getName(), list.get(0).getName());
	}

	@Test
	public void findDetaildByWorkflowInstancesTest() {

		List<WorkflowInstance> param = new ArrayList<>();

		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		w1.setId(workflowDao.insert(w1));

		WorkflowInstance wi1 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi1.setWorkflowId(w1.getId());
		wi1.setName("bbb");
		wi1.setId(workflowInstanceDao.insert(wi1));
		param.add(wi1);

		WorkflowInstance wi2 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi2.setWorkflowId(w1.getId());
		wi2.setName("aaa");
		wi2.setId(workflowInstanceDao.insert(wi2));
		param.add(wi2);

		Status s1 = TestUtils.manufacturePojo(Status.class);
		s1.setWorkflowId(w1.getId());
		s1.setPosition(2L);
		s1.setId(statusDao.insert(s1));

		Status s2 = TestUtils.manufacturePojo(Status.class);
		s2.setWorkflowId(w1.getId());
		s2.setPosition(1L);
		s2.setId(statusDao.insert(s2));

		Vorgang v1 = TestUtils.manufacturePojo(Vorgang.class);
		v1.setStatusId(s1.getId());
		v1.setWorkflowInstanceId(wi2.getId());
		v1.setId(vorgangDao.insert(v1));

		Vorgang v2 = TestUtils.manufacturePojo(Vorgang.class);
		v2.setStatusId(s2.getId());
		v2.setWorkflowInstanceId(wi2.getId());
		v2.setId(vorgangDao.insert(v2));

		Vorgang v3 = TestUtils.manufacturePojo(Vorgang.class);
		v3.setStatusId(s1.getId());
		v3.setWorkflowInstanceId(wi1.getId());
		v3.setId(vorgangDao.insert(v3));

		// Rechte verschaffen
		Rollen r1 = new Rollen();
		r1.setName("Test");
		r1.setId(rollenDao.insert(r1));

		Input i1 = TestUtils.manufacturePojo(Input.class);
		i1.setWorkflowId(w1.getId());
		i1.setInputTypeId(1L);
		i1.setId(inputDao.insert(i1));

		InputBerechtigung ib1 = TestUtils.manufacturePojo(InputBerechtigung.class);
		ib1.setRolleId(r1.getId());
		ib1.setInputId(i1.getId());
		ib1.setStatusId(s2.getId());
		ib1.setRecht(InputRechteEnum.READ);
		inputBerechtigungDao.insert(ib1);

		InputBerechtigung ib2 = TestUtils.manufacturePojo(InputBerechtigung.class);
		ib2.setRolleId(r1.getId());
		ib2.setInputId(i1.getId());
		ib2.setStatusId(s1.getId());
		ib2.setRecht(InputRechteEnum.READ);
		inputBerechtigungDao.insert(ib2);

		List<ExtendedWorkflowInstance> list =
				statusDao.findDetaildByWorkflowInstancesAndRoles(param, Arrays.asList(r1.getName()));
		assertEquals(2, list.size()); // zwei WorkflowInstanzen
		assertEquals("aaa", list.get(0).getName()); // Alphabetisch sortiert
		assertEquals(Integer.valueOf(2), list.get(0).getNumVorgaenge()); // zwei Vorgänge in wi2
		assertEquals(Integer.valueOf(1), list.get(1).getNumVorgaenge()); // ein Vorgang in wi1

		assertEquals(2, list.get(0).getStatuses().size()); // zwei Status
		assertEquals(2, list.get(1).getStatuses().size()); // zwei Status

		// wi2, s1 => 1 Vorgang
		assertEquals(Integer.valueOf(1), list.get(0).getStatuses().get(1).getNumVorgaenge()); // zwei Vorgänge in wi2
		// wi1, s2 => 0 Vorgänge
		assertEquals(Integer.valueOf(0), list.get(1).getStatuses().get(0).getNumVorgaenge()); // zwei Vorgänge in wi2
	}
}
