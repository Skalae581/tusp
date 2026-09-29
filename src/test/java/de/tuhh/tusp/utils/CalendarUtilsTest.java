package de.tuhh.tusp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

public class CalendarUtilsTest {

	Date date;
	Date test;

	@Before
	public void before() {

		Calendar cal = Calendar.getInstance();
		cal.set(2017, 04, 17, 0, 0, 0); // 17. Mai 2017
		date = cal.getTime();
	}

	private boolean compareDates(Date d1, Date d2) {

		if (d1 == null) {
			return false;
		}
		if (d2 == null) {
			return false;
		}

		long l1 = d1.getTime() / 1000;
		long l2 = d2.getTime() / 1000;

		return l1 == l2;
	}

	@Test
	public void dateCompareTest() {

		CalendarUtils cu = new CalendarUtils();
		XMLGregorianCalendar a =
				CalendarUtils.stringToXMLGregorianCalendar("2018-11-23", CalendarUtils.DATEFORMAT_ENGLISH);
		XMLGregorianCalendar b =
				CalendarUtils.stringToXMLGregorianCalendar("2018-11-24", CalendarUtils.DATEFORMAT_ENGLISH);

		assertEquals(0, cu.dateCompare(a, a));
		assertEquals(-1, cu.dateCompare(a, b));
		assertEquals(1, cu.dateCompare(b, a));
	}

	@Test
	public void dateToDatumsStringTest() {

		LocaleContextHolder.setLocale(Locale.GERMAN);
		assertEquals("17.05.2017", CalendarUtils.dateToDatumsString(date));

		LocaleContextHolder.setLocale(Locale.CHINESE);
		assertEquals("2017-05-17", CalendarUtils.dateToDatumsString(date));

		assertNull(CalendarUtils.dateToDatumsString(null));

		assertEquals("17.05.2017", CalendarUtils.dateToDatumsString(date, Locale.GERMAN));
		assertEquals("2017-05-17", CalendarUtils.dateToDatumsString(date, Locale.ENGLISH));
		assertEquals("2017-05-17", CalendarUtils.dateToDatumsString(date, Locale.ITALIAN));
		assertNull(CalendarUtils.dateToDatumsString(null, Locale.KOREA));
	}

	@Test
	public void getNowAsXMLGregorianCalendarTest() {

		Date now = new Date();
		XMLGregorianCalendar cal = new CalendarUtils().getNowAsXMLGregorianCalendar();

		assertTrue(now.getTime() - cal.toGregorianCalendar().getTimeInMillis() < 200);

	}

	@Test
	public void getSemesterTest() throws Exception {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(CalendarUtils.stringToDate("2018-03-30"));
		assertEquals(20172, CalendarUtils.getSemester(cal));

		cal.setTime(CalendarUtils.stringToDate("2018-01-01"));
		assertEquals(20172, CalendarUtils.getSemester(cal));

		cal.setTime(CalendarUtils.stringToDate("2018-04-01"));
		assertEquals(20181, CalendarUtils.getSemester(cal));

		cal.setTime(CalendarUtils.stringToDate("2018-09-30"));
		assertEquals(20181, CalendarUtils.getSemester(cal));

		cal.setTime(CalendarUtils.stringToDate("2018-10-01"));
		assertEquals(20182, CalendarUtils.getSemester(cal));

		cal.setTime(CalendarUtils.stringToDate("2019-10-18"));
		assertEquals(20192, CalendarUtils.getSemester(cal));
	}

