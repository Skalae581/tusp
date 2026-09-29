package de.tuhh.tusp.stu.sptim.utils;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.tuhh.tusp.utils.TestUtils;

public class TimStringUtilsImplTest {

	private static Logger logger = LogManager.getLogger(TimStringUtilsImplTest.class);

	private TimStringUtilsImpl util = new TimStringUtilsImpl();

	@Before
	public void before() throws Exception {

		TestUtils.setField(util, "sanitizer", new StringSanitizerImpl());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void replaceAndAllowListDoNotIntersect() {

		List<String> allow = (List<String>) TestUtils.getField(util, "allowed");
		Map<String, String> replace = (Map<String, String>) TestUtils.getField(util, "replaces");

		String result = "";
		for (String s : allow) {
			if (replace.containsKey(s)) {
				result += s + "\n";
			}
		}

		logger.debug("Liste der sinnvollen Ersetzungen:");
		for (String s : replace.keySet()) {
			if (!allow.contains(s)) {
				logger.debug(
						String.format("replaceChar(0x%x, '%s'); //%c", (int) s.charAt(0), replace.get(s), s.charAt(0)));
			}
		}

		assertTrue("Folgende Zeichen sind redundant (erlaubt und ersetzt): \n" + result, result.isEmpty());
	}

	@Test
	public void sanitizeTest() throws Exception {

		String result = util.sanitize("start®end");
		assertTrue(result.contains("®"));
	}
}
