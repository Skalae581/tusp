package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.Log;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedLog;
import de.tuhh.tusp.utils.BaseDao;

public interface LogDao extends BaseDao<Log> {

	/**
	 * Gibt die Log-Einträge eines Vorgangs absteigend sortiert zurück
	 * 
	 * @param vorgangId
	 * @return Liste der Einträge
	 */
	List<ExtendedLog> findByVorgang(Long vorgangId);
}
