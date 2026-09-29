package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlArchiveDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.utils.CrudService;

public interface StudiengangsauswahlArchiveService extends CrudService<StudiengangsauswahlArchive> {

	/**
	 * Methode zum Archivieren eines Studienganges / ECTS-Bogens
	 *
	 * @param stgId
	 * @param grund
	 */
	void archive(Long stgId, String grund);

	/**
	 * @see StudiengangsauswahlArchiveDao
	 *
	 * @param originId
	 * @return
	 */
	StudiengangsauswahlArchive fetchByOriginId(Long originId);

	/**
	 * @see StudiengangsauswahlArchiveDao
	 *
	 * @param stgId
	 * @return
	 */
	List<Map<String, Object>> findAllByOriginId(Long stgId);

	/**
	 * @see StudiengangsauswahlArchiveDao
	 *
	 * @return
	 */
	List<Map<String, Object>> findArchiveOriginIds();

	/**
	 * @see StudiengangsauswahlArchiveDao
	 *
	 * @param newStgId
	 * @return
	 */
	StudiengangsauswahlArchive getNewData(Long newStgId);

	/**
	 * Erstellt ein Objekt der Klasse StudiengangsauswahlArchive aus einem Objekt der Klasse Studiengangsauswahl
	 *
	 * @param studiengangsauswahlArchive
	 * @param stg
	 * @param bearbeiter
	 * @param grund
	 * @return Objekt der Klasse StudiengangsauswahlArchive
	 */
	StudiengangsauswahlArchive setArchive(StudiengangsauswahlArchive studiengangsauswahlArchive,
			Studiengangsauswahl stg, String bearbeiter, String grund);

}
