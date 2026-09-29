package de.tuhh.tusp.stu.studcredentials.service;

import java.util.List;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.pojo.PersonCredential;

public interface StudAccountDataService {

	/**
	 * Lese Account Daten für die Personen die das Account-Datum 'Valid From' ab
	 * heute haben
	 * 
	 * @param jobLogger
	 * @return
	 */
	List<PersonCredential> getAccountData(JobLogger jobLogger);

}
