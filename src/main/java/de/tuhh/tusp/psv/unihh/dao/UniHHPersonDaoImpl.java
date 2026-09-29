package de.tuhh.tusp.psv.unihh.dao;

import static de.tuhh.tusp.utils.CalendarUtils.DATEFORMAT_GERMAN;
import static de.tuhh.tusp.utils.CalendarUtils.getSemester;
import static de.tuhh.tusp.utils.CalendarUtils.lastDayOfSemester;
import static de.tuhh.tusp.utils.CalendarUtils.stringToXMLGregorianCalendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.psv.unihh.main.UniHHImportImpl;
import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.csv.CsvReader;
import de.tuhh.tusp.webservice.exception.NoRolesDefinedForOuException;
import de.tuhh.tusp.webservice.exception.RolesFromFileException;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.dao.DistrictDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.sync.pojo.District;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;
import de.tuhh.tusp.webservice.wsoutput.service.HisAddressesServiceOutput;

@Component
public class UniHHPersonDaoImpl implements UniHHPersonDao {

	enum PersonData {
		mtknr, semester, status, nachname, vorname, gebname, gebort, gebdat, geschl, staat, zweitstaat, hmkfzkz, hmkfz,
		poplz, poort, postrasse, pozusatz, potel, immdat, hzbart, hzbjahr, hzbkfzkz, hzbkfz, hzbland, erhskfz, erhsart,
		erhssembrd, hssem, erhsstaat, urlsem, kolsem, unimail
	}

	private static Logger logger = LogManager.getLogger(UniHHPersonDaoImpl.class);

	private static final String DEFAULT_ROLES_FILE_KEY = "de.tuhh.tusp.psv.unihh.DefaultRolesFile";

	@Autowired
	private CsvReader csvReader;

	@Autowired
	private HisRoleHelperDao hisRoleHelperDao;

	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private GenderService genderService;

	@Autowired
	private ConfigService config;

	@Autowired
	private HisAddressesServiceOutput addressService;

	@Override
	public HisPerson convertPersonData(UniHHPerson huniUser) {

		HisPerson user = new HisPerson(null, addressService);
		user.setIdExtern(huniUser.getUhhMtknr());
		user.setSemester(huniUser.getSemester());
		user.setStatus(huniUser.getStatus());
		user.setSurname(huniUser.getNachname());
		user.setFirstname(huniUser.getVorname());
		user.setBirthname(huniUser.getGebname());
		user.setBirthcity(huniUser.getGebort());
		user.setDateofbirth(stringToXMLGregorianCalendar(huniUser.getGebdat(), DATEFORMAT_GERMAN));
		user.setGenderId(genderService.fetchByString(huniUser.getGeschl()).getId());

		Country zweitstaat = countryDao.fetchByDefaultnameDe(huniUser.getZweitstaat());
		user.setSecondNationalityId(zweitstaat != null ? zweitstaat.getId() : null);

		user.setHmkfzkz(huniUser.getHmkfzkz().trim());

		if (user.getHmkfzkz().equals("I")) {
			user.setNationalityId(countryDao.fetchByDefaultnameDe(huniUser.getStaat()).getId());
			District district = districtDao.fetchByUniquename(huniUser.getHmkfz().trim());
			if (district == null) {
				throw new RuntimeException("HMKFZ " + huniUser.getHmkfz().trim() + " konnte nicht aufgelöst werden.");
			}
			user.setHmkfz(districtDao.fetchByUniquename(huniUser.getHmkfz().trim()).getId());
		} else if (user.getHmkfzkz().equals("A")) {
			user.setNationalityId(countryDao.fetchByUniquename(huniUser.getHmkfz().trim()).getId());
			Country hmCountry = countryDao.fetchByUniquename(huniUser.getHmkfz().trim());
			if (hmCountry == null) {
				throw new RuntimeException("HMKFZ " + huniUser.getHmkfz().trim() + " konnte nicht aufgelöst werden.");
			}
			user.setHmkfz(countryDao.fetchByUniquename(huniUser.getHmkfz().trim()).getId());
		} else {
			throw new RuntimeException("User ist weder In- noch Ausländer: Uni MTKNR" + huniUser.getUhhMtknr());
		}

		user.setImmdat(stringToXMLGregorianCalendar(huniUser.getImmdat(), DATEFORMAT_GERMAN));
		user.setHzbart(huniUser.getHzbart().trim());

		PersonAdressesWrapper personAddressWrapper = new PersonAdressesWrapper(user.getIdPerson());
		user.setAddresses(personAddressWrapper);

		HisPostAddress postAddress = personAddressWrapper.getPostAddress(
				new NotificationCategory[] { NotificationCategory.STU }, new AddressTag[] { AddressTag.HOME });

		// postAddress.setPersonId(user.getIdPerson());
		// postAddress.setPostcode(huniUser.getPoplz() + "");
		// postAddress.setCity(huniUser.getPoort());
		// postAddress.setStreet(huniUser.getPostrasse());
		// postAddress.setAddressaddition(huniUser.getPozusatz());
		//
		//
		postAddress.setPersonId(user.getIdPerson());
		postAddress.setCountry("D");
		postAddress.setPostcode("21071");
		postAddress.setCity("Hamburg");
		postAddress.setStreet("Am Schwarzenberg-Campus 3");

		HisEAddress mail = personAddressWrapper.getEAddress(NotificationCategory.STU, EAdressType.EMAIL,
				AddressTag.HOME);
		mail.setValue(huniUser.getUnimail());
		mail.setPersonId(user.getIdPerson());

		HisEAddress phone = personAddressWrapper.getEAddress(NotificationCategory.STU, EAdressType.PHONE,
				AddressTag.HOME);
		phone.setValue(huniUser.getPotel());
		phone.setPersonId(user.getIdPerson());

		HisAccount account = new HisAccount();
		account.setUsername(huniUser.getNachname() + "" + huniUser.getUhhMtknr());
		// Accounts müssen bis zum Ende des nächsten Semesters gültig sein
		// (Festlegung Muthana 14.11.2017)
		XMLGregorianCalendar validTo = lastDayOfSemester(getSemester(new GregorianCalendar()), 1);
		account.setValidTo(validTo);
		account.setPasswordValidTo(account.getValidTo());
		user.setAccount(account);

		String rolesFile = config.get(DEFAULT_ROLES_FILE_KEY, "uniHH/uniHHImportRoles/uniHHImportRoles.JSON", true);
		try {
			user.setRoles(hisRoleHelperDao.getRolesFromFileByOu(new ClassPathResource(rolesFile), "UniHHstudent",
					validTo.toGregorianCalendar()));
		} catch (RolesFromFileException | JSONException | NoRolesDefinedForOuException e) {

			throw new RuntimeException("Konnte die UniHH-Studenten-Rollendefinion nicht laden von " + rolesFile, e);
		}

		return user;

	}

