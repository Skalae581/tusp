package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.rightsandrolesservice.OrgRolesType;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.PersonDao;
import de.tuhh.tusp.webservice.sync.dao.PersonRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.Person;
import de.tuhh.tusp.webservice.sync.pojo.PersonRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class PersonRolesServiceImplTest {

	@InjectMocks
	private PersonRolesServiceImpl service;

	@Mock
	private PersonRolesDao dao;

	@Mock
	private PersonDao personDao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private RightsAndRolesServicePortType cosService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(RightsAndRolesService.class)).thenAnswer(i -> cosService);
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void syncTest() throws ServiceFault_Exception {

		List<Person> persons = new ArrayList<>();
		Person person = new Person();
		person.setId(25L);
		persons.add(person);
		when(personDao.findAll()).thenAnswer(i -> persons);

		Orgrole orgrole = new Orgrole();
		orgrole.setPersonId(25L);
		orgrole.setRoleId(12L);
		orgrole.setId(125L);
		orgrole.setOrgunitLid(1L);
		orgrole.setValidFrom(CalendarUtils.stringToXMLGregorianCalendar("01.01.1980", CalendarUtils.DATEFORMAT_GERMAN));
		orgrole.setValidTo(CalendarUtils.stringToXMLGregorianCalendar("01.01.1990", CalendarUtils.DATEFORMAT_GERMAN));
		OrgRolesType roles = new OrgRolesType();
		roles.getOrgRole().add(orgrole);
		when(cosService.readRolesForPerson(25L)).then(i -> roles);

		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<PersonRoles> argumentCaptor = ArgumentCaptor.forClass(PersonRoles.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		PersonRoles degree = argumentCaptor.getValue();

		assertEquals(Long.valueOf(125L), degree.getId());
		assertEquals(Long.valueOf(25L), degree.getPersonId());
		assertEquals(Long.valueOf(12L), degree.getRoleId());
		assertEquals(Long.valueOf(1L), degree.getOrgunitLid());
		assertEquals("Tue Jan 01 00:00:00 CET 1980", degree.getValidFrom().toString());
		assertEquals("Mon Jan 01 00:00:00 CET 1990", degree.getValidTo().toString());
		assertTrue(degree.getValid());
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws ServiceFault_Exception {

		List<Person> persons = new ArrayList<>();
		Person person = new Person();
		person.setId(25L);
		persons.add(person);
		when(personDao.findAll()).thenAnswer(i -> persons);

		doThrow(new ServiceFault_Exception(null, null)).when(cosService).readRolesForPerson(any(long.class));

		service.sync();
	}
}
