package de.tuhh.tusp.webservice.wsoutput.dao;

import de.his.ws.addressservice.AddressesType;
import de.his.ws.addressservice.EAddress;
import de.his.ws.addressservice.NotificationCategoryType;
import de.his.ws.addressservice.ServiceFault_Exception;

public interface HisSTUAddressHelperDao {

	/**
	 * gibt Adresse OBJ Zurück über Address ID ...}
	 *
	 * @param personId
	 * @param notificationCategoryType
	 * @return AddressesType
	 * @throws ServiceFault_Exception
	 */
	EAddress readAddressById(long addressId) throws ServiceFault_Exception;

	/**
	 * gibt Adressen Zurück für eine Person mit NotificationType { STU, STUALL, RZ
	 * ...}
	 *
	 * @param personId
	 * @param notificationCategoryType
	 * @return AddressesType
	 * @throws ServiceFault_Exception
	 */
	AddressesType readAddressesByNotificationType(long personId, NotificationCategoryType notificationCategoryType)
			throws ServiceFault_Exception;

}
