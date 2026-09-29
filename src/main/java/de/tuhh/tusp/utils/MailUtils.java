package de.tuhh.tusp.utils;
// TODO move to package de.tuhh.tusp.utils.net;

import java.io.ByteArrayOutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Schnittstelle zum versenden von Mails
 *
 * @author rztjm
 *
 */
public interface MailUtils {

	String MAIL_FROM_CONFIG_KEY = "de.tuhh.tusp.utils.MailUtils.SystemFromAddress";

	/**
	 * Sendet eine Mail im HTML-Format
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param body
	 * @throws MessagingException
	 *             Wenn die Nachricht nicht versand werden kann
	 * @throws AddressException
	 *             Wenn die Adressen nicht korrekt formatiert sind
	 */
	void sendHTMLMail(String to, String from, String subject, String body) throws AddressException, MessagingException;

	/**
	 * Sendet eine Mail im HTML-Format an Empfaenger plus CC
	 *
	 * @param to
	 * @param cc
	 * @param from
	 * @param subject
	 * @param body
	 * @throws MessagingException
	 *             Wenn die Nachricht nicht versandt werden kann
	 * @throws AddressException
	 *             Wenn die Adressen nicht korrekt formatiert sind
	 */
	void sendHTMLMailCC(String to, String cc, String from, String subject, String body) throws AddressException,
																						MessagingException;

	/**
	 * Sendet eine HTML-Mail an mehere Empfenger
	 *
	 * @param mailAddressTo
	 * @param from
	 * @param subject
	 * @param body
	 * @throws AddressException
	 * @throws MessagingException
	 */
	void sendHTMLMailWithCC(String mailAddressTo, String from, String subject, String body,
							String replyTo) throws AddressException, MessagingException;

	/**
	 * Sendet Mail mit Anhang
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param body
	 * @param filename
	 * @throws AddressException
	 * @throws MessagingException
	 */
	void sendMailWithAttachment(String to, String from, String subject, String body, ByteArrayOutputStream outputStream,
								String filename) throws AddressException, MessagingException;

	/**
	 * Sendet eine Mail im PlainText-Format
	 *
	 * @param to
	 * @param from
	 * @param subject
	 * @param body
	 * @throws MessagingException
	 *             Wenn die Nachricht nicht versand werden kann
	 * @throws AddressException
	 *             Wenn die Adressen nicht korrekt formatiert sind
	 */
	void sendPlainTextMail(String to, String from, String subject, String body) throws AddressException,
																				MessagingException;

}
