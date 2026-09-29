package de.tuhh.tusp.stu.studcredentials.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.service.StudCredentialsService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class StudCredentials implements Schedulable {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ConfigService config;

	@Autowired
	private StudCredentialsService studCredentialsService;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.stu.studcredentials.studcredentials_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {
		MessageCollector mc = MessageCollectorImpl.getInstance("studcredentials", jobLogger);

		jobLogger.debug("Studenten-Credentials gestartet!");

		try {
			studCredentialsService.sendMail(jobLogger);
		} catch (Exception e) {
			mc.addErrorMessage(e.getMessage());
		}

		jobLogger.debug("Studenten-Credentials beendet!");

	}

	@Override
	public String suggestName() {
		return "Studenten-Initial-Daten " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 0 21 * * *")
	public void trigger() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

}
