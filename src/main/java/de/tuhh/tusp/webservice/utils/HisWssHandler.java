package de.tuhh.tusp.webservice.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Der {@link HisWssHandler} trägt in den Header der Webservice-Requests den
 * erforderlichen WSS-Header ein zur Authentifizierung bei HISinOne.
 *
 * @author rztjm
 *
 */
public class HisWssHandler implements SOAPHandler<SOAPMessageContext> {

	private static Logger logger = LogManager.getLogger(HisWssHandler.class);

	private String password;
	private String username;

	/**
	 * Erstellt eine neue Instanz
	 *
	 * @param username
	 * @param password
	 */
	public HisWssHandler(String username, String password) {
		this.password = password;
		this.username = username;
	}

	@Override
	public void close(MessageContext arg0) {
	}

	@Override
	public Set<QName> getHeaders() {

		return null;
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {

		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) throws RuntimeException {

		Boolean outGoingMsg = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outGoingMsg) {

			try {
				context.getMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "utf-8");

				SOAPHeader header = context.getMessage().getSOAPPart().getEnvelope().getHeader();
				if (header == null) {
					header = context.getMessage().getSOAPPart().getEnvelope().addHeader();
				}

				QName qname = new QName(
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security",
						"wsse");
				SOAPHeaderElement securityElement = header.addHeaderElement(qname);

				SOAPElement usernameTokenElement = securityElement.addChildElement("UsernameToken", "wsse");

				SOAPElement usernameElement = usernameTokenElement.addChildElement("Username", "wsse");
				usernameElement.addTextNode(username);

				SOAPElement passwordElement = usernameTokenElement.addChildElement("Password", "wsse");
				passwordElement.setAttribute("Type",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
				passwordElement.addTextNode(password);

				context.getMessage().saveChanges();
			} catch (SOAPException e) {
				logger.error("SOAPHeader header Fehler!", e);
				throw new RuntimeException();
			}
		}

		logSoapMessage(context);
		return true;
	}

	/**
	 * Logt die WebserviceRequests- und Responses
	 *
	 * @param context
	 */
	private void logSoapMessage(SOAPMessageContext context) {

		Boolean outGoingMsg = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		try {
			SOAPMessage msg = context.getMessage();

			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			msg.writeTo(bas);

			if (outGoingMsg) {
				logger.trace("SOAP-Request: " + bas.toString());
			} else {
				logger.trace("SOAP-Response: " + bas.toString());
			}
		} catch (IOException | SOAPException e) {
			logger.error("SOAPMessage Message Fehler!", e);
			throw new RuntimeException();
		}
	}
}
