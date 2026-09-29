package de.tuhh.tusp.stu.sptim.utils;

import static org.junit.Assert.*;

import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StringSanitizerImplTest {

	private StringSanitizer sanitizer = new StringSanitizerImpl();

	@Test
	public void sanitize_allowsAllowedChars() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "c", "d", "ö");
		Map<String, String> replaces = new HashMap<>();
		String result = sanitizer.sanitize("abcdö", allowed, replaces);
		assertEquals("abcdö", result);
	}

	@Test
	public void sanitize_allowsNonUtf8AllowedChars() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "m̂", "d");
		Map<String, String> replaces = new HashMap<>();
		String result = sanitizer.sanitize("abm̂d", allowed, replaces);
		assertEquals("abm̂d", result);
	}

	@Test
	public void sanitize_allowsNonUtf8AllowedChars2() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "m\u0302\u0302", "d");
		Map<String, String> replaces = new HashMap<>();
		String result = sanitizer.sanitize("abm\u0302\u0302d", allowed, replaces);
		assertEquals("abm\u0302\u0302d", result);
	}

	@Test(expected = UnsupportedCharsetException.class)
	public void sanitize_allowsNonUtf8AllowedChars3() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "m\u0302", "m\u0302\u0302\u0302", "d");
		Map<String, String> replaces = new HashMap<>();
		sanitizer.sanitize("abm\u0302\u0302d", allowed, replaces);
	}

	@Test
	public void sanitize_replacesReplacableChars() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "c", "d");
		Map<String, String> replaces = new HashMap<>();
		replaces.put("-", null);
		replaces.put("!", "?");
		String result = sanitizer.sanitize("abc-d!", allowed, replaces);
		assertEquals("abcd?", result);
	}

	@Test(expected = UnsupportedCharsetException.class)
	public void sanitize_throwsExceptionForUnknownChars() throws Exception {

		List<String> allowed = Arrays.asList("a", "b", "c", "d");
		Map<String, String> replaces = new HashMap<>();
		sanitizer.sanitize("abc-d", allowed, replaces);
	}
}
