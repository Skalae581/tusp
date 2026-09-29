package de.tuhh.tusp.stu.hiodb;

import de.tuhh.tusp.global.service.JobLogger;

public interface BescheideLoeschen {

	/**
	 * Löscht nict abgerufene Bescheide aus der DB
	 */
	void cleenUpDb(JobLogger job);

}
