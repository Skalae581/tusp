package de.tuhh.tusp.saeulen.service;

import javax.servlet.http.HttpServletRequest;

public interface MailService {

	/**
	 * E-Mail senden für die Zustimmungen an alle Benutzer die zustimmen sollen In
	 * der Email sind 2 Urls vorhanden 1. Zustimmen 2. Zustimmung Ablehnen
	 *
	 * @param emailAddr
	 * @param request
	 * @param aktualisierungUID
	 * @param currentBenutzer
	 */
	void sendEmail(String emailAddr, HttpServletRequest request, Long aktualisierungUID, String currentBenutzer,
			String acceptUrlPath, String notAcceptURLPath);

}
