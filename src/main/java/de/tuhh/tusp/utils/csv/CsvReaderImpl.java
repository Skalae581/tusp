package de.tuhh.tusp.utils.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.MessageCollector;

@Component
public class CsvReaderImpl implements CsvReader {

	private static Logger logger = LogManager.getLogger(CsvReaderImpl.class);

	@Override
	public List<Map<Enum<?>, String>> readRecords(Resource inputFile, Class<?> columns, Character delimiter,
			Charset encoding, boolean withHeader) throws IOException {

		return readRecords(inputFile, columns, delimiter, encoding, withHeader, null);
	}

	@Override
	public List<Map<Enum<?>, String>> readRecords(Resource inputFile, Class<?> columns, Character delimiter,
			Charset encoding, boolean withHeader, MessageCollector messageCollector) throws IOException {

		logger.debug("Importiere CSV von " + inputFile);

		CSVFormat format = CSVFormat.DEFAULT.withDelimiter(delimiter);
		if (withHeader) {
			format = format.withHeader();
		}

		InputStream fis = inputFile.getInputStream();
		byte[] data = new byte[(int) inputFile.contentLength()];
		fis.read(data);

		// ByteOrderMark entfernen
		byte[] filteredData = data;
		if (data.length > 0 && data[0] < 0) {
			filteredData = Arrays.copyOfRange(data, 3, data.length);
		}

		String str = new String(filteredData, encoding);
		CSVParser parser = new CSVParser(new StringReader(str), format);

		Enum<?>[] enumColumns = (Enum<?>[]) columns.getEnumConstants();
		List<Map<Enum<?>, String>> res = new ArrayList<>();
		for (CSVRecord record : parser.getRecords()) {
			Map<Enum<?>, String> resultMap = new HashMap<>();
			if (record.size() < enumColumns.length) {
				String msg = "Weniger Spalten in CSVRecord als erwartet " + record.toString();
				if (messageCollector == null) {
					parser.close();
					throw new ParseException(msg);
				} else {
					messageCollector.addErrorMessage(msg);
					continue;
				}
			} else if (record.size() > enumColumns.length) {
				String msg = "Mehr Spalten in CSVRecord als erwartet " + record.toString();
				if (messageCollector == null) {
					parser.close();
					throw new ParseException(msg);
				} else {
					messageCollector.addErrorMessage(msg);
					continue;
				}
			}
			if (parser.getHeaderMap() == null || parser.getHeaderMap().isEmpty()) {
				// Wenn keine Header dann Zugriff per Index
				for (Enum<?> col : enumColumns) {
					resultMap.put(col, record.get(col.ordinal()));
				}
			} else {
				// Wenn header, dann Zugriff per name
				for (Enum<?> col : enumColumns) {
					resultMap.put(col, record.get(col.name()));
				}
			}
			res.add(resultMap);
		}

		parser.close();
		fis.close();

		return res;
	}

}
