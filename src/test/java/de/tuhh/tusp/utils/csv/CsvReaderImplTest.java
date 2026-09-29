package de.tuhh.tusp.utils.csv;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;

public class CsvReaderImplTest {

	private enum Columns {
		col_a, col_b
	}

	private CsvReaderImpl reader = new CsvReaderImpl();

	@Test
	public void readRecords_skipptDatensatzWennSpalteFehlt() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		MessageCollector collector = new MessageCollectorImpl(null, null);
		List<Map<Enum<?>, String>> result =
				reader.readRecords(inputFile, columns, delimiter, encoding, false, collector);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(1).get(Columns.col_a));
		assertEquals("f", result.get(1).get(Columns.col_b));
		assertEquals(1, collector.getErrors().size());
	}

	@Test
	public void readRecords_skipptDatensatzWennSpalteZuViel() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc;d;\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		MessageCollector collector = new MessageCollectorImpl(null, null);
		List<Map<Enum<?>, String>> result =
				reader.readRecords(inputFile, columns, delimiter, encoding, false, collector);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(1).get(Columns.col_a));
		assertEquals("f", result.get(1).get(Columns.col_b));
		assertEquals(1, collector.getErrors().size());
	}

	@Test
	public void readRecords_successfullWithHeader() throws Exception {

		Resource inputFile = new ByteArrayResource("col_a;col_b\na;b\nc;'d'\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		List<Map<Enum<?>, String>> result = reader.readRecords(inputFile, columns, delimiter, encoding, true);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(2).get(Columns.col_a));
		assertEquals("f", result.get(2).get(Columns.col_b));
	}

	@Test
	public void readRecords_successfullWithHeader2() throws Exception {

		Resource inputFile = new ByteArrayResource("col_a;col_b\na;b\nc;'d'\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		List<Map<Enum<?>, String>> result = reader.readRecords(inputFile, columns, delimiter, encoding, true, null);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(2).get(Columns.col_a));
		assertEquals("f", result.get(2).get(Columns.col_b));
	}

	@Test
	public void readRecords_successfullWithoutHeader() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc;d\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		List<Map<Enum<?>, String>> result = reader.readRecords(inputFile, columns, delimiter, encoding, false);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(2).get(Columns.col_a));
		assertEquals("f", result.get(2).get(Columns.col_b));
	}

	@Test
	public void readRecords_successfullWithoutHeader2() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc;d\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		List<Map<Enum<?>, String>> result = reader.readRecords(inputFile, columns, delimiter, encoding, false, null);

		assertEquals("a", result.get(0).get(Columns.col_a));
		assertEquals("b", result.get(0).get(Columns.col_b));
		assertEquals("e", result.get(2).get(Columns.col_a));
		assertEquals("f", result.get(2).get(Columns.col_b));
	}

	@Test(expected = RuntimeException.class)
	public void readRecords_throwsExceptionWennSpalteFehlt() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		reader.readRecords(inputFile, columns, delimiter, encoding, false);
	}

	@Test(expected = RuntimeException.class)
	public void readRecords_throwsExceptionWennSpalteZuViel() throws Exception {

		Resource inputFile = new ByteArrayResource("a;b\nc;d;\ne;f".getBytes());
		Class<?> columns = Columns.class;
		Character delimiter = ';';
		Charset encoding = Charset.forName("utf-8");
		reader.readRecords(inputFile, columns, delimiter, encoding, false);
	}
}
