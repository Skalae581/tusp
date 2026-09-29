package de.tuhh.tusp.lvp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.lvp.dao.LvpColumns;
import de.tuhh.tusp.lvp.pojo.Lvp;

@Component
public class LvpConverterImpl implements LvpConverter {

	private static Logger logger = LogManager.getLogger(LvpConverterImpl.class);

	@Override
	public Map<LvpColumns, String> lvpToMap(Lvp lvp) {

		Map<LvpColumns, String> data = new HashMap<>();

		data.put(LvpColumns.lv_nr_s, lvp.getLv_nr_s().toString() == null ? "" : lvp.getLv_nr_s().toString());
		data.put(LvpColumns.titel, lvp.getTitel() == null ? "" : lvp.getTitel());
		data.put(LvpColumns.nutzer, lvp.getNutzer() == null ? "" : lvp.getNutzer());
		data.put(LvpColumns.lv_typ, lvp.getLv_typ() == null ? "" : lvp.getLv_typ());
		data.put(LvpColumns.bereich, lvp.getBereich() == null ? "" : lvp.getBereich());

		logger.debug("Lvp Data Converted");

		return data;

	}

}
