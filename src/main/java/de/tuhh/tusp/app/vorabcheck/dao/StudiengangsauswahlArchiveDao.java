package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.utils.BaseDao;

public interface StudiengangsauswahlArchiveDao extends BaseDao<StudiengangsauswahlArchive> {

	/**
	 * Holt einen archivierten Studiengang anhand der Original-Stg-ID
	 *
	 * @param stgId
	 * @return Objekt der Klasse StudiengangsauswahlArchive
	 */
	StudiengangsauswahlArchive fetchByOriginId(Long originId);

	/**
	 * Holt alle archivierten Studiengänge anhand der Original-Stg-ID
	 *
	 * @param stgId
	 * @return Liste von archivierten Studiengängen
	 */
	List<Map<String, Object>> findAllByOriginId(Long stgId);

	/**
	 * Holt alle Original-Stg-IDs der archivierten Studiengänge
	 *
	 * @return Liste von Original-Stg-IDs aus dem Archiv
	 */
	List<Map<String, Object>> findArchiveOriginIds();

	/**
	 * Holt die Session-Id zum frisch archivierten Studiengang
	 *
	 * @param newStgId
	 * @return Objekt von StudiengangsauswahlArchive / Session-Id
	 */
	StudiengangsauswahlArchive getNewData(Long newStgId);

}
