package de.tuhh.tusp.webservice.wsoutput.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.GregorianCalendar;

import javax.xml.soap.SOAPException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersoninfoDto;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPersonHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

@RunWith(MockitoJUnitRunner.class)
public class HisPersonServiceOutputImplTest {

	@InjectMocks
	private HisPersonServiceOutputImpl service;

	@Mock
	private HisPersonHelperDao dao;

	@Before
	public void before() throws SOAPException {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createPerson_withNulls() {

		service.createPerson(new HisPerson(null, null));
		verify(dao).createPerson(any());

	}

	@Test
	public void createPerson_withSuccess() {

		HisPerson input = new HisPerson(null, null);
		input.setAcademicdegreeId(1L);
		input.setAcademicdegreesuffix("2");
		input.setAllfirstnames("3");
		input.setArtistname("4");
		input.setBirthcity("5");
		input.setBirthname("6");
		input.setCountryId(7l);
		input.setCreatedAt(CalendarUtils.stringToXMLGregorianCalendar("2008-08-08", CalendarUtils.DATEFORMAT_ENGLISH));
		input.setDateofbirth(CalendarUtils.stringToXMLGregorianCalendar("2009-09-09",
																		CalendarUtils.DATEFORMAT_ENGLISH));
		input.setFirstname("10");
		input.setGenderId(11l);
		input.setNameprefix("12");
		input.setNamesuffix("13");
		input.setObjGuid("14");
		input.setSelfregistrationStatusId(16L);
		input.setSurname("17");
		input.setTitleId(18l);
		input.setUpdatedAt(CalendarUtils.stringToXMLGregorianCalendar("2019-01-19", CalendarUtils.DATEFORMAT_ENGLISH));

		service.createPerson(input);

		ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
		verify(dao).createPerson(captor.capture());

		Person output = captor.getValue();
		assertEquals(Long.valueOf(1), output.getAcademicdegreeId());
		assertEquals("2", output.getAcademicdegreesuffix());
		assertNotNull(output.getAccounts());
		assertEquals("3", output.getAllfirstnames());
		assertEquals("4", output.getArtistname());
		assertEquals("5", output.getBirthcity());
		assertEquals("6", output.getBirthname());
		assertEquals(Long.valueOf(7), output.getCountryId());
		assertEquals(0, output	.getCreatedAt().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2008-08-08",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals(0, output	.getDateofbirth().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2009-09-09",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals("10", output.getFirstname());
		assertEquals(11, output.getGender());
		assertEquals("12", output.getNameprefix());
		assertEquals("13", output.getNamesuffix());
		assertEquals("14", output.getObjGuid());
		assertNull(output.getPersoninfo()); // Wird erst bei Update gesetzt
		assertEquals(Long.valueOf(16), output.getSelfregistrationStatusId());
		assertEquals("17", output.getSurname());
		assertEquals(Long.valueOf(18), output.getTitleId());
		assertEquals(0, output	.getUpdatedAt().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2019-01-19",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
	}

	@Test
	public void deletePersonTest() throws Exception {

		service.deletePerson(5L);
		verify(dao).deletePerson(5L);
	}

	@Test
	public void getPersonIfExistsTest() throws Exception {

		service.getPersonIdIfExists("l", "f", CalendarUtils.stringToDate("2018-03-20"));
		verify(dao).getPersonIdIfExists("l", "f", CalendarUtils.stringToDate("2018-03-20"));
	}

	@Test
	public void updatePerson_withNulls() throws Exception {

		PersonExisting person = new PersonExisting();
		person.setAcademicdegreeId(1L);
		person.setAcademicdegreesuffix("2");
		person.setAllfirstnames("3");
		person.setArtistname("4");
		person.setBirthcity("5");
		person.setBirthname("6");
		person.setCountryId(7l);
		person.setCreatedAt(CalendarUtils.stringToXMLGregorianCalendar("2008-08-08", CalendarUtils.DATEFORMAT_ENGLISH));
		person.setDateofbirth(CalendarUtils.stringToXMLGregorianCalendar(	"2009-09-09",
																			CalendarUtils.DATEFORMAT_ENGLISH));
		person.setFirstname("10");
		person.setGenderId(11l);
		person.setNameprefix("12");
		person.setNamesuffix("13");
		// input.setObjGuid("14");
		person.setSelfregistrationStatusId(16L);
		person.setSurname("17");
		person.setTitleId(18l);
		person.setUpdatedAt(CalendarUtils.stringToXMLGregorianCalendar("2019-01-19", CalendarUtils.DATEFORMAT_ENGLISH));

		person.setPersoninfo(new PersoninfoDto());
		person.getPersoninfo().setNationalityId(20L);
		person.getPersoninfo().setSecondNationalityId(21L);
		// person.getPersoninfo().setVisumnumber("22");
		person	.getPersoninfo()
				.setUpdatedAt(CalendarUtils.stringToXMLGregorianCalendar(	"2023-01-23",
																			CalendarUtils.DATEFORMAT_ENGLISH));

		Long personId = 3L;
		when(dao.fetchPerson(personId)).thenAnswer(i -> person);

		HisPerson hisPerson = new HisPerson(null, null);
		hisPerson.setIdPerson(personId);
		service.updatePerson(hisPerson);

		assertEquals(Long.valueOf(1), person.getAcademicdegreeId());
		assertEquals("2", person.getAcademicdegreesuffix());
		assertEquals("3", person.getAllfirstnames());
		assertEquals("4", person.getArtistname());
		assertEquals("5", person.getBirthcity());
		assertEquals("6", person.getBirthname());
		assertEquals(Long.valueOf(7l), person.getCountryId());
		assertEquals(0, person	.getCreatedAt().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2008-08-08",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals(0, person	.getDateofbirth().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2009-09-09",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals("10", person.getFirstname());
		assertEquals(Long.valueOf(11l), person.getGenderId());
		assertEquals("12", person.getNameprefix());
		assertEquals("13", person.getNamesuffix());
		assertEquals(Long.valueOf(16L), person.getSelfregistrationStatusId());
		assertEquals("17", person.getSurname());
		assertEquals(Long.valueOf(18l), person.getTitleId());
		assertEquals(Long.valueOf(20L), person.getPersoninfo().getNationalityId());
		assertEquals(Long.valueOf(21L), person.getPersoninfo().getSecondNationalityId());

		// Vor weniger als 2 Sekunden aktualisiert
		assertTrue(new GregorianCalendar().getTimeInMillis()
				- person.getUpdatedAt().toGregorianCalendar().getTimeInMillis() < 2000);
		assertTrue(new GregorianCalendar().getTimeInMillis()
				- person.getPersoninfo().getUpdatedAt().toGregorianCalendar().getTimeInMillis() < 2000);
	}

	@Test
	public void updatePerson_withSuccess() throws Exception {

		Long personId = 3L;
		HisPerson person = new HisPerson(null, null);
		person.setIdPerson(personId);

		person.setAcademicdegreeId(1L);
		person.setAcademicdegreesuffix("2");
		person.setAllfirstnames("3");
		person.setArtistname("4");
		person.setBirthcity("5");
		person.setBirthname("6");
		person.setCountryId(7l);
		person.setCreatedAt(CalendarUtils.stringToXMLGregorianCalendar("2008-08-08", CalendarUtils.DATEFORMAT_ENGLISH));
		person.setDateofbirth(CalendarUtils.stringToXMLGregorianCalendar(	"2009-09-09",
																			CalendarUtils.DATEFORMAT_ENGLISH));
		person.setFirstname("10");
		person.setGenderId(11l);
		person.setNameprefix("12");
		person.setNamesuffix("13");
		// input.setObjGuid("14");
		person.setSelfregistrationStatusId(16L);
		person.setSurname("17");
		person.setTitleId(18l);
		person.setUpdatedAt(CalendarUtils.stringToXMLGregorianCalendar("2019-01-19", CalendarUtils.DATEFORMAT_ENGLISH));

		person.setNationalityId(20L);
		person.setSecondNationalityId(21L);

		PersonExisting pe = new PersonExisting();
		pe.setPersoninfo(new PersoninfoDto());
		pe.setCreatedAt(CalendarUtils.stringToXMLGregorianCalendar("2008-08-08", CalendarUtils.DATEFORMAT_ENGLISH));
		when(dao.fetchPerson(personId)).thenAnswer(i -> pe);

		service.updatePerson(person);

		ArgumentCaptor<PersonExisting> captor = ArgumentCaptor.forClass(PersonExisting.class);
		verify(dao).updatePerson(captor.capture());

		PersonExisting output = captor.getValue();

		assertEquals(Long.valueOf(1), output.getAcademicdegreeId());
		assertEquals("2", output.getAcademicdegreesuffix());
		assertEquals("3", output.getAllfirstnames());
		assertEquals("4", output.getArtistname());
		assertEquals("5", output.getBirthcity());
		assertEquals("6", output.getBirthname());
		assertEquals(Long.valueOf(7l), output.getCountryId());
		assertEquals(0, output	.getCreatedAt().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2008-08-08",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals(0, output	.getDateofbirth().toGregorianCalendar()
								.compareTo(CalendarUtils.stringToXMLGregorianCalendar(	"2009-09-09",
																						CalendarUtils.DATEFORMAT_ENGLISH)
														.toGregorianCalendar()));
		assertEquals("10", output.getFirstname());
		assertEquals(Long.valueOf(11l), output.getGenderId());
		assertEquals("12", output.getNameprefix());
		assertEquals("13", output.getNamesuffix());
		assertEquals(Long.valueOf(16L), output.getSelfregistrationStatusId());
		assertEquals("17", output.getSurname());
		assertEquals(Long.valueOf(18l), output.getTitleId());
		assertEquals(Long.valueOf(20L), output.getPersoninfo().getNationalityId());
		assertEquals(Long.valueOf(21L), output.getPersoninfo().getSecondNationalityId());

		// Vor weniger als 2 Sekunden aktualisiert
		assertTrue(new GregorianCalendar().getTimeInMillis()
				- output.getUpdatedAt().toGregorianCalendar().getTimeInMillis() < 2000);
		assertTrue(new GregorianCalendar().getTimeInMillis()
				- output.getPersoninfo().getUpdatedAt().toGregorianCalendar().getTimeInMillis() < 2000);
	}
}
