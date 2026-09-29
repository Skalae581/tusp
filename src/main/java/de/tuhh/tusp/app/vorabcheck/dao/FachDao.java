package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.utils.BaseDao;

public interface FachDao extends BaseDao<Fach> {

	/**
	 * Holt alle zugehörigen Fächer anhand einer Bereich-ID
	 *
	 * @param bereichIds
	 * @return Liste mit Fächern
	 */
	List<Fach> findAllByBereichId(Long bereichId);

	/**
	 * Holt alle zugehörigen Fächer anhand einer Liste von Bereich-IDs
	 *
	 * @param bereichIds
	 * @return Liste mit Fächern
	 */
	List<Fach> findAllByBereichIds(List<Long> bereichIds);

}
