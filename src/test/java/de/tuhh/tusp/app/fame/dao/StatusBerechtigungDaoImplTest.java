package de.tuhh.tusp.app.fame.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao.ExtendedStatusBerechtigung;
import de.tuhh.tusp.app.fame.enums.FameConstants;
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
public class StatusBerechtigungDaoImplTest {

	@Autowired
	StatusBerechtigungDao statusBerechtigungDao;

	@Autowired
	RollenDao rollenDao;

	@Autowired
	WorkflowDao workflowDao;

	@Autowired
	WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	StatusDao statusDao;

	@Test
	public void crudTest() {

		StatusBerechtigung pojo = new StatusBerechtigung();
		pojo.setId(0L);
		pojo.setStatusFromId(FameTestDaoConfig.statusId);
		pojo.setStatusToId(FameTestDaoConfig.statusId);
		pojo.setRolleId(FameTestDaoConfig.rolleId);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	statusBerechtigungDao, pojo, ignoreGetters,
										new Change("setName", "Name wurde geändert"));

	}

	@Test
	public void findByRollenAndWorkflowInstance() {

		Rollen r1 = TestUtils.manufacturePojo(Rollen.class);
		r1.setName("Tester");
		r1.setId(rollenDao.insert(r1));

		Rollen r2 = TestUtils.manufacturePojo(Rollen.class);
		r2.setName("Debugger");
		r2.setId(rollenDao.insert(r1));

		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		w1.setId(workflowDao.insert(w1));

		Status s1 = TestUtils.manufacturePojo(Status.class);
		s1.setWorkflowId(w1.getId());
		s1.setPosition(1L);
		s1.setId(statusDao.insert(s1));

		Status s2 = TestUtils.manufacturePojo(Status.class);
		s2.setWorkflowId(w1.getId());
		s2.setPosition(2L);
		s2.setId(statusDao.insert(s2));

		Status s3 = TestUtils.manufacturePojo(Status.class);
		s3.setWorkflowId(w1.getId());
		s3.setPosition(3L);
		s3.setId(statusDao.insert(s3));

		Status s4 = TestUtils.manufacturePojo(Status.class);
		s4.setWorkflowId(w1.getId());
		s4.setName(FameConstants.ALL_STATUS);
		s4.setPosition(4L);
		s4.setHidden(1);
		s4.setId(statusDao.insert(s4));

		WorkflowInstance wi1 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi1.setWorkflowId(w1.getId());
		wi1.setParameter("FAME");
		wi1.setId(workflowInstanceDao.insert(wi1));

		String[] roles = { "Tester", "Debugger(FAME)" };

		// Kein Rechte => Leere Liste
		List<ExtendedStatusBerechtigung> rechte =
				statusBerechtigungDao.findByRollenAndWorkflowInstance(Arrays.asList(roles), wi1.getId());
		assertEquals(0, rechte.size());

		// Nicht parametrisiertes Recht
		StatusBerechtigung sb1 = TestUtils.manufacturePojo(StatusBerechtigung.class);
		sb1.setRolleId(r1.getId());
		sb1.setStatusFromId(s1.getId());
		sb1.setStatusToId(s2.getId());
		sb1.setId(statusBerechtigungDao.insert(sb1));
		rechte = statusBerechtigungDao.findByRollenAndWorkflowInstance(Arrays.asList(roles), wi1.getId());
		assertEquals(1, rechte.size());

		// Parametrisiertes Recht
		StatusBerechtigung sb2 = TestUtils.manufacturePojo(StatusBerechtigung.class);
		sb2.setRolleId(r2.getId());
		sb2.setStatusFromId(s2.getId());
		sb2.setStatusToId(s3.getId());
		sb2.setId(statusBerechtigungDao.insert(sb2));
		rechte = statusBerechtigungDao.findByRollenAndWorkflowInstance(Arrays.asList(roles), wi1.getId());
		assertEquals(2, rechte.size());

		StatusBerechtigung sbFromDb = statusBerechtigungDao.fetchById(sb2.getId());
		assertEquals(sbFromDb.getStatusFromId(), rechte.get(1).getStatusFromId());
	}
}
