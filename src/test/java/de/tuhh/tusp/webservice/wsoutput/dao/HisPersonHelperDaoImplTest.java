package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.personservice.DeletePerson;
import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersonIdsType;
import de.his.ws.personservice.PersonService;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.personservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class HisPersonHelperDaoImplTest {

	@InjectMocks
	private HisPersonHelperDaoImpl personDao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private PersonServicePortType personServicePort;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		when(factory.getWebservicePort(PersonService.class)).thenAnswer(i -> personServicePort);
	}

	@Test(expected = ServiceFault.class)
	public void createPerson_withNull() throws Exception {

		personDao.createPerson(null);
	}

	@Test(expected = ServiceFault.class)
	public void createPerson_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(personServicePort).savePerson(any());
		personDao.createPerson(new Person());
	}

	@Test
	public void createPerson_withSuccess() throws Exception {

		Person person = new Person();

		personDao.createPerson(person);

		verify(personServicePort).savePerson(person);
	}

	@Test
	public void deletePerson_withNull() throws Exception {

		personDao.deletePerson(null);
		verify(personServicePort, times(0)).deletePerson(any());
	}

	@Test(expected = ServiceFault.class)
	public void deletePerson_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(personServicePort).deletePerson(any());
		personDao.deletePerson(6L);
	}

	@Test
	public void deletePerson_withSuccess() throws Exception {

		personDao.deletePerson(5L);

		ArgumentCaptor<DeletePerson> captor = ArgumentCaptor.forClass(DeletePerson.class);
		verify(personServicePort).deletePerson(captor.capture());
		assertEquals(5, captor.getValue().getId());
	}

	@Test
	public void fetchPerson_withNull() throws Exception {

		assertNull(personDao.fetchPerson(null));
	}

	@Test(expected = ServiceFault.class)
	public void fetchPerson_withServiceFault() throws Exception {

		when(personDao.fetchPerson(6L)).thenThrow(new ServiceFault_Exception(null, null));
		personDao.fetchPerson(6L);
	}

	@Test
	public void fetchPerson_withSuccess() throws Exception {

		PersonExisting pe = new PersonExisting();
		when(personServicePort.readPerson(5L)).thenAnswer(i -> pe);
		assertEquals(pe, personDao.fetchPerson(5L));
	}

	@Test(expected = IllegalStateException.class)
	public void getPersonIdIfExists_withDoubleName() throws Exception {

		PersonIdsType answer = new PersonIdsType();
		answer.getId().add(15L);
		answer.getId().add(17L);

		when(personServicePort.findPerson60("=nachname", "=vorname", "", "", "", "", "=2018-03-20", "", "", "", "", "",
											"", "", "", "", "", "", "", "", "", "", "", "", "",
											"")).thenAnswer(i -> answer);

		personDao.getPersonIdIfExists("nachname", "vorname", CalendarUtils.stringToDate("2018-03-20"));

	}

	@Test
	public void getPersonIdIfExists_withExistingName() throws Exception {

		PersonIdsType answer = new PersonIdsType();
		answer.getId().add(15L);

		when(personServicePort.findPerson60("=nachname", "=vorname", "", "", "", "", "=2018-03-20", "", "", "", "", "",
											"", "", "", "", "", "", "", "", "", "", "", "", "",
											"")).thenAnswer(i -> answer);

		assertEquals(	Long.valueOf(15),
						personDao.getPersonIdIfExists("nachname", "vorname", CalendarUtils.stringToDate("2018-03-20")));

	}

	@Test
	public void getPersonIdIfExists_withNullLastname() throws Exception {

		when(personServicePort.findPerson60("=null", "=vorname", "", "", "", "", "=2018-03-20", "", "", "", "", "", "",
											"", "", "", "", "", "", "", "", "", "", "", "",
											"")).thenAnswer(i -> new PersonIdsType());

		assertNull(personDao.getPersonIdIfExists(null, "vorname", CalendarUtils.stringToDate("2018-03-20")));

	}

	@Test(expected = ServiceFault.class)
	public void getPersonIdIfExists_withServiceFault() throws Exception {

		when(personServicePort.findPerson60("=nachname", "=vorname", "", "", "", "", "=2018-03-20", "", "", "", "", "",
											"", "", "", "", "", "", "", "", "", "", "", "", "",
											"")).thenThrow(new ServiceFault_Exception(null, null));

		personDao.getPersonIdIfExists("nachname", "vorname", CalendarUtils.stringToDate("2018-03-20"));

	}

	@Test(expected = ServiceFault.class)
	public void updatePerson_withNull() throws Exception {

		personDao.updatePerson(null);
	}

	@Test(expected = ServiceFault.class)
	public void updatePerson_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(personServicePort).updatePerson(any());
		personDao.updatePerson(new PersonExisting());
	}

	@Test
	public void updatePerson_withSuccess() throws Exception {

		PersonExisting person = new PersonExisting();
		personDao.updatePerson(person);

		verify(personServicePort).updatePerson(person);
	}
}
