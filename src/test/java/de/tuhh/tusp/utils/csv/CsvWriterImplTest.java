package de.tuhh.tusp.utils.csv;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.junit.Test;

import de.tuhh.tusp.testutils.TestResource;

public class CsvWriterImplTest {

	private enum Columns {
		col_a, col_b
	}

	private enum FixedColumns implements EnumWithWidth {
		col_a, col_b;

		@Override
		public de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode getQuote() {

			return de.tuhh.tusp.utils.csv.EnumWithWidth.QuoteMode.ifNotEmpty;
		}

		@Override
		public int getWidth() {

			switch (this) {
				case col_a:

					return 5;
				case col_b:

					return 10;

				default:
					return 0;
			}
		}
	}

	private TestResource testResource;

	private CsvWriterImpl csvWriter = new CsvWriterImpl();
	private Charset encoding = Charset.forName("utf-8");

	@Test
	public void write_test() throws Exception {

		testResource = new TestResource();
		TestResource inputFile = testResource;

		List<Map<Columns, String>> data = new ArrayList<>();
		Map<Columns, String> map = new HashMap<>();

		map.put(Columns.values()[0], "c");
		map.put(Columns.values()[1], "ä");
		data.add(map);

		csvWriter.write(inputFile, data, encoding, Columns.class, ';', false, QuoteMode.ALL);

		assertEquals("\"c\";\"ä\"\n", new String(inputFile.getBytes(), encoding));

	}

	@Test
	public void write_withFixedWidth() throws Exception {

		testResource = new TestResource();
		TestResource inputFile = testResource;

		List<Map<FixedColumns, String>> data = new ArrayList<>();
		Map<FixedColumns, String> map = new HashMap<>();
		map.put(FixedColumns.values()[0], "c");
		map.put(FixedColumns.values()[1], "ä");
		data.add(map);

		Map<FixedColumns, String> map2 = new HashMap<>();
		map2.put(FixedColumns.values()[0], "");
		map2.put(FixedColumns.values()[1], "ein zu langer string ist auch was schönes");
		data.add(map2);

		csvWriter.write(testResource, data, encoding, FixedColumns.class, ';', false, QuoteMode.NONE);

		System.out.println(new String(inputFile.getBytes(), encoding));

		assertEquals(	"\"c    \";\"ä         \"\n" + ";\"ein zu langer string ist auch was schönes\"\n",
						new String(inputFile.getBytes(), encoding));
	}
}
