package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.Rollen;

public interface RollenService {

	/**
	 * Erstellt zu jeder HIO-Rolle und jeder WorkflowInstanz die entsprechende FaME-Rolle für Auswahllisten
	 */
	void aktualisiere();

	/**
	 * Liefert alle Rollen
	 * 
	 * @return Liste der Rollen
	 */
	List<Rollen> findAll();

}
