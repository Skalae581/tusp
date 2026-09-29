package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.json.JSONException;
import org.springframework.core.io.Resource;

import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.webservice.exception.NoRolesDefinedForOuException;
import de.tuhh.tusp.webservice.exception.RolesFromFileException;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

public interface HisRoleHelperDao {

	/**
	 * Prüft, ob in den Rollen eine mit entsprechendem Uniquename ist
	 *
	 * @param roles      Rollen z.B. eines Bnutzers
	 * @param uniquename
	 * @return die {@link Orgrole} order null
	 */
	Orgrole filterOrgrole(List<Orgrole> roles, String uniquename);

	/**
	 * lese die Rolle anhand ID
	 *
	 * @param id
	 * @return
	 * @throws ServiceFault
	 */
	SearchedRolesType findRoles60(String id, String uname) throws ServiceFault;

	/**
	 * Holt die gültigen Rollen einer Person
	 *
	 * @param personId
	 * @return Liste der Rollen
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	List<Orgrole> findValidRolesForPerson(Long personId) throws ServiceFault;

	/**
	 * alle Rollen abfragen valid und invalid
	 *
	 * @param personId
	 * @return
	 * @throws NoSuchElementException
	 */
	List<Orgrole> getAllRolesForPerson(long personId) throws NoSuchElementException;

	/**
	 * Holt alle Rollen einer Person
	 *
	 * @param personId
	 * @return sie Rollen
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	List<Orgrole> getAllRolesForPerson(Long personId) throws ServiceFault;

	/**
	 * Holt die Rollenzuordnung eines Bnutzers zu einer Rolle in HIO
	 *
	 * @param personId
	 * @param roleName
	 * @return Die Rolle oder Null, wenn sie nicht gefundne wurde
	 * @throws NoSuchElementException Wenn die Rolle nicht anhand ihres Namens
	 *                                gefunden werden kann
	 * @throws ServiceFault           Wenn die Rollen der Person nicht abrufbar sind
	 */
	Orgrole getRoleForPerson(long personId, String roleName) throws NoSuchElementException, ServiceFault;

	/**
	 * Holt alle Rollen aus der JSON Datei. Key der Map ist die Orgaeinheit.
	 *
	 * @param file    eine ClassPathRessource
	 * @param validTo
	 * @return Die Rollen aus dem File
	 * @throws RolesFromFileException Wirft eine Exception wenn die Datei nicht
	 *                                gelesen werden kann.
	 * @throws JSONException          Wenn die Datei syntaktisch oder inhaltlich
	 *                                nicht korrekt ist.
	 */
	Map<String, List<HisRole>> getRolesFromFile(Resource file, GregorianCalendar validTo)
			throws RolesFromFileException, JSONException;

	/**
	 * Holt die Rollen einer OU aus der JSON Datei. Als Key der Map wird die OU
	 * verwendet. Es kann also nur einen Eintrag geben.
	 *
	 * @param file    eine ClassPathRessource
	 * @param ou
	 * @param validTo
	 * @return Die Rollen der OU
	 * @throws NoRolesDefinedForOuException Wirft Exception wenn für die OU keine
	 *                                      Rollen hinterlegt sind
	 * @throws RolesFromFileException       Wirft eine Exception wenn die Datei
	 *                                      nicht gelesen werden kann.
	 * @throws JSONException                Wenn die Datei syntaktisch oder
	 *                                      inhaltlich nicht korrekt ist.
	 */
	List<HisRole> getRolesFromFileByOu(Resource file, String ou, GregorianCalendar validTo)
			throws NoRolesDefinedForOuException, RolesFromFileException, JSONException;

	/**
	 * lese rolle anhand id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceFault
	 */
	SearchedRolesType readRoleById(String id) throws ServiceFault;

	/**
	 * Entzieht einer Person eine Rolle
	 *
	 * @param personId
	 * @param orgunitLid
	 * @param roleId
	 * @throws ServiceFault Wenn die Rolle nicht uzgeordnet war oder was schief geht
	 */
	void removeRoleFromPersonInOrgunit(Long personId, Long orgunitLid, Long roleId) throws ServiceFault;

	boolean roleForPersonWithNameExists(long personId, String roleName);

	/**
	 * Speichert eine Rolle für eine Person mit einer OU
	 *
	 * @param parameters
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */

	void setPersonToOrgunitWithRole(SetPersonToOrgunitWithRole parameters) throws ServiceFault;

	/**
	 * bestehende rolle aktualisieren
	 *
	 * @param bestehendeRole
	 */
	void updateOrgRole(Orgrole bestehendeRole);

}
