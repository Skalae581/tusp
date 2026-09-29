package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.utils.BaseDao;

public interface EctsDao extends BaseDao<Ects> {

	/**
	 * Holt ECTS zum Bewerber anhand Bewerber-ID
	 *
	 * @param bewId
	 * @return Liste der ECTS
	 */
	List<Ects> fetchByBewId(Long bewId);

}
