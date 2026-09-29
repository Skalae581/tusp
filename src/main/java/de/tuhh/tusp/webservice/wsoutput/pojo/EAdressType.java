package de.tuhh.tusp.webservice.wsoutput.pojo;

public enum EAdressType {

	EMAIL, PHONE, MOBILE, FAX; // Ergänzen, wenn nötig

	public static EAdressType value(String name) {

		return valueOf(name.toUpperCase());
	}
}
