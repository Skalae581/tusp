package de.tuhh.tusp.utils;

import java.util.List;

public interface MessageCollector {

	String MESSAGE_RECIEVER_MAILADDRESS_CONFIG_KEY = "de.tuhh.tusp.utils.MessageCollector.SystemMailRecieverAddress";

	/**
	 * Erhöht den Zähler für erstellte Datensätze um eins
	 */
	void addCreated();

	/**
	 * Erhöht den Zähler für gelöschte Datensätze um eins
	 */
	void addDeleted();

	/**
	 * Fügt einen fehlögeschlagenen Datensatz hinzu
	 *
	 * @param message
	 */
	void addErrorMessage(String message);

	/**
	 * Fügt einen fehlögeschlagenen Datensatz hinzu
	 *
	 * @param message
	 */
	void addErrorMessage(String message, Throwable exception);

	/**
	 * Fügt einen erfolgreichen Datensatz hinzu
	 *
	 * @param message
	 */
	void addOkMessage(String message);

	/**
	 * Erhöht den Zähler für aktualisierte Datensätze um eins
	 */
	void addUpdated();

	/**
	 * Fügt einen DAtensatz mit Warnung hinzu
	 *
	 * @param message
	 */
	void addWarnMessage(String message);

	/**
	 *
	 * @return num Created
	 */
	int getCreated();

	/**
	 *
	 * @return num Deleted
	 */
	int getDeleted();

	/**
	 *
	 * @return num Errors
	 */
	List<String> getErrors();

	/**
	 *
	 * @return num Updated
	 */
	int getUpdated();

	/**
	 * Setzt die Listen zurück
	 */
	void reset();

	/**
	 * Sendet eine Mail mit allen Meldungen
	 */
	void sendAll(String additionalReciever);

	/**
	 * Sendet eine Mail mit allen Fehlern
	 */
	void sendErrors(String additionalReciever);

	/**
	 * Sendet eine Mail mit allen Warnungen und Fehlern
	 */
	void sendWarningsAndErrors(String additionalReciever);
}
