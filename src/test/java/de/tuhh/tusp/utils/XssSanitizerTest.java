package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class XssSanitizerTest {

	private static final String GOOD = "some value";
	private static final String DIFFICULT = "öäüÖÄÜ?ßasdf,;";
	private static final String ALLOWED = "<a title=\"test\" href=\"some.html\"><img src=\"\" alt=\"\" /></a>";
	private static final String EVIL = "<script>alert(123);</script>";

	private XssSanitizer sanitizer = new XssSanitizer();

	@Test
	public void sanitize_allowedTextisUnchanged() {

		assertEquals(ALLOWED, sanitizer.sanitize(ALLOWED).replaceAll(" rel=\"nofollow\"", ""));
	}

	@Test
	public void sanitize_cleanTextisUnchanged() {

		assertEquals(GOOD, sanitizer.sanitize(GOOD));
	}

	@Test
	public void sanitize_difficultTextisUnchanged() {

		assertEquals(DIFFICULT, sanitizer.sanitize(DIFFICULT));
	}

	@Test
	public void sanitize_evilTextRemovedAtBeginning() {

		assertEquals(GOOD, sanitizer.sanitize(EVIL + GOOD));
	}

	@Test
	public void sanitize_evilTextRemovedAtCenter() {

		assertEquals(GOOD + GOOD, sanitizer.sanitize(GOOD + EVIL + GOOD));
	}

	@Test
	public void sanitize_evilTextRemovedAtEnd() {

		assertEquals(GOOD, sanitizer.sanitize(GOOD + EVIL));
	}
}
