package de.tuhh.tusp.global.service;

import java.util.Date;
import java.util.List;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.utils.schedule.JobInUseException;
import de.tuhh.tusp.utils.schedule.Status;

public interface JobService {

	/**
	 * Ergänzt den String an der Log-Spalte
	 *
	 * @param jobId
	 * @param message
	 */
	void appendLog(Long jobId, String message);

	/**
	 * Markiert den Job als beendet mit dem jeweiligen Status.
	 *
	 * @param job
	 * @param status
	 */
	void beendeJob(Job job, Status status);

	/**
	 * Gibt den Job zurück
	 *
	 * @return job
	 */
	Job fetchById(Long id);

	/**
	 * Gibt alle Jobs zurück
	 *
	 * @return jobs
	 */
	List<Job> findAll();

	/**
	 * Holt die Jobs aus einer Zeitspanne
	 *
	 * @param start
	 * @param ende
	 * @return Liste der Jobs
	 */
	List<Job> findByTimespan(Date start, Date ende);

	/**
	 * Liefert alle pending Jobs
	 *
	 * @return Liste der Jobs
	 */
	List<Job> findPendingJobs();

	/**
	 * Markiert einen Schritt als erledigt
	 *
	 * @param job
	 * @param c
	 */
	void finishedStep(Job job, Case c);

	/**
	 * Markiert alle Jobs des Servers als abgebrochen
	 */
	void markAsKilled();

	/**
	 * Speichert einen Job in die DB. Macht aber nichts, wenn er schon existiert.
	 *
	 * @param beanName
	 *            Name der Bean, die dann ausgeführt werdne soll
	 * @param jobname
	 * @return den neuen Job
	 */
	Job registerJob(String beanName, String jobname);

	/**
	 * Vermerkt, welche einzelschritte der Job enthält
	 *
	 * @param job
	 * @param cases
	 */
	void registerSteps(Job job, List<Object> cases);

	/**
	 * Trägt einen Job als gestartet in die Datenbank ein.
	 *
	 * @param job
	 * @throws JobInUseException
	 *             Wenn der Job zwischenzeitlich von einem anderen Server begonnen wurde
	 */
	void startJob(Job job);
}
