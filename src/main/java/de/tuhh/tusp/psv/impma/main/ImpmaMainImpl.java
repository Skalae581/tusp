package de.tuhh.tusp.psv.impma.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.impma.service.MitarbeiterImportService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class ImpmaMainImpl implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Mitarbeiter-Import";

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private CalendarUtils calendar;

	@Autowired
	private MitarbeiterImportService mis;

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector collector = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, jobLogger);
		try {

			jobLogger.debug("Starte Mitarbeiter Import aus LDAP...");
			mis.importFromLdap(collector, jobLogger);
			jobLogger.debug("Mitarbeiter Import aus LDAP beendet.");

		} catch (Exception e) {
			collector.addErrorMessage("Der Mitarbeiterimport konnte nicht abgeschlossen werden", e);
		}

		collector.sendWarningsAndErrors("no-reply@tuhh.de");
	}

	@Override
	public String suggestName() {

		String now = CalendarUtils.toString(calendar.getNowAsXMLGregorianCalendar(), "dd.MM.yyyy kk:mm");
		return "Mitarbeiter-Import-LDAP | " + now;
	}

	/**
	 * Legt den Job in die Queue
	 */
	// Daten kommen um 3 Uhr
	@Scheduled(cron = "0 0 3 * * *")
	public void trigger() {

		scheduler.schedule(getClass(), suggestName());
	}

}
