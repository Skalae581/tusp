package de.tuhh.tusp.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;

@Component
public class MessageCollectorImpl implements MessageCollector {

	private static MailUtils mailUtils;
	private static ConfigService configService;

	private static Logger logger = LogManager.getLogger(MessageCollectorImpl.class);

	private static volatile Map<Long, MessageCollector> instancesByThreadId = new HashMap<>();
	private static volatile Map<String, MessageCollector> instancesByName = new HashMap<>();

	/**
	 * Liefert die Instanz anhand des Namens
	 *
	 * @param name
	 * @return Die Instance
	 */
	public static MessageCollector getInstance(String name, JobLogger l) {

		if (name == null) {
			return null;
		}

		if (instancesByName.containsKey(name)) {
			return instancesByName.get(name);
		}

		MessageCollectorImpl mc = new MessageCollectorImpl(name);
		instancesByThreadId.put(Thread.currentThread().getId(), mc);
		instancesByName.put(name, mc);

		if (l != null) {
			mc.jobLogger = l;
		}

		return mc;
	}

	/**
	 * Liefert die Instanz des aktuellen Threads
	 *
	 * @return Die Instance
	 */
	public static MessageCollector getInstance(Thread thread) {

		MessageCollector instance = instancesByThreadId.get(thread.getId());
		if (instance == null) {
			instance = getInstance(thread.getName(), null);
			instancesByThreadId.put(thread.getId(), instance);
		}
		return instance;
	}

	private JobLogger jobLogger = new JobLogger() {

		@Override
		public void debug(String message) {

			logger.debug("jobLogger: " + message);
		}

		@Override
		public void error(String message) {

			logger.error("jobLogger: " + message);
		}

		@Override
		public void error(String message, Throwable e) {

			logger.error("jobLogger: " + message, e);
		}

		@Override
		public Job getJob() {

			return null;
		}

		@Override
		public String getText() {

			return null;
		}

		@Override
		public void info(String message) {

			logger.info("jobLogger: " + message);
		}

		@Override
		public void warn(String message) {

			logger.warn("jobLogger: " + message);
		}
	};

	private List<String> ok;
	private List<String> error;
	private List<String> warn;
	private String name;
	private Date start;
	private int updated = 0;
	private int created = 0;
	private int deleted = 0;

	/**
	 * Dient Spring dazu eine Instanz zu erzeugen. Dadurch werden die static Fields
	 * gefüllt.
	 *
	 * @param mailUtils
	 * @param configService
	 */
	@Autowired
	public MessageCollectorImpl(MailUtils mailUtils, ConfigService configService) {

		reset();
		MessageCollectorImpl.mailUtils = mailUtils;
		MessageCollectorImpl.configService = configService;
	}

	/**
	 * Erzeugt leere Instanzen
	 */
	private MessageCollectorImpl(String name) {

		this.name = name;
		reset();
	}

	@Override
	public void addCreated() {

		created++;
		jobLogger.info("New created. Num: " + created);
	}

	@Override
	public void addDeleted() {

		deleted++;
		jobLogger.info("New deleted. Num: " + deleted);

	}

	@Override
	public void addErrorMessage(String message) {

		jobLogger.error(message);
		error.add(message);
	}

	@Override
	public void addErrorMessage(String message, Throwable exception) {

		jobLogger.error(message, exception);
		error.add(message);
	}

	@Override
	public void addOkMessage(String message) {

		jobLogger.info(message);
		ok.add(message);
	}

	@Override
	public void addUpdated() {

		updated++;
		jobLogger.info("New updated. Num: " + updated);
	}

	@Override
	public void addWarnMessage(String message) {

		jobLogger.warn(message);
		warn.add(message);
	}

	/**
	 * Verknüpft alle Elemente einer Liste
	 *
	 * @param list
	 * @return liste als String
	 */
	private String concat(List<String> list) {

		StringBuilder builder = new StringBuilder();

		for (String s : list) {
			builder.append(" * ");
			builder.append(s);
			builder.append("\n");
		}

		return builder.toString();
	}

