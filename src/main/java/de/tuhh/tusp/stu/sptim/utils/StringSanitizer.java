package de.tuhh.tusp.stu.sptim.utils;

import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.Map;

public interface StringSanitizer {

	/**
	 * Sucht in einem String für jedes Zeichen ob es erlaubt ist oder ersetzt werden muss
	 *
	 * @param string
	 * @param allowed
	 * @param replaces
	 * @return Den String mit nur erlaubten Zeichen
	 * @throws UnsupportedCharsetException
	 *             Wenn ein Zeichen weder erlaubt ist nach ersetzt werden kann.
	 */
	String sanitize(String string, List<String> allowed,
					Map<String, String> replaces) throws UnsupportedCharsetException;
}
