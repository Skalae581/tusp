package de.tuhh.tusp.webservice.sync.service;

import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;

public interface OrgUnitService extends WebserviceSync {

	/**
	 * Liefert eine {@link OrgUnit} anhand ihres ShorttextDe
	 *
	 * @param name
	 * @return die OU oder null, wenn sie nicht existiert
	 */
	OrgUnit fetchByShorttextDe(String name);

	/**
	 * Liefert eine {@link OrgUnit} anhand ihres Uniquename
	 *
	 * @param name
	 * @return die OU oder null, wenn sie nicht existiert
	 */
	OrgUnit fetchByUniquename(String ouUniquename);
}
