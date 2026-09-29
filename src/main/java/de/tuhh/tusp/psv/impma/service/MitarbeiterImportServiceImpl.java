package de.tuhh.tusp.psv.impma.service;

import static de.tuhh.tusp.utils.CalendarUtils.dateToDatumsString;
import static de.tuhh.tusp.utils.CalendarUtils.toDate;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_ACCOUNTAUTH_ID;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_ACCOUNT_VALIDITY;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_BLOCKED_ID;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_PURPOSE_ID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.personservice.AccountsType;
import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.impma.dao.MitarbeiterUndStudentFilterDao;
import de.tuhh.tusp.stu.sptim.utils.TimStringUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.LdapDao.LdapPerson;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAddressHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPersonHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

@Component
public class MitarbeiterImportServiceImpl implements MitarbeiterImportService {

	private static Logger logger = LogManager.getLogger(MitarbeiterImportServiceImpl.class);

	@Autowired
	private LdapDao ldap;

	@Autowired
	private ConfigService config;

	@Autowired
	private HisAccountHelperDao accountDao;

	@Autowired
	private HisAddressHelperDao addressDao;

	@Autowired
	private HisPersonHelperDao personDao;

	@Autowired
	private HisRoleHelperDao rolesDao;

	@Autowired
	private GenderService genderService;
	@Autowired
	private TimStringUtils stringUtils;

	@Autowired
	private MitarbeiterUndStudentFilterDao mitarbeiterUndStudentFilterDao;

	private Map<PersonExisting, ArrayList<String>> person2MailAdresses = new HashMap<>();

	private Multimap<Long, String> filteredList = ArrayListMultimap.create();

	private void accountVerlaengern(LdapPerson employee, PersonExisting tunePerson, JobLogger jobLogger) {

		XMLGregorianCalendar nextMonth = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_YEAR,
				config.get(DEFAULT_ACCOUNT_VALIDITY, 30, true));
		AccountInfo accountInfo = accountDao.fetchAccountByUsernameIgnoreDate(employee.getUid());
		if (accountInfo == null) {
			// neuen Account anlegen
			HisAccountWrapper account = new HisAccountWrapper();
			account.setPersonId(tunePerson.getId());
			account.setPasswordhash(UUID.randomUUID().toString());
			account.setUsername(employee.getUid());
			account.setIsInitialpassword(false);
			account.setIsLdapAccount(false);
			account.setAccountauthId(config.get(DEFAULT_ACCOUNTAUTH_ID, 1l, true));
			account.setBlockedId(config.get(DEFAULT_BLOCKED_ID, 1l, true));
			account.setValidFrom(CalendarUtils.toXMLGregorianCalendar(new Date()));

			account.setPurposeId(config.get(DEFAULT_PURPOSE_ID, 21L, true)); // employee_management

			account.setPasswordValidTo(CalendarUtils.strToXMLGregCal("2100-12-31"));

			account.setValidTo(nextMonth);

			jobLogger.info("Account angelegt: " + employee.toShortString());
			accountDao.createAccount(account);
			return;
		}

		if (accountInfo.getPersonId() != tunePerson.getId()) {
			throw new RuntimeException("Account " + employee.getUid() + " und personId person2MailAdresses"
					+ tunePerson.getId() + " passen nicht zusammen.");
		}

