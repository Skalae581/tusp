package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.courseofstudyservice.CourseOfStudy;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.exception.ServiceFault;

/**
 *
 * @author jjakus
 *
 *         Diese Schnittstelle definert standard Methoden die Studiengänge-Namen
 *         liefert
 */
public interface HisCourseOfStudyHelperDao {

	/**
	 * Holt den Uniquename eines Studiengangs anhand des Astat-Wertes der UniHH
	 *
	 * @param astatUnihh
	 * @return den Uniquename oder null wenn nicht gefunden
	 * @throws ServiceFault          Wenn Webservice Probleme macht.
	 * @throws NumberFormatException Wenn in HISinOne Studiengänge existieren deren
	 *                               PO-Version nicht nur aus einer Jahreszahl
	 *                               bestehen.
	 */
	String findUniquenameByUniHHAstat(String astatUnihh) throws ServiceFault, NumberFormatException;

	/**
	 * Holt den Uniquename des aktuellsten Studiengangs für MobilityOnline
	 *
	 * @return uniquename
	 * @throws ServiceFault          Wenn Webservice Probleme macht.
	 * @throws IllegalStateException Wenn kein Studiengang gefunden wurde.
	 * @throws NumberFormatException Wenn in HISinOne Studiengänge existieren deren
	 *                               PO-Version nicht nur aus einer Jahreszahl
	 *                               bestehen.
	 */
	String findUniquenameForMobilityOnline() throws ServiceFault, IllegalStateException, NumberFormatException;

	/**
	 * Holt den Uniquename des neuesten Studiengang zu einem Subject
	 *
	 * @param subjectLid
	 * @return den Uniquename oder null wenn kein Studiengang gefunden wurde
	 * @throws ServiceFault          Wenn der Webservice Probleme macht
	 * @throws NumberFormatException Wenn in HISinOne Studiengänge existieren deren
	 *                               PO-Version nicht nur aus einer Jahreszahl
	 *                               bestehen.
	 */
	String findUniqunameBySubjectLid(Long subjectLid) throws ServiceFault, NumberFormatException;

	/**
	 * Suche nach allen Studiengängen im HIO und liefert IDs zurüch
	 *
	 * @throws ServiceFault_Exception
	 *
	 *
	 */
	List<Long> getAllCourseOfStudiesIds() throws ServiceFault_Exception;

	/**
	 * suche Studiengang mit ID
	 *
	 * @param id
	 * @return
	 * @throws ServiceFault_Exception
	 */
	CourseOfStudy getCourseOfStudyById(Long id) throws ServiceFault_Exception;

}
