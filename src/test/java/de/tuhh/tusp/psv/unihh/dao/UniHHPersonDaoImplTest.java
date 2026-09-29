package de.tuhh.tusp.psv.unihh.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import de.his.ws.personaddressservice.PersonAddressesByNotificationsType;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.psv.unihh.dao.UniHHPersonDaoImpl.PersonData;
import de.tuhh.tusp.psv.unihh.main.UniHHImportImpl;
import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.csv.CsvReader;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.dao.DistrictDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.sync.pojo.District;
import de.tuhh.tusp.webservice.sync.pojo.Gender;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;
import de.tuhh.tusp.webservice.wsoutput.service.HisAddressesServiceOutput;

@RunWith(MockitoJUnitRunner.class)

public class UniHHPersonDaoImplTest {

	@InjectMocks
	private UniHHPersonDaoImpl dao;

	@Mock
	private CsvReader csvReader;

	@Mock
	private HisRoleHelperDao hisRoleHelperDao;

	@Mock
	private DistrictDao districtDao;

	@Mock
	private CountryDao countryDao;

	@Mock
	private GenderService genderService;

	@Mock
	private ConfigService config;

	@Mock
	private ApplicationContext context;

	@Mock
	private HisAddressesServiceOutput addressService;

	@SuppressWarnings("unchecked")
	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		MessageCollector dummy = MessageCollectorImpl.getInstance("", null);
		Object field1 = TestUtils.getField(dummy, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).clear();
		Object field2 = TestUtils.getField(dummy, "instancesByName");
		((Map<String, MessageCollector>) field2).clear();
	}

	@Test
	public void convertPersonData() throws Exception {

		when(addressService.readPersonAddresses(null))
				.then(x -> new PersonAdressesWrapper(new PersonAddressesByNotificationsType(), null));

		Gender gender = new Gender();
		gender.setId(5L);
		when(genderService.fetchByString(any(String.class))).thenAnswer(i -> gender);

		Country country = new Country();
		country.setId(6L);
		when(countryDao.fetchByDefaultnameDe(any(String.class))).thenAnswer(i -> country);

		District district = new District();
		district.setId(7L);
		when(districtDao.fetchByUniquename(any(String.class))).thenAnswer(i -> district);

		HisRole rolle = new HisRole();
		rolle.setRollenUniquename("ru");
		rolle.setOuUniquename("ou");
		List<HisRole> roles = new ArrayList<>();
		roles.add(rolle);
		when(hisRoleHelperDao.getRolesFromFileByOu(any(), any(), any())).thenAnswer(i -> roles);

		when(config.get(any(String.class), any(String.class), any(Boolean.class))).then(i -> "/path");

		UniHHPerson input = new UniHHPerson();
		input.setUhhMtknr(12345L);
		input.setSemester(4L);
		input.setStatus("1234");
		input.setNachname("Muster");
		input.setVorname("Max");
		input.setGebname("Mu");
		input.setGebort("Kaffhausen");
		input.setGebdat("20.11.2017");
		input.setGeschl("m");
		input.setStaat("de");
		input.setZweitstaat(null);
		input.setHmkfzkz("I");
		input.setHmkfz("HH");
		input.setImmdat("21.11.2017");
		input.setHzbart("Abi");

		input.setPoplz(21071L);
		input.setPoort("Hamburg");
		input.setPostrasse("Am Schwarzenberg-Campus 3");
		input.setPozusatz(null);

		input.setPotel("04161 12345");
		input.setUnimail("test@toaster.com");

		HisPerson output = dao.convertPersonData(input);

		assertEquals(input.getUhhMtknr(), output.getIdExtern());
		assertEquals(input.getSemester(), output.getSemester());
		assertEquals(input.getStatus(), output.getStatus());
		assertEquals(input.getNachname(), output.getSurname());
		assertEquals(input.getVorname(), output.getFirstname());
		assertEquals(input.getGebname(), output.getBirthname());
		assertEquals(input.getGebort(), output.getBirthcity());
		assertEquals(input.getGebdat(), CalendarUtils
				.dateToDatumsString(output.getDateofbirth().toGregorianCalendar().getTime(), Locale.GERMAN));
		assertEquals(gender.getId(), output.getGenderId());
		assertEquals(country.getId(), output.getNationalityId());
		assertEquals(country.getId(), output.getSecondNationalityId());
		assertEquals(input.getHmkfzkz(), output.getHmkfzkz());
		assertEquals(district.getId(), output.getHmkfz());
		assertEquals(input.getImmdat(),
				CalendarUtils.dateToDatumsString(output.getImmdat().toGregorianCalendar().getTime(), Locale.GERMAN));
		assertEquals(input.getHzbart(), output.getHzbart());

		HisPostAddress address = output.getAddresses().getPostAddress(
				new NotificationCategory[] { NotificationCategory.STU }, new AddressTag[] { AddressTag.HOME });
		assertEquals(input.getPoplz() + "", address.getPostcode());
		assertEquals(input.getPoort(), address.getCity());
		assertEquals(input.getPostrasse(), address.getStreet());
		assertEquals(input.getPozusatz(), address.getAddressaddition());

		HisEAddress eadress = output.getAddresses().getEAddress(NotificationCategory.STU, EAdressType.PHONE,
				AddressTag.HOME);
		assertEquals(input.getPotel(), eadress.getValue());
		eadress = output.getAddresses().getEAddress(NotificationCategory.STU, EAdressType.EMAIL, AddressTag.HOME);
		assertEquals(input.getUnimail(), eadress.getValue());

		assertEquals("Muster12345", output.getAccount().getUsername());
		assertTrue(CalendarUtils.isFuture(output.getAccount().getValidTo().toGregorianCalendar()));
		assertTrue(CalendarUtils.isFuture(output.getAccount().getPasswordValidTo().toGregorianCalendar()));

		assertEquals(rolle.getOuUniquename(), output.getRoles().get(0).getOuUniquename());
		assertEquals(rolle.getRollenUniquename(), output.getRoles().get(0).getRollenUniquename());
	}

	@Test
	public void readPersonData() throws Exception {

		MessageCollectorImpl.getInstance("test", null);

		List<Map<PersonData, String>> records = new ArrayList<>();
		Map<PersonData, String> record = new HashMap<>();
		for (PersonData attr : PersonData.values()) {
			record.put(attr, "1");
		}
		records.add(record);

		when(csvReader.readRecords(any(Resource.class), any(Class.class), any(Character.class), any(Charset.class),
				any(Boolean.class))).thenAnswer(i -> records);

		List<UniHHPerson> result = dao.readPersonData(new InMemoryResource("test.csv"), ';', Charset.defaultCharset());

		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getUhhMtknr().longValue());
		new TestUtils().assertNoFieldIsNull(result, "get");
	}

	@Test
	public void readPersonData_withParseException() throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null);

		List<Map<PersonData, String>> records = new ArrayList<>();
		Map<PersonData, String> record = new HashMap<>();
		for (PersonData attr : PersonData.values()) {
			record.put(attr, "1");
		}
		records.add(record);
		record.put(PersonData.kolsem, "asdf"); // NumberFormatException

		record = new HashMap<>();
		for (PersonData attr : PersonData.values()) {
			record.put(attr, "2");
		}
		records.add(record);

		when(csvReader.readRecords(any(Resource.class), any(Class.class), any(Character.class), any(Charset.class),
				any(Boolean.class))).thenAnswer(i -> records);

		List<UniHHPerson> result = dao.readPersonData(new InMemoryResource("test.csv"), ';', Charset.defaultCharset());

		assertEquals(1, result.size());
		assertEquals("2", result.get(0).getGebort());
		assertEquals(1, mc.getErrors().size());
	}
}
