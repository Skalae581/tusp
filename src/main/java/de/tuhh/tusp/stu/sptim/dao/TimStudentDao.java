package de.tuhh.tusp.stu.sptim.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.pojo.TimPerson;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface TimStudentDao {

	/**
	 * Gibt eine Liste der Studenten zurück
	 * 
	 * @param logger
	 *
	 *
	 * @return Studenten
	 * @throws ServiceFault
	 *             Wenn die Webservices Probleme machen
	 */
	List<Map<StudentColumns, String>> getStudentenDaten(MessageCollector mc, JobLogger logger) throws ServiceFault;

	/**
	 * Liest die Rückläuferdatei ein
	 *
	 * @param file
	 *
	 * @return Liste der Rückläufer
	 * @throws IOException
	 *             Wenn auf die Datei nicht zugegriffen werden kann
	 * @throws de.tuhh.tusp.utils.csv.ParseException
	 *             Wenn die Datei falscch formatiert ist
	 */
	List<TimPerson> readRuecklaeufer(Resource file) throws de.tuhh.tusp.utils.csv.ParseException, IOException;

	/**
	 * Schreibt die Ausgabe der Bewerber
	 *
	 * @param bewerber
	 *            Liste der Bewerber
	 * @throws IOException
	 *             Wenn die Daten wegen IO-Problemen nicht geschrieben werden
	 *             können.
	 * @throws NullPointerException
	 *             Wenn in einem der Datensätze eines der Felder null ist.
	 */
	void writeBewerber(List<Map<StudentColumns, String>> bewerber, WritableResource file)	throws IOException,
																							NullPointerException;

	/**
	 * Schreibt die Ausgabe der Studenten
	 *
	 * @param studenten
	 *            Liste der Studenten
	 * @param resource
	 * @throws IOException
	 *             Wenn die Daten wegen IO-Problemen nicht geschrieben werden
	 *             können.
	 * @throws NullPointerException
	 *             Wenn in einem der Datensätze eines der Felder null ist.
	 */
	void writeStudenten(List<Map<StudentColumns, String>> studenten, WritableResource resource) throws IOException,
																								NullPointerException;

}
