package de.tuhh.tusp.app.vorabcheck.service;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;
import de.tuhh.tusp.utils.CrudService;

public interface BereichArchiveService extends CrudService<BereichArchive> {

	/**
	 * Erstellt ein Objekt der Klasse BereichArchive aus einem Objekt der Klasse Bereich
	 *
	 * @param bereichArchive
	 * @param bereich
	 * @param newStgId
	 * @param sessionId
	 * @return Objekt der Klasse Bereich
	 */
	BereichArchive setArchive(BereichArchive bereichArchive, Bereich bereich, Long newStgId, Long sessionId);

}
