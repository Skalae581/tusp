package de.tuhh.tusp.stu.hiodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class BescheideLoeschenImpl implements BescheideLoeschen, Schedulable {

	private class DisabledException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	public static final String MESSAGE_COLLECTOR_NAME = "Bescheide-ohne-Anforderung";
	private static Logger logger = LogManager.getLogger(BescheideLoeschenImpl.class);

	// Die Abfrage ermittelt die vervizierten Bescheide ohne Anforderung, die
	// abgelaufen sind

	// Die Abfrage löscht die Datensätze aus der Tabelle document_access, die
	// abgelaufen sind
	private static final String STATEMENT = "delete FROM document_access WHERE document_metadata_id IN ("
			+ "SELECT document_metadata.id FROM document_access "
			+ "LEFT JOIN document_metadata ON (document_access.document_metadata_id=document_metadata.id) "
			+ "WHERE document_access.valid_to <= CURRENT_DATE)";

	@Autowired
	private ConfigService config;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private CalendarUtils calendar;

	@Override
	public void cleenUpDb(JobLogger job) {

		MessageCollector collector = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, null);
		DSLContext db = null;
		try {
			db = startConnection();
			int rows = db.execute(STATEMENT);
			job.info("Anzahl gelöschter Datensätze: " + rows);
		} catch (DisabledException e) {
			logger.info("Zugriff auf HISinOne Datenbank ist deaktiviert");
			job.info("Zugriff auf HISinOne Datenbank ist deaktiviert");
		} catch (Exception e) {
			logger.error("Bescheide-ohne-Anforderung Bereinigung fehlgeschlagen", e);
			job.error("Bescheide-ohne-Anforderung Bereinigung fehlgeschlagen", e);
			collector.addErrorMessage(e.getMessage());
			collector.sendWarningsAndErrors(null);
			throw e;
		}
	}

	@Override
	public void start(JobLogger job) throws Exception {

		cleenUpDb(job);
	}

	/**
	 * Erzeugt eine Datenbankverbindung
	 *
	 * @return Connection
	 */
	private DSLContext startConnection() {

		try {
			String url = config.get("de.tuhh.tusp.hio.db.connectionString", "", true);
			String userName = config.get("de.tuhh.tusp.hio.db.username", "", true);
			String password = config.get("de.tuhh.tusp.hio.db.password", "", true);

			if ("".equals(url)) {
				throw new RuntimeException("Zugriff auf HISinOne Datenbank nicht konfiguriert: de.tuhh.tusp.hio.db.*");
			}
			if ("disabled".equals(url)) {
				throw new DisabledException();
			}

			Connection conn = DriverManager.getConnection(url, userName, password);
			DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
			return context;
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
	@Scheduled(cron = "0 5 20 * * *")
	public void trigger() {

		scheduler.schedule(getClass(), suggestName());
	}

}
