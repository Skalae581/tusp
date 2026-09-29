package de.tuhh.tusp.webservice.wsoutput.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import de.his.ws.personaddressservice.AddressMeta;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class HisAddress {

	protected Long addressId;
	protected XMLGregorianCalendar validTo;
	protected XMLGregorianCalendar validFrom;
	protected Long personId;
	protected XMLGregorianCalendar createdAt;
	protected XMLGregorianCalendar updatedAt;
	protected AddressTag addressTag;

	@Setter(AccessLevel.NONE)
	protected List<NotificationCategory> notificationCategories = new ArrayList<>();

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	protected List<NotificationCategory> notificationCategoriesOnLoad;

	/**
	 * Constructor für neue Adressen
	 */
	public HisAddress() {}

	/**
	 * Constructor initialisert die Werte, die alle {@link AddressMeta} Klassen mitbringen
	 *
	 * @param addr
	 */
	public HisAddress(AddressMeta addr) {
		addressId = addr.getAddressId();
		validTo = addr.getValidTo();
		validFrom = addr.getValidFrom();
		personId = addr.getPersonId();
		createdAt = addr.getCreatedAt();
		updatedAt = addr.getUpdatedAt();
		notificationCategoriesOnLoad = new ArrayList<>();
		for (String name : addr.getNotificationCategories().getNotificationCategory()) {
			NotificationCategory nc = NotificationCategory.value(name);
			notificationCategoriesOnLoad.add(nc);
			notificationCategories.add(nc);
		}
	}

	/**
	 * Gibt alle neu hjinzugekommenen {@link NotificationCategory} zurück
	 *
	 * @return liste der NCs
	 */
	public List<NotificationCategory> getNewNotificationCategories() {

		if (notificationCategoriesOnLoad == null) {
			return notificationCategories;
		}

		List<NotificationCategory> list = new ArrayList<>();
		for (NotificationCategory nc : notificationCategories) {
			if (!notificationCategoriesOnLoad.contains(nc)) {
				list.add(nc);
			}
		}
		return list;
	}

	/**
	 * Gibt alle entfernen {@link NotificationCategory} zurück
	 *
	 * @return liste der NCs
	 */
	public List<NotificationCategory> getRemovedNotificationCategories() {

		if (notificationCategoriesOnLoad == null) {
			return new ArrayList<>();
		}

		List<NotificationCategory> list = new ArrayList<>();
		for (NotificationCategory nc : notificationCategoriesOnLoad) {
			if (!notificationCategories.contains(nc)) {
				list.add(nc);
			}
		}
		return list;
	}
}
