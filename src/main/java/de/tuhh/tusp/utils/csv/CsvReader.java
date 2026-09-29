package de.tuhh.tusp.utils.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import de.tuhh.tusp.utils.MessageCollector;

public interface CsvReader {

	/**
	 * Liest eine CSV-Datei ein.
	 *
	 * @param inputFile
	 *            Die Eingabedatei
	 * @param columns
	 *            Enum mit den Spalten der Datei
	 * @param delimiter
	 *            Das Zeilen-Trennzeichen
	 * @param encoding
	 *            Das Encoding
	 * @param withHeader
	 *            Ist die erste Zeile header?
	 * @return Liste der Datensätze
	 * @throws IOException
	 *             Wenn die Datei nicht gelesen werden kann.
	 * @throws ParseException
	 *             Wenn die Anzahl der Spalten eines Datensatzes nicht mit der Anzahl der columns übereinstimmt.
	 */
	List<Map<Enum<?>, String>> readRecords(	Resource inputFile, Class<?> columns, Character delimiter, Charset encoding,
											boolean withHeader) throws IOException, ParseException;

	/**
	 * Liest eine CSV-Datei ein.
	 *
	 * @param inputFile
	 *            Die Eingabedatei
	 * @param columns
	 *            Enum mit den Spalten der Datei
	 * @param delimiter
	 *            Das Zeilen-Trennzeichen
	 * @param encoding
	 *            Das Encoding
	 * @param withHeader
	 *            Ist die erste Zeile header?
	 * @param messageCollector
	 *            Hat ein Datensatz nicht die richtige Anzahl an Spalten, so wird dies hier vermerkt und er wird
	 *            übersprungen.
	 * @return Liste der Datensätze
	 * @throws IOException
	 *             Wenn die Datei nicht gelesen werden kann.
	 * @throws ParseException
	 *             Wenn die Anzahl der Spalten eines Datensatzes nicht mit der Anzahl der columns übereinstimmt.
	 */
	List<Map<Enum<?>, String>> readRecords(	Resource inputFile, Class<?> columns, Character delimiter, Charset encoding,
											boolean withHeader,
											MessageCollector messageCollector) throws IOException, ParseException;
}
