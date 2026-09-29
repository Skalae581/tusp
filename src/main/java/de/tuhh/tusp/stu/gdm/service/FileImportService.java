package de.tuhh.tusp.stu.gdm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;

import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;

public interface FileImportService {

	/**
	 * Methode liefert die Gebührendaten-Files des Tages. Zusätzlich löscht sie alle veralteten Files.
	 *
	 * Das Verzeichnis mit den Files kann konfiguriert werden, im ConfigService mit dem Key
	 * de.tuhh.tusp.stu.gdm.GebuehrenDatenImportPattern
	 *
	 * @return Liste der Gebührendaten-Files (kann auch leer sein wenn keine Dateien da sind)
	 * @throws IOException
	 *             Wenn das Verzeichnis mit den Gebührendaten nicht gelesen werden kann
	 * @throws KeineAktuellenFilesException
	 *             Wenn keine Dateien des aktuellen Tages vorhanden sind, aber ältere Dateien
	 */
	List<Resource> findGebuehrenFiles() throws IOException, KeineAktuellenFilesException;
}
