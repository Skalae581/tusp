package de.tuhh.tusp.app.vorabcheck.service;

import java.util.Date;
import java.util.List;

import de.tuhh.tusp.app.vorabcheck.dao.BewerberDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.utils.CrudService;

public interface BewerberService extends CrudService<Bewerber> {

	/**
	 * @see BewerberDao
	 *
	 * @return
	 */
	List<String> fetchAllCodenummer();

	/**
	 * @see BewerberDao
	 *
	 * @return
	 */
	Bewerber fetchByCode(String bewHash);

	/**
	 * @see BewerberDao
	 *
	 * @return
	 */
	Object findStgNameByCode(String bewHash);

	/**
	 * Generiert eine zufällige, 12stellige Codenummer mithilfe zweier Parametern
	 *
	 * @param stg
	 * @param zeitstempel
	 * @return Codenummer
	 */
	String hashCode(String stg, Date zeitstempel);

	/**
	 * Generiert eine zufällige, 12stellige Codenummer mithilfe dreier Parametern
	 *
	 * @param mailaddr
	 * @param stg
	 * @param zeitstempel
	 * @return Codenummer
	 */
	String hashCode(String mailaddr, String stg, long zeitstempel);

}
