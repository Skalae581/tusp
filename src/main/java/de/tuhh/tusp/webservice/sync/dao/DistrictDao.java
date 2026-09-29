package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.District;

public interface DistrictDao extends BaseDao<District> {

	/**
	 * Holt einen {@link District} anhand seines Uniquename
	 * 
	 * @param uniquename
	 * @return District or null if not exists
	 */
	District fetchByUniquename(String uniquename);

}
