package de.tuhh.tusp.webservice.wsoutput.pojo;

public enum AddressTag {

	COMMUNITY, BUSINESS, HOME, DELIVERY, LOGIN, POSTADDRESS, PRIVAT, RZ, INVOICE, SEMESTER, SEST, UNIASSIST, CEINVOICE,
	ZVS;

	public static AddressTag value(String name) {

		if (name == null) {
			return null;
		}

		return valueOf(name.toUpperCase());
	}

}
