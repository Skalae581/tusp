package de.tuhh.tusp.app.fame.connector.applicantservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;
import de.tuhh.tusp.app.fame.utils.ConnectorUtils;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.global.service.JobService;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class ApplicantImportSchedule implements Schedulable {

	private static final String CONFIG_ENABLE_SCHEDULING = "de.tuhh.tusp.app.fame.connector.applicantservice.ENABLE_SCHEDULING";

	private static Logger logger = LogManager.getLogger(ApplicantImportSchedule.class);

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private WorkflowInstanceService workflowInstanceService;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ConfigService config;

	@Autowired
	private JobService jobService;

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private ConnectorUtils connectorUtils;

	/**
	 * Übergibt dieses Objekt alle 60 Minuten an den Scheduler
	 */
	@Scheduled(fixedRate = 1000 * 60)
	public void schedule() {

		GregorianCalendar now = new GregorianCalendar();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 5 || hour > 21) {
			return;
		}

		if (!"true".equals(config.get(CONFIG_ENABLE_SCHEDULING, "true", true))) {
			return;
		}

		scheduleJobByTime(System.currentTimeMillis());
	}

	/**
	 * Verteilt die Starts der Jobs gleichmäßig in einer Stunde
	 *
	 * @param millis
	 */
	public void scheduleJobByTime(long millis) {

		long id = millis / (1000L * 60 * 60);
		String jobname = "ApplicantImportSchedule " + id;

		for (Workflow w : workflowDao.findWorkflowsByConnector(ApplicantConnectorImpl.class)) {
			List<WorkflowInstance> wis = workflowInstanceService.findByWorkflow(w.getId());

			if (wis.size() == 0) {
				continue;
			}

			if (wis.size() >= 60) {
				throw new RuntimeException(
						"Es werden nur bis zu 59 WorkflowInstanzen unterstützt. " + w.getName() + " hat " + wis.size());
			}

			long index = millis / (1000L * 60) % wis.size();
			WorkflowInstance wi = wis.get((int) index);

			logger.info("Schedule " + w.getName() + " " + wi.getName());

			scheduler.schedule(getClass(), jobname + ';' + wi.getId() + ';' + wi.getParameter());
		}
	}

	@Override
	public void start(JobLogger job) throws Exception {

		String[] parts = job.getJob().getName().split(";");
		Long id = Long.parseLong(parts[1]);

		List<Case> cases = connectorUtils.findCases(connectorUtils.initializeConnector(id), id, ImportFilter.Alle);
		jobService.registerSteps(job.getJob(), new ArrayList<Object>(cases));
		for (Case c : cases) {

			if (c.getValid() == Validity.ONLY_IF_EXISTS && c.getId() == null) {
				continue;
			}

			taskExecutor.execute(new ApplicantImportAsync(connectorUtils, jobService, (ApplicantCase) c, job.getJob()));
		}
	}

	@Override
	public String suggestName() {

		long id = System.currentTimeMillis() / (1000L * 60 * 60);
		String jobname = "ApplicantImportSchedule " + id + ";<WorkflowId>;<WorkflowInstanceParameter>";
		return jobname;
	}

}