	@Override
	public int getCreated() {

		return created;
	}

	@Override
	public int getDeleted() {

		return deleted;
	}

	@Override
	public List<String> getErrors() {

		return error;
	}

	@Override
	public int getUpdated() {

		return updated;
	}

	@Override
	public void reset() {

		ok = new ArrayList<>();
		error = new ArrayList<>();
		warn = new ArrayList<>();
		start = new Date();

		deleted = created = updated = 0;
	}

	@Override
	public void sendAll(String additionalReciever) {

		StringBuilder builder = new StringBuilder();

		builder.append("## Errors:\n");
		builder.append(concat(error));

		builder.append("\n\n");
		builder.append("## Warnings:\n");
		builder.append(concat(warn));

		builder.append("\n\n");
		builder.append("## Ok:\n");
		builder.append(concat(ok));

		sendMail(summary(), builder.toString(), additionalReciever);
	}

	@Override
	public void sendErrors(String additionalReciever) {

		if (error.size() == 0) {
			logger.info("No mail due to no errors: " + summary());
			return;
		}

		StringBuilder builder = new StringBuilder();

		builder.append("## Errors:\n");
		builder.append(concat(error));

		sendMail(summary(), builder.toString(), additionalReciever);
	}

	/**
	 * Versendet eine Mail
	 *
	 * @param subject
	 * @param message
	 */
	private void sendMail(String subject, String message, String additionalReciever) {

		// ZusammenfassungsTabelle in MailBody
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" ");
		builder.append(CalendarUtils.dateToDatumsString(start));
		builder.append(" ");
		try {
			builder.append(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e1) {
			builder.append("unknown host");
			logger.error("Unknown Host", e1);
		}
		builder.append("\n- Erstellte Datensätze:     ");
		builder.append(created);
		builder.append("\n- Aktualisierte Datensätze: ");
		builder.append(updated);
		builder.append("\n- Gelöschte Datensätze:     ");
		builder.append(deleted);
		builder.append("\n- Fehler:                   ");
		builder.append(error.size());
		builder.append("\n- Warnungen:                ");
		builder.append(warn.size());
		builder.append("\n\n# Details:\n");
		builder.append(message);

		if (configService == null) {
			throw new NullPointerException(
					"Der MessageCollector wurde nicht richtig initialisiert. Er muss als Spring-Component über den Constructor initialisiert werden!");
		}

		String from = configService.get(MailUtils.MAIL_FROM_CONFIG_KEY, "no-reply@tuhh.de", true);
		String to = configService.get(MessageCollector.MESSAGE_RECIEVER_MAILADDRESS_CONFIG_KEY, "iuk@tuhh.de", true);

		if (additionalReciever != null && !additionalReciever.isEmpty()) {
			to += ";" + additionalReciever;
		}

		try {
			mailUtils.sendPlainTextMail(to, from, subject, builder.toString());
			reset();
		} catch (Exception e) {
			logger.error("Benachrichtigung konnte nicht versand werden!" + subject + builder.toString(), e);
		}

	}

	@Override
	public void sendWarningsAndErrors(String additionalReciever) {

		if (error.size() == 0 && warn.size() == 0) {
			logger.info("No mail due to no errors or warnings: " + summary());
			return;
		}

		StringBuilder builder = new StringBuilder();

		builder.append("## Errors:\n");
		builder.append(concat(error));

		builder.append("\n\n");
		builder.append("## Warnings:\n");
		builder.append(concat(warn));

		sendMail(summary(), builder.toString(), additionalReciever);
	}

	/**
	 * Verfasst eine Zusammenfassung
	 *
	 * @return die Zusammenfassung
	 */
	private String summary() {

		StringBuilder builder = new StringBuilder();

		builder.append(name);
		builder.append(" ");
		builder.append(CalendarUtils.dateToDatumsString(start));
		builder.append(" Fehler: ");
		builder.append(error.size());
		builder.append(" Warnungen: ");
		builder.append(warn.size());
		builder.append(" Datensätze bearbeitet: ");
		builder.append(created + updated + deleted);

		return builder.toString();
	}
}
