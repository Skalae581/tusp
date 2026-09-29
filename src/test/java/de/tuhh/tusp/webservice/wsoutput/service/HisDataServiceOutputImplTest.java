package de.tuhh.tusp.webservice.wsoutput.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.personservice.PersonExisting;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

@RunWith(MockitoJUnitRunner.class)
public class HisDataServiceOutputImplTest {

	@InjectMocks
	private HisDataServiceOutputImpl service;

	@Mock
	private HisPersonServiceOutput hisPersonService;
	@Mock
	private HisAccountServiceOutput hisAccountService;
	@Mock
	private HisRolesServiceOutput hisRolesService;
	@Mock
	private HisStudentServiceOutput hisStudentService;
	@Mock
	private HisAddressesServiceOutput hisAdressService;
	@Mock
	private StudentDao studentDao;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void savePerson_createNonStudent() throws Exception {

		Long personId = 14L;

		HisPerson person = new HisPerson(null, null);
		person.setSurname("Mustermann");
		person.setFirstname("Max");
		person.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));

		HisStudent student = null;

		when(studentDao.getPersonIdFromMtknrId(null)).then(i -> null);
		when(hisPersonService.getPersonIdIfExists(	"Mustermann", "Max",
													CalendarUtils.stringToDate("2018-03-20"))).thenAnswer(i -> null);
		when(hisPersonService.createPerson(person)).thenAnswer(i -> personId);

		service.savePerson(person, student, null, null, null);

		verify(hisPersonService, times(1)).createPerson(person);
		verify(hisPersonService, times(1)).updatePerson(person);
		verify(hisRolesService, times(1)).updateOrCreateRolesForPerson(person, personId);
		verify(hisAdressService, times(1)).updateOrCreateAddresses(person);
		verify(hisAccountService, times(1)).updateOrCreateAccount(person);
		verify(hisStudentService, times(0)).updateStudent(person, student, null, null);
		verify(hisStudentService, times(0)).createStudent(student);

		assertEquals(personId, person.getIdPerson());
	}

	@Test
	public void savePerson_createStudent() throws Exception {

		Long personId = 14L;

		HisPerson person = new HisPerson(null, null);
		person.setSurname("Mustermann");
		person.setFirstname("Max");
		person.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));

		HisStudent student = new HisStudent();

		when(studentDao.getPersonIdFromMtknrId(null)).then(i -> null);
		when(hisPersonService.getPersonIdIfExists(	"Mustermann", "Max",
													CalendarUtils.stringToDate("2018-03-20"))).thenAnswer(i -> null);
		when(hisStudentService.createStudent(student)).thenAnswer(i -> personId);

		service.savePerson(person, student, null, null, null);

		verify(hisPersonService, times(0)).createPerson(person);
		verify(hisPersonService, times(1)).updatePerson(person);
		verify(hisRolesService, times(1)).updateOrCreateRolesForPerson(person, personId);
		verify(hisAdressService, times(1)).updateOrCreateAddresses(person);
		verify(hisAccountService, times(1)).updateOrCreateAccount(person);
		verify(hisStudentService, times(1)).updateStudent(person, student, null, null);
		verify(hisStudentService, times(1)).createStudent(student);

		assertEquals(personId, person.getIdPerson());
	}

	@Test
	public void savePerson_updateNonStudent() throws Exception {

		Long personId = 14L;

		HisPerson person = new HisPerson(null, null);
		person.setSurname("Mustermann");
		person.setFirstname("Max");
		person.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));
		person.setIdExtern(123L);

		HisStudent student = null;

		PersonExisting personExisting = new PersonExisting();
		personExisting.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));
		personExisting.setSurname("Mustermann");
		personExisting.setFirstname("Max");

		when(hisPersonService.fetchPerson(personId)).then(i -> personExisting);
		when(studentDao.getPersonIdFromMtknrId(123L)).then(i -> personId);

		service.savePerson(person, student, null, null, null);

		verify(hisPersonService, times(0)).createPerson(person);
		verify(hisPersonService, times(1)).updatePerson(person);
		verify(hisRolesService, times(1)).updateOrCreateRolesForPerson(person, personId);
		verify(hisAdressService, times(1)).updateOrCreateAddresses(person);
		verify(hisAccountService, times(1)).updateOrCreateAccount(person);
		verify(hisStudentService, times(0)).updateStudent(person, student, null, null);
		verify(hisStudentService, times(0)).createStudent(student);

		assertEquals(personId, person.getIdPerson());
	}

	@Test
	public void savePerson_updateStudent() throws Exception {

		Long personId = 14L;

		HisPerson person = new HisPerson(null, null);
		person.setSurname("Mustermann");
		person.setFirstname("Max");
		person.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));
		person.setIdExtern(123L);

		HisStudent student = new HisStudent();

		PersonExisting personExisting = new PersonExisting();
		personExisting.setDateofbirth(CalendarUtils.strToXMLGregCal("2018-03-20"));
		personExisting.setSurname("Mustermann");
		personExisting.setFirstname("Max");

		when(hisPersonService.fetchPerson(personId)).then(i -> personExisting);
		when(studentDao.getPersonIdFromMtknrId(123L)).then(i -> personId);

		service.savePerson(person, student, null, null, null);

		verify(hisPersonService, times(0)).createPerson(person);
		verify(hisPersonService, times(1)).updatePerson(person);
		verify(hisAdressService, times(1)).updateOrCreateAddresses(person);
		verify(hisAccountService, times(1)).updateOrCreateAccount(person);
		verify(hisStudentService, times(1)).updateStudent(person, student, null, null);
		verify(hisStudentService, times(0)).createStudent(student);

		assertEquals(personId, person.getIdPerson());
	}

}
