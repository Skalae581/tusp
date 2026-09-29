package de.tuhh.tusp.app.fame.connector.applicantservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tuhh.tusp.app.fame.utils.ConnectorUtils;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.JobService;

public class ApplicantImportAsync implements Runnable {

	private static Logger logger = LogManager.getLogger(ApplicantImportAsync.class);
	private ConnectorUtils connectorUtils;
	private JobService jobService;
	private ApplicantCase c;
	private Job job;

	/**
	 * Construct
	 *
	 * @param connectorUtils
	 * @param jobService
	 * @param c
	 * @param job
	 */
	public ApplicantImportAsync(ConnectorUtils connectorUtils, JobService jobService, ApplicantCase c, Job job) {
		this.connectorUtils = connectorUtils;
		this.jobService = jobService;
		this.c = c;
		this.job = job;
	}

	@Override
	public void run() {

		try {
			String[] parts = job.getName().split(";");
			Long workflowInstanceId = Long.parseLong(parts[1]);
			connectorUtils.importiereVorgang(connectorUtils.initializeConnector(workflowInstanceId), workflowInstanceId,
					c.getId());
		} catch (Exception e) {
			job.setLog(job.getLog() + "\n" + e.toString());
			logger.error("Konnte " + c.getId() + " nicht importieren", e);
		}
		jobService.finishedStep(job, c);
	}

}
