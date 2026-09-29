package de.tuhh.tusp.stu.studcredentials.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.addressservice.Address;
import de.his.ws.addressservice.AddressesType;
import de.his.ws.addressservice.EAddress;
import de.his.ws.addressservice.NotificationCategoryType;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.pojo.PersonCredential;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.HisSTUAddressHelperDao;

@Component
public class StudCredentialsServiceImpl implements StudCredentialsService {

	// Address Tags Ids = Email und Private muss sein
	private final static Long E_ADDRESS_TYPE_EMAIL = 10L;
	private final static Long E_ADDRESS_TAG_PRIVATE = 5L;

	private final static String FROM_ADDR = "noreply@tu-harburg.hamburg.de";
	private final static String SUBJECT = "Benachrichtigung über Ihr TUHH-Account\n";

	private static Logger logger = LogManager.getLogger(StudCredentialsServiceImpl.class);

	@Autowired
	private HisSTUAddressHelperDao hisSTUAddressHelperDao;
	@Autowired
	private StudEmailService studEmailService;
	@Autowired
	private StudAccountDataService studAccountDataService;

	@Autowired
	private MailUtils mailUtils;

	@Override
	public void sendMail(JobLogger jobLogger) throws Exception {

		// gefundene Account Daten für die Studirenden
		List<PersonCredential> credentials = studAccountDataService.getAccountData(jobLogger);

		// Hier speichern wir die Studenten -Email Addr, die eine E-mail bekommen
		List<String> emailAddresses = new ArrayList<>();

		if (null != credentials && !credentials.isEmpty()) {

			for (PersonCredential person : credentials) {

				// Abfragen ob es Addressen unter STU Notification gibt
				AddressesType addressesType = hisSTUAddressHelperDao
						.readAddressesByNotificationType(person.getPersonId(), NotificationCategoryType.STUALL); // test

				logger.debug("-------------PERSON-DETAILS--------------");
				jobLogger.debug("----------PERSON-DETAILS--------------");
				logger.debug(
						"Person gefunden: " + person.getUsername() + " | AddressType: " + addressesType.getAddress());
				jobLogger.debug(
						"Person gefunden: " + person.getUsername() + " | AddressType: " + addressesType.getAddress());

				for (Address address : addressesType.getAddress()) {

					try {
						logger.debug("Address Id: " + address.getId() + " für Person (Benutzername: "
								+ person.getUsername() + ") Person Id: " + person.getPersonId() + " AddressTag Id: "
								+ address.getAddresstagId());
						jobLogger.debug("Address Id: " + address.getId() + " für Person (Benutzername: "
								+ person.getUsername() + ") Person Id: " + person.getPersonId() + " AddressTag Id: "
								+ address.getAddresstagId());

						EAddress eaddress = hisSTUAddressHelperDao.readAddressById(address.getId());

						if (eaddress.getEaddresstypeId() == E_ADDRESS_TYPE_EMAIL) {

							logger.debug("Adresse ist Typ E-Mail");
							jobLogger.debug("Adresse ist Typ E-Mail");

							if (eaddress.getAddresstagId() == E_ADDRESS_TAG_PRIVATE) {

								logger.debug("E-Mail Adresse ist Typ PRIVATE");
								jobLogger.debug("E-Mail Adresse ist Typ PRIVATE");

								logger.debug("--------EAdress ID............" + eaddress.getId());
								logger.debug("--------Eadress: " + eaddress.getEaddress());
								logger.debug("--------EAddressTagId: " + eaddress.getAddresstagId());
								logger.debug("--------EadressTypeId: " + eaddress.getEaddresstypeId());

								// Addresse Speichern
								emailAddresses.add(eaddress.getEaddress());

								// passwort und benutzername
								String topTextDe = "Benutzername: " + person.getUsername() + "</br>Initialpasswort: "
										+ person.getInitPassword() + " </br></br>";
								String topTextEn = "Username: " + person.getUsername() + "</br>Initial-password: "
										+ person.getInitPassword() + " </br></br>";

								String body = studEmailService.getTextForEmail(topTextDe, topTextEn);

								try {

									// Mail hier verschicken
									mailUtils.sendHTMLMail(eaddress.getEaddress(), FROM_ADDR, SUBJECT, body);

									logger.debug("Mail gesendet an [ Benutezrname: " + person.getUsername()
											+ " E-Mail: " + eaddress.getEaddress() + " ] ");
									jobLogger.debug("Mail gesendet an [ Benutezrname: " + person.getUsername()
											+ " E-Mail: " + eaddress.getEaddress() + " ] ");
								} catch (AddressException e) {
									logger.debug("E-Mail Versand fehlgeschlagen! " + e.getMessage());
									jobLogger.debug("E-Mail Versand fehlgeschlagen! " + person.getUsername());
									continue;
								} catch (MessagingException e) {
									logger.debug("E-Mail Versand fehlgeschlagen! " + e.getMessage());
									jobLogger.debug("E-Mail Versand fehlgeschlagen! " + person.getUsername());
									continue;
								}

							} else {
								logger.debug("E-mail Address Typ 'PRIVAT' für Person: " + person.getUsername()
										+ " ist nicht gesetzt!!");
								jobLogger.debug("E-mail Address Typ 'PRIVAT' für Person: " + person.getUsername()
										+ " ist nicht gesetzt!!");

								// hier auf PRIVAT setzen
							}
						} else {

							logger.debug("Adresse ist keine TYP EMAIL! Überspringen..");
							jobLogger.debug("Adresse ist keine TYP EMAIL! Überspringen..");

						}

					} catch (Exception e) {
						logger.debug("Adresse ist NULL! Überspringen..");
						jobLogger.debug("Adresse ist NULL! Überspringen..");
					}

					logger.debug("---");
					jobLogger.debug("---");

				}

			}
			// Ergebniss Email an Administratior verschicken
			sendResultEmailToAdmin(emailAddresses, jobLogger);

		} else {
			logger.debug("Keine Daten für Heute! Datum: " + LocalDate.now());
			jobLogger.debug("Keine Daten für Heute! Datum: " + LocalDate.now());

		}

	}

