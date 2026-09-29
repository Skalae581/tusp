package de.tuhh.tusp.utils.schedule;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.global.service.JobService;

public class JobLoggerImpl implements JobLogger {

	private JobService jobService;
	private Job job;
	private StringBuilder text = new StringBuilder();
	private Logger logger;

	/**
	 * Construct
	 *
	 * @param job
	 * @param jobService
	 */
	public JobLoggerImpl(Job job, JobService jobService) {
		this.jobService = jobService;
		this.job = job;
		this.logger = LogManager.getLogger(job.getClassName());
	}

	/**
	 * Fügt die Message an das Log an
	 *
	 * @param message
	 * @param level
	 */
	private void append(String message, String level) {

		String s = LocalDate.now().toString() + " " + level + " " + message + "\n";

		text.append(s);
		jobService.appendLog(job.getId(), s);
	}

	@Override
	public void debug(String message) {

		logger.debug(message);
		append(message, "debug");
	}

	@Override
	public void error(String message) {

		logger.error(message);
		append(message, "error");
	}

	@Override
	public void error(String message, Throwable e) {

		logger.error(message, e);
		append(message + "\n" + e.getMessage() + "\n" + e.getStackTrace().toString(), "error");
	}

	@Override
	public Job getJob() {

		return job;
	}

	@Override
	public String getText() {

		return text.toString();
	}

	@Override
	public void info(String message) {

		logger.info(message);
		append(message, "info");
	}

	@Override
	public void warn(String message) {

		logger.warn(message);
		append(message, "warn");
	}
}