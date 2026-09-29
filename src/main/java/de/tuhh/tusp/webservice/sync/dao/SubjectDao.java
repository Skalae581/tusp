package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Subject;

public interface SubjectDao extends BaseDao<Subject> {

	/**
	 * Holt ein Subject anhand des Uniquename
	 * 
	 * @param uniquename
	 * @return Subject oder null wenn nicht gefunden
	 */
	Subject fetchByUniquename(String uniquename);
}
