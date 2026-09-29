package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.springframework.core.io.WritableResource;

import de.his.ws.studentservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.UpdateStudentFileWriter;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

public interface HisStudentServiceOutput {

	/**
	 * Speichert einen Student abhängig von seiner Rolle (vorläufig oder Student)
	 *
	 * @param student
	 * @return generierte Person Id des Studenten oder null, wenn param == null
	 * @throws ServiceFault Wenn der Webservice Probleme macht
	 */
	Long createStudent(HisStudent student) throws ServiceFault;

	/**
	 * liest einen Student über mtkrne von HIO
	 *
	 * @param regNumber
	 * @return der Student
	 */
	HisStudentWrapper fetchStudentByRegistrationNumber(Long regNumber);

	/**
	 * Exmatrikulation-Studenten vor 90 Tage suche
	 *
	 * @param mc
	 * @param jobLogger
	 * @return
	 * @throws ServiceFault
	 */
	List<HisStudentWrapper> findExmaStudents(MessageCollector mc, JobLogger jobLogger) throws ServiceFault;

	/**
	 * Liefert alle Studenten aus TUNE, die einen Account bekommen dürfen.
	 *
	 * @param messageCollector Wenn der mc != null, so werden Exceptions gefangen
	 *                         und im mc protokolliert
	 *
	 * @return Liste der Studenten
	 * @throws ServiceFault Wenn der Webservice Probleme macht
	 */
	List<HisStudentWrapper> findValidStudents(MessageCollector messageCollector, JobLogger jobLogger)
			throws ServiceFault;

	/**
	 * Liefert alle Studenten aus TUNE, die in einem Studiengan gund Abschluss sind.
	 *
	 * @param messageCollector Wenn der mc != null, so werden Exceptions gefangen
	 *                         und im mc protokolliert
	 * @param string2
	 * @param string
	 *
	 * @return Liste der Studenten
	 * @throws ServiceFault Wenn der Webservice Probleme macht
	 */
	List<HisStudentWrapper> findValidStudents(MessageCollector messageCollector, String stg, String abschl)
			throws ServiceFault;

	/**
	 * Student Daten update. Alle Werte, die in HisPerson gesetzt sind werden
	 * aktualisiert. Was null ist, bleibt erhalten.
	 *
	 * @param hisPerson
	 * @param updateStudentFileWriter Darf null sein, dann wird nichts protokolliert
	 * @throws ServiceFault           Wenn der Webservice Probleme macht
	 * @throws ServiceFault_Exception
	 */
	void updateStudent(HisPerson hisPerson, HisStudent hisStudent, WritableResource file,
			UpdateStudentFileWriter updateStudentFileWriter) throws ServiceFault, ServiceFault_Exception;

}
