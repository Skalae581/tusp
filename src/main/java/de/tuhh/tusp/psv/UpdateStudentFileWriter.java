package de.tuhh.tusp.psv;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;

import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

public interface UpdateStudentFileWriter {

	/**
	 * Protokolliert einen geänderten Studenten
	 *
	 * @param student
	 * @throws UnsupportedCharsetException
	 *             Wenn der Server das Charset nicht kennt
	 * @throws PflichtfeldException
	 *             Wenn Pflichtfelder nicht gesetzt sind
	 * @throws ParseException
	 *             Wenn Daten nicht geparst werden können
	 * @throws NullPointerException
	 *             Bei Null Werten
	 * @throws IOException
	 *             Bei IO Problemen
	 */
	void logStudentData(HisStudent student, WritableResource file)	throws UnsupportedCharsetException,
																	PflichtfeldException, ParseException,
																	NullPointerException, IOException;

}
