package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.utils.BaseDao;

public interface InputBerechtigungDao extends BaseDao<InputBerechtigung> {

	/**
	 * Gibt true zurück, wenn der Benutzer das Feld in dem Vorgang lesen darf
	 *
	 * @param inputId
	 * @param statusId
	 * @param vorgangId
	 * @param roles
	 * @return hat recht?
	 */
	boolean canRead(Long inputId, Long statusId, Long vorgangId, List<String> roles);

	/**
	 * Löscht alle Berechtigungen die mit einem Input verbunden sind
	 *
	 * @param id
	 */
	void deleteByInput(Long id);

	/**
	 * Löscht alle Input-Berechtigungen, die mit einem Status verbunden sind
	 *
	 * @param id
	 */
	void deleteByStatus(Long id);

	/**
	 * Liefert die Berechtigungen eines Workflows
	 *
	 * @param id
	 * @return Liste der Berechtigungen
	 */
	List<InputBerechtigung> findByWorkflow(Long id);

}
