package de.tuhh.tusp.saeulen.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.tuhh.tusp.utils.MailUtils;

@Component
public class MailServiceImpl implements MailService {

	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);

	private final static String FROM_ADDR = "noreply@tu-harburg.hamburg.de";
	private final static String SUBJECT = "Zustimmung für die Säuleaktualisierung\n";

	@Autowired
	private MailUtils mailUtils;

	/**
	 * E-Mail senden für die Zustimmungen an alle Benutzer die zustimmen sollen In
	 * der Email sind 2 Urls vorhanden 1. Zustimmen 2. Zustimmung Ablehnen
	 *
	 * @param emailAddr
	 * @param request
	 * @param aktualisierungUID
	 * @param currentBenutzer
	 */
	@Override
	public void sendEmail(String emailAddr, HttpServletRequest request, Long aktualisierungUID, String currentBenutzer,
			String acceptUrlPath, String notAcceptURLPath) {

		String from = FROM_ADDR;
		String subject = SUBJECT;

		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
		String acceptUrl = baseUrl + acceptUrlPath + aktualisierungUID + "&uname=" + currentBenutzer;

		String notAcceptURL = baseUrl + notAcceptURLPath + aktualisierungUID + "&uname=" + currentBenutzer;

		logger.debug("URL: " + acceptUrl);
		logger.debug("Not Accept URL: " + notAcceptURL);

		String body = "<p>Bitte ZUSTIMMEN für die Aktualisierung hier:</p>";

		body += "<a href='" + acceptUrl + "'>" + acceptUrl + " </a>";
		body += "<br>";
		body += "<p>Wenn Sie NICHT zustimmen wollen klicken hier: </p>";
		body += "<a href='" + notAcceptURL + "'>" + notAcceptURL + " </a>";

		logger.debug("Email Body: " + body);

		// send mail
		try {
			mailUtils.sendHTMLMail(emailAddr, from, subject, body);
			logger.debug("Mail gesendet an " + emailAddr);
		} catch (AddressException e) {
			logger.debug("Fehler biem senden der Mail: " + e.getMessage());
		} catch (MessagingException e) {
			logger.debug("Fehler biem senden der Mail: " + e.getMessage());

		}
	}

}
