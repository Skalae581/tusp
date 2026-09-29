package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Eaddresstype;

public interface EaddresstypeDao extends BaseDao<Eaddresstype> {

	/**
	 * Holt einen {@link Eaddresstype} anhand seines Uniquename
	 * 
	 * @param string
	 * @return der Eaddresstype oder null
	 */
	Eaddresstype fetchByUniquename(String string);

}
