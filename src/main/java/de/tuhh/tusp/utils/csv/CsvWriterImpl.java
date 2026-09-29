package de.tuhh.tusp.utils.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

@Component
public class CsvWriterImpl implements CsvWriter {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String NEW_LINE_SEPARATOR_COLON = ";\n";

	private String format(String val, int minLength, de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode quote) {

		if (val == null || val.isEmpty()) {
			return quote == de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.always ? "\"\"" : "";
		}

		if (quote == de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.never) {
			return StringUtils.rightPad(val, minLength);
		}

		return '"' + StringUtils.rightPad(val, minLength) + '"';
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends Enum<E>> void write(WritableResource outFile, List<Map<E, String>> data, Charset encoding,
			Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException {

		CSVPrinter csvFilePrinter = null;

		if (!outFile.isWritable()) {
			throw new RuntimeException("CSV-File not writable");
		}

		if (enumType.getEnumConstants()[0] instanceof EnumWithWidth) {
			writeWithFixedWidth(outFile, data, encoding, enumType, delimiter, withHeader, mode);
			return;
		}

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR).withDelimiter(delimiter)
				.withQuoteMode(mode);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outFile.getOutputStream(), encoding));

		try {

			csvFilePrinter = new CSVPrinter(out, csvFileFormat);

			if (withHeader) {
				List<String> headerRecord = new ArrayList<>();

				for (E e : enumType.getEnumConstants()) {
					headerRecord.add(e.name());
				}
				headerRecord.add(NEW_LINE_SEPARATOR);
				csvFilePrinter.printRecord(headerRecord);
			}

			for (Map<E, String> map : data) {
				int counter = 0;
				List<String> record = new ArrayList<>();

				for (E e : enumType.getEnumConstants()) {

					if (counter++ == enumType.getEnumConstants().length) {
						record.add(NEW_LINE_SEPARATOR);
						break;
					}

					String value = map.get(Enum.valueOf(enumType, e.name()));
					record.add(value);
				}

				csvFilePrinter.printRecord(record);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				out.flush();
				out.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private <E extends Enum<E>> void writeWithFixedWidth(WritableResource outFile, List<Map<E, String>> data,
			Charset encoding, Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outFile.getOutputStream(), encoding));
		EnumWithWidth[] cols = (EnumWithWidth[]) enumType.getEnumConstants();

		try {
			if (withHeader) {
				out.append(cols[0].name());
				for (int i = 1; i < cols.length; i++) {
					out.append(delimiter);
					out.append(cols[i].name());
				}
				out.append(NEW_LINE_SEPARATOR);
			}

			for (Map<E, String> map : data) {

				out.append(format(map.get(cols[0]), cols[0].getWidth(), cols[0].getQuote()));
				for (int i = 1; i < cols.length; i++) {

					out.append(delimiter);
					out.append(format(map.get(cols[i]), cols[i].getWidth(), cols[i].getQuote()));
				}

				out.append(NEW_LINE_SEPARATOR);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * TODO doppelte mthode
	 *
	 * @param <E>
	 * @param outFile
	 * @param data
	 * @param encoding
	 * @param enumType
	 * @param delimiter
	 * @param withHeader
	 * @param mode
	 * @throws IOException
	 * @throws NullPointerException
	 */
	private <E extends Enum<E>> void writeWithFixedWidthColon(WritableResource outFile, List<Map<E, String>> data,
			Charset encoding, Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outFile.getOutputStream(), encoding));
		EnumWithWidth[] cols = (EnumWithWidth[]) enumType.getEnumConstants();

		try {
			if (withHeader) {
				out.append(cols[0].name());
				for (int i = 1; i < cols.length; i++) {
					out.append(delimiter);
					out.append(cols[i].name());
				}
				out.append(NEW_LINE_SEPARATOR_COLON);
			}

			for (Map<E, String> map : data) {

				out.append(format(map.get(cols[0]), cols[0].getWidth(), cols[0].getQuote()));
				for (int i = 1; i < cols.length; i++) {

					out.append(delimiter);
					out.append(format(map.get(cols[i]), cols[i].getWidth(), cols[i].getQuote()));
				}

				out.append(NEW_LINE_SEPARATOR_COLON);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public <E extends Enum<E>> void writeWithNewLineSeparator(WritableResource outFile, List<Map<E, String>> data,
			Charset encoding, Class<E> enumType, char delimiter, boolean withHeader, QuoteMode mode)
			throws IOException, NullPointerException {

		CSVPrinter csvFilePrinter = null;

		if (!outFile.isWritable()) {
			throw new RuntimeException("CSV-File not writable");
		}

		if (enumType.getEnumConstants()[0] instanceof EnumWithWidth) {
			writeWithFixedWidthColon(outFile, data, encoding, enumType, delimiter, withHeader, mode);
			return;
		}

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR_COLON)
				.withDelimiter(delimiter).withQuoteMode(mode);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outFile.getOutputStream(), encoding));

		try {

			csvFilePrinter = new CSVPrinter(out, csvFileFormat);

			if (withHeader) {
				List<String> headerRecord = new ArrayList<>();

				for (E e : enumType.getEnumConstants()) {
					headerRecord.add(e.name());
				}
				headerRecord.add(NEW_LINE_SEPARATOR_COLON);
				csvFilePrinter.printRecord(headerRecord);
			}

			for (Map<E, String> map : data) {
				int counter = 0;
				List<String> record = new ArrayList<>();

				for (E e : enumType.getEnumConstants()) {

					if (counter++ == enumType.getEnumConstants().length) {
						record.add(NEW_LINE_SEPARATOR_COLON);
						break;
					}

					String value = map.get(Enum.valueOf(enumType, e.name()));
					record.add(value);
				}

				csvFilePrinter.printRecord(record);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				out.flush();
				out.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
