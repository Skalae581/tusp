package de.tuhh.tusp.webservice.wsoutput.service;

import static de.tuhh.tusp.utils.CoalesceUtils.coalesce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.personservice.PersonExisting;
import de.his.ws.studentservice.DegreePrograms70Type;
import de.his.ws.studentservice.EntranceQualificationDto;
import de.his.ws.studentservice.EntranceQualificationsType;
import de.his.ws.studentservice.Examplan70;
import de.his.ws.studentservice.ExamrelationDto;
import de.his.ws.studentservice.Person70;
import de.his.ws.studentservice.SaveHZB;
import de.his.ws.studentservice.ServiceFault_Exception;
import de.his.ws.studentservice.StayAbroadsType;
import de.his.ws.studentservice.StudentExisting;
import de.his.ws.studentservice.StudentFunctionlocksType;
import de.his.ws.studentservice.UpdateStudent;
import de.his.ws.studentservice201812.DegreeProgram;
import de.his.ws.studentservice201812.DegreeProgramProgress;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.UpdateStudentFileWriter;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.service.CourseOfStudyService;
import de.tuhh.tusp.webservice.utils.NullBehavior;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPersonHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisStudentHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDao;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

@Component
public class HisStudentServiceOutputImpl implements HisStudentServiceOutput {

	private static Logger logger = LogManager.getLogger(HisStudentServiceOutputImpl.class);

	private static CalendarUtils calender;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private HisStudentHelperDao hisStudentHelperDao;

	@Autowired
	private HisPersonHelperDao hisPersonHelperDao;

	@Autowired
	private CourseOfStudyService cosService;

	@Autowired
	private ConfigService config;

	@Autowired
	private CourseOfStudyDao courseOfStudyDao;

	@Override
	public Long createStudent(HisStudent student) throws ServiceFault {

		if (student == null) {
			return null;
		}

		// Schauen, ob der Student im Cache ist
		Long personId = studentDao.getPersonIdFromMtknrId(student.getExterneMtknr());

		// Student existiert => update
		if (personId != null) {
			logger.info(
					"Student [personId=" + personId + "] bereits lokal vorhanden. Es wird kein neuer Studet erzeugt.");
			return personId;
		}

		// // Student neu erzeugen
		logger.info("Einen neuen Student (" + student.getFirstname() + " " + student.getSurname() + ") hinzufügen...");
		Long newStudentId = null;

		// Mobility Online
		if (student.getIsCandidateStudent()) {
			// TODO 1L ?
			newStudentId = hisStudentHelperDao.createCandidateStudentWithStudentStatus(student, false, 1L);
		}
		// Uni HH
		else {
			newStudentId = hisStudentHelperDao.createCandidateStudent(student, true);
		}

		personId = hisStudentHelperDao.getPersonIdFromStudentId(newStudentId);

		Student s = new Student();
		s.setMtknr(student.getExterneMtknr());
		s.setPersonId(personId);
		s.setStudentId(newStudentId);
		s.setValid(true);
		studentDao.insert(s);

		return personId;
	}

	@Override
	public HisStudentWrapper fetchStudentByRegistrationNumber(Long regNumber) throws ServiceFault {

		return hisStudentHelperDao.fetchStudentByRegistrationNumber(regNumber);
	}

	@Override
	public List<HisStudentWrapper> findExmaStudents(MessageCollector mc, JobLogger jobLogger) throws ServiceFault {

		String studystatusFilter = config.get("de.tuhh.tusp.webservice.studystatusFilterExmatrikulation", "5", true);
		LocalDate datePast90Days = LocalDate.now().minusDays(90);

		String date = datePast90Days != null ? ">=" + datePast90Days + "& <= " + LocalDate.now() : "";

		List<Long> personIds = hisStudentHelperDao.findStudentsExma(null, studystatusFilter, date.toString());

		List<HisStudentWrapper> students = new ArrayList<>();

		for (Long personId : personIds) {
			try {
				HisStudentWrapper student = hisStudentHelperDao.fetchStudent(personId, true);

				if (student.getExmaDatum() != null) {

					students.add(student);
					logger.debug("Student Exma Datum Hinzugefügt: ["
							+ student.getRegistrationNumber(NullBehavior.RETURN_EMPTY) + " | "
							+ student.getGeburtsName() + " | " + student.getExmaDatum());

				}

			} catch (Exception e) {
				if (mc == null) {
					throw new RuntimeException("MC == NULL");
				}

				String msg = "Student mit personID " + personId + ": " + e.getMessage();
				logger.error(msg, e);
				jobLogger.error(msg);
				mc.addErrorMessage(msg);
			}
		}

		return students;
	}

