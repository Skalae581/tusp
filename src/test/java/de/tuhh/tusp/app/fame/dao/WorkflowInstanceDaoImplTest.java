package de.tuhh.tusp.app.fame.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkflowInstanceDaoImplTest {

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private InputDao inputDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private StatusBerechtigungDao statusBerechtigungDao;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private RollenDao rolleDao;

	@Test
	public void crudTest() {

		WorkflowInstance pojo = new WorkflowInstance();
		pojo.setId(0L);
		pojo.setName("Test-Workflow");
		pojo.setParameter("");
		pojo.setKommentar("");
		pojo.setRechteParameter("");
		pojo.setWorkflowId(FameTestDaoConfig.workflowId);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	workflowInstanceDao, pojo, ignoreGetters,
										new Change("setName", "Name wurde geändert"));
	}

	@Test
	public void WorkflowInstanceDaoImpl() {

		List<String> roles = new ArrayList<>();
		roles.add("Role_1");
		roles.add("Role_2");
		roles.add("Role_3(TEST3)");
		roles.add("Role_4(TEST4)");

		// Erstelle Rollen
		Rollen r1 = new Rollen();
		r1.setName("Role_1");
		r1.setId(rolleDao.insert(r1));

		Rollen r2 = new Rollen();
		r2.setName("Role_2");
		r2.setId(rolleDao.insert(r2));

		Rollen r3 = new Rollen();
		r3.setName("Role_3");
		r3.setId(rolleDao.insert(r3));

		Rollen r4 = new Rollen();
		r4.setName("Role_4");
		r4.setId(rolleDao.insert(r4));

		// Erstelle ersten Workflow
		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		w1.setId(workflowDao.insert(w1));

		// Erstelle erste Instanz
		WorkflowInstance wi1 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi1.setWorkflowId(w1.getId());
		wi1.setName("aaaa");
		wi1.setId(workflowInstanceDao.insert(wi1));

		// Erstelle zweite Instanz mit einschränkung auf "TEST3)
		WorkflowInstance wi2 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi2.setWorkflowId(w1.getId());
		wi2.setRechteParameter("TEST3");
		wi2.setName("bbbb");
		wi2.setId(workflowInstanceDao.insert(wi2));

		// Noch sollte nichts gefunden werden
		List<WorkflowInstance> list = workflowInstanceDao.findByRoles(roles);
		assertTrue(list.isEmpty());

		// Erstelle ein Input mit Berechtigungen für Role1
		Input i1 = TestUtils.manufacturePojo(Input.class);
		i1.setWorkflowId(w1.getId());
		i1.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i1.setId(inputDao.insert(i1));
		InputBerechtigung ib1 = TestUtils.manufacturePojo(InputBerechtigung.class);
		ib1.setInputId(i1.getId());
		ib1.setRolleId(r1.getId());
		ib1.setRecht(InputRechteEnum.READ);
		ib1.setStatusId(FameTestDaoConfig.statusId);
		ib1.setId(inputBerechtigungDao.insert(ib1));

		// Jetzt sollte wi1 und wi2 gefunden werden
		list = workflowInstanceDao.findByRoles(roles);
		assertEquals(2, list.size());
		assertEquals(wi1.getName(), list.get(0).getName());
		assertEquals(wi2.getName(), list.get(1).getName());

		// Hänge die Workflows an Rolle3
		ib1.setRolleId(r3.getId());
		inputBerechtigungDao.update(ib1);

		// Jetzt sollte wi2 gefunden werden
		list = workflowInstanceDao.findByRoles(roles);
		assertEquals(1, list.size());
		assertEquals(wi2.getName(), list.get(0).getName());

		// Input.-Berechtigungen sind bestanden, kommen also weg
		inputBerechtigungDao.deleteAll();

		// Status und StatusBerechtigung für Rolle1 erstellen
		Status s1 = TestUtils.manufacturePojo(Status.class);
		s1.setWorkflowId(w1.getId());
		s1.setId(statusDao.insert(s1));
		StatusBerechtigung sb1 = TestUtils.manufacturePojo(StatusBerechtigung.class);
		sb1.setRolleId(r1.getId());
		sb1.setStatusToId(s1.getId());
		sb1.setStatusFromId(s1.getId());
		sb1.setId(statusBerechtigungDao.insert(sb1));

		// Jetzt sollte wi1 und wi2 gefunden werden
		list = workflowInstanceDao.findByRoles(roles);
		assertEquals(2, list.size());
		assertEquals(wi1.getName(), list.get(0).getName());
		assertEquals(wi2.getName(), list.get(1).getName());

		// StatusBerechtigung an Rolle3 hängen
		sb1.setRolleId(r3.getId());
		statusBerechtigungDao.update(sb1);

		// Jetzt sollte wi2 gefunden werden
		list = workflowInstanceDao.findByRoles(roles);
		assertEquals(1, list.size());
		assertEquals(wi2.getName(), list.get(0).getName());
	}
}
