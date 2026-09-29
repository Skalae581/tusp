package de.tuhh.tusp.webservice.wsoutput.service;

import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

public interface HisAddressesServiceOutput {

	/**
	 * Liest die Addressen einer Person
	 * 
	 * @param personId
	 * @return die Adressen
	 */
	PersonAdressesWrapper readPersonAddresses(Long personId);

	/**
	 * Adressen für eine Person werden entweder angelegt oder aktualisiert
	 *
	 * @param user
	 */
	void updateOrCreateAddresses(HisPerson user);

}
