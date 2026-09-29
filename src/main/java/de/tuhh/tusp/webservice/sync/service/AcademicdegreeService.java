package de.tuhh.tusp.webservice.sync.service;

import java.util.Locale;

import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;
import de.tuhh.tusp.webservice.sync.pojo.Person;

public interface AcademicdegreeService extends WebserviceSync {

	/**
	 * Gibt den Academicdegree samt Suffix einer Person als String zurück.
	 *
	 * Es wird das Session-Locale verwendet
	 *
	 * @param person
	 * @return Academicdegree + Suffix
	 */
	String academicdegreeAsString(Person person);

	/**
	 * Gibt den Academicdegree samt Suffix einer Person als String zurück.
	 *
	 * @param person
	 * @param locale
	 *            Locale.ENGLISH für zur englischen Darstellung, alles andere zur Deutschen
	 * @return Academicdegree + Suffix
	 */
	String academicdegreeAsString(Person person, Locale locale);

	/**
	 * Versucht in einem String den {@link Academicdegree} zu erkennen und das dazugehörige Objekt zu laden.
	 * 
	 * @param title
	 * @return den Academicdegree
	 * @throws RuntimeException
	 *             wenn kein passender {@link Academicdegree} gefunden werden konnte.
	 */
	Academicdegree fetchByString(String title);

	/**
	 * Trennt von einem String den Academicdegree ab und gibt den Suffix zurück
	 * 
	 * @param title
	 * @param degree
	 * @return den Suffix
	 */
	String fetchSuffixFromString(String title, Academicdegree degree);

	/**
	 * Setzt in einem {@link Person}-Objekt die Academicdegree-ID und den Suffix
	 *
	 * @param person
	 * @param degree
	 *            z.B. Master of Science Informatics
	 *
	 * @throws RuntimeException
	 *             wenn kein passender {@link Academicdegree} gefunden werden konnte.
	 */
	void setAcademicdegree(Person person, String degree) throws RuntimeException;
}
