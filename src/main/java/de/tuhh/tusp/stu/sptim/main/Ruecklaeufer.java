package de.tuhh.tusp.stu.sptim.main;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.pojo.TimPerson;
import de.tuhh.tusp.stu.sptim.service.TimStudentenService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;

@Component
public class Ruecklaeufer implements Schedulable {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private TimStudentenService timStudentenService;

	@Autowired
	private ConfigService config;

	private boolean isEnabled() {

		return config.get("de.tuhh.tusp.stu.sptim.ruecklaeufer_import_enabled", "false", true).equals("true");
	}

	@Override
	public void start(JobLogger jobLogger) throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance("SPTIM", jobLogger);
		int erros = 0;
		try {
			List<TimPerson> daten = timStudentenService.readRuecklaeufer(mc, jobLogger);
			jobLogger.info(daten.size() + " Rückläufer gefunden");
			timStudentenService.importiere(daten, mc);
			jobLogger.info(mc.getCreated() + " Accounts erstellt");
			jobLogger.info(mc.getUpdated() + " Accounts aktualisiert");
			for (String msg : mc.getErrors()) {
				jobLogger.error(msg);
			}
		} catch (Exception e) {
			mc.addErrorMessage("Rückläuferimport ist fehlgeschlagen" + e.getMessage());
			jobLogger.error("Rückläuferimport ist fehlgeschlagen", e);
			throw e;
		} finally {
			erros = mc.getErrors().size();
			mc.sendWarningsAndErrors(null);
		}
		if (erros > 0) {
			throw new RuntimeException("Es sind Fehler aufgetreten.");
		}
	}

	@Override
	public String suggestName() {

		return "SPTIM-Rückläufer " + new Date();
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 15 8 * * *")
	public void trigger1() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}

	/**
	 * Legt den Job in die Queue
	 */
	@Scheduled(cron = "0 30 14 * * *")
	public void trigger2() {

		if (isEnabled()) {
			scheduler.schedule(getClass(), suggestName());
		}
	}
}
