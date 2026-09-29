package de.tuhh.tusp.psv.tmabs.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.tmabs.service.TmabsStudentenService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class TmabsExport implements Schedulable {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private TmabsStudentenService studentenService;

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("TMABS", jobLogger);

		studentenService.exportStudents(mc, new Date());

		mc.sendWarningsAndErrors(null);
	}

	@Override
	public String suggestName() {

		return "Technomathe-Bachelor-Export " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 0 19 * * *")
	public void trigger() {

		scheduler.schedule(getClass(), suggestName());
	}
}
