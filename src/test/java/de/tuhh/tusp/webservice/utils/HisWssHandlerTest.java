package de.tuhh.tusp.webservice.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class HisWssHandlerTest {

	private static final String PASSWORD = "Krrrrrrr";
	private static final String USERNAME = "Paulchen";

	private HisWssHandler handler = new HisWssHandler(USERNAME, PASSWORD);

	@Mock
	private Logger logger;

	@Mock
	private SOAPMessage message;

	@Mock
	private SOAPMessageContext context;

	@Before
	public void before() throws SOAPException {

		MockitoAnnotations.initMocks(this);

		when(context.getMessage()).thenAnswer(i -> message);
	}

	@Test
	public void handleFaultTest() {

		assertFalse(handler.handleFault(context));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = RuntimeException.class)
	public void handleMessage_convertsSAOPException() {

		when(message.getSOAPPart()).thenThrow(SOAPException.class);
		handler.handleMessage(context);
	}

	@Test
	public void handleMessage_inbound() throws SOAPException {

		message = MessageFactory.newInstance().createMessage();

		when(context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenAnswer(i -> Boolean.valueOf(false));
		TestUtils.setField(handler, "logger", logger);
		handler.handleMessage(context);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(logger, times(1)).trace(captor.capture());

		String value = captor.getValue();
		assertTrue(!value.contains(USERNAME));
		assertTrue(!value.contains(PASSWORD));
		assertTrue(!value.contains("wsse"));

	}

	@Test
	public void handleMessage_outbound() throws SOAPException {

		message = MessageFactory.newInstance().createMessage();

		when(context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenAnswer(i -> Boolean.valueOf(true));
		TestUtils.setField(handler, "logger", logger);
		handler.handleMessage(context);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(logger, times(1)).trace(captor.capture());

		String value = captor.getValue();
		assertTrue(value.contains(USERNAME));
		assertTrue(value.contains(PASSWORD));
		assertTrue(value.contains("wsse"));

	}
}
