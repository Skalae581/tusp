package de.tuhh.tusp.stu.sptim.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.AccountInfo;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.sptim.dao.TimStudentDao;
import de.tuhh.tusp.stu.sptim.pojo.TimPerson;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtils;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtilsImpl.CmdFaildException;
import de.tuhh.tusp.stu.timsl.service.TimSonderStudentService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.ParseException;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPortalMessageDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.service.HisAccountServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisAddressesServiceOutput;

@Component
public class TimStudentenServiceImpl implements TimStudentenService {

	private static Logger logger = LogManager.getLogger(TimStudentenService.class);

	@Autowired
	private TimStudentDao timStudentDao;
	@Autowired
	private HisAccountServiceOutput hisAccountServiceOutput;
	@Autowired
	private HisAccountHelperDao hisAccountHelperDao;
	@Autowired
	private HisAddressesServiceOutput hisAdressesServiceOutput;
	@Autowired
	private HisPortalMessageDao hisPortalMessageDao;
	@Autowired
	private ConfigService config;
	@Autowired
	private SyscallUtils syscall;
	@Autowired
	private TimSonderStudentService sonderStudentenService;

	@Override
	public List<Map<StudentColumns, String>> getExportStudents(MessageCollector mc, JobLogger logger) {

		List<Map<StudentColumns, String>> students = timStudentDao.getStudentenDaten(mc, logger);

		for (Map<StudentColumns, String> student : sonderStudentenService.getStudentenDaten(mc, logger)) {

			// Keine Studenten doppelt liefern
			if (students.stream()
					.anyMatch(o -> o.get(StudentColumns.MTKNR).equals(student.get(StudentColumns.MTKNR)))) {
				continue;
			}

			students.add(student);
		}

		return students;
	}

	@Override
	public void importiere(List<TimPerson> personList, MessageCollector mc) {

		for (TimPerson person : personList) {

			HisPerson hisPerson = new HisPerson(Long.parseLong(person.getPersonId()), hisAdressesServiceOutput);

			HisEAddress mail = hisPerson.getAddresses().getEAddress(NotificationCategory.STU, EAdressType.EMAIL,
					AddressTag.RZ);
			mail.setValue(person.getMailAddr());
			mc.addOkMessage("Für " + person.getPersonId() + " wird Mailaddresse " + mail + " verwendet.");

			HisAccount account = new HisAccount(person.getUname(), person.getPassword());
			account.setIsInitialpassword(true); // wichtig für
												// Accountmitteilungsbescheid
			account.setAccountauthId(3L); // wichtig für
											// Accountmitteilungsbescheid
			hisPerson.setAccount(account);
			mc.addOkMessage("Für " + person.getPersonId() + " wird " + account + " verwendet.");

			try {
				savePerson(person.getStatus(), hisPerson);
				mc.addUpdated();
			} catch (RuntimeException e) {
				logger.error(hisPerson.toShortString(), e);
				mc.addErrorMessage(hisPerson.toShortString() + ": " + e.getMessage());
			}

		}
	}

	@Override
	public List<TimPerson> readRuecklaeufer(MessageCollector mc, JobLogger jobLogger)
			throws ParseException, IOException, CmdFaildException, InterruptedException {

		GregorianCalendar now = new GregorianCalendar();
		String filename = "ruecklaeufer-" + CalendarUtils.getSemesterName(now) + "-"
				+ CalendarUtils.format(now.getTime(), "yyyyMMddHH") + ".txt";

		Resource classPathDir = new ClassPathResource("sptim/");

		File workdir = classPathDir.getFile();
		WritableResource resource = new FileSystemResource(workdir.getAbsolutePath() + "/" + filename);
		if (resource.exists()) {
			return timStudentDao.readRuecklaeufer(resource);
		}

		// Datei übertragen
		String script = "";
		if (config.get("de.tuhh.tusp.stu.sptim.service.test_run", "false", true).equals("true")) {
			script = "./getData.test.pl";
		} else {
			script = "./getData.pl";
		}
		syscall.runCmd(workdir, "chmod", "+x", script); // Just to be sure
		syscall.runCmd(workdir, script, "--from=ldapidm", "--output=" + filename);

		List<TimPerson> data = timStudentDao.readRuecklaeufer(resource);

		if (!data.isEmpty()) {
			for (TimPerson person : data) {
				jobLogger.debug("Person gefunden: " + person.getMailAddr());
			}

		}

		syscall.runCmd(workdir, "mv", resource.getFile().getAbsolutePath(), "/disk1/gebuehrendaten/");

		return data;
	}

