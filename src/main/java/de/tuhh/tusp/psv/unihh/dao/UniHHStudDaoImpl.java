package de.tuhh.tusp.psv.unihh.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.unihh.main.UniHHImportImpl;
import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.csv.CsvReader;

@Component
public class UniHHStudDaoImpl implements UniHHStudDao {

	enum StudData {
		mtknr, semester, stgnr, abschl, stg, kzfa, stgsem, status, beugrund, hrst, stuart, stutyp, stufrm
	}

	private static Logger logger = LogManager.getLogger(UniHHStudDaoImpl.class);

	@Autowired
	private CsvReader csvReader;

	@Override
	public List<UniHHStud> readStudData(Resource csv, Character delimiter, Charset charset)
			throws FileNotFoundException, IOException {

		List<UniHHStud> studList = new ArrayList<>();

		for (Map<Enum<?>, String> record : csvReader.readRecords(csv, StudData.class, delimiter, charset, true)) {
			UniHHStud stud = new UniHHStud();

			try {
				stud.setUhhMtknr(Long.parseLong(record.get(StudData.mtknr)));
				stud.setSemester(Integer.parseInt(record.get(StudData.semester)));
				stud.setStatus(record.get(StudData.status));
				String stgnr = record.get(StudData.stgnr);
				if (null == stgnr || stgnr.isEmpty()) {
					throw new RuntimeException("StudiengangNr fehlt für Mtknr: " + stud.getUhhMtknr());
				} else {
					stud.setStgnr(Integer.parseInt(stgnr));
				}

				stud.setAbschl(Integer.parseInt(record.get(StudData.abschl)));
				stud.setStgUnihhAstat(record.get(StudData.stg));
				stud.setKzfa(record.get(StudData.kzfa));
				stud.setStgsem(Integer.parseInt(record.get(StudData.stgsem)));
				stud.setStatus(record.get(StudData.status));
				stud.setBeugrund(record.get(StudData.beugrund));
				stud.setHrst(record.get(StudData.hrst));
				stud.setStuart(record.get(StudData.stuart));
				stud.setStutyp(record.get(StudData.stutyp));
				stud.setStufrm(Integer.parseInt(record.get(StudData.stufrm)));

				studList.add(stud);

			} catch (Exception ex) {
				String message = "Datensatzt für den Stud-Daten:" + "(" + record.get(StudData.mtknr) + ")"
						+ " ist fehlerhaft! Die Daten können nicht gelesen werden!";
				logger.debug(message, ex);
				MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null).addErrorMessage(message);
				continue;
			}
		}

		return studList;

	}
}
