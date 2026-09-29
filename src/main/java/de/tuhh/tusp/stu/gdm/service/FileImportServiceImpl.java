package de.tuhh.tusp.stu.gdm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.stu.gdm.dao.ProcessedFileDao;
import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;
import de.tuhh.tusp.stu.gdm.pojo.ProcessedFile;
import de.tuhh.tusp.utils.MD5Impl;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;

@Component
public class FileImportServiceImpl implements FileImportService {

	private static Logger logger = LogManager.getLogger(FileImportServiceImpl.class);

	static final String CONFIG_KEY_PATH = "de.tuhh.tusp.stu.gdm.GebuehrenDatenImportPattern";
	static final long TAG_IN_MILLISEKUNGEN = 86400000;

	@Autowired
	private ConfigService config;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ProcessedFileDao processedFileDao;

	@Override
	public List<Resource> findGebuehrenFiles() throws IOException, KeineAktuellenFilesException {

		// Beispiel Dateiname: "C54_0020101512_20171220_01.xml"
		// in Lutz' Script werden Dateien im Format "C54*512_*.xml" nach
		// /disk1/gebuehrendaten/ verschoben
		String importDir = config.get(CONFIG_KEY_PATH, "file:/disk1/gebuehrendaten/C54*512_*.xml", true);
		MessageCollector collector = MessageCollectorImpl.getInstance(Thread.currentThread());

		Resource[] files = applicationContext.getResources(importDir);

		Long now = System.currentTimeMillis();

		List<Resource> ret = new ArrayList<>();
		for (Resource resource : files) {
			logger.debug(resource);
			try {
				String fileName = resource.getFilename();
				logger.info("Resource gefunden: " + fileName);
				ProcessedFile processedFile = processedFileDao.fetchByInName(fileName);

				if (processedFile == null) {
					// Noch nicht verarbeitet also jetzt machen
					ret.add(resource);
					continue;
				}

				String hash = MD5Impl.getMd5(resource.getInputStream());

				if (!hash.equals(processedFile.getHash())) {
					String msg = "Die Datei scheint nach dem Verarbeiten verändert zu sein: " + resource
							+ ". Sie wird nicht automatisch gelöscht. Zum Verarbeiten braucht sie einen neuen Namen. ";
					logger.warn(msg);
					collector.addWarnMessage(msg);
					continue;
				}

				if (processedFile.getVerarbeitet().getTime() < now - TAG_IN_MILLISEKUNGEN * 7) {
					logger.info("Lösche Datei wegen ihres Alters: " + resource);
					resource.getFile().delete();
					continue;
				}
			} catch (Exception e) {
				logger.error("Überspringe Datei wegen eines Fehlers: " + resource, e);
				collector.addErrorMessage("Konnte Datei " + resource + " nicht einlesen: " + e.getMessage());
			}
		}

		if (files.length > 0 && ret.isEmpty()) {
			throw new KeineAktuellenFilesException("Keine Aktuellen Files Vorhanden!");
		}

		return ret;
	}
}
