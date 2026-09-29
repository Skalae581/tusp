package de.tuhh.tusp.td.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.td.dao.TdColumns;
import de.tuhh.tusp.td.pojo.Td;

@Component
public class TdConverterImpl implements TdConverter {

	private static Logger logger = LogManager.getLogger(TdConverterImpl.class);

	@Override
	public Map<TdColumns, String> tdToMap(Td td, JobLogger jobLogger) {

		Map<TdColumns, String> data = new HashMap<>();

		data.put(TdColumns.lv_nr_s, td.getLv_nr_s().toString() == null ? "" : td.getLv_nr_s().toString());
		data.put(TdColumns.titel, td.getTitel() == null ? "" : td.getTitel());
		data.put(TdColumns.nutzer, td.getNutzer() == null ? "" : td.getNutzer());
		data.put(TdColumns.datum, td.getDbeg() == null ? "" : td.getDbeg());
		data.put(TdColumns.startTime, td.getBeginn() == null ? "" : td.getBeginn());
		data.put(TdColumns.endTime, td.getEnde() == null ? "" : td.getEnde());
		data.put(TdColumns.day, td.getDay() == null ? "" : td.getDay());
		data.put(TdColumns.month, td.getMonth() == null ? "" : td.getMonth());
		data.put(TdColumns.roomName, td.getRaum_s() == null ? "" : td.getRaum_s());

		logger.debug("Technische-Dienst Data Converted");
		jobLogger.debug("Technische-Dienst Data Converted");

		return data;

	}

}
