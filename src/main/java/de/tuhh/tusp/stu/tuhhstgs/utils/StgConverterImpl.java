package de.tuhh.tusp.stu.tuhhstgs.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.dao.StgColumns;
import de.tuhh.tusp.stu.tuhhstgs.pojo.Stg;

@Component
public class StgConverterImpl implements StgConverter {

	private static Logger logger = LogManager.getLogger(StgConverterImpl.class);

	@Override
	public Map<StgColumns, String> stgToMap(Stg stg, JobLogger jobLogger) {

		Map<StgColumns, String> data = new HashMap<>();

		logger.debug("TuhhStg Daten to Map!");
		jobLogger.debug("TuhhStg Daten to Map!");

		data.put(StgColumns.stg_abschluss, stg.getAbschluss() == null ? "" : stg.getAbschluss());
		data.put(StgColumns.stg_name, stg.getStgName() == null ? "" : stg.getStgName());
		data.put(StgColumns.stg_unique_name, stg.getStgUname() == null ? "" : stg.getStgUname());

		logger.debug("Data resultat:  " + data);
		jobLogger.debug("Data resultat:  " + data);
		logger.debug("TUHH-Studiengänge Data Converted");
		jobLogger.debug("TUHH-Studiengänge Data Converted");

		return data;

	}

}
