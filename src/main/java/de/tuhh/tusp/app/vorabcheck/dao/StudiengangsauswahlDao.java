package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.utils.BaseDao;

public interface StudiengangsauswahlDao extends BaseDao<Studiengangsauswahl> {

	/**
	 * Holt alle Studiengänge mit aktiv_vervollst = 1 sowie standardkz = null
	 *
	 * @return Liste der aktiven Studiengänge ohne gesetztem Standardkz
	 */
	List<Map<String, Object>> findAllActiveVervollstWithoutStandardkz();

	/**
	 * Holt alle Bereiche anhand der entsprechenden Stg-Id
	 *
	 * @param stgId
	 * @return Liste von Bereichen
	 */
	List<Map<String, Object>> findAllBereichById(Long stgId);

	/**
	 * Holt alle Fächer anhand einer Liste von Bereich-Ids
	 *
	 * @param bereichIds
	 * @return Liste von Fächern
	 */
	List<Map<String, Object>> findAllFachByIds(List<Long> bereichIds);

	/**
	 * Holt für alle Vorab-Check-Studiengänge die Studiengangsnamen aus der
	 * CourseOfStudy-Tabelle
	 *
	 * @return Liste aller Studiengänge (einschl. aller Felder / Attributswerte)
	 */
	List<Map<String, Object>> findAllStg();

	/**
	 * Holt alle AOS-Studiengänge
	 *
	 * @return Liste aller MD-Studiengänge
	 */
	List<Map<String, Object>> findAllStgAOS();

	/**
	 * Holt alle MD-Studiengänge
	 *
	 * @return Liste aller MD-Studiengänge
	 */
	List<Map<String, Object>> findAllStgMD();

	/**
	 * Holt alle Stg-IDs, zu denen es Einträge in Vorabcheck_Bereich gibt, also
	 * Bereiche existieren
	 *
	 * @return Liste von Stg-IDs
	 */
	List<Map<String, Object>> findBereichStgId();

	/**
	 * Studiengangsnamen für ausgewählten Studiengang abrufen
	 *
	 * @param courseofstudyId
	 * @return Studiengangsnamen
	 */
	String getShorttextDe(Long courseofstudyId);
}
