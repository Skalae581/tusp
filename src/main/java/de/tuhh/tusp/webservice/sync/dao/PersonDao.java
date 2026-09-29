package de.tuhh.tusp.webservice.sync.dao;

import java.util.Date;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Person;

public interface PersonDao extends BaseDao<Person> {

	/**
	 * Gibt die Zeit des zuletzt aktualisierten Datensatzes zurück. Gibt es kein Datensatz mit einem UpdatedAt-Wert,
	 * wird der null zurückgegeben.
	 *
	 * Achtung: gemeint ist: zuletzt in HIO aktualisiert
	 *
	 * @return den Aktualisierungszeitpunkt
	 */
	Date getMaxUpdated();
}
