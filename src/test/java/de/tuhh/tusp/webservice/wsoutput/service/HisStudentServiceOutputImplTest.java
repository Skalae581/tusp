package de.tuhh.tusp.webservice.wsoutput.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.soap.SOAPException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.studentservice.Person70;
import de.his.ws.studentservice.StudentExisting;
import de.his.ws.studentservice.UpdateStudent;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.HisStudentHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

@RunWith(MockitoJUnitRunner.class)
public class HisStudentServiceOutputImplTest {

	@InjectMocks
	private HisStudentServiceOutputImpl candidate;

	@Mock
	private HisStudentHelperDao hisStudentHelperDao;

	@Mock
	private StudentDao studentDao;

	@Before
	public void before() throws SOAPException {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createStudent_nullParam() {

		HisStudent student = null;

		Long result = candidate.createStudent(student);

		assertNull(result);
	}

	@Ignore
	@Test
	public void createStudent_withCandidateStudnet() {

		Long mtknr = 5L;
		Long hisStudentId = 6L;
		Long hisPersonId = 7L;

		when(studentDao.getPersonIdFromMtknrId(mtknr)).thenAnswer(i -> null);
		when(hisStudentHelperDao.getPersonIdFromStudentId(hisStudentId)).thenAnswer(i -> hisPersonId);
		when(hisStudentHelperDao.createCandidateStudentWithStudentStatus(	any(HisStudent.class), any(Boolean.class),
																			any(Long.class))).thenAnswer(i -> hisStudentId);

		HisStudent student = new HisStudent();
		student.setExterneMtknr(mtknr);
		student.setIsCandidateStudent(true);

		Long result = candidate.createStudent(student);

		assertEquals(hisPersonId, result);

		verify(hisStudentHelperDao, times(0)).createCandidateStudent(student, false);
		verify(hisStudentHelperDao, times(1)).createCandidateStudentWithStudentStatus(	any(HisStudent.class),
																						any(Boolean.class),
																						any(Long.class));
		ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
		verify(studentDao).insert(captor.capture());
		assertEquals(mtknr, captor.getValue().getMtknr());
		assertEquals(hisStudentId, captor.getValue().getPersonId());
	}

	@Ignore
	@Test
	public void createStudent_withExisting() {

		Long mtknr = 5L;
		Long hisStudentId = 6L;
		Long hisPersonId = 7L;

		when(studentDao.getPersonIdFromMtknrId(mtknr)).thenAnswer(i -> hisStudentId);
		when(hisStudentHelperDao.getPersonIdFromStudentId(hisStudentId)).thenAnswer(i -> hisPersonId);
		when(hisStudentHelperDao.createCandidateStudentWithStudentStatus(	any(HisStudent.class), any(Boolean.class),
																			any(Long.class))).thenAnswer(i -> hisStudentId);

		HisStudent student = new HisStudent();
		student.setExterneMtknr(mtknr);
		student.setIsCandidateStudent(true);

		Long result = candidate.createStudent(student);

		assertEquals(hisPersonId, result);

		verify(hisStudentHelperDao, times(0)).createCandidateStudent(any(HisStudent.class), any(Boolean.class));
		verify(hisStudentHelperDao, times(0)).createCandidateStudentWithStudentStatus(	any(HisStudent.class),
																						any(Boolean.class),
																						any(Long.class));
	}

	@Ignore
	@Test
	public void createStudent_withNonCandidateStudnet() {

		Long mtknr = 5L;
		Long hisStudentId = 6L;
		Long hisPersonId = 7L;

		when(studentDao.getPersonIdFromMtknrId(mtknr)).thenAnswer(i -> null);
		when(hisStudentHelperDao.getPersonIdFromStudentId(hisStudentId)).thenAnswer(i -> hisPersonId);
		when(hisStudentHelperDao.createCandidateStudent(any(HisStudent.class),
														any(Boolean.class))).thenAnswer(i -> hisStudentId);
		when(hisStudentHelperDao.createCandidateStudentWithStudentStatus(	any(HisStudent.class), any(Boolean.class),
																			any(Long.class))).thenAnswer(i -> hisStudentId);

		HisStudent student = new HisStudent();
		student.setExterneMtknr(mtknr);
		student.setIsCandidateStudent(false);

		Long result = candidate.createStudent(student);

		assertEquals(hisPersonId, result);

		verify(hisStudentHelperDao, times(1)).createCandidateStudent(student, false);
		verify(hisStudentHelperDao, times(0)).createCandidateStudentWithStudentStatus(	any(HisStudent.class),
																						any(Boolean.class),
																						any(Long.class));
		ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
		verify(studentDao).insert(captor.capture());
		assertEquals(mtknr, captor.getValue().getMtknr());
		assertEquals(hisStudentId, captor.getValue().getPersonId());
	}

	@Ignore
	@Test
	public void updateStudent_setsAllFieldsIfNeeded() throws Exception {

		Long personId = 34L;

		StudentExisting studentExisting = new StudentExisting();
		studentExisting.setUniversitysemester(new BigDecimal(91));

		when(hisStudentHelperDao.fetchStudent(personId, true)).thenAnswer(i -> studentExisting);

		HisPerson person = new HisPerson(null, null);
		person.setIdPerson(personId);
		person.setImmdat(CalendarUtils.strToXMLGregCal("2001-01-01"));
		person.setHmkfz(2L);
		person.setFirstname("4");
		person.setSurname("5");
		person.setAllfirstnames("6");
		person.setDateofbirth(CalendarUtils.strToXMLGregCal("2007-07-07"));
		person.setGenderId(8L);
		person.setBirthname("9");
		person.setArtistname("10");
		person.setNameprefix("11");
		person.setNamesuffix("12");
		person.setAcademicdegreeId(13L);
		person.setAcademicdegreesuffix("14");
		person.setTitleId(15L);
		person.setBirthcity("16");
		person.setCountryId(17L);
		person.setNationalityId(18L);
		person.setSecondNationalityId(19L);
		person.setSelfregistrationStatusId(20L);
		person.setHmkfzkz("I");

		HisStudent student = new HisStudent();

		candidate.updateStudent(person, student, null, null);

		ArgumentCaptor<UpdateStudent> captor = ArgumentCaptor.forClass(UpdateStudent.class);
		verify(hisStudentHelperDao).updateStudent(captor.capture());

		StudentExisting stu = captor.getValue().getStudentDto();
		assertEquals(CalendarUtils.strToXMLGregCal("2001-01-01"), stu.getEnrollmentdate());
		assertEquals(2, stu.getHomeDistrictId().longValue());
		assertEquals(3, stu.getRegistrationnumber().intValue());
		assertEquals("4", stu.getPerson().getFirstname());
		assertEquals("5", stu.getPerson().getSurname());
		assertEquals("6", stu.getPerson().getAllfirstnames());
		assertEquals(CalendarUtils.strToXMLGregCal("2007-07-07"), stu.getPerson().getDateofbirth());
		assertEquals(Long.valueOf(8), stu.getPerson().getGenderId());
		assertEquals("9", stu.getPerson().getBirthname());
		assertEquals("10", stu.getPerson().getArtistname());
		assertEquals("11", stu.getPerson().getNameprefix());
		assertEquals("12", stu.getPerson().getNamesuffix());
		assertEquals(Long.valueOf(13), stu.getPerson().getAcademicdegreeId());
		assertEquals("14", stu.getPerson().getAcademicdegreesuffix());
		assertEquals(Long.valueOf(15), stu.getPerson().getTitleId());
		assertEquals("16", stu.getPerson().getBirthcity());
		assertEquals(Long.valueOf(17), stu.getPerson().getCountryId());
		assertEquals(Long.valueOf(18), stu.getPerson().getNationalityId());
		assertEquals(Long.valueOf(19), stu.getPerson().getSecondNationalityId());
		assertEquals(Long.valueOf(20), stu.getPerson().getSelfregistrationStatusId());
		assertEquals(91l, stu.getUniversitysemester().longValue());
	}

	@Ignore
	@Test
	public void updateStudent_setsNoFieldsIfNotNeeded() throws Exception {

		Long personId = 34L;

		StudentExisting person = new StudentExisting();
		person.setPerson(new Person70());
		person.setId(personId);
		person.setEnrollmentdate(CalendarUtils.strToXMLGregCal("2001-01-01"));
		person.setHomeDistrictId(2L);
		person.setRegistrationnumber(BigInteger.valueOf(3L));
		person.getPerson().setFirstname("4");
		person.getPerson().setSurname("5");
		person.getPerson().setAllfirstnames("6");
		person.getPerson().setDateofbirth(CalendarUtils.strToXMLGregCal("2007-07-07"));
		person.getPerson().setGenderId(8L);
		person.getPerson().setBirthname("9");
		person.getPerson().setArtistname("10");
		person.getPerson().setNameprefix("11");
		person.getPerson().setNamesuffix("12");
		person.getPerson().setAcademicdegreeId(13L);
		person.getPerson().setAcademicdegreesuffix("14");
		person.getPerson().setTitleId(15L);
		person.getPerson().setBirthcity("16");
		person.getPerson().setCountryId(17L);
		person.getPerson().setNationalityId(18L);
		person.getPerson().setSecondNationalityId(19L);
		person.getPerson().setSelfregistrationStatusId(20L);

		when(hisStudentHelperDao.fetchStudent(personId, true)).thenAnswer(i -> person);

		ArgumentCaptor<UpdateStudent> captor = ArgumentCaptor.forClass(UpdateStudent.class);
		verify(hisStudentHelperDao).updateStudent(captor.capture());

		StudentExisting stu = captor.getValue().getStudentDto();
		assertEquals(CalendarUtils.strToXMLGregCal("2001-01-01"), stu.getEnrollmentdate());
		assertEquals(2, stu.getHomeDistrictId().longValue());
		assertEquals(3, stu.getRegistrationnumber().intValue());
		assertEquals("4", stu.getPerson().getFirstname());
		assertEquals("5", stu.getPerson().getSurname());
		assertEquals("6", stu.getPerson().getAllfirstnames());
		assertEquals(CalendarUtils.strToXMLGregCal("2007-07-07"), stu.getPerson().getDateofbirth());
		assertEquals(Long.valueOf(8), stu.getPerson().getGenderId());
		assertEquals("9", stu.getPerson().getBirthname());
		assertEquals("10", stu.getPerson().getArtistname());
		assertEquals("11", stu.getPerson().getNameprefix());
		assertEquals("12", stu.getPerson().getNamesuffix());
		assertEquals(Long.valueOf(13), stu.getPerson().getAcademicdegreeId());
		assertEquals("14", stu.getPerson().getAcademicdegreesuffix());
		assertEquals(Long.valueOf(15), stu.getPerson().getTitleId());
		assertEquals("16", stu.getPerson().getBirthcity());
		assertEquals(Long.valueOf(17), stu.getPerson().getCountryId());
		assertEquals(Long.valueOf(18), stu.getPerson().getNationalityId());
		assertEquals(Long.valueOf(19), stu.getPerson().getSecondNationalityId());
		assertEquals(Long.valueOf(20), stu.getPerson().getSelfregistrationStatusId());
	}

	@Test
	public void updateStudent_withEmptyPerson() throws Exception {

		candidate.updateStudent(new HisPerson(null, null), new HisStudent(), null, null);
	}

	@Test
	public void updateStudent_withNull() throws Exception {

		candidate.updateStudent(null, null, null, null);
	}
}
