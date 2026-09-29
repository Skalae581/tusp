package de.tuhh.tusp.utils.schedule;

import de.tuhh.tusp.global.service.JobLogger;

public interface Schedulable {

	/**
	 * Methode führt die eigentliche aktion der Bean aus.
	 *
	 * @throws Exception
	 *             beliebiges
	 */
	void start(JobLogger jobLogger) throws Exception;

	/**
	 * Schlägt einen Eindeutigen Namen vor für den Job
	 *
	 * @return eindeutiger Name
	 */
	String suggestName();
}
