package de.tuhh.tusp.lvp.util;

import java.util.GregorianCalendar;

import de.tuhh.tusp.utils.CalendarUtils;

public class LvpUtils {

	private static final String WINTER_SEMESTER_ID = "31";
	private static final String SOMMER_SEMESTER_ID = "30";

	public static String getCurrentSemesterId() {

		String sem = CalendarUtils.getSemesterName(new GregorianCalendar());
		if (sem.charAt(0) == 'W') {
			return WINTER_SEMESTER_ID; // WINTER SEMSTER
		} else {
			return SOMMER_SEMESTER_ID; // SOMMER SEMETER
		}
	}
}
