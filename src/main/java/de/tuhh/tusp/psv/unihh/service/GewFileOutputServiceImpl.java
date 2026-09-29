package de.tuhh.tusp.psv.unihh.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.psv.unihh.dao.GewHistoryDao;
import de.tuhh.tusp.psv.unihh.pojo.GewHistory;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;

@Component
public class GewFileOutputServiceImpl implements GewFileOutputService {

	private static final Logger logger = LogManager.getLogger(GewFileOutputServiceImpl.class);

	private static final String FILE_PREFIX = "gewerbelehrer_";

	private static final String FILE_FORMAT = ".txt";

	@Autowired
	private SshUtils sshUtils;

	@Autowired
	private ConfigService config;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Autowired
	private GewHistoryDao historyDao;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Resource getFileByName(String name)
			throws IOException, SecurityException, FileNotFoundException, RuntimeException {

		String loadDir = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.remotePath", "", true);

		if (loadDir.isEmpty()) {
			throw new RuntimeException(
					"Gewerbelehrer-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.psv.gew.service.fileOutput.* ");
		}

		Resource resource = applicationContext.getResource("file:" + loadDir + name + FILE_FORMAT);

		List<String> lakaleDateien = new ArrayList<>();
		for (Resource res : listLokaleDateien()) {
			lakaleDateien.add(res.getFilename());
		}
		if (!lakaleDateien.contains(resource.getFilename())) {
			logger.warn("Versuchte Pfadmanipulation erkannt! " + resource
					+ " sollte als GEW-Datei heruntergeladen werden. Erlaubt sind: " + lakaleDateien);
			throw new SecurityException("Pfad nicht erlaubt! " + resource);
		}

		if (!resource.exists()) {
			throw new FileNotFoundException(resource.toString());
		}

		// Protokollierung des Downloads
		GewHistory eintrag = new GewHistory();
		eintrag.setFilename(resource.getFilename());
		eintrag.setZeit(new Date());
		eintrag.setUsername(authorityUtils.getCurrentUserName());
		historyDao.insert(eintrag);

		return resource;
	}

	@Override
	public List<Resource> listLokaleDateien() throws RuntimeException, IOException {

		String loadDir = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.remotePath", "", true);

		if (loadDir.isEmpty()) {
			throw new RuntimeException(
					"Gewerbelehrer-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.psv.gew.service.fileOutput.* ");
		}

		String resourceSearchUri = "file:/" + loadDir + FILE_PREFIX + "*" + FILE_FORMAT;
		logger.debug("Suche Dateien in: " + resourceSearchUri);

		List<Resource> gebuehrenFiles = new ArrayList<>();
		Resource[] files = applicationContext.getResources(resourceSearchUri);

		for (Resource resource : files) {
			gebuehrenFiles.add(resource);
		}
		Collections.sort(gebuehrenFiles, (Resource r1, Resource r2) -> r2.getFilename().compareTo(r1.getFilename()));
		return gebuehrenFiles;
	}

	@Override
	public WritableResource writeFile(Document document) {

		int port = config.get("de.tuhh.tusp.psv.gew.service.fileOutput.port", 22, true);
		String user = config.get("de.tuhh.tusp.psv.gew.service.fileOutput.user", "", true);
		String remoteHosts = config.get("de.tuhh.tusp.psv.gew.service.fileOutput.remoteHosts", "", true);
		String remotePath = config.get("de.tuhh.tusp.psv.gew.service.fileOutput.remotePath", "", true);

		if (user.isEmpty() || remoteHosts.isEmpty() || remotePath.isEmpty()) {
			throw new RuntimeException(
					"Gewerbelehrer-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.psv.gew.service.fileOutput.* ");
		}

		if (document == null) {
			throw new NullPointerException("Gewerberlehrer-Datei ist null");
		}

		MessageCollector collector = MessageCollectorImpl.getInstance(Thread.currentThread());
		WritableResource inFile = new InMemoryResource(
				FILE_PREFIX + CalendarUtils.dateToDatumsString(new Date(), Locale.ENGLISH) + FILE_FORMAT);

		// Dokument in String umwandeln
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(); // Transformer
																							// anlegen
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Properties
																		// für
																		// Transformer
																		// setzen
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "1");
			transformer.transform(new DOMSource(document), new StreamResult(inFile.getOutputStream()));
		} catch (Exception e) {
			String message = "Konnte Gebührendaten-Dokument nicht in String konvertieren";
			logger.error(message, e);
			collector.addErrorMessage(message + ": " + e.getMessage());
			return null;
		}

		// Dokument auf Zielsysteme verteilen
		String[] hosts = remoteHosts.split(",");
		for (String host : hosts) {
			try {
				SshSession ssh = sshUtils.getSession(user, host, port);
				ssh.copyToRemote(inFile, remotePath + inFile.getFilename());
				ssh.close();
			} catch (Exception e) {
				String message = "Konnte Gebührendaten-Dokument nicht an " + host + " übertragen";
				logger.error(message, e);
				collector.addErrorMessage(message + ": " + e.getMessage());
			}
		}

		return inFile;
	}

}
