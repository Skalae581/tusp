package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

public class DatePropertyEditorSupportTest {

	private DatePropertyEditorSupport candidate = new DatePropertyEditorSupport();

	@Test
	public void getAsText_englishLocale_Test() {

		candidate.setValue(new Date(86400000L));

		LocaleContextHolder.setLocale(Locale.US);

		String value = candidate.getAsText();
		assertEquals("1970-01-02", value);
	}

	@Test
	public void getAsText_germanLocale_Test() {

		candidate.setValue(new Date(86400000L));

		LocaleContextHolder.setLocale(Locale.GERMAN);

		String value = candidate.getAsText();
		assertEquals("02.01.1970", value);
	}

	@Test
	public void getAsText_nullValue_Test() {

		candidate.setValue(null);

		LocaleContextHolder.setLocale(Locale.US);

		String value = candidate.getAsText();
		assertEquals(null, value);
	}

	@Test
	public void setAsText_emptyString_Test() {

		LocaleContextHolder.setLocale(Locale.GERMAN);

		candidate.setAsText("");

		assertNull(candidate.getValue());
	}

	@Test
	public void setAsText_englishLocale_Test() {

		LocaleContextHolder.setLocale(Locale.US);

		candidate.setAsText("1970-01-02");

		assertEquals(82800000L, ((Date) candidate.getValue()).getTime());
	}

	@Test
	public void setAsText_germanLocale_Test() {

		LocaleContextHolder.setLocale(Locale.GERMAN);

		candidate.setAsText("02.01.1970");

		assertEquals(82800000L, ((Date) candidate.getValue()).getTime());
	}

	@Test
	public void setAsText_nonDateString_Test() {

		candidate.setAsText("11:40");

		assertNull(candidate.getValue());
	}
}
