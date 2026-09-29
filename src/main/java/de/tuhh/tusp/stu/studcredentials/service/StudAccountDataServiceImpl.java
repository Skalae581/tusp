package de.tuhh.tusp.stu.studcredentials.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.accountservice.CompleteAccount;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.pojo.PersonCredential;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;

@Component
public class StudAccountDataServiceImpl implements StudAccountDataService {

	private final static String STUDENT_ROLE = "student";
	private final static Long STUDENT_ROLE_ID = 5L;

	private static Logger logger = LogManager.getLogger(StudAccountDataServiceImpl.class);

	@Autowired
	private HisAccountHelperDao hisAccountHelperDao;
	@Autowired
	private HisRoleHelperDao hisRoleHelperDao;

	@Override
	public List<PersonCredential> getAccountData(JobLogger jobLogger) {

		List<PersonCredential> personData = getPersonData(jobLogger);
		List<PersonCredential> personCredentialsList = new ArrayList<>();

		if (null != personData) {

			for (PersonCredential person : personData) {

				// check if more then one account
				List<CompleteAccount> completeAccount = hisAccountHelperDao
						.searchAccountForPerson(person.getPersonId());

				// wenn es mehr als ein (1) Account gibt
				if (completeAccount.size() > 1) {

					String accounts = "";

					for (CompleteAccount account : completeAccount) {
						accounts += account.getUsername() + ",";
					}
					logger.debug("Person: (" + person.getUsername() + ") hat " + completeAccount.size() + " Accounts: ("
							+ accounts + ")");
					jobLogger.debug("Person: (" + person.getUsername() + ") hat " + completeAccount.size()
							+ " Accounts: (" + accounts + ")");

					continue; // mach weiter, überspringen

				} else {

					// baue neu liste von Studenten die bearbeitet werden müssen
					PersonCredential personCredential = new PersonCredential();

					if (completeAccount.get(0).isIsInitialpassword()) {

						personCredential.setInitPassword(completeAccount.get(0).getPasswordhash());
						personCredential.setUsername(person.getUsername());
						personCredential.setPersonId(person.getPersonId());
						personCredential.setAccountId(person.getAccountId());

						logger.debug("Student gefunden -> Zur Liste hinzufügen... [ Benutzername: ("
								+ person.getUsername() + ") Init-Password: (" + completeAccount.get(0).getPasswordhash()
								+ ") PersonId: (" + person.getPersonId() + ") ]");
						jobLogger.debug("Student gefunden -> Zur Liste hinzufügen... [ Benutzername: ("
								+ person.getUsername() + ") Init-Password: (" + completeAccount.get(0).getPasswordhash()
								+ ") PersonId: (" + person.getPersonId() + ") ]");
					} else {
						logger.debug("Person: (" + person.getUsername() + ") hat kein Initial Passwort!");
						jobLogger.debug("Person: (" + person.getUsername() + ") hat kein Initial Passwort!");

						continue;
					}

					personCredentialsList.add(personCredential);
				}

			}
		}
		// die Liste mit validen Studenten die bearbeitet werden später
		return personCredentialsList;

	}

	private List<PersonCredential> getPersonData(JobLogger jobLogger) {

		List<PersonCredential> newPersonList = new ArrayList<>();
		List<PersonCredential> personCredentials = readAccounts();

		if (null == personCredentials) {
			return null;
		}

		for (PersonCredential personCredential : personCredentials) {

			Long pId = getPersonWithStudBewRole(personCredential.getPersonId());

			if (null != pId) {
				// check if person has role
				if (personCredential.getPersonId().longValue() == pId.longValue()) {

					PersonCredential person = new PersonCredential();
					person.setPersonId(pId);
					person.setUsername(personCredential.getUsername());
					person.setAccountId(personCredential.getAccountId());
					newPersonList.add(person);
					jobLogger.debug("Student Rolle: (" + personCredential.getUsername() + ")");
					logger.debug("Student Rolle: (" + personCredential.getUsername() + ")");
				}

			} else {
				jobLogger.debug("Keine Student Rolle: (" + personCredential.getUsername() + ")");
				logger.debug("Keine Student Rolle: (" + personCredential.getUsername() + ")");
				continue;
			}
		}

		return newPersonList;

	}

	/**
	 * liefert personen id für studenten Rolle
	 *
	 * @param personId
	 * @return
	 */
	private Long getPersonWithStudBewRole(Long personId) {

		Orgrole role = hisRoleHelperDao.getRoleForPerson(personId, STUDENT_ROLE);

		if (null != role) {
			if (role.getRoleId() == STUDENT_ROLE_ID) {
				return role.getPersonId();
			}
		}
		return null;

	}

	/**
	 * lese alle Accounts ab Datum gültig ab
	 *
	 * @return
	 */
	private List<PersonCredential> readAccounts() {

		List<PersonCredential> personInfoList = new ArrayList<>();

		LocalDate today = LocalDate.now();

		// Lokal Test
//		String str = "2022-03-22";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate today = LocalDate.parse(str, formatter);

		List<AccountInfo> accountInfos = hisAccountHelperDao.findAccountFromDate(today.toString());

		if (!accountInfos.isEmpty()) {
			for (AccountInfo info : accountInfos) {
				PersonCredential personInfo = new PersonCredential();
				personInfo.setUsername(info.getUsername());
				personInfo.setPersonId(info.getPersonId());
				personInfo.setAccountId(info.getId());
				personInfoList.add(personInfo);
			}

			return personInfoList;
		}
		return null;
	}
}