	@Test
	public void getXMLGregorianCalendarTest() {

		XMLGregorianCalendar cal = CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.HOUR_OF_DAY, 1);
		Date now = new Date(new Date().getTime() + 3600000); // jetz +1h
		Date test = new Date(cal.toGregorianCalendar().getTimeInMillis());
		assertTrue(compareDates(now, test));
	}

	@Test
	public void isFuture_CalendarTest() throws ParseException {

		Calendar future = new GregorianCalendar();
		future.add(Calendar.SECOND, 10); // 10 sec in der Zugunft
		Calendar past = new GregorianCalendar(); // milli secs in der
													// Vergangenheit

		assertTrue(CalendarUtils.isFuture(future));
		assertFalse(CalendarUtils.isFuture(past));

		Calendar nullCalendar = null;
		assertFalse(CalendarUtils.isFuture(nullCalendar));
	}

	@Test
	public void isFuture_DateTest() throws ParseException {

		Date now = new Date();
		Date future = new Date(now.getTime() + 10000); // 10 sec in der Zugunft
		Date past = new Date(now.getTime() - 10000); // 10 sec in der
														// Vergangenheit

		assertTrue(CalendarUtils.isFuture(future));
		assertFalse(CalendarUtils.isFuture(past));
		assertFalse(CalendarUtils.isFuture(new Date()));

		Date nullDate = null;
		assertFalse(CalendarUtils.isFuture(nullDate));
	}

	@Test
	public void isFuture_StringTest() throws ParseException {

		assertTrue(CalendarUtils.isFuture("31.12.2100"));
		assertFalse(CalendarUtils.isFuture("1990-01-01"));

		String nullString = null;
		assertFalse(CalendarUtils.isFuture(nullString));
	}

	@Test
	public void lastDayOfSemesterTest1() throws Exception {

		XMLGregorianCalendar test1 = CalendarUtils.lastDayOfSemester(20151);
		assertEquals(2015, test1.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(8, test1.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 8
																					// weil
																					// nullbasiert
		assertEquals(30, test1.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void lastDayOfSemesterTest2() throws Exception {

		XMLGregorianCalendar test2 = CalendarUtils.lastDayOfSemester(20172);
		assertEquals(2018, test2.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(2, test2.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 2
																					// weil
																					// nullbasiert
		assertEquals(30, test2.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void lastDayOfSemesterTest3() throws Exception {

		XMLGregorianCalendar test3 = CalendarUtils.lastDayOfSemester(20171, 1);
		assertEquals(2018, test3.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(2, test3.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 2
																					// weil
																					// nullbasiert
		assertEquals(30, test3.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void lastDayOfSemesterTest4() throws Exception {

		XMLGregorianCalendar test4 = CalendarUtils.lastDayOfSemester(20181, -1);
		assertEquals(2018, test4.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(2, test4.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 2
																					// weil
																					// nullbasiert
		assertEquals(30, test4.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void lastDayOfSemesterTest5() throws Exception {

		XMLGregorianCalendar test5 = CalendarUtils.lastDayOfSemester(20142, 1);
		assertEquals(2015, test5.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(8, test5.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 8
																					// weil
																					// nullbasiert
		assertEquals(30, test5.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void lastDayOfSemesterTest6() throws Exception {

		XMLGregorianCalendar test6 = CalendarUtils.lastDayOfSemester(20152, -1);
		assertEquals(2015, test6.toGregorianCalendar().get(GregorianCalendar.YEAR));
		assertEquals(8, test6.toGregorianCalendar().get(GregorianCalendar.MONTH)); // 8
																					// weil
																					// nullbasiert
		assertEquals(30, test6.toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test
	public void sopToHisSemester() throws Exception {

		assertEquals(Long.valueOf(20172), CalendarUtils.sopToHisSemester("WS 2017/18"));
		assertEquals(Long.valueOf(20181), CalendarUtils.sopToHisSemester("SS 2018"));

		try {
			CalendarUtils.sopToHisSemester("AS 2018");
			fail("Es hätte eine Exceptiongeben sollen");
		}
		catch (RuntimeException e) {}

		try {
			CalendarUtils.sopToHisSemester("SS 20181");
			fail("Es hätte eine Exceptiongeben sollen");
		}
		catch (RuntimeException e) {}
	}

	@Test
	public void stringToDate_Ausnahmen_Test() throws ParseException {

		assertNull(CalendarUtils.stringToDate(null));

		try {
			CalendarUtils.stringToDate("17-05-x7 00:00:00");
			fail("Es hätte eine Exceptiongeben sollen");
		}
		catch (ParseException e) {}

		try {
			CalendarUtils.stringToDate("asdf");
			fail("Es hätte eine Exceptiongeben sollen");
		}
		catch (ParseException e) {}
	}

	@Test
	public void stringToDate_MitMinutenUndStunden_Test() throws ParseException {

		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.2017 00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.17 00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("2017-05-17 00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17-05-17 00:00")));
	}

	@Test
	public void stringToDate_MitTimeUndSekunden_Test() throws ParseException {

		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.2017 00:00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.17 00:00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("2017-05-17 00:00:00")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17-05-17 00:00:00")));
	}

	@Test
	public void stringToDate_OhneTime_Test() throws ParseException {

		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.2017")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17.05.17")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("2017-05-17")));
		assertTrue(compareDates(date, CalendarUtils.stringToDate("17-05-17")));
	}

	@Test
	public void stringToXMLGregorianCalendar() {

		assertNull(CalendarUtils.stringToXMLGregorianCalendar(null, ""));

		assertTrue(compareDates(date,
								new Date(CalendarUtils	.stringToXMLGregorianCalendar(	"17.05.2017",
																						CalendarUtils.DATEFORMAT_GERMAN)
														.toGregorianCalendar().getTimeInMillis())));

		try {
			CalendarUtils.stringToXMLGregorianCalendar("17.05.2017", "MM-dd-yyyy");
			fail("Exception erwartet");
		}
		catch (RuntimeException e) {
			assertTrue(e.getCause() instanceof ParseException);
		}
	}

	@Test
	public void toDateTest() {

		assertNull(CalendarUtils.toDate((XMLGregorianCalendar) null));

		Date now = CalendarUtils.toDate(new CalendarUtils().getNowAsXMLGregorianCalendar());
		assertTrue(compareDates(new Date(), now));
	}

	@Test
	public void toXMLGregorianCalendarTest() {

		assertNull(CalendarUtils.toXMLGregorianCalendar(null));

		XMLGregorianCalendar cal = CalendarUtils.toXMLGregorianCalendar(new Date());
		assertEquals(new Date().getTime() / 1000, cal.toGregorianCalendar().getTimeInMillis() / 1000);
	}
}
