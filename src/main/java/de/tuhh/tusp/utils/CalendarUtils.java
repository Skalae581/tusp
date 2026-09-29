package de.tuhh.tusp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CalendarUtils {

	public static final String DATEFORMAT_GERMAN = "dd.MM.yyyy";
	public static final String DATEFORMAT_ENGLISH = "yyyy-MM-dd";
	public static final String DATETIMEFORMAT_GERMAN = "dd.MM.yyyy HH:mm:ss";
	public static final String DATETIMEFORMAT_ENGLISH = "yyyy-MM-dd HH:mm:ss";

	private static Logger logger = LogManager.getLogger(CalendarUtils.class);

	private static DatatypeFactory datatypeFactory;

	static {
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Berechnet den Abstand zwischen zwei {@link XMLGregorianCalendar} in
	 * Millisekunden
	 *
	 * @param cal1
	 * @param cal2
	 * @return null wenn einer Kalender null ist, sonst der Abstand in Millisekunden
	 */
	public static Long absoluteDifferenceInMillis(XMLGregorianCalendar cal1, XMLGregorianCalendar cal2) {

		Long diff = differenceInMillis(cal1, cal2);

		if (diff == null) {
			return null;
		}

		if (diff < 0) {
			diff *= 1;
		}

		return diff;
	}

	/**
	 * Wandelt ein Date in einen String um, entsprechend des Locale des Contextes
	 *
	 * @param date Datum
	 * @return null wenn Date = null, "dd.MM.yyyy" wenn Deutsch oder "yyyy-MM-dd"
	 *         sonst
	 */
	public static String dateToDatumsString(Date date) { // TODO rename in
															// ToString

		Locale l = LocaleContextHolder.getLocale();
		return dateToDatumsString(date, l);
	}

	/**
	 * Wandelt ein Date in einen String um, entsprechend des Locale (Deutsch oder
	 * Rest)
	 *
	 * @param date
	 * @param locale
	 * @return null wenn Date = null, "dd.MM.yyyy" wenn Deutsch oder "yyyy-MM-dd"
	 *         sonst
	 */
	public static String dateToDatumsString(Date date, Locale locale) { // TODO
																		// rename
																		// in
																		// ToString

		if (date == null) {
			return null;
		}

		if (locale.equals(Locale.GERMAN) || locale.equals(Locale.GERMANY)) {
			return new SimpleDateFormat(CalendarUtils.DATEFORMAT_GERMAN).format(date);
		} else {
			return new SimpleDateFormat(CalendarUtils.DATEFORMAT_ENGLISH).format(date);
		}
	}

	/**
	 * Berechnet den Abstand zwischen zwei {@link XMLGregorianCalendar} in Tagen
	 *
	 * @param cal1
	 * @param cal2
	 * @return null wenn einer Kalender null ist, sonst der Abstand in Millisekunden
	 */
	public static Long differenceInDays(XMLGregorianCalendar cal1, XMLGregorianCalendar cal2) {

		if (cal1 == null || cal2 == null) {
			return null;
		}

		return (cal1.toGregorianCalendar().getTimeInMillis() - cal2.toGregorianCalendar().getTimeInMillis()) / 86400000;
	}

	/**
	 * Berechnet den Abstand zwischen zwei {@link XMLGregorianCalendar} in
	 * Millisekunden
	 *
	 * @param cal1
	 * @param cal2
	 * @return null wenn einer Kalender null ist, sonst der Abstand in Millisekunden
	 */
	public static Long differenceInMillis(XMLGregorianCalendar cal1, XMLGregorianCalendar cal2) {

		if (cal1 == null || cal2 == null) {
			return null;
		}

		return cal1.toGregorianCalendar().getTimeInMillis() - cal2.toGregorianCalendar().getTimeInMillis();
	}

	/**
	 * Formartiert ein Datum
	 *
	 * @param date
	 * @param format
	 * @return das formartierte Datum
	 */
	public static String format(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Covertiere String Date zu anderen Format
	 *
	 * @param oldFormat
	 * @param newFormat
	 * @param oldDateString
	 * @return
	 * @throws ParseException
	 */
	public static String formatStringDate(String oldFormat, String newFormat, String oldDateString)
			throws ParseException {

		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(oldDateString);
		sdf.applyPattern(newFormat);
		newDateString = sdf.format(d);

		return newDateString;
	}

	/**
	 * Returns Datum - morgen Tag + 1
	 *
	 * @param curDate
	 * @return
	 * @throws ParseException
	 */
	public static String getNextDate(String curDate, String formatedDate) throws ParseException {

		final SimpleDateFormat format = new SimpleDateFormat(formatedDate);
		final Date date = format.parse(curDate);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}

	/**
	 * Gibt das aktuelle Semester als HIS-Bezeichner zurück.
	 *
	 * 20161 => SoSe16, 20162 => WiSe16/17, 20171 => SoSe17, ...
	 *
	 * @param calendar GregorianCalendar Datum
	 * @return das Semester als Zahl
	 */
	public static int getSemester(GregorianCalendar calendar) {

		int sem = calendar.get(GregorianCalendar.YEAR) * 10;

		if (calendar.get(GregorianCalendar.MONTH) <= GregorianCalendar.MARCH) {
			// DIe ersten drei Monate gehören in das WiSe des Vorjahres
			sem -= 10;
		}

		if (calendar.get(GregorianCalendar.MONTH) > GregorianCalendar.MARCH
				&& calendar.get(GregorianCalendar.MONTH) <= GregorianCalendar.SEPTEMBER) {
			sem += 1;
		} else {
			sem += 2;
		}

		return sem;
	}

	/**
	 * Gibt das aktuelle Semester als HIS-Bezeichner zurück.
	 *
	 * 20161 => SoSe16, 20162 => WiSe16/17, 20171 => SoSe17, ...
	 *
	 * @param calendar GregorianCalendar Datum
	 * @return das Semester String
	 */
	public static String getSemesterName(GregorianCalendar calendar) {

		int sem = calendar.get(GregorianCalendar.YEAR) - 2000;

		if (calendar.get(GregorianCalendar.MONTH) <= GregorianCalendar.MARCH) {
			// DIe ersten drei Monate gehören in das WiSe des Vorjahres
			sem -= 1;
		}

		if (calendar.get(GregorianCalendar.MONTH) >= GregorianCalendar.MARCH
				&& calendar.get(GregorianCalendar.MONTH) < GregorianCalendar.SEPTEMBER) {
			return "SoSe" + sem;
		} else {
			return "WiSe" + sem;
		}

	}

	/**
	 * Gibt die aktuelle Zeit als {@link XMLGregorianCalendar} zurück mit einem
	 * Versatz der über die Parameter gesteuert werden kann.
	 *
	 * @param field  Art des Versatzes z.B. GregorianCalendar.HOUR_OF_DAY
	 * @param amount Größe des Versatzes
	 * @return der {@link XMLGregorianCalendar}
	 */
	public static XMLGregorianCalendar getXMLGregorianCalendar(int field, int amount) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(field, amount);
		return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	}

	/**
	 * Testet ob ein Datum in der Zukunft liegt.
	 *
	 * @param date
	 * @return true wenn date größer als now ist, sonst false
	 */
	public static boolean isFuture(Calendar date) {

		if (date == null) {
			return false;
		}

		GregorianCalendar cal = new GregorianCalendar();
		return date.after(cal);
	}

	/**
	 * Prüft ob ein Datum in der Zukunft liegt
	 *
	 * @param date
	 * @return true wenn date größer als now ist, sonst false
	 */
	public static boolean isFuture(Date date) {

		if (date == null) {
			return false;
		}

		return date.after(new Date());
	}

	/**
	 * Prüft ob ein Datum in der Zukunft liegt
	 *
	 * @param date
	 * @return true wenn date größer als now ist, sonst false
	 * @throws ParseException wenn String kein deutsches oder englisches Datum ist
	 */
	public static boolean isFuture(String date) throws ParseException {

		if (date == null) {
			return false;
		}
		return isFuture(stringToDate(date));

	}

	/**
	 * Prüft ob Stunde, Minute und Sekunde Null sind
	 *
	 * @param date
	 * @return true wenn ja
	 */
	public static boolean isMidnight(Date date) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.SECOND) == 0 && cal.get(GregorianCalendar.MINUTE) == 0
				&& cal.get(GregorianCalendar.HOUR_OF_DAY) == 0;
	}

	/**
	 * Prüft, ob ein Datum in der Vergangenheit liegt
	 *
	 * @param date
	 * @return true, wenn date kleiner als now ist, sonst false
	 */
	public static boolean isPast(Date date) {

		if (date == null) {
			return false;
		}

		return date.before(new Date());
	}

	/**
	 * Prüft, ob ein Datum in der Vergangenheit liegt
	 *
	 * @param date
	 * @return true, wenn date kleiner als now ist, sonst false
	 * @throws ParseException wenn das Datum nicht Parsbar ist
	 */
	public static boolean isPast(String date) throws ParseException {

		if (date == null) {
			return false;
		}
		return isPast(stringToDate(date));

	}

	/**
	 * Berechnet den letzten Tag eies Semesters
	 *
	 * @param semester als HIS-Bezeichner (siehe currentSemester())
	 * @return das Datum
	 */
	public static XMLGregorianCalendar lastDayOfSemester(int semester) {

		return lastDayOfSemester(semester, 0);
	}

	/**
	 * Gibt den letzen Tag ein Semesters zurück. Relativ zu dem aktuellen semester
	 * können offsetsemester angegeben werden.
	 *
	 * @param semester
	 * @param offset
	 * @return Letzten Tag des Semesters
	 */
	public static XMLGregorianCalendar lastDayOfSemester(int semester, int offset) {

		// Eingabesemester parsen
		int year = semester / 10;
		int sem = semester - 10 * year;

		// Offset einrechnen
		int yearOffset = offset / 2;
		int halfYears = offset % 2;
		year += yearOffset;
		if (offset < 0) {
			year--;

			if (halfYears == -1) {
				year += sem == 2 ? 1 : 0;
				sem = sem == 1 ? 2 : 1;
			}
		} else if (halfYears == 1) {
			year += sem == 2 ? 1 : 0;
			sem = sem == 1 ? 2 : 1;
		}

		// Datum erzeugen
		if (sem == 1) {
			// SoSe
			return stringToXMLGregorianCalendar(year + "-09-30", DATEFORMAT_ENGLISH);
		} else {
			// WiSe
			return stringToXMLGregorianCalendar(year + 1 + "-03-30", DATEFORMAT_ENGLISH);
		}
	}

	/**
	 * Wandelt das Semester von 20172 in WS 2017/18 usw.
	 *
	 * @param semester
	 * @return Sop Semester
	 */
	public static String sopFromHisSemester(int semester) {

		int sem = semester % 10;
		int year = semester / 10;

		if (sem == 1) {
			return "SS " + year;
		} else {
			return "WS " + year + "/" + year % 100;
		}
	}

	/**
	 * Wandelt das Semester von beispielsweise WS 2017/18 zu 20172
	 *
	 * @param semester
	 * @return HIS Semester ID oder 0 im Fehlerfall
	 * @throws IllegalArgumentException Wenn das Semester nicht dem Muster
	 *                                  entspricht
	 */
	public static Long sopToHisSemester(String semester) throws IllegalArgumentException {

		if (semester == null) {
			return null;
		}

		if (!semester.matches("^[SW]S \\d{4}(/\\d{2})?$")) {
			throw new IllegalArgumentException("SOP-Semester hat ein falsches Format");
		}

		int sem = semester.startsWith("WS") ? 2 : 1;
		String year = semester.substring(3, 7);

		String hisSemester = year + sem;
		return Long.parseLong(hisSemester);
	}

	/**
	 * Versucht aus einem gegeben String ein Date-Objekt zu machen
	 *
	 * @param datum Ein Datum in deutscher oder englischer Schreibweise
	 * @return Das Date-Objekt oder null wenn Parameter null ist
	 * @throws ParseException Wenn String kein gültiges (deutsch oder englisch)
	 *                        Datum repräsentiert
	 */
	public static Date stringToDate(String datum) throws ParseException {

		if (datum == null) {
			return null;
		}

		String[] formate = new String[] { "dd.MM.yy HH:mm:ss", "dd.MM.yy HH:mm", "dd.MM.yy", "dd.MM.yyyy HH:mm:ss",
				"dd.MM.yyyy HH:mm", CalendarUtils.DATEFORMAT_GERMAN, "yy-MM-dd HH:mm:ss", "yy-MM-dd",
				CalendarUtils.DATEFORMAT_ENGLISH, "yyyyMMdd" };

		for (String format : formate) {
			try {
				return new SimpleDateFormat(format).parse(datum);
			} catch (ParseException e) {
				/* continue */ } // NOPMD
		}

		throw new ParseException("Kein gültiges Datum: " + datum, 0);
	}

	/**
	 * String Konvertierung aus dem einem Datumsformat (z.B. dd.MM.yyyy)
	 *
	 * @param value  ein String
	 * @param format Ein Datumsformat z.B.: CalendarUtils.DATEFORMAT_GERMAN
	 * @return den {@link XMLGregorianCalendar} oder null, wenn value == null
	 * @throws RuntimeException Wenn value mit dem Format nicht geparst werden kann
	 */
	public static XMLGregorianCalendar stringToXMLGregorianCalendar(String value, String format)
			throws RuntimeException {

		if (value == null) {
			return null;
		}

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		Date date;
		try {
			date = simpleDateFormat.parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		gregorianCalendar.setTime(date);
		return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	}

	/**
	 * String Konvertierung aus dem englischen Datumsformat. Es ist ein Alias für
	 * stringToXMLGregorianCalendar
	 *
	 * @param value ein String im Format "yyyy-mm-dd"
	 * @return den {@link XMLGregorianCalendar} oder null, wenn value == null
	 * @throws RuntimeException Wenn value mit dem Englischen Format nicht geparst
	 *                          werden kann
	 */
	public static XMLGregorianCalendar strToXMLGregCal(String value) throws RuntimeException {

		return stringToXMLGregorianCalendar(value, DATEFORMAT_ENGLISH);
	}

	public static Date toDate(LocalDate date) {

		if (date == null) {
			return null;
		}
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDateTime date) {

		if (date == null) {
			return null;
		}
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Erzeugt ein java.util.Date aus einem XMLGregorianCalendar
	 *
	 * @param calendar ein {@link XMLGregorianCalendar}
	 * @return Das Date oder null, wenn Parameter null
	 */
	public static Date toDate(XMLGregorianCalendar calendar) {

		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public static LocalDate toLocalDate(Date date) {

		if (date == null) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date) {

		if (date == null) {
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * Formatiert ein Datum
	 *
	 * @param date
	 * @param format
	 * @return Das formatierte Datum
	 */
	public static String toString(XMLGregorianCalendar date, String format) {

		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(format).format(toDate(date));
	}

	/**
	 * Erzeugt einen XMLGregorianCalendar aus einem java.util.Date
	 *
	 * @param date
	 * @return den {@link XMLGregorianCalendar} oder null, wenn Parameter == null
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {

		if (date == null) {
			return null;
		}

		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			logger.error(ex);
		}
		return xmlCalendar;
	}

	/**
	 * Vergleich zwei Calendar mit einander. Aber nur Yahr, Monat und Tag!
	 *
	 * @param a
	 * @param b
	 * @return 1 wenn a > b, -1 wenn a < b, 0 sonst
	 */
	public int dateCompare(XMLGregorianCalendar a, XMLGregorianCalendar b) {

		if (a.getYear() > b.getYear()) {
			return 1;
		}
		if (a.getYear() < b.getYear()) {
			return -1;
		}

		if (a.getMonth() > b.getMonth()) {
			return 1;
		}
		if (a.getMonth() < b.getMonth()) {
			return -1;
		}

		if (a.getDay() > b.getDay()) {
			return 1;
		}
		if (a.getDay() < b.getDay()) {
			return -1;
		}

		return 0;
	}

	/**
	 * Erzeugt einen {@link XMLGregorianCalendar} mit der aktuellen Zeit
	 *
	 * @return den {@link XMLGregorianCalendar}
	 */
	public XMLGregorianCalendar getNowAsXMLGregorianCalendar() {

		return datatypeFactory.newXMLGregorianCalendar(new GregorianCalendar());
	}

	/**
	 * Erzeugt einen {@link XMLGregorianCalendar} mit dem aktuellen Datum
	 *
	 * @return den {@link XMLGregorianCalendar}
	 */
	public XMLGregorianCalendar getTodayAsXMLGregorianCalendar() {

		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar today = new GregorianCalendar(now.get(GregorianCalendar.YEAR),
				now.get(GregorianCalendar.MONDAY), now.get(GregorianCalendar.DAY_OF_MONTH));
		return datatypeFactory.newXMLGregorianCalendar(today);
	}
}
