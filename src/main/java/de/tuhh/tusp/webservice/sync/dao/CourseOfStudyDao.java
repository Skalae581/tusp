package de.tuhh.tusp.webservice.sync.dao;

import java.util.List;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;

public interface CourseOfStudyDao extends BaseDao<CourseOfStudy> {

	/**
	 * Sucht CourseOfStudys
	 *
	 * @param stg
	 * @param abschl
	 * @return Liste der Treffer
	 */
	List<CourseOfStudy> findByStgUndAbschl(String stg, String abschl);
}
