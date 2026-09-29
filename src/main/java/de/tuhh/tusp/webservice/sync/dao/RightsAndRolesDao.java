package de.tuhh.tusp.webservice.sync.dao;

import java.util.NoSuchElementException;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

public interface RightsAndRolesDao extends BaseDao<RightsAndRoles> {

	/**
	 * Holt ein {@link RightsAndRoles} Objekt anhand des eindeutigen Namen
	 *
	 * @param name
	 * @return Das Objekt
	 * @throws NoSuchElementException
	 *             Wenn Kein Objekt existiert
	 */
	RightsAndRoles fetchByUniqename(String name) throws NoSuchElementException;

}
