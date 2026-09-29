package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Addresstag;

public interface AddresstagDao extends BaseDao<Addresstag> {

	/**
	 * Holt ein Addresstag anhand seines Uniquename
	 *
	 * @param uniqueName
	 * @return das Addresstag oder null
	 */
	Addresstag fetchByUniquename(String uniqueName);

}