	@Override
	public List<HisStudentWrapper> findValidStudents(MessageCollector mc, JobLogger jobLogger) throws ServiceFault {

		String studystatusFilter = config.get("de.tuhh.tusp.webservice.studystatusFilterFilter", "1|2|3|4", true);
		String roleIdFilter = config.get("de.tuhh.tusp.webservice.studentRoleFilter", "5 & !=65", true);

		List<Long> personIds = hisPersonHelperDao.findPersonsByRoleAndStudystatus(roleIdFilter, studystatusFilter);
		List<HisStudentWrapper> students = new ArrayList<>();
		for (Long personId : personIds) {
			try {
				HisStudentWrapper student = hisStudentHelperDao.fetchStudent(personId, true);

				if (!hisStudentHelperDao.isThisOrNextPeriodEnroled(student)) {
					jobLogger.info("Student " + student + " hat keinen aktuellen Studienverlaufssatz");
					continue;
				}

				logger.debug("Student hinzugefügt " + student);
				students.add(student);
			} catch (Exception e) {
				if (mc == null) {
					throw e;
				}

				String msg = "Student mit personID " + personId + ": " + e.getMessage();
				logger.error(msg, e);
				jobLogger.error(msg);
				mc.addErrorMessage(msg);
			}
		}

		return students;
	}

	@Override
	public List<HisStudentWrapper> findValidStudents(MessageCollector mc, String stg, String abschl)
			throws ServiceFault {

		List<CourseOfStudy> coses = cosService.findByStgUndAbschl(stg, abschl);
		if (coses.size() == 0) {
			throw new RuntimeException("Kein Studiengang gefunden mit " + stg + " und " + abschl);
		}
		StringBuilder cosesFilter = new StringBuilder(coses.get(0).getId() + "");
		for (int i = 1; i < coses.size(); i++) {
			cosesFilter.append(" | ");
			cosesFilter.append(coses.get(i).getId());
		}

		String studystatusFilter = config.get("de.tuhh.tusp.webservice.studystatusFilterFilter", "1|2|3|4", true);
		List<Long> personIds = hisStudentHelperDao.findStudent(cosesFilter.toString(), studystatusFilter);
		List<HisStudentWrapper> students = new ArrayList<>();
		for (Long personId : personIds) {
			try {
				HisStudentWrapper student = hisStudentHelperDao.fetchStudent(personId, true);

				if (!hisStudentHelperDao.isThisOrNextPeriodEnroled(student)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Student " + student + " hat keinen aktuellen Studienverlaufssatz");
					}
					continue;
				}

				logger.debug("Student hinzugefügt " + student);
				students.add(student);
			} catch (Exception e) {
				if (mc == null) {
					throw e;
				}

				String msg = "Student mit personID " + personId + ": " + e.getMessage();
				logger.error(msg, e);
				mc.addErrorMessage(msg);
			}
		}

