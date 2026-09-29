package de.tuhh.tusp.stu.gdm.main;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;
import de.tuhh.tusp.stu.gdm.service.FileImportService;
import de.tuhh.tusp.stu.gdm.service.FileMergeService;
import de.tuhh.tusp.stu.gdm.service.FileOutputService;
import de.tuhh.tusp.stu.gdm.service.ProcessedFileService;
import de.tuhh.tusp.testutils.TestResource;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class MainImplTest {

	@InjectMocks
	private MainImpl main = new MainImpl();

	@Mock
	private FileImportService importService;

	@Mock
	private FileOutputService outputService;

	@Mock
	private FileMergeService mergeService;

	@Mock
	private ProcessedFileService processedFielService;

	@Mock
	private MessageCollector collector;

	@Mock
	private ConfigService config;

	@Mock
	private MailUtils mailUtils;

	@Mock
	private JobLogger jobLogger;

	@SuppressWarnings("unchecked")
	@Before
	public void before() throws Exception {

		MessageCollector dummy = MessageCollectorImpl.getInstance("", null);
		Object field1 = TestUtils.getField(dummy, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).put(Thread.currentThread().getId(), collector);
		Object field2 = TestUtils.getField(dummy, "instancesByName");
		((Map<String, MessageCollector>) field2).put(MainImpl.MESSAGE_COLLECTOR_NAME, collector);
	}

	@Test
	public void verarbeiteGebuehrendaten_handleIOException() throws Exception {

		when(importService.findGebuehrenFiles()).thenThrow(new IOException());

		main.start(jobLogger);

		verify(collector).addErrorMessage(any(String.class));
		verify(collector).sendWarningsAndErrors(null);
	}

	@Test
	public void verarbeiteGebuehrendaten_handleKeineAktuelleFilesException() throws Exception {

		when(importService.findGebuehrenFiles())
				.thenThrow(new KeineAktuellenFilesException("Keine Aktuellen Files Exception!"));

		main.start(jobLogger);

		verify(collector).addErrorMessage(any(String.class));
		verify(collector).sendWarningsAndErrors(null);
	}

	@Test
	public void verarbeiteGebuehrendaten_handleRuntimeException() throws Exception {

		doThrow(new RuntimeException()).when(importService).findGebuehrenFiles();

		main.start(jobLogger);

		verify(collector).addErrorMessage(any(String.class));
		verify(collector).sendWarningsAndErrors(null);
	}

	@Test
	public void verarbeiteGebuehrendaten_withEmptyFiles() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		List<Resource> resources = new ArrayList<>();
		int seqNr = 0;

		doReturn(resources).when(importService).findGebuehrenFiles();
		doReturn(document).when(mergeService).mergeFiles(resources, seqNr);

		main.start(jobLogger);

		verify(importService).findGebuehrenFiles();
		verify(mergeService, times(0)).mergeFiles(resources, seqNr);
		verify(outputService, times(0)).writeFile(document);
		verify(collector, times(0)).sendWarningsAndErrors(null);
		verify(collector, times(0)).addErrorMessage(any(String.class));
	}

	@Test
	public void verarbeiteGebuehrendaten_withSuccess() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		List<Resource> resources = new ArrayList<>();
		resources.add(new TestResource());
		int seqNr = 0;

		doReturn(resources).when(importService).findGebuehrenFiles();
		doReturn(document).when(mergeService).mergeFiles(resources, seqNr);

		main.start(jobLogger);

		verify(importService).findGebuehrenFiles();
		verify(mergeService).mergeFiles(resources, seqNr);
		verify(outputService).writeFile(document);
		verify(processedFielService).markAsProcessed(resources, null);
		verify(collector).sendWarningsAndErrors(null);
		verify(collector, times(0)).addErrorMessage(any(String.class));
	}
}
