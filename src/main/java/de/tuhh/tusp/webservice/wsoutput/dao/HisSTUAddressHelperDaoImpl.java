package de.tuhh.tusp.webservice.wsoutput.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.addressservice.AddressService;
import de.his.ws.addressservice.AddressServicePortType;
import de.his.ws.addressservice.AddressesType;
import de.his.ws.addressservice.EAddress;
import de.his.ws.addressservice.NotificationCategoryType;
import de.his.ws.addressservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisSTUAddressHelperDaoImpl implements HisSTUAddressHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	private AddressServicePortType addressService;

	/**
	 * Service Port Initialisierung
	 *
	 * @return den Port
	 */
	private AddressServicePortType getPort() {

		if (addressService == null) {
			addressService = serviceFactory.getWebservicePort(AddressService.class);
		}

		return addressService;
	}

	/**
	 * gibt Adresse OBJ Zurück über Address ID ...}
	 *
	 * @param addressId
	 * @return EAddress
	 * @throws ServiceFault_Exception
	 */
	@Override
	public EAddress readAddressById(long addressId) throws ServiceFault_Exception {
		return getPort().readEAddress(addressId);
	}

	/**
	 * gibt Adressen / IDs Zurück für eine Person mit NotificationType { STU,
	 * STUALL, RZ ...}
	 *
	 * @param personId
	 * @param notificationCategoryType
	 * @return AddressesType
	 * @throws ServiceFault_Exception
	 */
	@Override
	public AddressesType readAddressesByNotificationType(long personId,
			NotificationCategoryType notificationCategoryType) throws ServiceFault_Exception {
		return getPort().readAddressesOfPersonWithNotificationCategory(personId, notificationCategoryType);

	}
}
