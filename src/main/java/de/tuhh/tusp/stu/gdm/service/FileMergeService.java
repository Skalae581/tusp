package de.tuhh.tusp.stu.gdm.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

public interface FileMergeService {

	/**
	 * Methode liest eine Reihe von Dateien ein und fasst diese in einem XML-Dokument zusammen (Speichert das aber
	 * nicht).
	 *
	 * Es wird versucht jede einzelne Datei zu verarbeiten. Sollte eine der Dateien Probleme machen, werden diese in
	 * einen MessageCollector gesammelt.
	 *
	 * @param files
	 *            Liste der Dateien, die verarbeitet werden sollen
	 *
	 * @return Das neue Dokument oder null, wenn die Liste leer war oder keines der Files verarbeitet werden konnte.
	 */
	// Document mergeFiles(List<Resource> files);

	Document mergeFiles(List<Resource> files, int sequenceNumber);
}
