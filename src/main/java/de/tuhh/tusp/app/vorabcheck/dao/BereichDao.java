package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.utils.BaseDao;

public interface BereichDao extends BaseDao<Bereich> {

	/**
	 * Holt alle Bereiche anhand der Stg-Id
	 *
	 * @param stgId
	 * @return Liste mit Bereichen
	 */
	List<Bereich> findAllByStgId(Long stgId);

	/**
	 * Holt alle IDs der Bereiche anhand der Stg-ID
	 *
	 * @param stgId
	 * @return Liste mit IDs (von Bereichen)
	 */
	List<Long> getBereichIdsById(Long stgId);

}
