package de.tuhh.tusp.stu.gdm.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.stu.gdm.dao.ProcessedFileDao;
import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;
import de.tuhh.tusp.stu.gdm.main.MainImpl;
import de.tuhh.tusp.stu.gdm.pojo.ProcessedFile;
import de.tuhh.tusp.testutils.TestResource;
import de.tuhh.tusp.utils.MD5Impl;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;

/**
 *
 * @author rztjm
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FileImportServiceImplTest {

	private static final String TEST_PATH = "test";

	@InjectMocks
	private FileImportServiceImpl service = new FileImportServiceImpl();

	@Mock
	private ProcessedFileDao processedFileDao;

	@Mock
	private ConfigService config;

	@Mock
	private MessageCollector collector;

	@Mock
	private ApplicationContext applicationContext;

	String heute;
	String vorgestern;

	@SuppressWarnings("unchecked")
	@Before
	public void before() {

		when(config.get(any(String.class), any(String.class), any(Boolean.class))).then(i -> TEST_PATH);

		heute = DateFormatUtils.format(new Date(), "yyyyMMdd");
		Date vg = new Date(System.currentTimeMillis() - 86400000);
		vorgestern = DateFormatUtils.format(vg, "yyyyMMdd");

		MessageCollector dummy = MessageCollectorImpl.getInstance("", null);
		Object field1 = TestUtils.getField(dummy, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).put(Thread.currentThread().getId(), collector);
		Object field2 = TestUtils.getField(dummy, "instancesByName");
		((Map<String, MessageCollector>) field2).put(MainImpl.MESSAGE_COLLECTOR_NAME, collector);
	}

	@Test
	public void findGebuehrenFiles_loescheVeraltete() throws Exception {

		TestResource file1 = new TestResource("Aktuelle Datei".getBytes(), "bla" + heute + ".xml", 0L);
		TestResource file2 = new TestResource("Ganz alte Datei".getBytes(), "bla1990-04-11.xml", 1000000L);

		Resource[] inFiles = { file1, file2 };
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);

		ProcessedFile processedFile = new ProcessedFile();
		processedFile.setVerarbeitet(new Date(10000l));
		processedFile.setHash(MD5Impl.getMd5(file2.getInputStream()));
		when(processedFileDao.fetchByInName(file2.getFilename())).thenAnswer(i -> processedFile);

		List<Resource> outFiles = service.findGebuehrenFiles();

		assertTrue(outFiles.contains(file1));
		assertFalse(outFiles.contains(file2));

		verify(file1.getFile(), times(0)).delete();
		verify(file2.getFile(), times(1)).delete();
	}

	@Test
	public void findGebuehrenFiles_machtNichtsWennKeineDateiExistiert() throws Exception {

		Resource[] inFiles = {};
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);

		List<Resource> outFiles = service.findGebuehrenFiles();

		assertTrue(outFiles.isEmpty());
	}

	@Test
	public void findGebuehrenFiles_nimmtAktuelleFiles() throws Exception {

		TestResource file1 = new TestResource("Aktuelle Datei".getBytes(), "bla" + heute + ".xml", 0L);

		Resource[] inFiles = { file1 };
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);

		List<Resource> outFiles = service.findGebuehrenFiles();

		assertTrue(outFiles.contains(file1));

		verify(file1.getFile(), times(0)).delete();
	}

	@Test
	public void findGebuehrenFiles_sammeltExceptions() throws Exception {

		TestResource file1 = new TestResource("Aktuelle Datei".getBytes(), "bla1" + heute + ".xml", 0L);
		TestResource file2 = new TestResource("Aktuelle Datei".getBytes(), "bla2" + heute + ".xml", 0L);

		when(processedFileDao.fetchByInName(file2.getFilename())).thenThrow(new RuntimeException("Testexception"));

		Resource[] inFiles = { file1, file2 };
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);
		List<Resource> outFiles = null;

		outFiles = service.findGebuehrenFiles();

		assertTrue(outFiles.contains(file1));
		assertFalse(outFiles.contains(file2));

		verify(file1.getFile(), times(0)).delete();
		verify(file2.getFile(), times(0)).delete();
		verify(collector, times(1)).addErrorMessage(any());
	}

	@Test
	public void findGebuehrenFiles_warntBeiFalschemHash() throws Exception {

		TestResource file1 = new TestResource("Aktuelle Datei".getBytes(), "bla" + heute + ".xml", 0L);
		TestResource file2 = new TestResource("Ganz alte Datei".getBytes(), "bla1990-04-11.xml", 1000000L);

		Resource[] inFiles = { file1, file2 };
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);

		ProcessedFile processedFile = new ProcessedFile();
		processedFile.setVerarbeitet(new Date(10000l));
		processedFile.setHash("asdfg");
		when(processedFileDao.fetchByInName(file2.getFilename())).thenAnswer(i -> processedFile);

		List<Resource> outFiles = service.findGebuehrenFiles();

		assertTrue(outFiles.contains(file1));
		assertFalse(outFiles.contains(file2));

		verify(collector, times(1)).addWarnMessage(any());

		verify(file1.getFile(), times(0)).delete();
		verify(file2.getFile(), times(0)).delete();

	}

	@Test(expected = KeineAktuellenFilesException.class)
	public void findGebuehrenFiles_wirftExceptionWennKeineAktuelleDatei() throws Exception {

		TestResource file2 = new TestResource("Alte Datei".getBytes(), "bla" + vorgestern + ".xml", 1000000L);

		Resource[] inFiles = { file2 };
		when(applicationContext.getResources(TEST_PATH)).then(i -> inFiles);

		when(processedFileDao.fetchByInName(file2.getFilename())).thenAnswer(i -> new ProcessedFile());

		service.findGebuehrenFiles();
	}
}
