package de.tuhh.tusp.psv.unihh.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import de.tuhh.tusp.psv.unihh.dao.UniHHStudDaoImpl.StudData;
import de.tuhh.tusp.psv.unihh.main.UniHHImportImpl;
import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.csv.CsvReader;

@RunWith(MockitoJUnitRunner.class)

public class UniHHStudDaoImplTest {

	@InjectMocks
	private UniHHStudDaoImpl dao;

	@Mock
	private CsvReader csvReader;

	@Mock
	private ApplicationContext context;

	@SuppressWarnings("unchecked")
	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		MessageCollector dummy = MessageCollectorImpl.getInstance("", null);
		Object field1 = TestUtils.getField(dummy, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).clear();
		Object field2 = TestUtils.getField(dummy, "instancesByName");
		((Map<String, MessageCollector>) field2).clear();
	}

	@Test
	public void readStudData() throws Exception {

		MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null);

		List<Map<StudData, String>> records = new ArrayList<>();
		Map<StudData, String> record = new HashMap<>();
		for (StudData attr : StudData.values()) {
			record.put(attr, "1");
		}
		records.add(record);

		when(csvReader.readRecords(	any(Resource.class), any(Class.class), any(Character.class), any(Charset.class),
									any(Boolean.class))).thenAnswer(i -> records);

		List<UniHHStud> result = dao.readStudData(new InMemoryResource("test.csv"), ';', Charset.defaultCharset());

		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getUhhMtknr().longValue());
		new TestUtils().assertNoFieldIsNull(result, "get");
	}

	@Test
	public void readStudData_withParseException() throws Exception {

		MessageCollector mc = MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null);
		List<Map<StudData, String>> records = new ArrayList<>();
		Map<StudData, String> record = new HashMap<>();
		for (StudData attr : StudData.values()) {
			record.put(attr, "1");
		}
		record.put(StudData.stgnr, "asdfg");
		records.add(record);

		record = new HashMap<>();
		for (StudData attr : StudData.values()) {
			record.put(attr, "2");
		}
		records.add(record);

		when(csvReader.readRecords(	any(Resource.class), any(Class.class), any(Character.class), any(Charset.class),
									any(Boolean.class))).thenAnswer(i -> records);

		List<UniHHStud> result = dao.readStudData(new InMemoryResource("test.csv"), ';', Charset.defaultCharset());

		assertEquals(1, result.size());
		assertEquals("2", result.get(0).getHrst());
		assertEquals(1, mc.getErrors().size());
	}
}
