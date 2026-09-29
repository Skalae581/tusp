package de.tuhh.tusp.stu.tuhhstgs.utils;

import java.util.Map;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.dao.StgColumns;
import de.tuhh.tusp.stu.tuhhstgs.pojo.Stg;

public interface StgConverter {

	/**
	 * convertiere daten für die csv
	 * 
	 * @param stg
	 * @param jobLogger
	 * @return
	 */
	Map<StgColumns, String> stgToMap(Stg stg, JobLogger jobLogger);

}
