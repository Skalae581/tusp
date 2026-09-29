package de.tuhh.tusp.global.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.global.dao.JobDao;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.utils.HostUtils;
import de.tuhh.tusp.utils.schedule.JobInUseException;
import de.tuhh.tusp.utils.schedule.Status;

@Component
public class JobServiceImpl implements JobService {

	private static Logger logger = LogManager.getLogger(JobServiceImpl.class);

	private static Map<Long, List<Object>> jobsWithSteps = new HashMap<>();

	@Autowired
	private JobDao dao;

	@Override
	public void appendLog(Long jobId, String message) {

		dao.appendLog(jobId, message);
	}

	@Override
	public void beendeJob(Job job, Status status) {

		job.setEnde(new Date());
		job.setStatus(status);

		long dauer = (job.getEnde().getTime() - job.getStart().getTime()) / 1000;
		logger.info("Beende Job " + job.getName() + " mit Status " + status + " nach " + dauer + " Sekunden");
		dao.update(job);
	}

	@Override
	public Job fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public List<Job> findAll() {

		return dao.findAll();
	}

	@Override
	public List<Job> findByTimespan(Date start, Date ende) {

		return dao.findByTimespan(start, ende);
	}

	@Override
	public List<Job> findPendingJobs() {

		return dao.findByStatus(Status.Pending);
	}

	@Override
	public void finishedStep(Job job, Case c) {

		List<Object> steps = jobsWithSteps.get(job.getId());

		steps.remove(c);
		if (steps.isEmpty()) {
			beendeJob(job, Status.Done);
			jobsWithSteps.remove(job.getId());
		}
	}

	@Override
	public void markAsKilled() {

		List<Job> jobs = dao.findByStatus(Status.Running);
		for (Job job : jobs) {
			if (!job.getServer().equals(HostUtils.getServername())) {
				continue;
			}

			job.setStatus(Status.Killed);
			job.setEnde(new Date());
			dao.update(job);
		}
	}

	@Override
	public Job registerJob(String beanName, String jobname) {

		Job test = dao.fetchByName(jobname);
		if (test != null) {
			logger.info("Job bereits vorhanden. " + jobname);
			return test;
		}

		Job job = new Job();
		job.setName(jobname);
		job.setClassName(beanName);
		job.setStatus(Status.Pending);

		try {
			job.setId(dao.insert(job));
			logger.info("Job registriert " + jobname);
		} catch (DataAccessException e) {
			if (e.getMessage().contains("already exist")) {
				logger.info("Job war bereits registriert " + jobname);
			} else {
				throw e;
			}
		}
		return job;
	}

	@Override
	public void registerSteps(Job job, List<Object> cases) {

		jobsWithSteps.put(job.getId(), cases);
	}

	@Override
	public void startJob(Job job) {

		Job dbJob = dao.fetchById(job.getId()); // Nochmal aktualisieren
		if (Status.Pending != dbJob.getStatus()) {
			throw new JobInUseException();
		}

		Date date = new Date();

		job.setStatus(Status.Running);
		job.setStart(date);
		job.setServer(HostUtils.getServername());

		// Schreibe ohne Transaktion!
		dao.setRunning(job);

		// Schreibe einfach nochmal mit transaktion
		dao.update(job);
		logger.info("Job gestartet" + job.getName());
	}
}
