package de.tuhh.tusp.psv.unihh.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.Resource;

public interface UniHHImport {

	/**
	 * Startete den Import von Gewerbelehrern, wenn dieser noch nicht läuft
	 *
	 * @throws FileNotFoundException
	 *             Wenn eine CSV-Datei nicht gefunden werden kann
	 * @throws IOException
	 *             Wenn eine CSV-Datei nicht gelesen werden kann
	 */
	void importGewerbelehrer(Map<String, Resource> files) throws FileNotFoundException, IOException;

	/**
	 * Startete den Import von Technomathematikern, wenn dieser noch nicht läuft
	 *
	 * @throws FileNotFoundException
	 *             Wenn eine CSV-Datei nicht gefunden werden kann
	 * @throws IOException
	 *             Wenn eine CSV-Datei nicht gelesen werden kann
	 */
	void importTechnomathematiker(Map<String, Resource> files) throws FileNotFoundException, IOException;

}
