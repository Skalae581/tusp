package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.utils.BaseDao;

public interface BewerberDao extends BaseDao<Bewerber> {

	/**
	 * Holt alle existierenden Codenummern
	 *
	 * @return Liste von Codenummern
	 */
	List<String> fetchAllCodenummer();

	/**
	 * Holt einen Bewerber anhand der Codenummer
	 *
	 * @param bewHash
	 * @return spezifischer Bewerber
	 */
	Bewerber fetchByCode(String bewHash);

	/**
	 * Holt den Studiengangsnamen, der einer Codenummer zugeordnet ist
	 *
	 * @param bewHash
	 * @return Studiengangsname
	 */
	Object findStgNameByCode(String bewHash);

}
