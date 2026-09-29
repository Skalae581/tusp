package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedLog;

public interface LogService {

	/**
	 * Holt die Logeinträge eines Vorgangs
	 *
	 * @param vorgangId
	 * @return List absteigend sortiert
	 */
	List<ExtendedLog> findByVorgang(Long vorgangId);

	/**
	 * Protokolliert eine beliebige Nachricht
	 * 
	 * @param message
	 * @param vorgangId
	 */
	void logChange(String message, Long vorgangId);

	/**
	 * Protokolliert einen Statusübergang
	 *
	 * @param from
	 * @param to
	 */
	void logStatusChange(Status from, Status to, Long vorgangId);

	/**
	 * Protokolliert eine Änderung eines Feldes
	 *
	 * @param input
	 * @param value
	 */
	void logValueChange(Input input, Value value);
}
