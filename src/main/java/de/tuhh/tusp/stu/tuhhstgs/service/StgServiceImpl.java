package de.tuhh.tusp.stu.tuhhstgs.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.dao.StgColumns;
import de.tuhh.tusp.stu.tuhhstgs.dao.StgDao;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;

@Component
public class StgServiceImpl implements StgService {

	private static Logger logger = LogManager.getLogger(StgServiceImpl.class);

	private static final String FILE_PREFIX = "studiengaenge_";

	private static final String FILE_FORMAT = ".csv";

	@Autowired
	private SshUtils sshUtils;

	@Autowired
	private StgDao stgDao;

	@Autowired
	private ConfigService config;

	@Override
	public WritableResource exportStgs(MessageCollector collector, JobLogger jobLogger) {

		jobLogger.debug("TUHH Studiengänge Service Gestartet......");

		try {

			List<Map<StgColumns, String>> stgs;
			try {
				stgs = stgDao.getCourseOfStudiesData(jobLogger);
			} catch (ServiceFault e) {
				collector.addErrorMessage("Konnte TuhhStgs nicht abrufen: " + e.getMessage());
				logger.error("Konnte TuhhStgs nicht abrufen", e);
				collector.sendWarningsAndErrors(e.getMessage());
				return null;
			}

			///////////////////////////////////////////////////////////////////////

//			int port = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.port", 22, true);
//			String user = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.user", "", true);
//			String remoteHosts = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remoteHosts", "", true);
//			String remotePath = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remotePath", "", true);
//
//			jobLogger.debug("port: " + port);
//			jobLogger.debug("user: " + user);
//			jobLogger.debug("remoteHosts: " + remoteHosts);
//			jobLogger.debug("remotePath: " + remotePath);

//			if (user.isEmpty() || remoteHosts.isEmpty() || remotePath.isEmpty()) {
//				throw new RuntimeException(
//						"Studiengänge-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.stu.stgs.service.fileOutput.* ");
//			}

			// füge Zeit zu Dateiname hinzu
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			String time = formatter.format(date);
			WritableResource res = new InMemoryResource(FILE_PREFIX
					+ CalendarUtils.dateToDatumsString(new Date(), Locale.ENGLISH) + "_" + time + FILE_FORMAT);

			// schreibe in die Datei
			try {
				stgDao.writeStgs(stgs, res, jobLogger);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

//			// Dokument auf Zielsysteme verteilen
//			String[] hosts = remoteHosts.split(",");
//			for (String host : hosts) {
//				try {
//					SshSession ssh = sshUtils.getSession(user, host, port);
//					ssh.copyToRemote(res, remotePath + res.getFilename());
//					ssh.close();
//					jobLogger.error("Datei wurde auf " + host + " erforgreich übertragen!");
//				} catch (Exception e) {
//					String message = "Konnte Studiengänge-Dokument nicht an " + host + " übertragen" + e.getMessage();
//					logger.error(message, e);
//					jobLogger.error(message);
//					collector.addErrorMessage(message + ": " + e.getMessage());
//				}
//			}

//local test
			String filePath = "/home/rztjj/git/tusp-data/stgs/";
			String fileName = FILE_PREFIX + CalendarUtils.dateToDatumsString(new Date(), Locale.ENGLISH) + "_" + time
					+ FILE_FORMAT;

			File newFile = new File(filePath + fileName);
			try {
				boolean success = newFile.createNewFile();

				if (success) {
					// Schreibe CSV
					// InMemoryResource res = new InMemoryResource("stgs.csv");
					res = new FileSystemResource(filePath + fileName);

					try {
						stgDao.writeStgs(stgs, res, jobLogger);
						return res;

					} catch (Exception ex) {
						jobLogger.debug(
								"Die Daten können nicht geschrieben werden, etwas ist falsch!!" + ex.getMessage());
					}

				} else {
					jobLogger.error("Die Datei kann nicht erstellt werden, Premissions Probleme!");

				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (ServiceFault_Exception e) {
			collector.addErrorMessage("Studiengänge Job Fehler " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
