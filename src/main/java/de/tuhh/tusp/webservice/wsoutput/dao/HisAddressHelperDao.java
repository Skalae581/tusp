package de.tuhh.tusp.webservice.wsoutput.dao;

import de.his.ws.personaddressservice.EaddressInfosType;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

public interface HisAddressHelperDao {

	/**
	 * Erstellt eine HisPostAddress
	 *
	 * @param eaddress
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void create(HisEAddress address);

	/**
	 * Erstellt eine HisPostAddress
	 *
	 * @param eaddress
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void create(HisPostAddress address);

	Long createNewEmailForOffice(HisEAddress eaddress);

	/**
	 * Löscht eine Adresse
	 *
	 * @param id
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void deleteAddress(HisAddress address) throws ServiceFault;

	EaddressInfosType findEAdress(String value);

	/**
	 * Liest die Adressen einer Person
	 *
	 * @param personId
	 * @return Liste der Adressen
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	PersonAdressesWrapper readPersonAddresses(Long personId) throws ServiceFault;

	/**
	 * Aktualisiert eine HisEAddress
	 *
	 * @param eaddress
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void update(HisEAddress eaddress) throws ServiceFault;

	/**
	 * Aktualisiert eine HisPostAddress
	 *
	 * @param eaddress
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void update(HisPostAddress address);

}
