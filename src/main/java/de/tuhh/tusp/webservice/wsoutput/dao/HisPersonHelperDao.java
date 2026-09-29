package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.Date;
import java.util.List;

import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.tuhh.tusp.webservice.exception.ServiceFault;

/**
 *
 * @author jjakus Definition von Personbezogenen-Metoden
 */
public interface HisPersonHelperDao {

	/**
	 * Erstellt eine Person und gibt die PersonId zurück
	 *
	 * @param person
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 *             oder wenn Person null ist
	 * @return neue PeronId
	 */
	Long createPerson(Person person) throws ServiceFault;

	/**
	 * Löscht eine Person
	 *
	 * @param personId
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 */
	void deletePerson(Long personId) throws ServiceFault;

	/**
	 * Gibt PersonExisting zurück falls Person mit @param personId vorhanden
	 *
	 * @param personId
	 * @return PersonExisting (his objekt)
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 */

	PersonExisting fetchPerson(Long personId) throws ServiceFault;

	/**
	 * Sucht Personen nach Rolle
	 *
	 * @param roleIdFilter
	 * @return Liste der Person IDs
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 */
	List<Long> findPersonsByRole(String roleIdFilter) throws ServiceFault;

	/**
	 * Sucht nach Personen anhand der gegebenen Filter
	 *
	 * @param roleIdFilter
	 * @param studystatusFilter
	 * @return Liste der PersonenIds
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 */
	List<Long> findPersonsByRoleAndStudystatus(String roleIdFilter, String studystatusFilter) throws ServiceFault;

	/**
	 * Holt die PersonID wenn die Person existiert
	 *
	 * @param lastname
	 * @param firstname
	 * @param dateOfBirth
	 * @return Person id oder null falls nicht vorhanden
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 * @throws IllegalStateException
	 *             Wenn mehrere IDs gefunden werden
	 */
	Long getPersonIdIfExists(String lastname, String firstname, Date dateOfBirth)	throws ServiceFault,
																					IllegalStateException;

	/**
	 * Sucht nach einer Person mit den gegebenen Daten
	 *
	 * @param lastname
	 *            Name muss das enthalten
	 * @param firstname
	 *            Name muss das enthalten
	 * @param dateOfBirth
	 *            darf null sein, wird dann ignoriert
	 * @return Liste der PersonIDs
	 */
	List<Long> searchFuzzy(String lastname, String firstname, Date dateOfBirth);

	/**
	 * Aktualisiert eine Person
	 *
	 * @param person
	 * @throws ServiceFault
	 *             Im Falle von Exceptions bedingt durch die HIS Webservices
	 *             oder wenn Person null ist
	 */
	void updatePerson(PersonExisting person) throws ServiceFault;
}
