package de.tuhh.tusp.stu.tuhhstgs.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.tuhhstgs.service.StgFileOutputService;
import de.tuhh.tusp.stu.tuhhstgs.service.StgHistoryService;
import de.tuhh.tusp.stu.tuhhstgs.service.StgProcessedFileService;
import de.tuhh.tusp.stu.tuhhstgs.service.StgService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class StgMain implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Tuhh-Studiengänge";
	static final String CONFIG_SEQNR = "de.tuhh.tusp.stu.stgs.sequenceNumber";
	static final String NOTIFICATION_RECIEVER = "de.tuhh.tusp.stu.stgs.notificationReciever";

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private CalendarUtils calendar;

	@Autowired
	private ConfigService config;

	@Autowired
	private StgService stgService;

	@Autowired
	private StgFileOutputService stgFileOutputService;

	@Autowired
	private StgHistoryService histpryService;
	@Autowired
	private StgProcessedFileService processedFileService;

	private boolean isEnabled() {
		return config.get("de.tuhh.tusp.stu.tuhhstgs_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector collector = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, jobLogger);
		int sequenceNumber = config.get(CONFIG_SEQNR, 999003);

		List<Resource> files = null;

		try {

			WritableResource resource = stgService.exportStgs(collector, jobLogger); // return and save file

			jobLogger.debug("TUHH Studiengänge Service Beendet!");
		} catch (Exception e) {
			collector.addErrorMessage("Die Studiengang Suche konnte nicht abgeschlossen werden", e);
		}

		collector.sendWarningsAndErrors("no-reply@tuhh.de");
	}

	@Override
	public String suggestName() {
		String now = CalendarUtils.toString(calendar.getNowAsXMLGregorianCalendar(), "dd.MM.yyyy kk:mm");
		return "Studiengänge TUHH | " + now;
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 30 17 * * *")
	public void trigger() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

}
