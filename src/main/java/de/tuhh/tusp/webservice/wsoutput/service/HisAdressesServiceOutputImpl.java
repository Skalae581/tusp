package de.tuhh.tusp.webservice.wsoutput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.webservice.wsoutput.dao.HisAddressHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

@Component
public class HisAdressesServiceOutputImpl implements HisAddressesServiceOutput {

	@Autowired
	private HisAddressHelperDao addressHelperDao;

	@Override
	public PersonAdressesWrapper readPersonAddresses(Long personId) {

		return addressHelperDao.readPersonAddresses(personId);
	}

	@Override
	public void updateOrCreateAddresses(HisPerson user) {

		PersonAdressesWrapper addresses = user.getAddresses();

		// Wenn nicht bereits geladen wurde, lade hier die bestehenden Adressen und
		// mache, dass ein Update gemacht wird, indme die ID des bisherigen Datensatzes in den neuen kommt
		if (!addresses.isInit()) {

			PersonAdressesWrapper readAddresses = addressHelperDao.readPersonAddresses(user.getIdPerson());

			for (HisPostAddress soll : addresses.getUnmappedPostAdresses()) {

				AddressTag[] ats = new AddressTag[] { soll.getAddressTag() };
				NotificationCategory[] ncs =
						soll.getNotificationCategories()
							.toArray(new NotificationCategory[soll.getNotificationCategories().size()]);

				HisPostAddress ist = readAddresses.getPostAddress(ncs, ats);
				soll.setAddressId(ist.getAddressId());
				soll.setPersonId(user.getIdPerson());
			}

			for (HisEAddress soll : addresses.getUnmappedEaddresses()) {

				HisEAddress ist = readAddresses.getEAddress(soll.getNewNotificationCategories().get(0),
															soll.getEaddressType(), soll.getAddressTag());
				soll.setAddressId(ist.getAddressId());
				soll.setPersonId(user.getIdPerson());
			}
		}

		// EAddresses aktualisieren oder anlegen
		for (HisEAddress address : addresses.getEaddresses()) {

			if (address.getValue().isEmpty()) {
				continue;
			}

			if (address.getAddressId() != null) {
				addressHelperDao.update(address);
			}
			else {
				addressHelperDao.create(address);
			}
		}

		// PostAddresses aktualisieren oder anlegen
		for (HisPostAddress address : addresses.getPostAdresses()) {

			if (address.getAddressId() != null) {
				addressHelperDao.update(address);
			}
			else {
				addressHelperDao.create(address);
			}
		}
	}
}
