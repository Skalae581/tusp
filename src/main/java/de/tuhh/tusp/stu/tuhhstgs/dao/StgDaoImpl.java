package de.tuhh.tusp.stu.tuhhstgs.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.courseofstudyservice.CourseOfStudy;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.pojo.Stg;
import de.tuhh.tusp.stu.tuhhstgs.utils.StgConverter;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.wsoutput.dao.HisCourseOfStudyHelperDaoImpl;

@Component
public class StgDaoImpl implements StgDao {

	private static Logger logger = LogManager.getLogger(StgDaoImpl.class);

	@Autowired
	private HisCourseOfStudyHelperDaoImpl hisCosHelperDao;
	@Autowired
	private CsvWriter csvWriter;
	@Autowired
	private StgConverter stgConverter;

	private List<Stg> getAllStgs(JobLogger jobLogger) throws ServiceFault_Exception {

		List<Long> ids = hisCosHelperDao.getAllCourseOfStudiesIds();
		List<Stg> tuhhStgs = new ArrayList<>();

		for (Long id : ids) {

			try {
				CourseOfStudy cos = hisCosHelperDao.getCourseOfStudyById(id);
				if (null != cos) {

					logger.debug("TUHH Studiengang: " + cos.getShorttext() + " gefunden! Name: " + cos.getUniquename());
					jobLogger.debug(
							"TUHH Studiengang: " + cos.getShorttext() + " gefunden! Name: " + cos.getUniquename());

					if (cos.getUniquename().indexOf('|') != -1) {

						// Studiengang Object
						Stg stg = new Stg();

						String uniquename = cos.getUniquename();
						String degree = uniquename.substring(0, 2);

						stg.setAbschluss(degree);

						stg.setStgName(cos.getShorttext());
						stg.setStgUname(uniquename);
						tuhhStgs.add(stg);
					}

					else {
						logger.debug("Studiengang: " + cos.getShorttext() + " übersprungen");
						jobLogger.debug("Studiengang: " + cos.getShorttext() + " übersprungen");
					}

				} else {
					logger.debug("Studiengang ID: " + id + " NULL");
					jobLogger.debug("Studiengang ID: " + id + " NULL");
					continue;
				}
			} catch (Exception ex) {
				logger.debug("Fehler bei Studengang! Weitermachen...");
				continue;
			}

		}

		jobLogger.debug("Gefundene Studiengänge: " + ids.size());
		jobLogger.debug("TUHH Studiengänge: " + tuhhStgs.size());

		return tuhhStgs;

	}

	@Override
	public List<Map<StgColumns, String>> getCourseOfStudiesData(JobLogger jobLogger) throws ServiceFault_Exception {
		List<Map<StgColumns, String>> result = new ArrayList<>();

		List<Stg> stgList = getAllStgs(jobLogger);

		logger.debug("Konvertiere TuhhStg- Daten...");
		jobLogger.debug("Konvertiere TuhhStg- Daten...");

		for (Stg stg : stgList) {

			try {
				logger.debug("TuhhStg-Convertieren: " + stg.getStgUname());
				result.add(stgConverter.stgToMap(stg, jobLogger));
			} catch (Exception e) {
				logger.error(stg, e);
				jobLogger.debug("Etwas ist schief gelaufen! " + e.getMessage());

			}

		}

		return result;

	}

	@Override
	public void writeStgs(List<Map<StgColumns, String>> stgs, WritableResource resource, JobLogger jobLogger)
			throws IOException, NullPointerException {

		logger.debug("Studiangangs-Daten werden in die .csv Datei geschreiben");
		jobLogger.debug("Studiangangs-Daten werden in die .csv Datei geschreiben");

		csvWriter.write(resource, stgs, Charset.forName("UTF-8"), StgColumns.class, ';', false, QuoteMode.MINIMAL);

	}

}
