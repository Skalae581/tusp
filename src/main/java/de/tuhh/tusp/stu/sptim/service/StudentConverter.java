package de.tuhh.tusp.stu.sptim.service;

import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;
import java.util.Map;

import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;

public interface StudentConverter {

	/**
	 * Wandelt einen Studnetendatensatz um
	 *
	 * @param student
	 * @return Der Student als Map
	 * @throws PflichtfeldException
	 *             Wenn eines der Pflichtfelder nicht gesetzt ist
	 * @throws ParseException
	 *             Wenn das Geburtsdatum nicht konvertiert werden kann
	 * @throws UnsupportedCharsetException
	 *             Wenn ein Feld ein nicht erlaubtes Zeichen enthält, dass nicht konvertiert werden kann
	 */
	Map<StudentColumns, String> studentToMap(HisStudentWrapper student)	throws PflichtfeldException, ParseException,
																			UnsupportedCharsetException;
}
