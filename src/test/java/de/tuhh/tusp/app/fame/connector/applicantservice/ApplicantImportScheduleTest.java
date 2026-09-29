package de.tuhh.tusp.app.fame.connector.applicantservice;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantImportScheduleTest {

	@Mock
	private WorkflowInstanceService workflowInstanceService;

	@Mock
	private WorkflowDao workflowDao;

	private Scheduler scheduler = new Scheduler() {

		@Override
		public void schedule(Class<? extends Schedulable> job, String jobname) {

			if (!jobs.contains(jobname)) {
				jobs.add(jobname);
			}
		}
	};
	private List<String> jobs;

	@InjectMocks
	private ApplicantImportSchedule schedule;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		TestUtils.setField(schedule, "scheduler", scheduler);

	}

	private void scheduleJobByTime_n_jobs(int n) {

		System.out.println("Starte mit n = " + n);

		List<WorkflowInstance> wis = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			WorkflowInstance wi = new WorkflowInstance();
			wi.setId((long) i);
			wi.setParameter("wi" + wi.getId());
			wi.setName(wi.getParameter());
			wis.add(wi);
		}
		when(workflowInstanceService.findByWorkflow(1L)).then(x -> wis);

		jobs = new ArrayList<>();

		for (long t = 0; t <= 3599999; t += 60000) { // eine Stunde lang jede Minute
			schedule.scheduleJobByTime(t);
		}

		if (n == 0) {
			assertTrue(jobs.isEmpty());
		}
		else {
			// Schau, dass jeder min einmal dran war
			for (int i = 1; i <= n; i++) {
				assertTrue(jobs.contains("ApplicantImportSchedule 0;" + i + ";wi" + i));
			}
		}
	}

	@Test
	public void scheduleJobByTime_test() {

		List<Workflow> ws = new ArrayList<>();
		Workflow w = new Workflow();
		w.setId(1L);
		w.setName("w" + w.getId());
		ws.add(w);
		when(workflowDao.findWorkflowsByConnector(ApplicantConnectorImpl.class)).then(x -> ws);

		scheduleJobByTime_n_jobs(0);
		scheduleJobByTime_n_jobs(1);
		scheduleJobByTime_n_jobs(2);
		scheduleJobByTime_n_jobs(3);
		scheduleJobByTime_n_jobs(59);
	}

	@Test(expected = RuntimeException.class)
	public void scheduleJobByTime_throws_max_instances() {

		List<Workflow> ws = new ArrayList<>();
		Workflow w = new Workflow();
		w.setId(1L);
		w.setName("w" + w.getId());
		ws.add(w);
		when(workflowDao.findWorkflowsByConnector(ApplicantConnectorImpl.class)).then(x -> ws);

		scheduleJobByTime_n_jobs(60);
	}
}
