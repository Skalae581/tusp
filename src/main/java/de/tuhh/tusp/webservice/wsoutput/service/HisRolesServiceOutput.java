package de.tuhh.tusp.webservice.wsoutput.service;

import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

public interface HisRolesServiceOutput {

	/**
	 * Erstellt oder updatet die Rollen eines Benutzers.
	 * 
	 * @param user
	 * @param personId
	 * @throws ServiceFault
	 *             Wenn der Webservice Probleme macht.
	 * @throws IllegalStateException
	 *             Wenn eine Rollenbezeichnung oder eine OU nicht in eine ID umgewandelt werden kann
	 */
	void updateOrCreateRolesForPerson(HisPerson user, long personId) throws ServiceFault, IllegalStateException;

}
