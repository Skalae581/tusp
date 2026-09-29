package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;

public interface OrgUnitDao extends BaseDao<OrgUnit> {

	/**
	 * Holt eine {@link OrgUnit} anhand des deutschen ShortText
	 *
	 * @param name
	 * @return das Objekt
	 */
	OrgUnit fetchByShorttextDe(String name);

	/**
	 * Holt eine {@link OrgUnit} anhand des Uniquename
	 *
	 * @param name
	 * @return das Objekt
	 */
	OrgUnit fetchByUniquename(String ouUniquename);

}
