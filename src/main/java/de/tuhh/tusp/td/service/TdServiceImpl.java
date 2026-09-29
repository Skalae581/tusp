package de.tuhh.tusp.td.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;

import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.td.dao.TdColumns;
import de.tuhh.tusp.td.dao.TdDao;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

@Component
public class TdServiceImpl implements TdService {

	private static Logger logger = LogManager.getLogger(TdServiceImpl.class);

	@Autowired
	private TdDao tdDao;

	@Autowired
	private MailUtils mailUtils;

	@Override
	public void exportTd(MessageCollector mc, Date date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.termservice.ServiceFault_Exception, ParseException, JSchException {

		List<Map<TdColumns, String>> tds;
		try {
			tds = tdDao.getTdDaten(mc, jobLogger);
		} catch (ServiceFault e) {
			// mc.addErrorMessage("Konnte TD nicht abrufen: " + e.getMessage());
			logger.error("Konnte TD nicht abrufen", e);
			jobLogger.error("Konnte TD nicht abrufen", e);
			return;
		}

		// Schreibe CSV
		InMemoryResource res = new InMemoryResource("td.csv");
		// WritableResource res = new
		// FileSystemResource("/home/rztjj/tdienst-test.csv");

		try {
			tdDao.writeTd(tds, res);

			// Convert Date to dd.mm.yyyy
			DateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date d = originalFormat.parse(date.toString());
			String formattedDate = targetFormat.format(d);

			String to = "zlt@tuhh.de";
			String from = "noreply@tu-harburg.hamburg.de";
			String subject = "Ergebnis der Raumsuche für den TD vom " + formattedDate;
			String body = "Dies E-Mail wurde automatisiert von der Datenbank erstellt.";
			String filename = res.getFilename();

			logger.debug("Versuche die E-Mail zu verschicken mit Datei: " + filename);
			jobLogger.debug("Versuche die E-Mail zu verschicken mit Datei: " + filename);

			ByteArrayOutputStream outputStream = (ByteArrayOutputStream) res.getOutputStream();

			try {
				mailUtils.sendMailWithAttachment(to, from, subject, body, outputStream, filename);

				logger.debug("E-Mail an " + to + " erfolgreich gesendet! ");
				jobLogger.debug("E-Mail an " + to + " erfolgreich gesendet! ");

			} catch (AddressException e) {
				logger.debug("Fehler: " + e.getMessage());
				jobLogger.debug("Fehler: " + e.getMessage());
			} catch (MessagingException e) {
				logger.debug("Fehler: " + e.getMessage());
				jobLogger.debug("Fehler: " + e.getMessage());

			}

		} catch (IOException e) {
			mc.addErrorMessage("Konnte Datei td.csv nicht verschicken: " + e.getMessage());
			logger.error("Konnte Datei td.csv nicht exportieren", e);
			jobLogger.error("Konnte Datei td.csv nicht exportieren", e);

		}

	}
}
