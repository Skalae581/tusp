package de.tuhh.tusp.exa.massmails.verteiler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.service.ReceiverSyncService;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class HIOVerteilerMain implements Schedulable {

	public static final String MESSAGE_COLLECTOR_NAME = "Verteiler Sync";

	@Autowired
	private Scheduler scheduler;
	@Autowired
	private ConfigService config;

	@Autowired
	private ReceiverSyncService recieverSyncService;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.td.verteiler_dienst_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("Verteiler", jobLogger);

		try {
			jobLogger.debug("Verteiler Sync Starten....");
			recieverSyncService.sync(jobLogger);
		}
		catch (Exception e) {
			mc.addErrorMessage("Verteiler Sync ist fehlgeschlagen " + e.getMessage());
			jobLogger.error("Verteiler Sync ist fehlgeschlagen ", e.getCause());
			throw e;
		}
		finally {
			mc.sendWarningsAndErrors(null);
		}
	}

	@Override
	public String suggestName() {

		return "Verteiler Sync " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 0 4 * * *")
	public void trigger() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

}
