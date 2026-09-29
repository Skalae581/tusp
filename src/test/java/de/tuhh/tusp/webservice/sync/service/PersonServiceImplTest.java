package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersonIdsType;
import de.his.ws.personservice.PersonService;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.personservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.PersonDao;
import de.tuhh.tusp.webservice.sync.pojo.Person;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

	@InjectMocks
	private PersonServiceImpl service;

	@Mock
	private PersonDao dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private PersonServicePortType personService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(PersonService.class)).thenAnswer(i -> personService);
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void syncTest() throws ServiceFault_Exception {

		PersonIdsType ids = new PersonIdsType();
		ids.getId().add(512L);

		when(personService.findPerson60(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any(), any(), any(), any())).thenAnswer(i -> ids);

		PersonExisting hisPerson = new PersonExisting();
		hisPerson.setId(512L);
		hisPerson.setFirstname("setFirstname");
		hisPerson.setSurname("setSurname");
		hisPerson.setAcademicdegreeId(100L);
		hisPerson.setAcademicdegreesuffix("asdf");
		hisPerson.setUpdatedAt(CalendarUtils.stringToXMLGregorianCalendar(	"11.04.1990",
																			CalendarUtils.DATEFORMAT_GERMAN));
		when(personService.readPerson(512L)).thenAnswer(i -> hisPerson);

		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		Person person = argumentCaptor.getValue();

		assertEquals(Long.valueOf(512L), person.getId());
		assertEquals(hisPerson.getFirstname(), person.getFirstname());
		assertEquals(hisPerson.getSurname(), person.getSurname());
		assertEquals("Wed Apr 11 00:00:00 CEST 1990", person.getUpdatedAt().toString());
		assertEquals(hisPerson.getAcademicdegreeId(), person.getAcademicdegreeId());
		assertEquals(hisPerson.getAcademicdegreesuffix(), person.getAcademicdegreeSuffix());
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws ServiceFault_Exception {

		when(personService.findPerson60(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any(), any(), any(), any())).thenThrow(new ServiceFault_Exception(null, null));

		service.sync();
	}
}
