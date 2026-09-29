package de.tuhh.tusp.stu.studcredentials.service;

import de.tuhh.tusp.global.service.JobLogger;

public interface StudCredentialsService {

	/**
	 * sendet die Account- Daten für Studenten die es nicht bekommen konnten
	 *
	 * @return
	 * @throws Exception
	 */
	void sendMail(JobLogger jobLogger) throws Exception;

}
