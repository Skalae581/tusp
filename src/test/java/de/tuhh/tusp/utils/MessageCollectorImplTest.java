package de.tuhh.tusp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.lang.Thread.State;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.global.service.ConfigService;
import lombok.Getter;

@RunWith(MockitoJUnitRunner.class)

public class MessageCollectorImplTest {

	private class TestThread extends Thread {

		private String name;
		@Getter
		private MessageCollector mc;

		TestThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {

			mc = MessageCollectorImpl.getInstance(name, null);
		}
	}

	private static Logger logger = LogManager.getLogger(MessageCollectorImplTest.class);

	@Mock
	private ConfigService configService;

	@Mock
	private MailUtils mailUtils;

	@InjectMocks
	private MessageCollectorImpl collector;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		((Map<?, ?>) TestUtils.getField(collector, "instancesByThreadId")).clear();
		((Map<?, ?>) TestUtils.getField(collector, "instancesByName")).clear();

	}

	@Test
	public void createInstance_createsInstanceOnePerThread() throws Exception {

		TestThread t1 = new TestThread("TestThreat0");
		TestThread t2 = new TestThread("TestThreat1");

		t1.start();
		t2.start();

		// Bis zu 100ms warten
		for (int i = 0; i < 10; i++) {
			if (t1.getState() == State.TERMINATED && t2.getState() == State.TERMINATED) {
				break;
			}
			Thread.sleep(10);
		}

		MessageCollector mc = MessageCollectorImpl.getInstance("test", null);

		assertEquals(t1.getMc(), MessageCollectorImpl.getInstance(t1));
		assertEquals(t1.getMc(), MessageCollectorImpl.getInstance("TestThreat0", null));

		assertEquals(t2.getMc(), MessageCollectorImpl.getInstance(t2));
		assertEquals(t2.getMc(), MessageCollectorImpl.getInstance("TestThreat1", null));

		assertEquals(mc, MessageCollectorImpl.getInstance(Thread.currentThread()));
		assertEquals(mc, MessageCollectorImpl.getInstance("test", null));
	}

	@Test
	public void getInstanceByName() throws Exception {

		TestThread t1 = new TestThread("TestThreat0");
		TestThread t2 = new TestThread("TestThreat1");

		t1.start();
		t2.start();

		// Bis zu 100ms warten
		for (int i = 0; i < 10; i++) {
			if (t1.getState() == State.TERMINATED && t2.getState() == State.TERMINATED) {
				break;
			}
			Thread.sleep(10);
		}

		MessageCollector mc = MessageCollectorImpl.getInstance("TestThreat1", null);
		assertEquals(t2.getMc(), mc);
	}

	@Test
	public void sendAllTest() throws Exception {

		MessageCollector mc = new MessageCollectorImpl(mailUtils, configService);

		mc.addCreated();
		mc.addCreated();
		mc.addCreated();
		mc.addDeleted();
		mc.addDeleted();
		mc.addUpdated();

		mc.addErrorMessage("errormsg");
		mc.addOkMessage("okmsg");
		mc.addWarnMessage("warnmsg");

		mc.sendAll(null);

		ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> fromCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
		verify(mailUtils).sendPlainTextMail(toCaptor.capture(), fromCaptor.capture(), subjectCaptor.capture(),
				bodyCaptor.capture());

		logger.debug(subjectCaptor.getValue());
		logger.debug(bodyCaptor.getValue());

		assertTrue(subjectCaptor.getValue().contains("Fehler: 1 Warnungen: 1 Datensätze bearbeitet: 6"));
		assertTrue(bodyCaptor.getValue().contains("errormsg"));
		assertTrue(bodyCaptor.getValue().contains("okmsg"));
		assertTrue(bodyCaptor.getValue().contains("warnmsg"));
	}

	@Test
	public void sendErrorsTest() throws Exception {

		MessageCollector mc = new MessageCollectorImpl(mailUtils, configService);

		mc.addCreated();
		mc.addCreated();
		mc.addCreated();
		mc.addDeleted();
		mc.addDeleted();
		mc.addUpdated();

		mc.addErrorMessage("errormsg");
		mc.addOkMessage("okmsg");
		mc.addWarnMessage("warnmsg");

		mc.sendErrors(null);

		ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> fromCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
		verify(mailUtils).sendPlainTextMail(toCaptor.capture(), fromCaptor.capture(), subjectCaptor.capture(),
				bodyCaptor.capture());

		logger.debug(subjectCaptor.getValue());
		logger.debug(bodyCaptor.getValue());

		assertTrue(subjectCaptor.getValue().contains("Fehler: 1 Warnungen: 1 Datensätze bearbeitet: 6"));
		assertTrue(bodyCaptor.getValue().contains("errormsg"));
		assertFalse(bodyCaptor.getValue().contains("okmsg"));
		assertFalse(bodyCaptor.getValue().contains("warnmsg"));
	}

	@Test
	public void sendWarningsAndErrorsTest() throws Exception {

		MessageCollector mc = new MessageCollectorImpl(mailUtils, configService);

		mc.addCreated();
		mc.addCreated();
		mc.addCreated();
		mc.addDeleted();
		mc.addDeleted();
		mc.addUpdated();

		mc.addErrorMessage("errormsg");
		mc.addOkMessage("okmsg");
		mc.addWarnMessage("warnmsg");

		mc.sendWarningsAndErrors(null);

		ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> fromCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
		verify(mailUtils).sendPlainTextMail(toCaptor.capture(), fromCaptor.capture(), subjectCaptor.capture(),
				bodyCaptor.capture());

		logger.debug(subjectCaptor.getValue());
		logger.debug(bodyCaptor.getValue());

		assertTrue(subjectCaptor.getValue().contains("Fehler: 1 Warnungen: 1 Datensätze bearbeitet: 6"));
		assertTrue(bodyCaptor.getValue().contains("errormsg"));
		assertFalse(bodyCaptor.getValue().contains("okmsg"));
		assertTrue(bodyCaptor.getValue().contains("warnmsg"));
	}
}
