package de.tuhh.tusp.utils;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;

@Component
public class MailUtilsImpl implements MailUtils {

	private static Logger logger = LogManager.getLogger(MailUtilsImpl.class);

	@Autowired
	private ConfigService config;

	/**
	 * Setzt die nötigen Properties zum Mailversand
	 *
	 * @param host
	 * @param port
	 * @param debug
	 */
	private Session getSessionInstance() {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", config.get("de.tuhh.tusp.utils.mail.smtp.host", "localhost", true));
		properties.put("mail.smtp.port", config.get("de.tuhh.tusp.utils.mail.smtp.port", 25, true));
		properties.put("debug", config.get("de.tuhh.tusp.utils.mail.smtp.debug", "false", true));

		return Session.getInstance(properties);
	}

	@Override
	public void sendHTMLMail(String to, String from, String subject, String body)
			throws AddressException, MessagingException {

		Session session = getSessionInstance();
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		InternetAddress[] address = { new InternetAddress(to) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject, "UTF-8");
		msg.setSentDate(new Date());

		msg.setContent(body, "text/html; charset=utf-8");

		Transport.send(msg);
		logger.debug("HTML-Mail an " + to + " versandt, Betreff: " + subject);
	}

	@Override
	public void sendHTMLMailCC(String to, String cc, String from, String subject, String body)
			throws AddressException, MessagingException {

		Session session = getSessionInstance();
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		InternetAddress[] address = { new InternetAddress(to) };
		InternetAddress[] addressCC = { new InternetAddress(cc) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.addRecipients(Message.RecipientType.CC, addressCC);
		msg.setSubject(subject, "UTF-8");
		msg.setSentDate(new Date());

		msg.setContent(body, "text/plain; charset=utf-8");

		Transport.send(msg);
		logger.debug("HTML-Mail an " + to + " versandt, Betreff: " + subject);
	}

	@Override
	public void sendHTMLMailWithCC(String mailAddressTo, String from, String subject, String body, String replyTo)
			throws AddressException, MessagingException {

		Session session = getSessionInstance();
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));

		// InternetAddress[] address = new
		// InternetAddress[mailAddressTo.length];

		InternetAddress[] address = { new InternetAddress(mailAddressTo) };

		msg.addRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject, "UTF-8");
		msg.setSentDate(new Date());
		msg.setReplyTo(new InternetAddress[] { new InternetAddress(replyTo) });
		msg.setContent(body, "text/html; charset=utf-8");

		Transport.send(msg);
		logger.debug("HTML-Mail an " + mailAddressTo.toString() + " versandt, Betreff: " + subject);
	}

	@Override
	public void sendMailWithAttachment(String to, String from, String subject, String body,
			ByteArrayOutputStream outputStream, String filename) throws AddressException, MessagingException {

		Session session = getSessionInstance();

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());

			try {

				MimeBodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setText(body);

				// csv Content
				byte[] bytes = outputStream.toByteArray();

				DataSource dataSource = new ByteArrayDataSource(bytes, "text/csv");
				MimeBodyPart csvBodyPart = new MimeBodyPart();
				csvBodyPart.setDataHandler(new DataHandler(dataSource));
				csvBodyPart.setFileName(filename);

				MimeMultipart mimeMultipart = new MimeMultipart();
				mimeMultipart.addBodyPart(textBodyPart);
				mimeMultipart.addBodyPart(csvBodyPart);

				msg.setContent(mimeMultipart);

				Transport.send(msg);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				// clean off
				if (null != outputStream) {
					try {
						outputStream.close();
						outputStream = null;
					} catch (Exception ex) {
					}
				}
			}

		} catch (MessagingException e) {
			logger.debug("Error while sending email" + e);
		}

	}

	@Override
	public void sendPlainTextMail(String to, String from, String subject, String body)
			throws AddressException, MessagingException {

		Session session = getSessionInstance();
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		InternetAddress[] address = { new InternetAddress(to) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject, "UTF-8");
		msg.setSentDate(new Date());

		msg.setText(body, "UTF-8");

		Transport.send(msg);
		logger.debug("Text-Mail an " + to + " versandt, Betreff: " + subject);
	}
}
