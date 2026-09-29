package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.studentservice.EntranceQualificationsType;
import de.his.ws.studentservice.SaveHZB;
import de.his.ws.studentservice.ServiceFault_Exception;
import de.his.ws.studentservice.Student70;
import de.his.ws.studentservice.StudentExternalsType;
import de.his.ws.studentservice.StudentInfo;
import de.his.ws.studentservice.StudentInfosType;
import de.his.ws.studentservice.StudentService;
import de.his.ws.studentservice.StudentServicePortType;
import de.his.ws.studentservice.UpdateStudent;
import de.his.ws.studentservice201812.Student;
import de.his.ws.studentservice201812.StudentService201812;
import de.his.ws.studentservice201812.StudentService201812PortType;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.service.CourseOfStudyService;
import de.tuhh.tusp.webservice.sync.service.PeriodService;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisExternalStudyWrapper;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

@Component
public class HisStudentHelperDaoImpl implements HisStudentHelperDao {

	private static Logger logger = LogManager.getLogger(HisStudentHelperDaoImpl.class);

	private StudentServicePortType studentServicePort;
	private StudentService201812PortType studentServicePort201812;

	@Autowired
	private WebserviceFactory serviceFactory;
	@Autowired
	private CourseOfStudyService courseOfStudyService;
	@Autowired
	private HisAddressHelperDao hisAddressHelperDao;
	@Autowired
	private HisRoleHelperDao hisRoleHelperDao;
	@Autowired
	private PeriodService periodService;
	@Autowired
	private CountryDao countryDao;

	private Long currentPeriodId;
	private Long nextPeriodId;

	@Override
	public Long createCandidateStudent(HisStudent student, boolean generateMtknr) throws ServiceFault {

		try {
			return getPort().createCandidateStudent(student.getSurname(), student.getFirstname(),
					student.getGenderHiskey(), student.getCourseOfStudyUniquename(), generateMtknr);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public Long createCandidateStudentWithStudentStatus(HisStudent student, boolean generateMtknr, long studentHisKeyId)
			throws ServiceFault {

		try {
			return getPort().createCandidateStudentWithStudentStatus(student.getSurname(), student.getFirstname(),
					student.getGenderHiskey(), student.getCourseOfStudyUniquename(), generateMtknr, studentHisKeyId);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public HisStudentWrapper fetchStudent(Long personId, boolean withDegreeProgram) {

		try {
			Student student = getPort201812().readStudentByPersonId(personId, withDegreeProgram, false, true, true);
			return new HisStudentWrapper(student, hisRoleHelperDao, courseOfStudyService, hisAddressHelperDao,
					countryDao, this);
		} catch (de.his.ws.studentservice201812.ServiceFault_Exception e) {
			logger.error("Person konnte nicht geladen werden. PersonId = " + personId, e);
			throw new ServiceFault(e);
		}
	}

	@Override
	public HisStudentWrapper fetchStudentByRegistrationNumber(Long regNumber) throws ServiceFault {

		try {
			return fetchStudent(getPort201812().readPersonIdByRegistrationNumber(regNumber), true);
		} catch (de.his.ws.studentservice201812.ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public Student70 fetchStudentByStudentId(Long studentId, Long periodId) {

		try {
			Student70 student = getPort().readStudentByStudentId(studentId, true, periodId, true, true, true);
			return student;
		} catch (ServiceFault_Exception e) {
			logger.error("Person konnte nicht geladen werden. StudnetId = " + studentId + ", PeriodId = " + periodId,
					e);
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<Long> findStudent(String cosFilter, String studystatusFilter) throws ServiceFault {

		List<Long> result = new ArrayList<>();
		try {
			StudentInfosType students = getPort().findStudent61("", "", "", "", "", studystatusFilter, "", "", "", "",
					"", "", "", "", "", "", "", "", cosFilter, "");
			for (StudentInfo studi : students.getStudentInfo()) {
				result.add(studi.getPersionId());
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
		return result;
	}

	@Override
	public List<Long> findStudentsExma(String cosFilter, String studystatusFilter, String date) throws ServiceFault {

		List<Long> result = new ArrayList<>();
		try {
			StudentInfosType students = getPort().findStudent61("", "", "", "", "", studystatusFilter, date, "", "", "",
					"", "", "", "", "", "", "", "", cosFilter, "");
			for (StudentInfo studi : students.getStudentInfo()) {
				result.add(studi.getPersionId());
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
		return result;
	}

	/**
	 * mit Datum
	 */
	@Override
	public List<Long> findStudentWithExmaDate(String date) throws ServiceFault {

		List<Long> result = new ArrayList<>();
		try {
			StudentInfosType students = getPort().findStudent61("", "", "", "", "", "5", date, "", "", "", "", "", "",
					"", "", "", "", "", "", "");
			for (StudentInfo studi : students.getStudentInfo()) {
				result.add(studi.getPersionId());
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
		return result;
	}

	/**
	 *
	 * @return ID des aktuellen Semesterzeitraums
	 */
	private Long getCurrentPeriodId() {

		if (currentPeriodId == null) {
			currentPeriodId = periodService.getCurrentSemesterzeitraumId();
		}
		return currentPeriodId;
	}

	/**
	 *
	 * @return ID des nächsten Semesterzeitraums
	 */
	private Long getNextPeriodId() {

		if (nextPeriodId == null) {
			nextPeriodId = periodService.getNextSemesterzeitraumId();
		}
		return nextPeriodId;
	}

	@Override
	public Long getPersonIdFromStudentId(long studentId) {

		StudentInfosType result;
		try {
			result = getPort().findStudent61("", "=" + studentId, "", "", "", "", "", "", "", "", "", "", "", "", "",
					"", "", "", "", "");

		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (result.getStudentInfo().size() != 1) {
			throw new IllegalStateException("Mehrere PersonenIDs gefunden für StudentID " + studentId);
		}

		return result.getStudentInfo().get(0).getPersionId();
	}

	/**
	 * Port Initialisierung
	 *
	 * @return StudentService Port
	 */
	private StudentServicePortType getPort() {

		if (studentServicePort == null) {
			studentServicePort = serviceFactory.getWebservicePort(StudentService.class);
		}
		return studentServicePort;
	}

	/**
	 * Port Initialisierung
	 *
	 * @return StudentService Port
	 */
	private StudentService201812PortType getPort201812() {

		if (studentServicePort201812 == null) {
			studentServicePort201812 = serviceFactory.getWebservicePort(StudentService201812.class);
		}
		return studentServicePort201812;
	}

	@Override
	public boolean isThisOrNextPeriodEnroled(HisStudentWrapper student) {

		if (!student.hasDegreeProgramms()) {
			throw new RuntimeException("Student " + student + " hat keine Studienverlaufssätze.");
		}

		if (student.getEnrollmentByPeriodId(getCurrentPeriodId()) != null) {
			return true;
		}

		if (student.getEnrollmentByPeriodId(getNextPeriodId()) != null) {
			return true;
		}

		return false;
	}

	@Override
	public EntranceQualificationsType readHZB(Long personId) throws ServiceFault_Exception {
		return getPort().findHzbByPersonId(personId);
	}

	@Override
	public HisExternalStudyWrapper readStudyExternal(Long personId) throws ServiceFault {

		try {
			StudentExternalsType result = getPort().searchStudentExternalByPersonId(personId);

			return new HisExternalStudyWrapper(result);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void saveHZB(SaveHZB params) throws ServiceFault_Exception {
		getPort().saveHZB(params);
	}

	@Override
	public void updateStudent(UpdateStudent updateStd) {

		try {
			getPort().updateStudent(updateStd);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

}
