package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.Date;

import de.his.ws.personservice.PersonExisting;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

public interface HisPersonServiceOutput {

	/**
	 * Anlegen einer Person
	 *
	 * @param user
	 * @return die PersonID
	 * @throws ServiceFault
	 *             Wenn Probleme durch die Webservices entstehen
	 */
	Long createPerson(HisPerson user) throws ServiceFault;

	/**
	 * Löschen einer Person
	 *
	 * @param personId
	 * @throws ServiceFault
	 *             Wenn Probleme durch die Webservices entstehen
	 */
	void deletePerson(Long personId) throws ServiceFault;

	/**
	 *
	 * finde person über id
	 *
	 * @param personId
	 * @return
	 */
	PersonExisting fetchPerson(Long personId);

	/**
	 * Liefert person ID aus HIO falls vorhanden, sonst null
	 *
	 * @param username
	 * @return id oser null
	 * @throws ServiceFault
	 *             Wenn der Webservice Probleme macht
	 */
	Long getPersonIdIfExists(String lastname, String firstname, Date dateOfBirth) throws ServiceFault;

	/**
	 * Aktualisieren einer Person
	 *
	 * @param user
	 * @throws ServiceFault
	 *             Wenn Probleme durch die Webservices entstehen
	 */
	void updatePerson(HisPerson user) throws ServiceFault;
}
