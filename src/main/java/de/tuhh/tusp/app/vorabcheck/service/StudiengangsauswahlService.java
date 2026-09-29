package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlDao;
import de.tuhh.tusp.app.vorabcheck.pojo.EctsForm;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.utils.CrudService;

public interface StudiengangsauswahlService extends CrudService<Studiengangsauswahl> {

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @return Liste der aktiven Studiengänge
	 */
	List<Map<String, Object>> findAllActiveVervollstWithoutStandardkz();

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @param stgId
	 * @return Liste von Bereichen
	 */
	List<Map<String, Object>> findAllBereichById(Long stgId);

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @param bereichIds
	 * @return Liste von Fächern
	 */
	List<Map<String, Object>> findAllFachByIds(List<Long> bereichIds);

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @return Liste alle Studiengänge (einschl. aller Felder / Attributswerte)
	 */
	List<Map<String, Object>> findAllStg();

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @return Liste aller AOS-Studiengänge
	 */
	List<Map<String, Object>> findAllStgAOS();

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @return Liste aller MD-Studiengänge
	 */
	List<Map<String, Object>> findAllStgMD();

	/**
	 * @see StudiengangsauswahlDao
	 *
	 * @return Liste von Stg-IDs
	 */
	List<Map<String, Object>> findBereichStgId();

	/**
	 * Ermittelt die Ergebnisfarbe nach dem Ampelsystem (grün, gelb, rot)
	 *
	 * @param double1
	 * @param double2
	 * @param stgId
	 * @return String (Farbenname)
	 */
	String getColor(Double double1, Double double2, Long stgId);

	/**
	 * Ergebnis ermitteln aus ECTS-Punkten
	 *
	 * @param ectsForm
	 * @param stgId
	 * @return Ergebnisfarbe
	 */
	String getResult(EctsForm ectsForm, Long stgId);

	/**
	 * Studiengangsnamen für ausgewählten Studiengang abrufen
	 *
	 * @param courseofstudyId
	 * @return Studiengangsnamen
	 */
	String getShorttextDe(Long courseofstudyId);

}
