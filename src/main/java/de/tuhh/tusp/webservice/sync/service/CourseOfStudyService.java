package de.tuhh.tusp.webservice.sync.service;

import java.util.List;

import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;

public interface CourseOfStudyService extends WebserviceSync {

	/**
	 * Holt einen Studiengang
	 *
	 * @param hisId
	 * @return der stg
	 */
	CourseOfStudy fetchById(Long hisId);

	/**
	 * Sucht Studiengange anhand von Name und Abschluss
	 *
	 * @param stg
	 * @param abschl
	 * @return Liste der Studiengänge
	 */
	List<CourseOfStudy> findByStgUndAbschl(String stg, String abschl);

	/**
	 * Gibt den Abschluss des stg zurück
	 *
	 * @param courseOfStudy
	 * @return z.B. MS oder BS
	 */
	String getAbschlussFromCourseOfStudy(CourseOfStudy courseOfStudy);

	/**
	 * Gibt die PO-Version des stg zurück
	 *
	 * @param courseOfStudy
	 * @return z.B. PO92
	 */
	String getPOVersionFromCourseOfStudy(CourseOfStudy courseOfStudy);

	/**
	 * Gibt den stg name des stg zurück
	 *
	 * @param courseOfStudy
	 * @return z.B. MB
	 */
	String getStgNameFromCourseOfStudy(CourseOfStudy courseOfStudy);

	/**
	 * Gibt die Vertiefung des stg zurück
	 *
	 * @param courseOfStudy
	 * @return z.B. MB
	 */
	String getVertiefungFromCourseOfStudy(CourseOfStudy courseOfStudy);
}
