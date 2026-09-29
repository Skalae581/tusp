package de.tuhh.tusp.webservice.wsoutput.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.his.ws.personaddressservice.EmailMeta;
import de.his.ws.personaddressservice.PersonAddressesByNotification;
import de.his.ws.personaddressservice.PersonAddressesByNotificationsType;
import de.his.ws.personaddressservice.PhoneMeta;
import de.his.ws.personaddressservice.PostaddressMeta;
import lombok.Getter;

public class PersonAdressesWrapper {

	private Long personId;

	@Getter
	private List<HisEAddress> eaddresses = new ArrayList<>();

	@Getter
	private List<HisEAddress> unmappedEaddresses = new ArrayList<>();

	@Getter
	private List<HisPostAddress> postAdresses = new ArrayList<>();

	@Getter
	private List<HisPostAddress> unmappedPostAdresses = new ArrayList<>();

	@Getter
	private boolean init = false;

	/**
	 * Constructor für die Ergebnisse einer Suche
	 *
	 * @param personId
	 */
	public PersonAdressesWrapper(Long personId) {
		this.personId = personId;
	}

	/**
	 * Constructor für die Ergebnisse einer Suche
	 *
	 * @param readAddresses
	 * @param personId
	 */
	public PersonAdressesWrapper(PersonAddressesByNotificationsType readAddresses, Long personId) {
		this.personId = personId;
		setExistingAddresses(readAddresses);
	}

	/**
	 *
	 * @param category
	 * @param type
	 * @param tag
	 * @return Das bestehnde Object oder ein leeres (value == null)
	 */
	public HisEAddress getEAddress(NotificationCategory category, EAdressType type, AddressTag tag) {

		for (HisEAddress addr : eaddresses) {
			if (addr.getNotificationCategories().contains(category) && addr.getEaddressType().equals(type)
					&& tag.equals(addr.getAddressTag())) {
				return addr;
			}
		}
		HisEAddress addr = new HisEAddress();
		addr.setEaddressType(type);
		addr.setAddressTag(tag);
		addr.setPersonId(personId);
		addr.getNotificationCategories().add(category);
		eaddresses.add(addr);
		unmappedEaddresses.add(addr);
		return addr;
	}

	/**
	 *
	 * @param category
	 * @param type
	 * @param tag
	 * @return Das bestehnde Object oder ein leeres (value == null)
	 */
	public HisPostAddress getPostAddress(NotificationCategory[] categories, AddressTag[] tags) {

		if (categories.length == 0 || tags.length == 0) {
			return null;
		}

		for (HisPostAddress addr : postAdresses) {
			if (addr.getNotificationCategories() == null || addr.getAddressTag() == null) {
				continue;
			}
			for (NotificationCategory nc : categories) {
				for (AddressTag tag : tags) {
					if (addr.getNotificationCategories().contains(nc) && addr.getAddressTag() == tag) {
						return addr;
					}
				}
			}
		}

		HisPostAddress addr = new HisPostAddress();
		addr.setAddressTag(tags[0]);
		addr.getNotificationCategories().addAll(Arrays.asList(categories));
		addr.setPersonId(personId);
		postAdresses.add(addr);
		unmappedPostAdresses.add(addr);
		return addr;
	}

	public List<HisPostAddress> getUpdatePostAddresses() {

		ArrayList<HisPostAddress> result = new ArrayList<>();

		return result;
	}

	/**
	 * Liest die Adressen ein
	 *
	 * @param readAddresses
	 */
	public void setExistingAddresses(PersonAddressesByNotificationsType readAddresses) {

		this.init = true;

		for (PersonAddressesByNotification addrMitCat : readAddresses.getPersonAddressesByNotification()) {
			for (EmailMeta addr : addrMitCat.getEmails().getEmailMeta()) {
				HisEAddress newAddr = new HisEAddress(addr);
				eaddresses.add(newAddr);
			}
			for (PhoneMeta addr : addrMitCat.getPhones().getPhoneMeta()) {
				HisEAddress newAddr = new HisEAddress(addr);
				eaddresses.add(newAddr);
			}
			for (PostaddressMeta addr : addrMitCat.getPostaddresses().getPostaddressMeta()) {
				HisPostAddress newAddr = new HisPostAddress(addr);
				postAdresses.add(newAddr);
			}
		}
	}
}
