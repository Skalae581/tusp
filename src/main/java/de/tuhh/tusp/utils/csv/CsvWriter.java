package de.tuhh.tusp.utils.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.springframework.core.io.WritableResource;

public interface CsvWriter {

	/**
	 * Schreibt Daten in eine CSV-Datei.
	 *
	 * @param outFile  Die Datei zur Ausgabe
	 * @param enumType Die Spalten der CSV-Datei
	 * @param data     Die Daten
	 * @param encoding Das Encoding
	 * @throws IOException          Wenn die Datei nicht geschrieben werden kann.
	 * @throws NullPointerException Wenn eines der Felder null ist.
	 */
	<E extends Enum<E>> void write(WritableResource outFile, List<Map<E, String>> data, Charset encoding,
			Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException;

	<E extends Enum<E>> void writeWithNewLineSeparator(WritableResource outFile, List<Map<E, String>> data,
			Charset encoding, Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException;

}
