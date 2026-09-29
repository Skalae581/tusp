package de.tuhh.tusp.global.service;

import org.apache.logging.log4j.Logger;

import de.tuhh.tusp.global.pojo.Job;

public class JobLogger4J implements JobLogger {

	private Logger logger;

	/**
	 * Construct
	 *
	 * @param logger
	 */
	public JobLogger4J(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void debug(String message) {

		logger.debug(message);
	}

	@Override
	public void error(String message) {

		logger.error(message);

	}

	@Override
	public void error(String message, Throwable e) {

		logger.error(message, e);
	}

	@Override
	public Job getJob() {

		return new Job();
	}

	@Override
	public String getText() {

		throw new RuntimeException("Not implemented");
	}

	@Override
	public void info(String message) {

		logger.info(message);
	}

	@Override
	public void warn(String message) {

		logger.warn(message);
	}

}
