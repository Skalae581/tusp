package de.tuhh.tusp.psv.unihh.dao;

import java.util.List;

import de.tuhh.tusp.psv.unihh.pojo.GewHistory;
import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.utils.BaseDao;

public interface GewHistoryDao extends BaseDao<GewHistory> {

	/**
	 * Holt alle History-Eintrage eines Dateinamens
	 *
	 * @param name
	 * @return Die History
	 */
	List<History> findByFilename(String name);
}