	@Override
	public void savePerson(String status, HisPerson hisPerson) {

		HisAccount account = hisPerson.getAccount();
		String username = account.getUsername();
		AccountInfo acc = hisAccountHelperDao.fetchAccountByUsername(username);
		logger.debug("savePerson(" + status + ", " + hisPerson.getIdPerson() + ") mit username=" + username
				+ " gefunden=" + (acc != null));

		switch (status) {
		case "n":

			if (acc != null) {
				logger.warn("Account " + username + " existiert bereits");
				if (acc.getPersonId() == hisPerson.getIdPerson()) {
					hisAccountServiceOutput.updateAccount(hisPerson, acc.getId());
				} else {
					throw new RuntimeException("Account " + username + " ist bereits an " + acc.getPersonId()
							+ " vergeben und kann nicht " + hisPerson.getIdPerson() + " gegeben werden!");
				}
			} else {
				Long accId = hisAccountServiceOutput.addAccountForPerson(hisPerson);
				logger.info("Account angelegt " + accId + " For Person " + hisPerson.getIdPerson());

				sendPortalMessage(hisPerson);
			}

			break;

		case "u":

			if (acc == null) {
				logger.warn("Account " + username + " existiert nicht");

				// Einfach neu anlegen
				Long accId = hisAccountServiceOutput.addAccountForPerson(hisPerson);
				logger.info("Account angelegt " + accId + " For Person " + hisPerson.getIdPerson());
				break;
			}

			if (acc.getPersonId() != hisPerson.getIdPerson()) {
				throw new RuntimeException("Der Account " + username + " gehört bereits zu einer anderen Person als "
						+ hisPerson.getIdPerson());
			}

			hisAccountServiceOutput.updateAccount(hisPerson, acc.getId());
			logger.info("Account " + username + " aktualisiert  For Person " + hisPerson.getIdPerson());

			break;

		default:
			throw new RuntimeException(
					"Status " + status + " für Person " + hisPerson.getIdPerson() + " ist unbekannt");
		}

		// Adressen sichern
		hisAdressesServiceOutput.updateOrCreateAddresses(hisPerson);
	}

	/**
	 * Sendet die Portal-Nachricht, mit den Account daten
	 *
	 * @param hisPerson
	 */
	private void sendPortalMessage(HisPerson hisPerson) {

		String categoryUniquename = config.get("de.tuhh.tusp.stu.sptim.portalmessage.uniquename", "false", true);

		if ("false".equals(categoryUniquename)) {
			logger.info("Portalnachrichten für die Accountmitteilung sind deaktiviert.");
			return;
		}

		String[] params = { config.get("de.tuhh.tusp.stu.sptim.portalmessage.header", "false", true),
				config.get("de.tuhh.tusp.stu.sptim.portalmessage.subject", "false", true),
				config.get("de.tuhh.tusp.stu.sptim.portalmessage.body", "false", true), };

		for (int i = 0; i < params.length; i++) {
			params[i] = params[i]

					.replace("%PASSWORD%", hisPerson.getAccount().getPasswordhash())

					.replace("%USERNAME%", hisPerson.getAccount().getUsername())

					// Dies ist die Mailadresse, die dem
					// Studenten als neu eingerichtete Adresse
					// präsentiert wird.
					// Es ist nicht die Adresse an die eine
					// Information über die neue Portalnachricht
					// gesendet wird!
					.replace("%EMAIL%", hisPerson.getAddresses()
							.getEAddress(NotificationCategory.STU, EAdressType.EMAIL, AddressTag.RZ).getValue());
		}

		XMLGregorianCalendar from = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_MONTH, 0);
		XMLGregorianCalendar to = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_MONTH, 90);

		List<Long> ids = hisPortalMessageDao.sendMessage(Arrays.asList(hisPerson.getIdPerson()), categoryUniquename,
				from, to, params);
		logger.info("Portalnachricht für " + hisPerson + " wurde erstellt. ID: " + ids.get(0));
	}

	@Override
	public void sentStudentFile(List<Map<StudentColumns, String>> studenten, GregorianCalendar time,
			MessageCollector mc) throws IOException, CmdFaildException, InterruptedException {

		String filename = "rzcsv-" + CalendarUtils.getSemesterName(time) + "-"
				+ CalendarUtils.format(time.getTime(), "yyyyMMddHH") + ".txt";

		Resource classPathDir = new ClassPathResource("sptim/");
		String path = classPathDir.getFile().getAbsolutePath();

		WritableResource resource = new FileSystemResource(path + "/" + filename);
		resource.getFile().createNewFile();

		timStudentDao.writeStudenten(studenten, resource);

		// Datei übertragen
		String script;
		if (config.get("de.tuhh.tusp.stu.sptim.service.test_run", "false", true).equals("true")) {
			script = "./studdata.test.sh";
		} else {
			script = "./studdata.sh";
		}

		File workdir = new File(path);
		syscall.runCmd(workdir, "chmod", "+x", script); // Just to be sure
		syscall.runCmd(workdir, script, filename);

		syscall.runCmd(workdir, "mv", resource.getFile().getAbsolutePath(), "/disk1/gebuehrendaten/");
	}

}
