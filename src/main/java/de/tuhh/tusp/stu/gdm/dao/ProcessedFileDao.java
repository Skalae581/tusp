package de.tuhh.tusp.stu.gdm.dao;

import de.tuhh.tusp.stu.gdm.pojo.ProcessedFile;
import de.tuhh.tusp.utils.BaseDao;

public interface ProcessedFileDao extends BaseDao<ProcessedFile> {

	/**
	 * Holt einen Datensatz anhand des Dateinamens
	 *
	 * @param name
	 * @return Der Datensatz
	 */
	ProcessedFile fetchByInName(String name);
}
