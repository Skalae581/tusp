package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.studentservice.ServiceFault_Exception;
import de.his.ws.studentservice.StudentInfo;
import de.his.ws.studentservice.StudentInfosType;
import de.his.ws.studentservice.StudentService;
import de.his.ws.studentservice.StudentServicePortType;
import de.his.ws.studentservice.UpdateStudent;
import de.his.ws.studentservice201812.StudentService201812;
import de.his.ws.studentservice201812.StudentService201812PortType;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

@RunWith(MockitoJUnitRunner.class)
public class HisStudentHelperDaoImplTest {

	@InjectMocks
	private HisStudentHelperDaoImpl dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private StudentServicePortType port;
	@Mock
	private StudentService201812PortType port201812;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		when(factory.getWebservicePort(StudentService.class)).thenAnswer(i -> port);
		when(factory.getWebservicePort(StudentService201812.class)).thenAnswer(i -> port201812);
	}

	@Test
	public void createCandidateStudent_success() throws Exception {

		HisStudent student = new HisStudent();
		student.setGenderHiskey(1L);
		student.setFirstname("vorname");
		student.setSurname("nachname");
		student.setCourseOfStudyUniquename("cos");
		dao.createCandidateStudent(student, true);

		verify(port).createCandidateStudent("nachname", "vorname", 1l, "cos", true);
	}

	@Test(expected = ServiceFault.class)
	public void createCandidateStudent_withServiceFault() throws Exception {

		when(port.createCandidateStudent(null, null, 1l, null, true)).thenThrow(new ServiceFault_Exception(null, null));

		HisStudent student = new HisStudent();
		student.setGenderHiskey(1L);
		dao.createCandidateStudent(student, true);
	}

	@Test
	public void createCandidateStudentWithStudentStatus_success() throws Exception {

		HisStudent student = new HisStudent();
		student.setGenderHiskey(1L);
		student.setFirstname("vorname");
		student.setSurname("nachname");
		student.setCourseOfStudyUniquename("cos");
		dao.createCandidateStudentWithStudentStatus(student, true, 11l);

		verify(port).createCandidateStudentWithStudentStatus("nachname", "vorname", 1l, "cos", true, 11l);
	}

	@Test(expected = ServiceFault.class)
	public void createCandidateStudentWithStudentStatus_withServiceFault() throws Exception {

		when(port.createCandidateStudentWithStudentStatus(	null, null, 1l, null, true,
															1l)).thenThrow(new ServiceFault_Exception(null, null));

		HisStudent student = new HisStudent();
		student.setGenderHiskey(1L);
		dao.createCandidateStudentWithStudentStatus(student, true, 1l);
	}

	@Test
	public void fetchStudent_success() throws Exception {

		de.his.ws.studentservice201812.Student student = new de.his.ws.studentservice201812.Student();
		student.setSurname("Muster Max");

		when(port201812.readStudentByPersonId(5l, true, false, true, true)).thenAnswer(i -> student);

		HisStudentWrapper result = dao.fetchStudent(5l, true);

		assertEquals("Muster Max", result.getSurname(null));
	}

	@Test(expected = ServiceFault.class)
	public void fetchStudent_withServiceFault() throws Exception {

		de.his.ws.studentservice201812.ServiceFault_Exception ex =
				new de.his.ws.studentservice201812.ServiceFault_Exception(null, null);
		when(port201812.readStudentByPersonId(5l, true, false, true, true)).thenThrow(ex);

		dao.fetchStudent(5l, true);
	}

	@Test
	public void getPersonIdFromStudentId_success() throws Exception {

		StudentInfosType answer = new StudentInfosType();
		StudentInfo student = new StudentInfo();
		student.setPersionId(1234L);
		answer.getStudentInfo().add(student);

		when(port.findStudent61("", "=5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
								"")).thenAnswer(i -> answer);

		assertEquals(1234l, dao.getPersonIdFromStudentId(5l).longValue());
	}

	@Test(expected = IllegalStateException.class)
	public void getPersonIdFromStudentId_withMultiplePersons() throws Exception {

		StudentInfosType answer = new StudentInfosType();
		answer.getStudentInfo().add(new StudentInfo());
		answer.getStudentInfo().add(new StudentInfo());

		when(port.findStudent61("", "=5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
								"")).thenAnswer(i -> answer);

		dao.getPersonIdFromStudentId(5l);
	}

	@Test(expected = ServiceFault.class)
	public void getPersonIdFromStudentId_withServiceFault() throws Exception {

		when(port.findStudent61("", "=5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
								"")).thenThrow(new ServiceFault_Exception(null, null));

		dao.getPersonIdFromStudentId(5l);
	}

	@Test
	public void updateStudent_success() throws Exception {

		UpdateStudent update = new UpdateStudent();

		dao.updateStudent(update);

		verify(port).updateStudent(update);
	}

	@Test(expected = ServiceFault.class)
	public void updateStudent_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(port).updateStudent(any());

		dao.updateStudent(new UpdateStudent());
	}
}
