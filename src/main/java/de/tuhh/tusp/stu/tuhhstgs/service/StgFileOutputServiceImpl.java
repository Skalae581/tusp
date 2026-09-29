package de.tuhh.tusp.stu.tuhhstgs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.dao.StgHistoryDao;
import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;

@Component
public class StgFileOutputServiceImpl implements StgFileOutputService {

	private static Logger logger = LogManager.getLogger(StgFileOutputServiceImpl.class);

	private static final String FILE_PREFIX = "studiengaenge_";

	private static final String FILE_FORMAT = ".csv";

	@Autowired
	private SshUtils sshUtils;

	@Autowired
	private ConfigService config;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Autowired
	private StgHistoryDao historyDao;

	@Autowired
	private ApplicationContext applicationContext;

	// TODO test local
	@Override
	public Resource getFileByName(String name)
			throws IOException, SecurityException, FileNotFoundException, RuntimeException {

		String loadDir = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remotePath", "", true);
		// String loadDir = "/home/rztjj/git/tusp-data/stgs/"; // local test

		if (loadDir.isEmpty()) {
			throw new RuntimeException(
					"Studiengänge-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.stu.stgs.service.fileOutput.* ");
		}

		Resource resource = applicationContext.getResource("file:" + loadDir + name + FILE_FORMAT);

		List<String> lakaleDateien = new ArrayList<>();
		for (Resource res : listLokaleDateien()) {
			lakaleDateien.add(res.getFilename());
		}
		if (!lakaleDateien.contains(resource.getFilename())) {
			logger.warn("Versuchte Pfadmanipulation erkannt! " + resource
					+ " sollte als STG-Datei heruntergeladen werden. Erlaubt sind: " + lakaleDateien);
			throw new SecurityException("Pfad nicht erlaubt! " + resource);
		}

		if (!resource.exists()) {
			throw new FileNotFoundException(resource.toString());
		}

		// Protokollierung des Downloads
		StgHistory eintrag = new StgHistory();
		eintrag.setFilename(resource.getFilename());
		eintrag.setZeit(new Date());
		eintrag.setUsername(authorityUtils.getCurrentUserName());
		historyDao.insert(eintrag);

		return resource;
	}

	// TODO test local
	@Override
	public List<Resource> listLokaleDateien() throws IOException, RuntimeException {

		String loadDir = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remotePath", "", true);
		// String loadDir = "/home/rztjj/git/stgs/"; // local test

		if (loadDir.isEmpty()) {

			logger.debug(loadDir + " ist leer");
			throw new RuntimeException(
					"Studiengänge-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.stu.stgs.service.fileOutput.* ");
		}

		String resourceSearchUri = "file:/" + loadDir + FILE_PREFIX + "*" + FILE_FORMAT;
		logger.debug("Suche Dateien in: " + resourceSearchUri);

		// resources
		List<Resource> stgsFiles = new ArrayList<>();
		Resource[] files = applicationContext.getResources(resourceSearchUri);

		for (Resource resource : files) {
			stgsFiles.add(resource);
		}
		Collections.sort(stgsFiles, (Resource r1, Resource r2) -> r2.getFilename().compareTo(r1.getFilename()));
		return stgsFiles;

	}

	// TODO use later
	@Override
	public WritableResource writeFile(Document document, JobLogger jobLogger) {

		int port = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.port", 22, true);
		String user = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.user", "", true);
		String remoteHosts = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remoteHosts", "", true);
		String remotePath = config.get("de.tuhh.tusp.stu.stgs.service.fileOutput.remotePath", "", true);

		jobLogger.debug("port: " + port);
		jobLogger.debug("user: " + user);
		jobLogger.debug("remoteHosts: " + remoteHosts);
		jobLogger.debug("remotePath: " + remotePath);

		if (user.isEmpty() || remoteHosts.isEmpty() || remotePath.isEmpty()) {
			throw new RuntimeException(
					"Studiengänge-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.stu.stgs.service.fileOutput.* ");
		}

		MessageCollector collector = MessageCollectorImpl.getInstance(Thread.currentThread());
		WritableResource inFile = new InMemoryResource(
				FILE_PREFIX + CalendarUtils.dateToDatumsString(new Date(), Locale.ENGLISH) + FILE_FORMAT);

		// Dokument auf Zielsysteme verteilen
		String[] hosts = remoteHosts.split(",");
		for (String host : hosts) {
			try {
				SshSession ssh = sshUtils.getSession(user, host, port);
				ssh.copyToRemote(inFile, remotePath + inFile.getFilename());
				ssh.close();
				jobLogger.error("Datei wurde auf " + host + " erforgreich übertragen!");
			} catch (Exception e) {
				String message = "Konnte Studiengänge-Dokument nicht an " + host + " übertragen";
				logger.error(message, e);
				jobLogger.error(message);
				collector.addErrorMessage(message + ": " + e.getMessage());
			}
		}

		return inFile;

	}

}
