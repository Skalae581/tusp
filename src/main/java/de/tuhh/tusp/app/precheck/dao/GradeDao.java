package de.tuhh.tusp.app.precheck.dao;

import de.tuhh.tusp.app.precheck.pojo.CountryGrades;
import de.tuhh.tusp.utils.BaseDao;

public interface GradeDao extends BaseDao<CountryGrades> {

	/**
	 * Holt die Notenabweichung für ein Land
	 *
	 * @param id
	 * @return Abweichung
	 */
	Float getGradeForCountry(Long id);

}
