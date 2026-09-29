package de.tuhh.tusp.psv.tmabs.service;

import java.util.Date;

import de.tuhh.tusp.utils.MessageCollector;

public interface TmabsStudentenService {

	final String SERVER = "de.tuhh.tusp.psv.tmabs.autauschServer";
	final String USER = "de.tuhh.tusp.psv.tmabs.austauschUser";
	final String PORT = "de.tuhh.tusp.psv.tmabs.austauschPort";
	final String FOLDER = "de.tuhh.tusp.psv.tmabs.austauschFolder";

	/**
	 * Exportiert die Studierenden-Daten
	 *
	 * Suche Studenten aus aus HIO
	 *
	 * Wandle Datensätze in CSV-konforme Datensätze um
	 *
	 * Schreibe CSV
	 *
	 * Es wird für jeden fehlschlagenden Datensatz eine Message in den {@link MessageCollector} geschrieben.
	 *
	 * @param date
	 * @param mc
	 *
	 */
	void exportStudents(MessageCollector mc, Date date);

}