		// Account verlängern
		HisAccountWrapper account = accountDao.readAccount(accountInfo.getId());
		long diff = CalendarUtils.differenceInDays(nextMonth, account.getValidTo());
		if (diff > 0 && diff < 21) {
			account.setValidTo(nextMonth);
			jobLogger.info("Account verlängert: " + employee.toShortString());
			accountDao.updateAccount(account);
		}
	}

	/**
	 * Von der Orginal Person -Liste alle entfernen die als Mitarbeiter und Student
	 * - Emal haben Alle gefilterte Personen speichere in die Multimap
	 * {filteredList}
	 *
	 * @param originalList
	 */
	private void cleanEmailList(Map<PersonExisting, ArrayList<String>> originalList, JobLogger jobLogger) {
		// mach alle Personen weg von der Orginal Liste die gefiltert sind
		Set<Long> keySetList = new HashSet<>();
		for (Long personId : filteredList.keySet()) {

			Collection<String> addrs = filteredList.get(personId);
			System.out.println("personID: " + personId + " addr: " + addrs);
			keySetList.add(personId);
		}
		// weg damit
		Set<PersonExisting> keySetListPerson = new HashSet<>();
		if (null != keySetList || !keySetList.isEmpty()) {
			System.out.println("Addresse Liste vor Filter -Länge: " + originalList.size());
			for (Entry<PersonExisting, ArrayList<String>> entry : originalList.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				for (Long ll : keySetList) {
					if (entry.getKey().getId() == ll) {
						// person enfernen von der lsite
						keySetListPerson.add(entry.getKey());
					}
				}
			}

			// alle die wir finden sollen weg
			originalList.keySet().removeAll(keySetListPerson);

			jobLogger.debug("Addresse Liste nach Filter -Länge: " + originalList.size());

		}
	}

	private Long createPerson(LdapPerson employee, JobLogger jobLogger) {

		Person person = new Person();
		person.setSurname(employee.getSurname());
		person.setDateofbirth(CalendarUtils.toXMLGregorianCalendar(employee.getDateOfBirth()));
		person.setFirstname(employee.getGivenName().split(" ")[0]);
		person.setAllfirstnames(employee.getGivenName());
		person.setGender(genderService.fetchByString(employee.getGender()).getId());
		person.setAccounts(new AccountsType());

		return personDao.createPerson(person);
	}

	private List<LdapPerson> findLdapPerson(String vorname, String nachname) throws NamingException {

		ldap.initContextWithDn(config.get("de.tuhh.tusp.utils.ldap.userDn", "", true),
				config.get("de.tuhh.tusp.utils.ldap.password", "", true));
		String ldapFilter = "(&(tuhhEduPersonScopedAffiliation=student@tu-harburg.de)(cn= " + vorname + " " + nachname
				+ "))";
		return ldap.searchLdapPerson(ldapFilter);
	}

	private PersonExisting findPersonInHio(LdapPerson employee) {

		// 1. anhand Account
		// TODO fetchAccountByUsername not working , employee.getUid ist nicht richtig
		// gesetzt
		AccountInfo account = accountDao.fetchAccountByUsername(employee.getUid());
		if (account != null) {
			PersonExisting person = personDao.fetchPerson(account.getPersonId());
			if (null != person) {
				if (richtigePersonGefunden(person, employee)) {
					return person;
				} else {
					logger.error("Vorname: Ldap= " + employee.getGivenName() + " : TUNE= " + person.getFirstname()
							+ " Nachname: Ldap= " + employee.getSurname() + " : TUNE= " + person.getSurname()
							+ " GebDat: Ldap= " + employee.getDateOfBirth() + " : TUNE= "
							+ toDate(person.getDateofbirth()));
					throw new RuntimeException(
							"Konnte nicht sicherstellen, dass der Account an der richtigen Person verbunden ist.");
				}
			} else {
				logger.error("Person gibt es nicht (person=null) AccountId: " + account.getId() + " Account-PersonId: "
						+ account.getPersonId());
			}
		}

		// 2. anhand von Name, Vorname, GebDat exact match
		Long personId = personDao.getPersonIdIfExists(employee.getSurname(), employee.getGivenName(),
				employee.getDateOfBirth());
		PersonExisting person = personDao.fetchPerson(personId);
		if (person != null) {
			return person;
		}

		// 3. fuzzy finding
		List<Long> personIds = personDao.searchFuzzy(employee.getSurname(), employee.getGivenName().split(" ")[0],
				employee.getDateOfBirth());
		for (Long pid : personIds) {
			PersonExisting p = personDao.fetchPerson(pid);
			logger.debug("vergleiche: Vorname: Ldap= " + employee.getGivenName() + " : TUNE= " + p.getFirstname()
					+ " Nachname: Ldap= " + employee.getSurname() + " : TUNE= " + p.getSurname() + " GebDat: Ldap= "
					+ employee.getDateOfBirth() + " : TUNE= " + toDate(p.getDateofbirth()));

			if (richtigePersonGefunden(p, employee)) {
				logger.debug("Match!");
				if (person == null) {
					person = p;
				} else {
					throw new RuntimeException("Zweite mögliche Person in TUNE gefunden.");
				}
			}
		}

		return person;
	}

	private void gastRolleVerlaengern(PersonExisting tunePerson, JobLogger jobLogger) {

		long roleId = 21;// == "Gast Rolle"; == nur lesend
		XMLGregorianCalendar nextMonth = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_YEAR,
				config.get(DEFAULT_ACCOUNT_VALIDITY, 30, true));

		// Rolle aktualisieren
		boolean roleExists = false;
		List<Orgrole> roles = rolesDao.findValidRolesForPerson(tunePerson.getId());
		for (Orgrole role : roles) {
			if (role.getRoleId() == roleId) {
				long diff = CalendarUtils.differenceInDays(nextMonth, role.getValidTo());
				if (diff > 0 && diff < 21) {
					role.setValidTo(nextMonth);
					jobLogger.info("Rolle verlängert " + role.getId());
					rolesDao.updateOrgRole(role);
				}
				roleExists = true;
			}
		}

		// TODO orgunit lid
		// Rolle vergeben
		if (!roleExists) {
			SetPersonToOrgunitWithRole parameters = new SetPersonToOrgunitWithRole();
			parameters.setOrgunitLid(100003l); // 100003l = oDekanat
			parameters.setPersonId(tunePerson.getId());
			parameters.setOrgroleId(roleId);
			parameters.setValidFrom(CalendarUtils.toXMLGregorianCalendar(new Date()));
			parameters.setValidTo(nextMonth);
			jobLogger.info("Rolle vergeben");
			rolesDao.setPersonToOrgunitWithRole(parameters);
		}
	}

	private List<LdapPerson> getAllAffiliateses() throws NamingException {

		ldap.initContextWithDn(config.get("de.tuhh.tusp.utils.ldap.userDn", "", true),
				config.get("de.tuhh.tusp.utils.ldap.password", "", true));
		String ldapFilter = "(&(tuhhEduPersonScopedAffiliation=affiliate@tu-harburg.de)(!(tuhhEduPersonScopedAffiliation=employee@tu-harburg.de)))";
		ldapFilter = config.get("de.tuhh.tusp.impma.mitarbeiter_ldap_filter_affiliates", ldapFilter, true);
		return ldap.searchLdapPerson(ldapFilter);
	}

	private List<LdapPerson> getAllEmployees() throws NamingException {

		ldap.initContextWithDn(config.get("de.tuhh.tusp.utils.ldap.userDn", "", true),
				config.get("de.tuhh.tusp.utils.ldap.password", "", true));
		String ldapFilter = "(|(tuhhEduPersonScopedAffiliation=employee@tu-harburg.de)"
				+ "(tuhhEduPersonScopedAffiliation=employee@tutech.de))";
		ldapFilter = config.get("de.tuhh.tusp.impma.mitarbeiter_ldap_filter", ldapFilter, true);
		return ldap.searchLdapPerson(ldapFilter);
	}

	@Override
	public void importFromLdap(MessageCollector collector, JobLogger jobLogger) throws NamingException {

		List<LdapPerson> employees = getAllEmployees();

		for (LdapPerson employee : employees) {

			try {

				verarbeiteEmployee(employee, collector, jobLogger);

			} catch (Exception e) {
				String msg = "Konnte Person " + employee.toShortString() + " nicht verarbeiten. ";
				jobLogger.error(msg + e.getMessage());
				logger.error(msg, e);
				collector.addErrorMessage(msg, e);
			}
		}

		List<LdapPerson> affiliates = getAllAffiliateses();

		for (LdapPerson employee : affiliates) {

			try {

				verarbeiteAffiliate(employee, collector, jobLogger);

			} catch (Exception e) {
				String msg = "Konnte Person " + employee.toShortString() + " nicht verarbeiten. ";
				jobLogger.error(msg + e.getMessage());
				logger.error(msg, e);
				collector.addErrorMessage(msg, e);
			}
		}

		// Orginal Person Liste sauber machen
		cleanEmailList(person2MailAdresses, jobLogger);

		// verarbeite E-Mail Adressen
		for (PersonExisting person : person2MailAdresses.keySet()) {
			try {

				verarbeiteMailAusCache(person, person2MailAdresses.get(person), jobLogger);

			} catch (Exception e) {
				String msg = "Konnte Mailadresse von personId " + person.getId() + " nicht verarbeiten. ";
				jobLogger.error(msg + e.getMessage());
				logger.error(msg, e);
				collector.addErrorMessage(msg, e);
			}
		}

		// verarbeite E-Mail Adressen die gefiltert sind
		for (Long keys : filteredList.keySet()) {
			try {

				// verarbeite filter daten
				Collection<String> addrs = filteredList.get(keys);
				ArrayList<String> addrLst = new ArrayList<>(addrs);

				verarbeiteMailGescheftlich(keys, addrLst, jobLogger);

			} catch (Exception e) {
				String msg = "Konnte Mailadresse von personId " + keys + " nicht verarbeiten. ";
				jobLogger.error(msg + e.getMessage());
				logger.error(msg, e);
				collector.addErrorMessage(msg, e);
			}
		}

		jobLogger.debug("Orginal-List " + person2MailAdresses.keySet().size());
		jobLogger.debug("Filtered-List " + filteredList.keySet().size());

	}

	private void mitarbeiterRolleVerlaengern(PersonExisting tunePerson, JobLogger jobLogger) {

		long roleId = 100113;// == "Employee_tuhh"; == nur lesend
		XMLGregorianCalendar nextMonth = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_YEAR,
				config.get(DEFAULT_ACCOUNT_VALIDITY, 30, true));

		// Rolle aktualisieren
		boolean roleExists = false;
		List<Orgrole> roles = rolesDao.findValidRolesForPerson(tunePerson.getId());
		for (Orgrole role : roles) {
			if (role.getRoleId() == roleId) {
				long diff = CalendarUtils.differenceInDays(nextMonth, role.getValidTo());
				if (diff > 0 && diff < 21) {
					role.setValidTo(nextMonth);
					jobLogger.info("Rolle verlängert " + role.getId());
					rolesDao.updateOrgRole(role);
				}
				roleExists = true;
			}
		}

		// Rolle vergeben
		if (!roleExists) {
			SetPersonToOrgunitWithRole parameters = new SetPersonToOrgunitWithRole();
			parameters.setOrgunitLid(2l); // 2l = TUHH
			parameters.setPersonId(tunePerson.getId());
			parameters.setOrgroleId(roleId);
			parameters.setValidFrom(CalendarUtils.toXMLGregorianCalendar(new Date()));
			parameters.setValidTo(nextMonth);
			jobLogger.info("Rolle vergeben");
			rolesDao.setPersonToOrgunitWithRole(parameters);
		}
	}

	private boolean richtigePersonGefunden(PersonExisting person, LdapPerson employee) {

		if (person == null) {
			return false;
		}

		String hioFirstName = stringUtils.sanitize(person.getFirstname());
		String ldapFirstName = stringUtils.sanitize(employee.getGivenName());

		String hioLastName = stringUtils.sanitize(person.getSurname());
		String ldapLastName = stringUtils.sanitize(employee.getSurname());

		if (!ldapFirstName.contains(hioFirstName) && !hioFirstName.contains(ldapFirstName)) {
			logger.debug("Vornamen weichen ab: LDAP=" + ldapFirstName + " TUNE=" + hioFirstName);
			return false;
		}
		if (!ldapLastName.contains(hioLastName)) {
			logger.debug("Nachnamen weichen ab: LDAP=" + ldapLastName + " TUNE=" + hioLastName);
			return false;
		}
		if (person.getDateofbirth() != null && !dateToDatumsString(employee.getDateOfBirth())
				.equals(dateToDatumsString(toDate(person.getDateofbirth())))) {
			logger.debug("Geburtsdaten weichen ab: LDAP=" + dateToDatumsString(employee.getDateOfBirth()) + " TUNE="
					+ dateToDatumsString(toDate(person.getDateofbirth())));
			return false;
		}

		return true;
	}

	private void rzMailZuordnen(LdapPerson employee, PersonExisting tunePerson, JobLogger jobLogger) {

		if (employee.getMail() != null && !employee.getMail().equals("") && !employee.getMail().equals("no-mail")) {
			if (!person2MailAdresses.containsKey(tunePerson)) {
				person2MailAdresses.put(tunePerson, new ArrayList<String>());
			}

			// Filter Student + Mitarbeiter bei HIO
			if (mitarbeiterUndStudentFilterDao.filterEmployeeAndStudent(tunePerson.getId())) {

				// zu Liste hinzufügen wenn bei LDAP memeber = student
				try {
					// ist diese Person im Ldap als Student drin?
					List<LdapPerson> p = findLdapPerson(employee.getGivenName(), employee.getSurname());

					if (!p.isEmpty()) {
						// person gefunden
						ArrayList<String> addrArr = new ArrayList<>();

						addrArr.add(p.get(0).getMail());
						addrArr.add(employee.getMail());

						filteredList.get(tunePerson.getId()).addAll(addrArr);
					}
				} catch (NamingException e) {
					jobLogger.debug("Kann LDAP nicht abfragen");
					e.printStackTrace();
				}

				jobLogger.debug("Person ID: " + tunePerson.getId() + " Name: " + employee.getGivenName() + " Email: "
						+ employee.getMail() + " hinzugefügt");
			}

			person2MailAdresses.get(tunePerson).add(employee.getMail());

		}

	}

	private void verarbeiteAffiliate(LdapPerson employee, MessageCollector collector, JobLogger jobLogger) {

		jobLogger.debug("Verarbeite Account " + employee.toShortString());

		if (employee.getDateOfBirth() == null) {
			try {
				employee.setDateOfBirth(CalendarUtils.stringToDate("1900-01-01"));
				jobLogger.info("Person hat kein GebDat im LDAP und bekommt daher 1900-01-01");
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		// schaue ob in TUNE vorhanden
		PersonExisting tunePerson = findPersonInHio(employee);

		if (tunePerson == null) {
			jobLogger.info("Erstelle neue Affiliate aus " + employee.toShortString());
			Long pid = createPerson(employee, jobLogger);
			tunePerson = new PersonExisting();
			tunePerson.setId(pid);
			collector.addCreated();
		}

		if (tunePerson.getId() == 48164L) {
			System.out.println("stop");
		}

		jobLogger.info("Aktualisiere PersonId " + tunePerson.getId() + " mit " + employee.toShortString());
		gastRolleVerlaengern(tunePerson, jobLogger); // Gast Rolle
		accountVerlaengern(employee, tunePerson, jobLogger);
		rzMailZuordnen(employee, tunePerson, jobLogger);

		// collector ad Update
		collector.addUpdated();

	}

	private void verarbeiteEmployee(LdapPerson employee, MessageCollector collector, JobLogger jobLogger) {

		if (employee.getMail().equals("paula.alberts@tuhh.de")
				|| employee.getMail().equals("paula.alberts.fds@tuhh.de")) {
			System.out.println("wait!! what??!!? " + employee.getSurname());
		}

		jobLogger.debug("Verarbeite Account " + employee.toShortString());

		if (employee.getDateOfBirth() == null) {
			try {
				employee.setDateOfBirth(CalendarUtils.stringToDate("1900-01-01"));
				jobLogger.info("Person hat kein GebDat im LDAP und bekommt daher 1900-01-01");
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		// schaue ob in TUNE vorhanden
		PersonExisting tunePerson = findPersonInHio(employee);

		if (tunePerson == null) {
			jobLogger.info("Erstelle neue Person aus " + employee.toShortString());
			Long pid = createPerson(employee, jobLogger);
			tunePerson = new PersonExisting();
			tunePerson.setId(pid);
			collector.addCreated();
		}

		if (tunePerson.getId() == 48164L) {
			System.out.println("stop");
		}

		jobLogger.info("Aktualisiere PersonId " + tunePerson.getId() + " mit " + employee.toShortString());

		mitarbeiterRolleVerlaengern(tunePerson, jobLogger);
		accountVerlaengern(employee, tunePerson, jobLogger);
		rzMailZuordnen(employee, tunePerson, jobLogger);

		// collector ad Update
		collector.addUpdated();

	}

	private void verarbeiteMailAusCache(PersonExisting tunePerson, ArrayList<String> mailadressen,
			JobLogger jobLogger) {

		jobLogger.info("Verarbeite personId " + tunePerson.getId() + " mit adressen " + mailadressen);
		PersonAdressesWrapper adresses = addressDao.readPersonAddresses(tunePerson.getId());
		HisEAddress address = adresses.getEAddress(NotificationCategory.RZ, EAdressType.EMAIL, AddressTag.RZ);

		if (address.getValue() != null && mailadressen.isEmpty()) {
			jobLogger.info("Lösche " + address.getValue() + " von personId " + tunePerson.getId());
			addressDao.deleteAddress(address);
		} else if (address.getValue() == null) {
			if (mailadressen.isEmpty()) {
				return;
			}

			address.setValue(mailadressen.get(0));
			jobLogger.info("Lege Mailadresse " + mailadressen.get(0) + " an");
			addressDao.create(address);
		} else {
			if (!mailadressen.contains(address.getValue())) {
				jobLogger.info("Ändere Mailadresse von " + address.getValue() + " auf " + mailadressen.get(0));
				address.setValue(mailadressen.get(0));
				addressDao.update(address);
			}
		}

	}

	private void verarbeiteMailGescheftlich(Long id, ArrayList<String> mailadressen, JobLogger jobLogger) {

		if (mailadressen.size() == 2) {

			for (int i = 0; i < mailadressen.size(); i++) {

				if (i == 0) {
					writeEmailRZ(id, mailadressen.get(i), jobLogger);
				}
				if (i == 1) {
					writeEmailBuissiness(id, mailadressen.get(i), jobLogger);
				}

			}
		} else {
			jobLogger.debug("Mail Adresse size > 2 oder < 2");
		}

	}

	private void writeEmailBuissiness(Long id, String currentAddress, JobLogger jobLogger) {
		PersonAdressesWrapper adresses = addressDao.readPersonAddresses(id);
		HisEAddress address = adresses.getEAddress(NotificationCategory.OFFICE, EAdressType.EMAIL, AddressTag.BUSINESS);

		if (address.getValue() != null && currentAddress.isEmpty()) {
			jobLogger.info("Lösche " + address.getValue() + " von personId " + id);
			addressDao.deleteAddress(address);
		} else if (address.getValue() == null) {
			if (currentAddress.isEmpty()) {
				return;
			}

			address.setValue(currentAddress);
			jobLogger.info("Lege Mailadresse " + currentAddress + " an");

			HisEAddress addr = new HisEAddress();
			addr.setPersonId(id);
			addr.setValue(currentAddress);
			addr.setAddressTag(AddressTag.BUSINESS);
			addr.setEaddressType(EAdressType.EMAIL);

			addressDao.createNewEmailForOffice(addr);
		} else {
			if (!currentAddress.contains(address.getValue())) {
				jobLogger.info("Ändere Mailadresse von " + address.getValue() + " auf " + currentAddress);
				address.setValue(currentAddress);
				addressDao.update(address);
			}
		}
	}

	private void writeEmailRZ(Long id, String currentAddress, JobLogger jobLogger) {
		PersonAdressesWrapper adresses = addressDao.readPersonAddresses(id);
		HisEAddress address = adresses.getEAddress(NotificationCategory.RZ, EAdressType.EMAIL, AddressTag.RZ);

		if (address.getValue() != null && currentAddress.isEmpty()) {

			jobLogger.info("Lösche " + address.getValue() + " von personId " + id);
			addressDao.deleteAddress(address);

		} else if (address.getValue() == null) {

			if (currentAddress.isEmpty()) {
				return;
			}

			address.setValue(currentAddress);
			jobLogger.info("Lege Mailadresse " + currentAddress + " an");
			addressDao.create(address);

		} else {

			if (!currentAddress.contains(address.getValue())) {
				jobLogger.info("Ändere Mailadresse von " + address.getValue() + " auf " + currentAddress);
				address.setValue(currentAddress);
				addressDao.update(address);
			}
		}

	}

}
