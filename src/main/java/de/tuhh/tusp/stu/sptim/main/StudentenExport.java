package de.tuhh.tusp.stu.sptim.main;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.sptim.service.TimStudentenService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class StudentenExport implements Schedulable {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ConfigService config;

	@Autowired
	private TimStudentenService timStudentenService;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.stu.sptim.studenten_export_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("SPTIM", jobLogger);

		try {
			List<Map<StudentColumns, String>> data = timStudentenService.getExportStudents(mc, jobLogger);
			jobLogger.info(data.size() + " Datensätze zum Exportieren gefunden");
			timStudentenService.sentStudentFile(data, new GregorianCalendar(), mc);
		}
		catch (Exception e) {
			mc.addErrorMessage("TIM Export ist fehlgeschlagen " + e.getMessage());
			jobLogger.error("TIM Export ist fehlgeschlagen ", e);
			throw e;
		}
		finally {
			mc.sendWarningsAndErrors(null);
		}
	}

	@Override
	public String suggestName() {

		return "SPTIM-Export " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 15 21 * * *")
	public void trigger1() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 30 12 * * *")
	public void trigger2() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}
}
