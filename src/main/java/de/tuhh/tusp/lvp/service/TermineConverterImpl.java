package de.tuhh.tusp.lvp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.lvp.dao.TermineColumns;
import de.tuhh.tusp.lvp.pojo.Termine;

@Component
public class TermineConverterImpl implements TermineConverter {

	private static Logger logger = LogManager.getLogger(TermineConverterImpl.class);

	@Override
	public Map<TermineColumns, String> termineToMap(Termine termine) {

		Map<TermineColumns, String> data = new HashMap<>();

		data.put(TermineColumns.lv_nr_s, termine.getLv_nr_s().toString());
		data.put(TermineColumns.gbd_s, termine.getGbd_s());
		data.put(TermineColumns.raum_s, termine.getRaum_s());

		if (null == termine.getDbeg()) {
			data.put(TermineColumns.dbeg, "");
		} else {
			data.put(TermineColumns.dbeg, termine.getDbeg().toString());
		}
		if (null == termine.getDend()) {
			data.put(TermineColumns.dend, "");
		} else {
			data.put(TermineColumns.dend, termine.getDend().toString());
		}
		if (null == termine.getBeginn()) {
			data.put(TermineColumns.beginn, "");
		} else {
			data.put(TermineColumns.beginn, termine.getBeginn().toString());
		}
		if (null == termine.getEnde()) {
			data.put(TermineColumns.ende, "");
		} else {
			data.put(TermineColumns.ende, termine.getEnde().toString());
		}

		logger.debug("Termine Data Converted");

		return data;

	}
}
