package de.tuhh.tusp.app.vorabcheck.service;

import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;
import de.tuhh.tusp.utils.CrudService;

public interface FachArchiveService extends CrudService<FachArchive> {

	/**
	 * Erstellt ein Objekt der Klasse FachArchive aus einem Objekt der Klasse Fach
	 *
	 * @param fachArchive
	 * @param fach
	 * @param newBereichId
	 * @param sessionId
	 * @return Objekt der Klasse FachArchive
	 */
	FachArchive setArchive(FachArchive fachArchive, Fach fach, Long newBereichId, Long sessionId);
}
