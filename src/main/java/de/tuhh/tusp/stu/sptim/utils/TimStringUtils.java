package de.tuhh.tusp.stu.sptim.utils;

import java.nio.charset.UnsupportedCharsetException;

public interface TimStringUtils {

	/**
	 * Bereinigt einen String, sodass er vom TIM-System sicher verarbietet werden kann.
	 *
	 * Es wird versucht Zeichen umzuwandeln oder wenn sie z.B. nicht druckbar sind sie zu entfernt.
	 *
	 * @param value
	 *            Eingabe
	 * @return Bereinigter String
	 * @throws UnsupportedCharsetException
	 *             Wenn die Eingabe Zeichen enthält, die nicht verarbeitet werdne können.
	 */
	String sanitize(String value) throws UnsupportedCharsetException;
}
