package de.tuhh.tusp.psv.tmabs.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface TmabsStudentDao {

	/**
	 * Gibt eine Liste der Studenten zurück
	 *
	 *
	 * @return Studenten
	 * @throws ServiceFault
	 *             Wenn die Webservices Probleme machen
	 */
	List<Map<TmabsColumns, String>> getStudentenDaten(MessageCollector mc) throws ServiceFault;

	/**
	 * Schreibt die Ausgabe der Studenten
	 *
	 * @param studenten
	 *            Liste der Studenten
	 * @param resource
	 * @throws IOException
	 *             Wenn die Daten wegen IO-Problemen nicht geschrieben werden können.
	 * @throws NullPointerException
	 *             Wenn in einem der Datensätze eines der Felder null ist.
	 */
	void writeStudenten(List<Map<TmabsColumns, String>> studenten, WritableResource resource)	throws IOException,
																								NullPointerException;

}
