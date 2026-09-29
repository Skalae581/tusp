package de.tuhh.tusp.webservice.wsoutput.dao;

import javax.xml.ws.Holder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.personaddressservice.EaddressInfosType;
import de.his.ws.personaddressservice.Email;
import de.his.ws.personaddressservice.PersonAddressService;
import de.his.ws.personaddressservice.PersonAddressServicePortType;
import de.his.ws.personaddressservice.Phone;
import de.his.ws.personaddressservice.Postaddress;
import de.his.ws.personaddressservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

/**
 *
 * @author rztjm
 */
@Component
public class HisAddressHelperDaoImpl implements HisAddressHelperDao {

	private static Logger logger = LogManager.getLogger(HisAddressHelperDaoImpl.class);

	@Autowired
	private WebserviceFactory serviceFactory;
	private PersonAddressServicePortType personAddressService;

	@Override
	public void create(HisEAddress eaddress) {

		try {
			long id;
			Phone phone;

			switch (eaddress.getEaddressType()) {

			case EMAIL:
				Email mail = new Email();
				mail.setEmailValue(eaddress.getValue());
				mail.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				mail.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());

				id = getPAPort().createEmail(eaddress.getPersonId(), eaddress.getNotificationCategories().get(0).name(),
						mail);
				break;

			case PHONE:
				phone = new Phone();
				phone.setPhoneNumber(eaddress.getValue());
				phone.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				phone.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());
				id = getPAPort().createPhone(eaddress.getPersonId(), eaddress.getNotificationCategories().get(0).name(),
						phone);
				break;

			case MOBILE:
				phone = new Phone();
				phone.setPhoneNumber(eaddress.getValue());
				phone.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				phone.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());
				id = getPAPort().createPhone(eaddress.getPersonId(), eaddress.getNotificationCategories().get(0).name(),
						phone);
				break;

			default:
				throw new RuntimeException("EAddressType " + eaddress.getEaddressType() + " nicht hinterlegt");
			}

			eaddress.setAddressId(id);
			for (int i = 1; i < eaddress.getNotificationCategories().size(); i++) {
				getPAPort().assignAddressToNotification(id, eaddress.getNotificationCategories().get(i).name());
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public void create(HisPostAddress address) {

		try {

			Postaddress pAddress = address.asPostAddress();

			long id = getPAPort().createPostaddress(address.getPersonId(),
					address.getNotificationCategories().get(0).name(), pAddress);

			address.setAddressId(id);

			for (int i = 1; i < address.getNotificationCategories().size(); i++) {
				getPAPort().assignAddressToNotification(id, address.getNotificationCategories().get(i).name());
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public Long createNewEmailForOffice(HisEAddress eaddress) {
		Email mail = new Email();
		mail.setEmailValue(eaddress.getValue());
		mail.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
		mail.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());

		if (eaddress.getNotificationCategories().isEmpty()) {
			NotificationCategory notCategory = NotificationCategory.OFFICE;
			eaddress.getNotificationCategories().add(notCategory);
		}
		try {
			return getPAPort().createEmail(eaddress.getPersonId(), eaddress.getNotificationCategories().get(0).name(),
					mail);
		} catch (ServiceFault_Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteAddress(HisAddress address) throws ServiceFault {

		if (address == null || address.getAddressId() == null) {
			return;
		}

		try {
			for (NotificationCategory nc : address.getNotificationCategories()) {
				getPAPort().removeAddressFromNotification(address.getAddressId(), nc.name());
			}

			getPAPort().deleteAddress(address.getAddressId());
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public EaddressInfosType findEAdress(String value) {
		try {
			return getPAPort().findEaddress("", "", "", "", value, "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	/**
	 * Service Port Initialisierung
	 *
	 * @return den Port
	 */
	private PersonAddressServicePortType getPAPort() {

		if (personAddressService == null) {
			personAddressService = serviceFactory.getWebservicePort(PersonAddressService.class);
		}

		return personAddressService;
	}

	@Override
	public PersonAdressesWrapper readPersonAddresses(Long personId) throws ServiceFault {

		if (personId == null) {
			return null;
		}

		try {
			return new PersonAdressesWrapper(getPAPort().readAddresses(personId), personId);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	/**
	 * Überträgt die Änderungen in den {@link NotificationCategory} an HIO
	 *
	 * @param addr
	 * @throws ServiceFault_Exception wenn der Webservice Probleme macht
	 */
	private void syncNotificationCategories(HisAddress addr) throws ServiceFault_Exception {

		// Neue NotificationCategories setzen
		for (NotificationCategory nc : addr.getNewNotificationCategories()) {
			getPAPort().assignAddressToNotification(addr.getAddressId(), nc.name());
		}

		// Überzählige NCs entfernen
		for (NotificationCategory nc : addr.getRemovedNotificationCategories()) {
			getPAPort().removeAddressFromNotification(addr.getAddressId(), nc.name());
		}
	}

	@Override
	public void update(HisEAddress eaddress) {

		if (eaddress.getAddressTag() == null) {
			logger.warn("EAddress (id=" + eaddress.getAddressId() + ", value=" + eaddress.getValue()
					+ ") konnte nicht aktualisiert werden weil AddressTag null ist. Keine weitere Fehlerbehandlung!!");
			return;
		}

		try {
			Holder<Long> addressId = new Holder<>(eaddress.getAddressId());
			Phone phone;
			switch (eaddress.getEaddressType()) {
			case EMAIL:
				Email mail = new Email();
				mail.setEmailValue(eaddress.getValue());
				mail.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				mail.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());
				getPAPort().updateEmail(addressId, mail);
				break;

			case PHONE:
				phone = new Phone();
				phone.setPhoneNumber(eaddress.getValue());
				phone.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				phone.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());
				getPAPort().updatePhone(addressId, phone);
				break;

			case MOBILE:
				phone = new Phone();
				phone.setPhoneNumber(eaddress.getValue());
				phone.setAddresstag(eaddress.getAddressTag().name().toLowerCase());
				phone.setEaddresstype(eaddress.getEaddressType().name().toLowerCase());
				getPAPort().updatePhone(addressId, phone);
				break;

			default:
				throw new RuntimeException("EAddressType " + eaddress.getEaddressType() + " nicht hinterlegt");
			}

			syncNotificationCategories(eaddress);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public void update(HisPostAddress address) {

		try {
			Holder<Long> addressId = new Holder<>(address.getAddressId());
			getPAPort().updatePostaddress(addressId, address.asPostAddress());

			syncNotificationCategories(address);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

}
