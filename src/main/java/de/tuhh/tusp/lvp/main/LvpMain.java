package de.tuhh.tusp.lvp.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.lvp.service.LvpService;
import de.tuhh.tusp.lvp.service.TermineService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class LvpMain implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Monitore-Export";

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ConfigService config;

	@Autowired
	private LvpService lvpService;

	@Autowired
	private TermineService termineService;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.lvp.monitore_export_csv_ enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("LVP", jobLogger);

		try {
			termineService.exportTermine(mc, new Date(), jobLogger);
			lvpService.exportLvp(mc, new Date(), jobLogger);
		} catch (Exception e) {
			mc.addErrorMessage("Lvp Export ist fehlgeschlagen " + e.getMessage());
			jobLogger.debug("Lvp Export ist fehlgeschlagen " + e.getMessage());
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
	@Scheduled(cron = "0 30 18 * * *")
	public void trigger() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

}
