package de.tuhh.tusp.webservice.sync.dao;

import java.util.NoSuchElementException;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Gender;

public interface GenderDao extends BaseDao<Gender> {

	/**
	 * Holt ein {@link Gender} Objekt anhand des eindeutigen Namen.
	 *
	 * @param string
	 * @return das Objekt
	 * @throws NoSuchElementException
	 *             Wenn ekin Objekt gefunden
	 */
	Gender fetchByUniqename(String string) throws NoSuchElementException;

	Gender fetchUniqenameById(Long id);

}
