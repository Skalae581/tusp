package de.tuhh.tusp.stu.exmastudentportal.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.CompleteAccount;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPortalMessageDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisStudentHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

@Component
public class ExmaStudentPortalServiceImpl implements ExmaStudentPortalService {

	private static Logger logger = LogManager.getLogger(ExmaStudentPortalServiceImpl.class);

	@Autowired
	private HisPortalMessageDao hisPortalMessage;

	@Autowired
	private HisStudentHelperDao studentDao;
	@Autowired
	private HisAccountHelperDao accountDao;

	@Autowired
	private ConfigService config;

	@Override
	public void sendData(JobLogger jobLogger) {
		jobLogger.debug("send Data started...");
		logger.debug("Normal logger working");

		updateStudentAccount(jobLogger);

	}

	/**
	 * Sendet die Portal-Nachricht, mit den Account daten
	 *
	 * @param hisPerson
	 */
	private void sendPortalMessage(HisPerson hisPerson, JobLogger jobLogger) {

		String categoryUniquename = "TUHH_EXMA_PSWD";
		String[] params = { hisPerson.getAccount().getPasswordhash(), "", "" };

		XMLGregorianCalendar from = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_MONTH, 0);
		XMLGregorianCalendar to = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DAY_OF_MONTH, 90);

		List<Long> ids = hisPortalMessage.sendMessage(Arrays.asList(hisPerson.getIdPerson()), categoryUniquename, from,
				to, params);
		logger.info("Portalnachricht für " + hisPerson + " wurde erstellt. ID: " + ids.get(0));
		jobLogger.info("Portalnachricht für " + hisPerson + " wurde erstellt. ID: " + ids.get(0));
	}

	private List<Long> updateStudentAccount(JobLogger jobLogger) {

		// String date = "2021-09-20"; // zum testen

		String date = LocalDate.now().minusDays(150).toString();

		List<Long> students = studentDao.findStudentWithExmaDate(date);

		logger.debug("Studenten gefunden: " + students.size() + " für das Datum: " + date);
		jobLogger.debug("Studenten gefunden: " + students.size() + " für das Datum: " + date);

		for (Long personId : students) {

			logger.debug("person id: " + personId);
			jobLogger.debug("person id: " + personId);

			List<CompleteAccount> account = accountDao.searchAccountForPerson(personId);

			if (account.isEmpty()) {
				logger.debug("Person " + personId + " hat kein Account");
				jobLogger.debug("Person " + personId + " hat kein Account");
				continue;
			}
			logger.debug("Benutzername: " + account.get(0).getUsername());
			jobLogger.debug("Benutzername: " + account.get(0).getUsername());

			HisAccountWrapper accountWrapper = accountDao.readAccount(account.get(0).getId());
			accountWrapper.setIsInitialpassword(true);

			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			String pwd = RandomStringUtils.random(6, characters);
			logger.debug("Init PWD: " + pwd);
			jobLogger.debug("Init PWD: " + pwd);
			accountWrapper.setPasswordhash(pwd);

			accountWrapper.setUsername(account.get(0).getUsername());

			accountDao.updateAccount(accountWrapper);

			HisPerson hisPerson = new HisPerson(personId, null);
			HisAccount hisAccount = new HisAccount(accountWrapper.getUsername(), accountWrapper.getPasswordhash());
			hisPerson.setAccount(hisAccount);

			sendPortalMessage(hisPerson, jobLogger);

		}
		return students;
	}

}
