package de.tuhh.tusp.td.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.td.service.TdService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class TdMain implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Technische Dienst Export";

	@Autowired
	private Scheduler scheduler;
	@Autowired
	private ConfigService config;
	@Autowired
	private TdService tdService;

	private boolean isEnabled() {
		return config.get("de.tuhh.tusp.td.technische_dienst_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("TD", jobLogger);

		try {
			jobLogger.debug("TD Starten....");
			tdService.exportTd(mc, new Date(), jobLogger);
		} catch (Exception e) {
			mc.addErrorMessage("TD Export ist fehlgeschlagen " + e.getMessage());
			jobLogger.error("TD Export ist fehlgeschlagen ", e.getCause());
			throw e;
		} finally {
			mc.sendWarningsAndErrors(null);
		}
	}

	@Override
	public String suggestName() {

		return MESSAGE_COLLECTOR_NAME + " " + new Date();
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
