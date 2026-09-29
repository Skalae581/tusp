package de.tuhh.tusp.psv.unihh.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.w3c.dom.Document;

public interface GewFileOutputService {

	/**
	 * Gibt eine Gebührendatei zurück
	 *
	 * @param name
	 *            Dateiname
	 * @return Die Datei
	 * @throws FileNotFoundException
	 *             Wenn die Datei nicht gefunden wurde.
	 * @throws RuntimeException
	 *             Wenn GDM nicht richtig konfiguriert ist.
	 * @throws IOException
	 *             Wenn es IO Probleme gibt
	 * @throws SecurityException
	 *             Wenn die Resource nicht in den aufgelisteten Resourcen
	 *             enthalten ist.
	 */
	Resource getFileByName(String name) throws IOException, SecurityException, FileNotFoundException, RuntimeException;

	/**
	 * Gibt eine Liste aller lokalen Gebührendaten Files zurück.
	 *
	 * @return Liste der Dateien
	 * @throws RuntimeException
	 *             Wenn GDM nicht richtig konfiguriert ist
	 * @throws IOException
	 *             Bei IO Problmen
	 */
	List<Resource> listLokaleDateien() throws RuntimeException, IOException;

	/**
	 * Speichert ein Dokument an die nötigen Stellen (Im Dateisystem von jedem
	 * ApplicationServer der Säule).
	 *
	 * Sollte einer der Speicherorte nicht beschreibbar sein, wird dies in einem
	 * MessageCollector gesammelt
	 *
	 * @param document
	 *            Das zu speichernde Dokumnet
	 *
	 * @return Die Ausgabedatei
	 */
	WritableResource writeFile(Document document);
}
