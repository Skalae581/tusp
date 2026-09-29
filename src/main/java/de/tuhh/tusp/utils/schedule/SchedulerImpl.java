package de.tuhh.tusp.utils.schedule;

import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.global.service.JobService;
import de.tuhh.tusp.utils.HostUtils;

@Component
public class SchedulerImpl implements Scheduler {

	private static final Logger logger = LogManager.getLogger(Scheduler.class);

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private JobService jobService;

	@Autowired
	private ConfigService config;

	/**
	 * Vermerkt bei abgebrochenen Jobs das sie nicht abgeschlossen werden können
	 */
	@PreDestroy
	public void destroy() {

		jobService.markAsKilled();
	}

	/**
	 * Schaut regelmäßig ob es Jobs gibt, die bearbeitet werden könnten
	 */
	@Scheduled(fixedRate = 1000)
	public void schedule() {

		List<Job> jobs = jobService.findPendingJobs();
		try {
			if ("true".equals(config.get("de.tuhh.tusp.utils.schedule.disable_host", "false", false))) {
				// Nichts zu tun also kann man auch mal ne Stunde schlafen :)
				Thread.sleep(3600000);
				return;
			}

			if (jobs.isEmpty()) {
				// Nichts zu tun also kann man auch mal 5 Sekunden schlafen :)
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			logger.error("Killed while sleeping!", e);
		}

		String key = "de.tuhh.tusp.utils.schedule." + HostUtils.getServername();
		long delay = 1000 * config.get(key, new Date().getTime() % 20, true);

		Job job = null;
		for (Job j : jobs) {

			// Jobstart protokollieren
			try {
				Thread.sleep(delay);

				jobService.startJob(j);
				job = j;
				break;
			} catch (JobInUseException e) {
				logger.debug("Job is already running" + j.getName());
			} catch (InterruptedException e) {
				logger.error("Killed while sleeping!", e);
			}
		}

		if (job == null) {
			return;
		}

		Schedulable bean;
		try {
			bean = (Schedulable) ctx.getBean(Class.forName(job.getClassName()));
		} catch (BeansException | ClassNotFoundException e1) {
			logger.error("Bean konnte nicht geladen werden", e1);
			jobService.beendeJob(job, Status.Fail);
			return;
		}

		// Job ausführen
		JobLogger jobLogger = new JobLoggerImpl(job, jobService);
		try {
			bean.start(jobLogger);
			jobService.beendeJob(job, Status.Done);
		} catch (WorkerStartedException e) {
			logger.debug("Job wird nicht als beendet markiert weil nur ein Worker gestartet wurde.");
		} catch (Exception e) {
			logger.error("Cronjob hat eine Exception geworfen", e);
			jobService.beendeJob(job, Status.Fail);
		}
	}

	@Override
	public void schedule(Class<? extends Schedulable> beanDef, String jobname) {

		if ("true".equals(config.get(beanDef.getName() + ".disable_cron", "false", true))) {
			logger.debug("CronJob deaktiviert in Konfig " + jobname);
			return;
		}

		logger.info("Register CronJob " + jobname);
		jobService.registerJob(beanDef.getName(), jobname);
	}
}
