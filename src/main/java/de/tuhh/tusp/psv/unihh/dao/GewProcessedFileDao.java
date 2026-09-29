package de.tuhh.tusp.psv.unihh.dao;

import de.tuhh.tusp.psv.unihh.pojo.GewProcessedFile;
import de.tuhh.tusp.utils.BaseDao;

public interface GewProcessedFileDao extends BaseDao<GewProcessedFile> {

	/**
	 * Holt einen Datensatz anhand des Dateinamens
	 *
	 * @param name
	 * @return Der Datensatz
	 */
	GewProcessedFile fetchByInName(String name);
}
