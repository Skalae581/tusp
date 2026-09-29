package de.tuhh.tusp.stu.gdm.main;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;
import de.tuhh.tusp.stu.gdm.service.FileImportService;
import de.tuhh.tusp.stu.gdm.service.FileMergeService;
import de.tuhh.tusp.stu.gdm.service.FileOutputService;
import de.tuhh.tusp.stu.gdm.service.ProcessedFileService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class MainImpl implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Gebührendaten-Management";
	static final String CONFIG_SEQNR = "de.tuhh.tusp.stu.gdm.sequenceNumber";
	static final String NOTIFICATION_RECIEVER = "de.tuhh.tusp.stu.gdm.notificationReciever";

	@Autowired
	private ConfigService config;

	@Autowired
	private FileImportService importService;

	@Autowired
	private FileOutputService outputService;

	@Autowired
	private FileMergeService mergeService;

	@Autowired
	private ProcessedFileService processedFileService;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private CalendarUtils calendar;

	/**
	 * Verarbeitet die Gebührendaten
	 *
	 * 1. Initialisiere einen MessageCollector
	 *
	 * 2. Importiere die Files
	 *
	 * 3. Merge Files in eines
	 *
	 * 4. Write Files
	 *
	 * 5. MessageCollector.sendErrors()
	 */
	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector collector = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, jobLogger);
		int sequenceNumber = config.get(CONFIG_SEQNR, 999003);

		List<Resource> files = null;
		try {
			files = importService.findGebuehrenFiles();

			if (files.isEmpty()) {
				jobLogger.info("Keine Kontodaten gefunden. Nichts zu tun.");
				return;
			}

			Document result = mergeService.mergeFiles(files, sequenceNumber);
			Resource outfile = null;
			try {
				outfile = outputService.writeFile(result);
				sequenceNumber++;
				String newSequenceNumber = Integer.toString(sequenceNumber);
				config.set(CONFIG_SEQNR, newSequenceNumber);
			} catch (Exception e) {
				String message = "Die Gebührendaten-File konnte nicht geschrieben werden: " + e.getMessage();
				jobLogger.error(message, e);
				collector.addErrorMessage(message);
			}

			processedFileService.markAsProcessed(files, outfile);
		} catch (IOException e) {
			String message = "Die Gebührendaten-Files können nicht gelesen werden: " + e.getMessage();
			jobLogger.error(message, e);
			collector.addErrorMessage(message);
		} catch (KeineAktuellenFilesException e) {
			String message = "Keine Gebührendaten-Files für heute vorhanden." + e.getStackTrace();
			jobLogger.warn(message);
			collector.addErrorMessage(message);
		} catch (Exception e) {
			String message = "Es ist ein unbehandelter Fehler aufgetreten.";
			jobLogger.error(message, e);
			collector.addErrorMessage(message);
		}

		List<String> errors = collector.getErrors();
		collector.sendWarningsAndErrors(config.get(NOTIFICATION_RECIEVER, "", true));
		if (errors.size() > 0) {
			// Job als Fehlerhaft markieren
			throw new RuntimeException("Gebührendaten konnten nicht fehlerfrei verarbeitet werden.");
		}
	}

	@Override
	public String suggestName() {

		String now = CalendarUtils.toString(calendar.getNowAsXMLGregorianCalendar(), "yyyy-MM-dd kk:mm");
		return this.getClass().getName() + " " + now;
	}

	/**
	 * Legt den Job in die Queue
	 */
	// Daten kommen um 21 Uhr
	@Scheduled(cron = "0 0 22 * * *")
	public void trigger() {

		scheduler.schedule(getClass(), suggestName());
	}

}
