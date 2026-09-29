package de.tuhh.tusp.psv.mobon.service;

//import org.springframework.stereotype.Component;

/**
 * Schreibt die Daten zu HIO
 *
 * @author rztsgr
 */
//@Component
public class SopOutputServiceImpl /* implements SopOutputService */ {

//	public static final String UNIHH_ASTAT_STG_CONFIG_KEY = "de.tuhh.tusp.psv.mobon.service.unihh_astat_stg_id";
//	public static final String ROLES_FILE_PATH = "de.tuhh.tusp.psv.mobon.service.rolesFile";
//	private static Logger logger = Logger.getLogger(SopOutputServiceImpl.class);
//
//	@Autowired
//	private SopApplicantDao sopApplicantDao;
//
//	@Autowired
//	private SopConverter sopConverter;
//
//	@Autowired
//	private HisDataServiceOutput hisDataServiceOutput;
//
//	@Autowired
//	private ConfigService config;
//
//	@Autowired
//	private HisRoleHelperDao hisRoleHelperDao;
//
//	@Override
//	public void importSopData(SopArgumentsHolder sopFilter)	throws RolesFromFileException, JSONException,
//															NoRolesDefinedForOuException, RemoteException,
//															ServiceException {
//
//		MessageCollector mc = MessageCollectorImpl.getInstance(Thread.currentThread());
//
//		String unihhAstatStg = config.get(UNIHH_ASTAT_STG_CONFIG_KEY, "11", true);
//
//		// Accounts müssen bis zum Ende des nächsten Semesters gültig sein
//		// (Festlegung Muthana 14.11.2017)
//		int semester = CalendarUtils.getSemester(new GregorianCalendar());
//		GregorianCalendar validTo = CalendarUtils.lastDayOfSemester(semester, 1).toGregorianCalendar();
//
//		Resource file = new ClassPathResource(config.get(ROLES_FILE_PATH, "sop\\importRoles.JSON", true));
//		List<HisRole> grantedRoles = hisRoleHelperDao.getRolesFromFileByOu(file, "MobilityOnline", validTo);
//
//		Application[] apps = sopApplicantDao.applicationDataToSopApplicant(sopFilter);
//		for (Application application : apps) {
//			try {
//				SopApplicant applicant = sopConverter.convertApplicationToSopApplicant(application);
//				HisPerson hisP = sopConverter.convertToHisPerson(applicant, grantedRoles);
//				HisStudent student = sopConverter.convertToHisStudent(hisP, unihhAstatStg);
//				Long personId = hisDataServiceOutput.savePerson(hisP, student, mc, new InMemoryResource(""), null);
//				String message = "Person {" + personId + "} wird erfolgreich gespeichert!";
//				mc.addOkMessage(message);
//				logger.debug(message);
//			}
//			catch (Exception e) {
//				String message = "Person " + application.getLastName() + " kann nicht gespeichert werden! ";
//				logger.error(message, e);
//				mc.addErrorMessage(message + e.toString());
//			}
//
//		}
//
//	}

}