	@Override
	public List<UniHHPerson> readPersonData(Resource csv, char delimiter, Charset charset)
			throws FileNotFoundException, IOException {

		List<UniHHPerson> personList = new ArrayList<>();

		for (Map<Enum<?>, String> record : csvReader.readRecords(csv, PersonData.class, delimiter, charset, true)) {
			UniHHPerson person = new UniHHPerson();

			try {
				person.setUhhMtknr(Long.parseLong(record.get(PersonData.mtknr)));
				person.setSemester(Long.parseLong(record.get(PersonData.semester)));
				person.setStatus(record.get(PersonData.status));
				person.setNachname(record.get(PersonData.nachname));
				person.setVorname(record.get(PersonData.vorname));
				person.setGebname(record.get(PersonData.gebname));
				person.setGebort(record.get(PersonData.gebort));
				person.setGebdat(record.get(PersonData.gebdat));
				person.setGeschl(record.get(PersonData.geschl));
				person.setStaat(record.get(PersonData.staat));
				person.setZweitstaat(record.get(PersonData.zweitstaat));
				person.setHmkfzkz(record.get(PersonData.hmkfzkz));
				person.setHmkfz(record.get(PersonData.hmkfz));
				person.setPoplz(Long.parseLong(record.get(PersonData.poplz)));
				person.setPoort(record.get(PersonData.poort));
				person.setPostrasse(record.get(PersonData.postrasse));
				person.setPozusatz(record.get(PersonData.pozusatz));
				person.setPotel(record.get(PersonData.potel));
				person.setImmdat(record.get(PersonData.immdat));
				person.setHzbart(record.get(PersonData.hzbart));
				person.setHzbjahr(record.get(PersonData.hzbjahr));
				person.setHzbkfzkz(record.get(PersonData.hzbkfzkz));
				person.setHzbkfz(record.get(PersonData.hzbkfz));
				person.setHzbland(record.get(PersonData.hzbland));
				person.setErhskfz(record.get(PersonData.erhskfz));
				person.setErhsart(record.get(PersonData.erhsart));
				person.setErhssembrd(Long.parseLong(record.get(PersonData.erhssembrd)));
				person.setHssem(Long.parseLong(record.get(PersonData.hssem)));
				person.setUrlsem(Long.parseLong(record.get(PersonData.urlsem)));
				person.setKolsem(Long.parseLong(record.get(PersonData.kolsem)));
				person.setUnimail(record.get(PersonData.unimail));

				personList.add(person);
			} catch (Exception ex) {
				String message = "Datensatzt für den Student:" + "(" + record.get(PersonData.vorname) + " "
						+ record.get(PersonData.nachname) + ")"
						+ " ist fehlerhaft! Die Daten können nicht gelesen werden!";
				logger.warn(message, ex);
				MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null).addErrorMessage(message);
				continue;
			}

		}
		return personList;

	}

}
