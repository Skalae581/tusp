package de.tuhh.tusp.webservice.sync.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

public interface DegreeDao extends BaseDao<Degree> {

	/**
	 * Holt einen {@link Degree} anhand des Uniquename.
	 *
	 * @param uniquename
	 * @return Degree or null if not exists
	 */
	Degree fetchByUniquename(String uniquename);

	String fetchUniquenameById(Integer id);
}
