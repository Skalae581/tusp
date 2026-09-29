package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.studentservice.EntranceQualificationsType;
import de.his.ws.studentservice.SaveHZB;
import de.his.ws.studentservice.ServiceFault_Exception;
import de.his.ws.studentservice.Student70;
import de.his.ws.studentservice.UpdateStudent;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisExternalStudyWrapper;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

public interface HisStudentHelperDao {

	/**
	 * Erstellt einen CandidateStudent und gibt die neue ID zurück.
	 *
	 * @param student
	 * @param cosUniqueName
	 * @param generateMtknr
	 * @return die ID
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	Long createCandidateStudent(HisStudent student, boolean generateMtknr) throws ServiceFault;

	/**
	 * Erstellt einen CandidateStudent und gibt die neue ID zurück. Es wird auch der
	 * Status gesetzt.
	 *
	 * @param student
	 * @param generateMtknr
	 * @param studentHisKeyId
	 * @return die ID
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	Long createCandidateStudentWithStudentStatus(HisStudent student, boolean generateMtknr, long studentHisKeyId)
			throws ServiceFault;

	/**
	 * Holt einen Studenten anhand der PersonenId
	 *
	 * @param personId
	 * @return den Studenten oder null, wenn keiner gefunden wurde
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	HisStudentWrapper fetchStudent(Long personId, boolean withDegreeprograms) throws ServiceFault;

	/**
	 * Liest einen Student über matriekelnummer
	 *
	 * @param updateStd
	 * @return den Studnet
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	HisStudentWrapper fetchStudentByRegistrationNumber(Long regNumber) throws ServiceFault;

	/**
	 * Läd einen Studneten anhand seiner StudentId mit den Daten für eine bestimmte
	 * Period
	 *
	 * @param studentId
	 * @param periodId
	 * @return den Student
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	Student70 fetchStudentByStudentId(Long studentId, Long periodId) throws ServiceFault;

	/**
	 * Holt die Liste der PersonenIds
	 *
	 * @param studystatusFilter
	 * @param cosFilter         Filter für Studiengänge z.B. "1 & !=2"
	 * @return PersonenIds der Studenten
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	List<Long> findStudent(String cosFilter, String studystatusFilter) throws ServiceFault;

	/**
	 *
	 * @param cosFilter
	 * @param studystatusFilter
	 * @param date
	 * @return
	 * @throws ServiceFault
	 */
	List<Long> findStudentsExma(String cosFilter, String studystatusFilter, String date) throws ServiceFault;

	/**
	 * mit Datum
	 */
	List<Long> findStudentWithExmaDate(String date) throws ServiceFault;

	/**
	 * Holt die PersonenID eines Studenten.
	 *
	 * @param studentId
	 * @return Die ID
	 * @throws ServiceFault          Wenn der Webservice Probleme macht.
	 * @throws IllegalStateException Wenn keine oder mehrere Personen gefunden
	 *                               wurden.
	 */
	Long getPersonIdFromStudentId(long studentId) throws ServiceFault, IllegalStateException;

	/**
	 * returns true when Student has "Studienverlaufssatz" for current or next
	 * Semester
	 *
	 * @param student
	 * @return boolean true wenn Studienverlaufssatz existiert
	 * @throws RuntimeException wenn keine Studienverlaufssätze existieren
	 *
	 */
	boolean isThisOrNextPeriodEnroled(HisStudentWrapper student) throws RuntimeException;

	/**
	 * hat er HZB?
	 *
	 * @param personId
	 * @return
	 */
	EntranceQualificationsType readHZB(Long personId) throws ServiceFault_Exception;

	/**
	 * Gibt das Externe Studium einer Person zurück, wenn es eines gibt. Sonst null;
	 *
	 * @param personId
	 * @return externes Studium
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	HisExternalStudyWrapper readStudyExternal(Long personId) throws ServiceFault;

	/**
	 *
	 * speichert HZB mit eingetragene Params
	 *
	 * @param params
	 * @throws ServiceFault_Exception
	 */
	void saveHZB(SaveHZB params) throws ServiceFault_Exception;

	/**
	 * Aktualisiert einen Studenten.
	 *
	 * @param updateStd
	 * @throws ServiceFault Wenn der Webservice Probleme macht.
	 */
	void updateStudent(UpdateStudent updateStd) throws ServiceFault;

}