	// TODO aus Config auslesen
	// TODO ergebniss mail schöner machen
	private void sendResultEmailToAdmin(List<String> emailAddresses, JobLogger jobLogger) {

		for (String email : emailAddresses) {
			logger.debug("E-Mail Adresse gefunden: [ " + email + " ]");
			jobLogger.debug("E-Mail Adresse gefunden: [ " + email + " ]");
		}

		// TODO Hier alles aus DB auslesen!!

		String addrBody = "Liste von Studis die Email bekommen haben</br></br>";
		String subjectAdmin = "CronJob StudentCredentials Ergebniss für " + LocalDate.now();

		String tuspEmailAddr = "tusp@tuhh.de";

		for (String addr : emailAddresses) {
			addrBody += "E-Mail gefunden: " + addr + "</br>";
		}

		try {

			logger.debug("Versuche die Ergebnisse an " + tuspEmailAddr + " zu senden...");
			jobLogger.debug("Versuche die Ergebnisse an " + tuspEmailAddr + " zu senden...");

			mailUtils.sendHTMLMail(tuspEmailAddr, FROM_ADDR, subjectAdmin, addrBody);

			logger.debug("Ergebniss Email erfolgreich an tusp@tuhh.de gesendet!");
			jobLogger.debug("Ergebniss Email erfolgreich an tusp@tuhh.de gesendet!");

		} catch (AddressException e) {
			logger.debug("Fehler biem senden der Ergebniss E-Mail an Admin: " + e.getMessage());
		} catch (MessagingException e) {
			logger.debug("Fehler biem senden der Ergebniss E-Mail an Admin: " + e.getMessage());
		}

	}

}
