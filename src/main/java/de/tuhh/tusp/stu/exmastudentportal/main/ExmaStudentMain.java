package de.tuhh.tusp.stu.exmastudentportal.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.exmastudentportal.service.ExmaStudentPortalService;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class ExmaStudentMain implements Schedulable {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ConfigService config;

	@Autowired
	private ExmaStudentPortalService exmaStudentPortalService;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.stu.exmastudentportal.exmastudentportal_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {
		// MessageCollector mc = MessageCollectorImpl.getInstance("exmastudentportal",
		// jobLogger);

		jobLogger.debug("Exma-Student-Portal-Nachtricht gestartet!");

		try {

			exmaStudentPortalService.sendData(jobLogger);

		} catch (Exception e) {
			jobLogger.debug(e.getMessage());
		}

		jobLogger.debug("Exma-Student-Portal-Nachtricht beendet!");

	}

	@Override
	public String suggestName() {
		return "Exma-Student-Portal-Nachtricht " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 0 20 * * *")
	public void trigger() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

}
