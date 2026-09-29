package de.tuhh.tusp.global.service;

import de.tuhh.tusp.global.pojo.Job;

public interface JobLogger {

	/**
	 * Log eine Message
	 *
	 * @param message
	 */
	void debug(String message);

	/**
	 * Log eine Message
	 *
	 * @param message
	 */
	void error(String message);

	/**
	 * Log eine Message
	 *
	 * @param message
	 */
	void error(String message, Throwable e);

	/**
	 * Gibt den Job zurück
	 *
	 * @return der Job
	 */
	Job getJob();

	/**
	 * Gibt den bisher geloggten Text zurück.
	 *
	 * @return Logtext
	 */
	String getText();

	/**
	 * Log eine Message
	 *
	 * @param message
	 */
	void info(String message);

	/**
	 * Log eine Message
	 *
	 * @param message
	 */
	void warn(String message);
}
