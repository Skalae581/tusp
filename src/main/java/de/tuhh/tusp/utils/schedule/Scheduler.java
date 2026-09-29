package de.tuhh.tusp.utils.schedule;

public interface Scheduler {

	/**
	 * Führt die start()-Methode aus, wenn der Server dafür zuständig ist.
	 *
	 * @param job
	 * @param jobname
	 *            Ein eindeutiger Name für den Job. Er Sollte aus der Klasse und dem Datum und ggf. der Uhrzeit oder
	 *            eine Abbildung der Uhrzeit bestehen.
	 */
	void schedule(Class<? extends Schedulable> job, String jobname);

}
