package de.tuhh.tusp.psv.mobon.dao;

import org.springframework.stereotype.Component;

@Component
public class SopConverterImpl implements SopConverter {

//	private static Logger logger = Logger.getLogger(SopConverterImpl.class);
//
//	@Autowired
//	private MobonHioCountryMappingDao mobonHioCountryMappingDao;
//
//	@Autowired
//	private GenderService genderService;
//
//	@Autowired
//	private DistrictDao districtDao;
//
//	@Autowired
//	private HisAddressesServiceOutput addressService;
//
//	@Autowired
//	private HisCourseOfStudyServiceOutput hisCourseOfStudyHelper;
//
//	@Override
//	public SopApplicant convertApplicationToSopApplicant(Application application) {
//
//		Map<String, String> elementMap = new HashMap<>();
//		ApplicationDataElement[] applicationElements = application.getApplicationDataElements();
//		for (ApplicationDataElement c : applicationElements) {
//			if (c.getComboboxFirstValue() != null && c.getElementName().equals("sem_id")) {
//				elementMap.put(c.getElementName(), c.getComboboxFirstValue());
//			} else {
//				elementMap.put(c.getElementName(), c.getElementValue());
//			}
//
//		}
//
//		SopApplicant applicant = new SopApplicant();
//		applicant.setId(application.getApplicationID());
//		applicant.setVorname(application.getFirstName());
//		applicant.setNachname(application.getLastName());
//		applicant.setEmail(application.getEmail());
//		applicant.setGeschlecht(elementMap.get("bew_geschlecht"));
//		applicant.setGeburtstag(elementMap.get("bew_geb_datum"));
//		applicant.setGeburtsort(elementMap.get("bew_geb_ort"));
//		applicant.setNationalitaet(Long.parseLong(elementMap.get("lcd_id_nat")));
//		applicant.setBewerbungsSemester(elementMap.get("sem_id"));
//		applicant.setGeburtsland(Long.parseLong(elementMap.get("lcd_id_geb")));
//		applicant.setAnzahlAbsolSemester(studienjahre2semester(elementMap.get("bew_anz_studienjahre")));
//
//		return applicant;
//	}
//
//	@Override
//	public HisPerson convertToHisPerson(SopApplicant sop, List<HisRole> grantedRoles)
//			throws CountryNotFoundException, RuntimeException {
//
//		if (grantedRoles == null || grantedRoles.isEmpty()) {
//			throw new RuntimeException("Keine Rollen angegeben");
//		}
//
//		logger.debug("parsing to HisPerson");
//
//		HisPerson user = new HisPerson(null, addressService);
//		user.setHmkfzkz("A");
//		user.setIdExtern(sop.getId());
//		user.setGenderId(genderService.fetchByString(sop.getGeschlecht()).getId());
//
//		PersonAdressesWrapper personAddressWrapper = new PersonAdressesWrapper(user.getIdPerson());
//		user.setAddresses(personAddressWrapper);
//
//		HisPostAddress postAddress = personAddressWrapper.getPostAddress(
//				new NotificationCategory[] { NotificationCategory.STU }, new AddressTag[] { AddressTag.HOME });
//
//		postAddress.setPersonId(user.getIdPerson());
//		postAddress.setCountry("D");
//		postAddress.setPostcode("21071");
//		postAddress.setCity("Hamburg");
//		postAddress.setStreet("Am Schwarzenberg-Campus 3");
//
//		HisEAddress mail = personAddressWrapper.getEAddress(NotificationCategory.STU, EAdressType.EMAIL,
//				AddressTag.HOME);
//		mail.setValue(sop.getEmail());
//		mail.setPersonId(user.getIdPerson());
//
//		// Vorname
//		user.setFirstname(sop.getVorname());
//
//		// Geburtstag
//		user.setDateofbirth(
//				CalendarUtils.stringToXMLGregorianCalendar(sop.getGeburtstag(), CalendarUtils.DATEFORMAT_GERMAN));
//
//		// Geburtsort
//		user.setBirthcity(sop.getGeburtsort());
//
//		// Nationalität
//		if (sop.getNationalitaet() != null) {
//
//			// Versuche es über das Mapping zu finden
//			Long countryId = mobonHioCountryMappingDao.fetchHioIdByMobonId(sop.getNationalitaet());
//			user.setCountryId(countryId);
//			user.setNationalityId(countryId);
//
//		}
//		// HmKFZ
//		user.setHmkfz(districtDao.fetchByUniquename("HH").getId());
//		// Bewsemester
//		user.setSemester(CalendarUtils.sopToHisSemester(sop.getBewerbungsSemester()));
//
//		// Nachname + account
//		HisAccount account = new HisAccount();
//		if (sop.getNachname() != null) {
//			user.setSurname(sop.getNachname());
//			account.setUsername(sop.getNachname() + sop.getId());
//			account.setValidTo(grantedRoles.get(0).getValidTo());
//			account.setPasswordValidTo(account.getValidTo());
//		}
//
//		user.setAccount(account);
//		user.setRoles(grantedRoles);
//
//		return user;
//	}
//
//	@Override
//	public HisStudent convertToHisStudent(HisPerson hisP, String unihhAstatStg) {
//
//		HisStudent student = new HisStudent();
//
//		student.setExterneMtknr(hisP.getIdExtern());
//		student.setUnihhAstatStg(unihhAstatStg);
//		student.setSurname(hisP.getSurname());
//		student.setFirstname(hisP.getFirstname());
//		student.setGenderHiskey(hisP.getGenderId());
//		student.setIsCandidateStudent(Boolean.TRUE);
//		student.setCourseOfStudyUniquename(hisCourseOfStudyHelper.findUniquenameForMobilityOnline());
//		return student;
//	}
//
//	/**
//	 * Konvertiert eine zahl als String in die doppelte Zahl (als int)
//	 *
//	 * @param number
//	 *            z.B. "2.0"
//	 * @return int oder null, bei null-Eingabe
//	 * @throws NumberFormatException
//	 *             Wenn number keine Zahl ist
//	 */
//	private Integer studienjahre2semester(String number) throws NumberFormatException {
//
//		if (number == null) {
//			return null;
//		}
//		float semester = Float.parseFloat(number.replace(',', '.'));
//		return (int) (2 * semester);
//	}

}