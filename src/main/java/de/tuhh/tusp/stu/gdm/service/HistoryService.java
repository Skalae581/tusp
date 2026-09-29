package de.tuhh.tusp.stu.gdm.service;

import java.util.List;

import de.tuhh.tusp.stu.gdm.pojo.History;

public interface HistoryService {

	/**
	 * Ruft die History einer Datei ab.
	 * 
	 * @param filename
	 * @return Liste der History-Einträge
	 */
	List<History> findHistoryByFilename(String filename);
}
