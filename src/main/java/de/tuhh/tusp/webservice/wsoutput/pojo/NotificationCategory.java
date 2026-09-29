package de.tuhh.tusp.webservice.wsoutput.pojo;

public enum NotificationCategory {

	COMA, ALU, ALUG, ALUK, ALUR, CA, COM, OFFICE, EXA, UP, DOSV, JOB, APP, PWRESET, HRM, DOKALL, DOK, RZ, STUALL, STU,
	TEMP, UNIASSIST, WBTALL, WBT, AFFILIATION, MIG;

	public static NotificationCategory value(String name) {

		return valueOf(name.toUpperCase());
	}
}