		return students;
	}

	private void setHzbForStudent(HisPerson hisPerson) {

		// TODO
		////////////////////////// HZB///////////////////////////////////////////////////
		EntranceQualificationsType type = null;
		try {
			type = hisStudentHelperDao.readHZB(hisPerson.getIdPerson());
		} catch (ServiceFault_Exception e1) {
			e1.printStackTrace();
		}
		if (type.getEntrancequalification().size() == 0) {
			// KEIN HZB
			// init parameters und speichern
			SaveHZB hzb = new SaveHZB();

			EntranceQualificationDto dto = new EntranceQualificationDto();
			dto.setEntranceQualificationTypeId(14L); // TODO
			// Examplan
			Examplan70 examplan = new Examplan70();
			examplan.setPersonId(hisPerson.getIdPerson());
			examplan.setUnitId(1L); // TODO in bearbeitung
			examplan.setTermSegment(null);
			examplan.setDateOfWork(calender.getNowAsXMLGregorianCalendar());

			ExamrelationDto rel = new ExamrelationDto();
			rel.setUnitrelationtypeId(1L); // TODO 3 -> standard zuordnung
			rel.setWorkstatusId(8L); // TODO 8-> zugelassen
			examplan.setDefaultExamrelation(rel);

			dto.setExamplan(examplan);
			hzb.setAusinland(0L); // TODO deutsche bewerber
			hzb.setEq(dto);
			try {
				hisStudentHelperDao.saveHZB(hzb);
			} catch (ServiceFault_Exception e) {
				e.printStackTrace();
			}
		}

		//////////////////////////////////////////////////////////////////////////////////////

	}

	@Override
	public void updateStudent(HisPerson hisPerson, HisStudent hisStudent, WritableResource file,
			UpdateStudentFileWriter updateStudentFileWriter) throws ServiceFault {

		if (hisPerson == null || hisPerson.getIdPerson() == null) {
			logger.warn("Soll Student aktualsieren bekomme aber keine PersonenID");
			return;
		}

		logger.info("Aktualisiere Student " + hisPerson.toShortString());

		setHzbForStudent(hisPerson); // HZB

		StudentExisting student = new StudentExisting();

		student.setDegreePrograms70(new DegreePrograms70Type());

		Person70 person = coalesce(student.getPerson(), new Person70());
		person.setId(hisPerson.getIdPerson());
		person.setFirstname(coalesce(hisPerson.getFirstname(), person.getFirstname()));
		person.setSurname(coalesce(hisPerson.getSurname(), person.getSurname()));
		person.setAllfirstnames(coalesce(hisPerson.getAllfirstnames(), person.getAllfirstnames()));
		person.setDateofbirth(coalesce(hisPerson.getDateofbirth(), person.getDateofbirth()));
		person.setGenderId(coalesce(hisPerson.getGenderId(), person.getGenderId()));
		person.setBirthname(coalesce(hisPerson.getBirthname(), person.getBirthname()));
		person.setArtistname(coalesce(hisPerson.getArtistname(), person.getArtistname()));
		person.setNameprefix(coalesce(hisPerson.getNameprefix(), person.getNameprefix()));
		person.setNamesuffix(coalesce(hisPerson.getNamesuffix(), person.getNamesuffix()));
		person.setAcademicdegreesuffix(coalesce(hisPerson.getAcademicdegreesuffix(), person.getAcademicdegreesuffix()));
		person.setAcademicdegreeId(coalesce(hisPerson.getAcademicdegreeId(), person.getAcademicdegreeId()));
		person.setTitleId(coalesce(hisPerson.getTitleId(), person.getTitleId()));
		person.setBirthcity(coalesce(hisPerson.getBirthcity(), person.getBirthcity()));
		person.setCountryId(coalesce(hisPerson.getCountryId(), person.getCountryId()));
		person.setNationalityId(coalesce(hisPerson.getNationalityId(), person.getNationalityId()));
		person.setSecondNationalityId(coalesce(hisPerson.getSecondNationalityId(), person.getSecondNationalityId()));
		person.setSelfregistrationStatusId(
				coalesce(hisPerson.getSelfregistrationStatusId(), person.getSelfregistrationStatusId()));
		person.setStayAbroads(new StayAbroadsType());

		// Das ist so was von Info Objekt FIXME !!!!!!
		if (hisStudent.getIsCandidateStudent()) {
			PersonExisting perso = hisPersonHelperDao.fetchPerson(person.getId());
			de.his.ws.personservice.PersoninfoDto info = perso.getPersoninfo();
			info.setInsurantStatusId(2L);
			perso.setPersoninfo(info);

			hisPersonHelperDao.updatePerson(perso);
		}

		student.setPerson(person);

		HisStudentWrapper wrapper = hisStudentHelperDao.fetchStudent(hisPerson.getIdPerson(), true);

		// Das könnte man noch alles setzen:
		student.setId(wrapper.getStudent().getStudentId());

		student.setEduType(wrapper.getStudent().getEduType());
		student.setExchangeprogramId(wrapper.getStudent().getExchangeprogramId());
		student.setHasLoanRequest(wrapper.getStudent().getHasLoanRequest());

		student.setKollegsemester(wrapper.getStudent().getKollegsemester());
		student.setLeavesemester(wrapper.getStudent().getLeavesemester());
		// student.setOrgunitId(wrapper.getStudent().getOrgunitId());
		student.setOrgunitLid(wrapper.getStudent().getOrgunitLid());
		student.setPracticalsemester(wrapper.getStudent().getPracticalsemester());
		// student.setRequestForDisenrollment(wrapper.getStudent().getRequestForDisenrollment());
		// student.setSemesterCountryId(wrapper.getStudent().getSemesterCountryId());
		// student.setSemesterDistrictId(wrapper.getStudent().getSemesterDistrictId());
		// student.setStudystatusId(hisStudentHelperDao.getStudentStatus(hisPerson.getStatus()));
		// student.setStudystatusId(wrapper.getStudent().getStudystatus());
		// student.setTermTypeValueId(wrapper.getStudent().getTermTypeValueId());

		if (null == hisStudent.getStgnr()) {
			hisStudent.setStgnr("1");
		}
		student.setUniversitysemester(BigDecimal
				.valueOf(wrapper.getUniSemester(NullBehavior.RETURN_EMPTY, Long.parseLong(hisStudent.getStgnr()))));

		student.setStudentFunctionlocks(new StudentFunctionlocksType());
		student.setYear(student.getYear());
		student.setStudentFunctionlocks(student.getStudentFunctionlocks());
		// Bleibt immer die alte
		student.setRegistrationnumber(wrapper.getStudent().getRegistrationnumber());
		student.setConnectedToSubject(null);
		student.setEnrollmentdate(coalesce(hisPerson.getImmdat(), wrapper.getStudent().getEnrollmentdate()));
		student.setHomeDistrictId(coalesce(hisPerson.getHmkfz(), wrapper.getStudent().getHomeDistrictLid()));

		UpdateStudent updateStd = new UpdateStudent();
		updateStd.setStudentDto(student);
		hisStudentHelperDao.updateStudent(updateStd);

		if (!hisStudent.getIsCandidateStudent()) {
			// Überprüfe Studiengang
			List<DegreeProgram> degreePrograms = wrapper.getStudent().getDegreePrograms().getDegreeProgram();

			if (!degreePrograms.isEmpty()) {
				for (DegreeProgram degreeProgram : degreePrograms) {
					List<DegreeProgramProgress> progresses = degreeProgram.getProgress().getDegreeProgramProgress();
					if (!progresses.isEmpty()) {
						for (DegreeProgramProgress progress : progresses) {

							CourseOfStudy courseOfStudy = courseOfStudyDao.fetchById(progress.getCourseOfStudyId());

							// Anderer Studiengang
							if (!courseOfStudy.getUniquename().equals(hisStudent.getCourseOfStudyUniquename())) {
								String msg = "Der Student " + student.getRegistrationnumber()
										+ " muss noch zu Studiengang " + hisStudent.getCourseOfStudyUniquename()
										+ " hinzugefügt werden.";
								hisStudent.setTodo("Ergänzen");
								hisStudent
										.setDetails("Stg: " + hisStudent.getCourseOfStudyUniquename() + " hinzufügen");
								logger.info(msg);
							} else {
								if (null != hisStudent.getSemester()) {
									if (Integer.parseInt(hisStudent.getSemester()) > progress.getStudysemester()
											.intValue()) { // test

										String msg = "Der Student " + student.getRegistrationnumber()
												+ " muss für Studiengang " + hisStudent.getCourseOfStudyUniquename()
												+ " zurückgemeldet werden.";
										hisStudent.setTodo("Zurückmelden");
										hisStudent.setDetails(
												"Stg: " + hisStudent.getCourseOfStudyUniquename() + " zurückmelden");
										logger.info(msg);
									}
								}

							}

							try {
								if (updateStudentFileWriter != null) {
									updateStudentFileWriter.logStudentData(hisStudent, file);
								}
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
		}

	}

}
