package de.tuhh.tusp.app.fame.service;

import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.CrudService;

public interface ValueService {

	/**
	 * Löscht den Datensatz
	 *
	 * @param valueId
	 * @return num datensätze
	 */
	int delete(Long valueId);

	/**
	 * {@link CrudService#fetchById(Long)}
	 *
	 * @param id
	 * @return das Objetk
	 */
	Value fetchById(Long id);

	/**
	 * Macht ein Insert oder Update und gibt die ID das Datensatzes zurück
	 *
	 * @param input
	 * @param value
	 * @param connectorReadonly
	 *            Wenn Wert auf true geseztz ist werden Felder aus dem Connector nicht überschrieben.
	 * @return
	 * @throws PflichtfeldException
	 *             Wenn ein Pflichtfeld nicht ausgefüllt ist
	 * @throws RegexException
	 *             Wenn die Eingabe nicht dem vorgegebenen Muster entspricht
	 */
	Long save(ExtendedInput input, String value, boolean connectorReadonly) throws PflichtfeldException, RegexException;

	void setLetzteBearbeitung(Long vorgangId);

	void setLetzterBearbeiter(Long vorgangId);
}
