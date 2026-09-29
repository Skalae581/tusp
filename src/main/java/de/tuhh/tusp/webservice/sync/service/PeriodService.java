package de.tuhh.tusp.webservice.sync.service;

public interface PeriodService extends WebserviceSync {

	/**
	 *
	 * @return ID des aktuellen Semesterzeitraumes
	 */
	Long getCurrentSemesterzeitraumId();

	/**
	 *
	 * @return ID des nächsten Semesterzeitraumes
	 */
	Long getNextSemesterzeitraumId();

}
