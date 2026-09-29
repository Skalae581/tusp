package de.tuhh.tusp.global.dao;

import java.util.List;

import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.utils.BaseDao;

public interface IdentityDao extends BaseDao<Identity> {

	/**
	 * Holt alle gültigen Identities
	 * 
	 * @return Liste der Identities
	 */
	List<Identity> findValid();

}
