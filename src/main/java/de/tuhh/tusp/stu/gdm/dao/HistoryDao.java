package de.tuhh.tusp.stu.gdm.dao;

import java.util.List;

import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.utils.BaseDao;

public interface HistoryDao extends BaseDao<History> {

	/**
	 * Holt alle History-Eintrage eines Dateinamens
	 *
	 * @param name
	 * @return Die History
	 */
	List<History> findByFilename(String name);
}
