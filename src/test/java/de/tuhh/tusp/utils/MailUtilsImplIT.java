package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dumbster.smtp.MailMessage;
import com.dumbster.smtp.SmtpServer;

import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.config.MailTestConfig;

/**
 * Test startet einen Dumbster-Server und versucht mit diesem die Mails zu validieren
 *
 * @author rztjm
 *
 */
@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, MailTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class MailUtilsImplIT {

	@Autowired
	private MailUtils utils;

	@Autowired
	private SmtpServer server;

	@Before
	public void before() throws Exception {

		server.clearMessages();
	}

	@Test
	public void sendHtmlMail_mitUmlauten() throws AddressException, MessagingException {

		utils.sendHTMLMail("tusp@tuhh.de", "nobody@tuhh.de", "Unit test äöüÄÖÜß", "<p>Test: abcdefgä</p>");

		assertEquals(1, server.getEmailCount());

		MailMessage message = server.getMessage(0);
		assertEquals("tusp@tuhh.de", message.getFirstHeaderValue("To"));
		assertEquals("nobody@tuhh.de", message.getFirstHeaderValue("From"));

		assertEquals(	"=?UTF-8?Q?Unit_test_=C3=A4=C3=B6=C3=BC=C3=84=C3=96=C3=9C=C3=9F?=",
						message.getFirstHeaderValue("Subject"));
		assertEquals("text/html; charset=utf-8", message.getFirstHeaderValue("Content-Type"));
		assertEquals("<p>Test: abcdefg=C3=A4</p>", message.getBody());
	}

	@Test
	public void sendHtmlMail_ohneUmlaute() throws AddressException, MessagingException {

		utils.sendHTMLMail("tusp@tuhh.de", "nobody@tuhh.de", "Unit test äöüÄÖÜß", "<p>Test: abcdefg</p>");

		assertEquals(1, server.getEmailCount());

		MailMessage message = server.getMessage(0);
		assertEquals("tusp@tuhh.de", message.getFirstHeaderValue("To"));
		assertEquals("nobody@tuhh.de", message.getFirstHeaderValue("From"));

		assertEquals(	"=?UTF-8?Q?Unit_test_=C3=A4=C3=B6=C3=BC=C3=84=C3=96=C3=9C=C3=9F?=",
						message.getFirstHeaderValue("Subject"));
		assertEquals("text/html; charset=utf-8", message.getFirstHeaderValue("Content-Type"));
		assertEquals("<p>Test: abcdefg</p>", message.getBody());
	}

	@Test
	public void sendPlainTextMail_mitUmlauten() throws AddressException, MessagingException {

		utils.sendPlainTextMail("tusp@tuhh.de", "nobody@tuhh.de", "Unit test äöüÄÖÜß", "Test: äöüÄÖÜß?");

		assertEquals(1, server.getEmailCount());

		MailMessage message = server.getMessage(0);
		assertEquals("tusp@tuhh.de", message.getFirstHeaderValue("To"));
		assertEquals("nobody@tuhh.de", message.getFirstHeaderValue("From"));

		assertEquals(	"=?UTF-8?Q?Unit_test_=C3=A4=C3=B6=C3=BC=C3=84=C3=96=C3=9C=C3=9F?=",
						message.getFirstHeaderValue("Subject"));
		assertEquals("text/plain; charset=UTF-8", message.getFirstHeaderValue("Content-Type"));
		assertEquals("VGVzdDogw6TDtsO8w4TDlsOcw58/", message.getBody());
	}

	@Test
	public void sendPlainTextMail_ohneUmlaute() throws AddressException, MessagingException {

		utils.sendPlainTextMail("tusp@tuhh.de", "nobody@tuhh.de", "Unit test äöüÄÖÜß", "Test: abcdefg");

		assertEquals(1, server.getEmailCount());

		MailMessage message = server.getMessage(0);
		assertEquals("tusp@tuhh.de", message.getFirstHeaderValue("To"));
		assertEquals("nobody@tuhh.de", message.getFirstHeaderValue("From"));

		assertEquals(	"=?UTF-8?Q?Unit_test_=C3=A4=C3=B6=C3=BC=C3=84=C3=96=C3=9C=C3=9F?=",
						message.getFirstHeaderValue("Subject"));
		assertEquals("text/plain; charset=UTF-8", message.getFirstHeaderValue("Content-Type"));
		assertEquals("Test: abcdefg", message.getBody());
	}

}
