package de.tuhh.tusp.stu.gdm.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;

import de.tuhh.tusp.stu.gdm.dao.ProcessedFileDao;
import de.tuhh.tusp.testutils.TestResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProcessedFileServiceImplTest {

	@InjectMocks
	private ProcessedFileServiceImpl service = new ProcessedFileServiceImpl();

	private MessageCollectorImpl collector;

	@Mock
	private ProcessedFileDao dao;

	private Resource outRes = new TestResource();
	private Resource inRes1 = new TestResource();
	private Resource inRes2 = new TestResource();
	private Resource inRes3 = new TestResource();

	@SuppressWarnings("unchecked")
	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		collector = new MessageCollectorImpl(null, null);
		Object field1 = TestUtils.getField(collector, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).put(Thread.currentThread().getId(), collector);
	}

	@Test
	public void markAsProcessed_collectsExceptions() throws Exception {

		when(inRes2.getFile().getName()).thenThrow(new RuntimeException());

		service.markAsProcessed(Arrays.asList(inRes1, inRes2, inRes3), outRes);

		assertEquals(1, collector.getErrors().size());
	}

	@Test
	public void markAsProcessed_returnsWithoutOutResource() throws Exception {

		service.markAsProcessed(Arrays.asList(inRes1, inRes2), null);

		verify(dao, times(0)).insert(any());
	}
}
