package de.tuhh.tusp.global.dao;

import java.util.Date;
import java.util.List;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.utils.schedule.Status;

public interface JobDao extends BaseDao<Job> {

	/**
	 * Ergänzt den String an der Log-Spalte
	 *
	 * @param jobId
	 * @param message
	 */
	void appendLog(Long jobId, String message);

	/**
	 * Holt einen Job anhand seines Namen
	 *
	 * @param name
	 * @return den Job oder null, wenn er nicht gefundne werden konnte.
	 */
	Job fetchByName(String name);

	/**
	 * Liefert alle Jobs in einem Status
	 *
	 * @return Liste der Jobs
	 */
	List<Job> findByStatus(Status status);

	/**
	 * Holt die Jobs aus einer Zeitspanne
	 *
	 * @param start
	 * @param ende
	 * @return Liste der Jobs
	 */
	List<Job> findByTimespan(Date start, Date ende);

	/**
	 * Erstellt eine Datenbank-Verbindung und speichert ohne Transaktion
	 *
	 * @param date
	 */
	void setRunning(Job job);
}
