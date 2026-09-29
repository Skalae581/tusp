package de.tuhh.tusp.webservice.wsoutput.service;

import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface HisCourseOfStudyServiceOutput {

	/**
	 * Holt den Uniquename eines Studiengangs anhand des Astat-Wertes der UniHH
	 *
	 * @param astatUnihh
	 * @return den Uniquename oder null wenn nicht gefunden
	 * @throws ServiceFault
	 *             Wenn Webservice Probleme macht.
	 * @throws NumberFormatException
	 *             Wenn in HISinOne Studiengänge existieren deren PO-Version nicht nur aus einer Jahreszahl bestehen.
	 */
	String findUniquenameByUniHHAstat(String astatUnihh) throws ServiceFault, NumberFormatException;

	/**
	 * Holt den Uniquename des aktuellsten Studiengangs für MobilityOnline
	 *
	 * @return uniquename
	 * @throws ServiceFault
	 *             Wenn Webservice Probleme macht.
	 * @throws IllegalStateException
	 *             Wenn kein Studiengang gefunden wurde.
	 * @throws NumberFormatException
	 *             Wenn in HISinOne Studiengänge existieren deren PO-Version nicht nur aus einer Jahreszahl bestehen.
	 */
	String findUniquenameForMobilityOnline() throws ServiceFault, IllegalStateException, NumberFormatException;

}
