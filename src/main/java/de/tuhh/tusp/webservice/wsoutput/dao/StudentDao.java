package de.tuhh.tusp.webservice.wsoutput.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

public interface StudentDao extends BaseDao<Student> {

	/**
	 * Holt die His PersonId für einen Studenten
	 *
	 * @param mtknr
	 *            Matrikelnummer der UniHH
	 * @return Die HisId oder null wenn nicht gefunden
	 */
	Long getPersonIdFromMtknrId(Long mtknr);

}
