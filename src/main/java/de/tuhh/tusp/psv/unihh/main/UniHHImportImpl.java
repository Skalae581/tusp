package de.tuhh.tusp.psv.unihh.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.unihh.dao.UniHHPersonDao;
import de.tuhh.tusp.psv.unihh.dao.UniHHStudDao;
import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;
import de.tuhh.tusp.psv.unihh.service.UniHHPersonService;
import de.tuhh.tusp.psv.unihh.util.CSVDownload;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;

@Component
public class UniHHImportImpl implements UniHHImport, Schedulable {

	private static Logger logger = LogManager.getLogger(UniHHImport.class);

	private static final String TM_IMPORT_ENABLED_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerImportEnabled";
	private static final String GL_IMPORT_ENABLED_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerImportEnabled";
	private static final String GL_PERSON_FILE_NAME_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerPersonFileName";
	private static final String GL_PERSON_DELIMITER_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerPersonDelimiter";
	private static final String GL_PERSON_CHARSET_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerPersonCharset";
	private static final String GL_STUDENT_FILE_NAME_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerStudentFileName";
	private static final String GL_STUDENT_DELIMITER_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerStudentDelimiter";
	private static final String GL_STUDENT_CHARSET_KEY = "de.tuhh.tusp.psv.unihh.GewerbelehrerStudentCharset";
	private static final String TM_PERSON_FILE_NAME_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerPersonFileName";
	private static final String TM_PERSON_DELIMITER_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerPersonDelimiter";
	private static final String TM_PERSON_CHARSET_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerPersonCharset";
	private static final String TM_STUDENT_FILE_NAME_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerStudentFileName";
	private static final String TM_STUDENT_DELIMITER_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerStudentDelimiter";
	private static final String TM_STUDENT_CHARSET_KEY = "de.tuhh.tusp.psv.unihh.TechnomathematikerStudentCharset";

	public static final String MESSAGE_COLLECTOR_NAME = "Gewerbelehrer-und-Technomathematiker-Import";

	@Autowired
	private UniHHPersonDao uniHHPersonDao;

	@Autowired
	private UniHHStudDao uniHHStudntDao;

	@Autowired
	private UniHHPersonService uniHHPersonSerivce;

	@Autowired
	private CSVDownload csvDownload;

	@Autowired
	private ConfigService config;

	@Override
	public void importGewerbelehrer(Map<String, Resource> files) throws FileNotFoundException, IOException {

		String glPersonFileName = config.get(GL_PERSON_FILE_NAME_KEY, "TUHH_Studierenden_Daten.csv", true);
		String glPersonDelimiter = config.get(GL_PERSON_DELIMITER_KEY, ";", true);
		Charset glPersonCharset = Charset.forName(config.get(GL_PERSON_CHARSET_KEY, "utf-8", true));
		String glStudentFileName = config.get(GL_STUDENT_FILE_NAME_KEY, "TUHH_Studien_Daten.csv", true);
		String glStudentDelimiter = config.get(GL_STUDENT_DELIMITER_KEY, ";", true);
		Charset glStudentCharset = Charset.forName(config.get(GL_STUDENT_CHARSET_KEY, "utf-8", true));

		if (glPersonFileName == null || glPersonFileName.isEmpty() || glPersonDelimiter == null) {
			logger.warn("Der Import für die Gewerbelehrer ist nicht richtig konfiguriert!");
			return;
		}

		logger.info("Gewerbelehrer Daten werden importiert...");
		List<UniHHPerson> gwPersonList = uniHHPersonDao.readPersonData(files.get(glPersonFileName),
				glPersonDelimiter.charAt(0), glPersonCharset);
		List<UniHHStud> gwStudList = uniHHStudntDao.readStudData(files.get(glStudentFileName),
				glStudentDelimiter.charAt(0), glStudentCharset);
		uniHHPersonSerivce.importHHData(gwPersonList, gwStudList, this);
		logger.info("Gewerbelehrer Daten wurden erfolgreich importiert!");
	}

	@Override
	public void importTechnomathematiker(Map<String, Resource> files) throws FileNotFoundException, IOException {

		String tmPersonFileName = config.get(TM_PERSON_FILE_NAME_KEY, "TUHH_Technomathe_StudierendenDaten.csv", true);
		String tmPersonDelimiter = config.get(TM_PERSON_DELIMITER_KEY, ";", true);
		Charset tmPersonCharset = Charset.forName(config.get(TM_PERSON_CHARSET_KEY, "utf-8", true));
		String tmStudentFileName = config.get(TM_STUDENT_FILE_NAME_KEY, "TUHH_Technomathe_StudienDaten.csv", true);
		String tmStudentDelimiter = config.get(TM_STUDENT_DELIMITER_KEY, ";", true);
		Charset tmStudentCharset = Charset.forName(config.get(TM_STUDENT_CHARSET_KEY, "utf-8", true));

		if (tmPersonFileName == null || tmPersonFileName.isEmpty() || tmPersonDelimiter == null) {
			logger.warn("Der Import für die Technomathematiker ist nicht richtig konfiguriert!");
			return;
		}

		logger.info("Technomathematiker Daten werden importiert...");
		List<UniHHPerson> tmPersonList = uniHHPersonDao.readPersonData(files.get(tmPersonFileName),
				tmPersonDelimiter.charAt(0), tmPersonCharset);
		List<UniHHStud> tmStudList = uniHHStudntDao.readStudData(files.get(tmStudentFileName),
				tmStudentDelimiter.charAt(0), tmStudentCharset);
		uniHHPersonSerivce.importHHData(tmPersonList, tmStudList, this);
		logger.info("Technomathematiker Daten wurden erfolgreich importiert!");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		logger.info("Starte UniHH Import...");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Map<String, Resource> files = csvDownload.download();

			MessageCollector mc = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, jobLogger);

			if (!"true".equals(config.get(GL_IMPORT_ENABLED_KEY, "false", true))) {
				importGewerbelehrer(files);
			}
			if (!"true".equals(config.get(TM_IMPORT_ENABLED_KEY, "false", true))) {
				importTechnomathematiker(files);
			}
			mc.sendWarningsAndErrors(null);
		} catch (IOException e) {

			logger.error("Daten Import Fehlgeschlagen!", e);
			throw new RuntimeException(e);
		} catch (Exception e) {

			logger.error("UHH-Import fehlgeschlagen, Daten nicht abrufbar", e);
			throw new RuntimeException(e);
		} finally {
			stopWatch.stop();
			logger.info("UniHH Import abgeschlossen nach " + stopWatch.getTime() / 1000 + "sec");

		}
	}

	@Override
	public String suggestName() {

		return "UniHamburg und GewLehrer " + CalendarUtils.dateToDatumsString(new Date());
	}

}
