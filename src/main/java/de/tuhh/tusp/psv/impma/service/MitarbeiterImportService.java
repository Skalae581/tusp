package de.tuhh.tusp.psv.impma.service;

import javax.naming.NamingException;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;

public interface MitarbeiterImportService {

	/**
	 * importiert die Mitarbeiter und Affiliates aus LDAP
	 * 
	 * @param collector
	 * @param jobLogger
	 * @throws NamingException
	 */
	void importFromLdap(MessageCollector collector, JobLogger jobLogger) throws NamingException;

}
