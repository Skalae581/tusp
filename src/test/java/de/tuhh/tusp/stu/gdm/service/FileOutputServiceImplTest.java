package de.tuhh.tusp.stu.gdm.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.jcraft.jsch.JSchException;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.stu.gdm.dao.HistoryDao;
import de.tuhh.tusp.stu.gdm.main.MainImpl;
import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.testutils.TestResource;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;

@RunWith(MockitoJUnitRunner.class)
public class FileOutputServiceImplTest {

	@InjectMocks
	private FileOutputServiceImpl service = new FileOutputServiceImpl();

	@Mock
	private SshUtils sshUtils;

	@Mock
	private SshSession sshSession;

	@Mock
	private Document document;

	@Mock
	private MessageCollector collector;

	@Mock
	private ConfigService config;

	@Mock
	private HistoryDao historyDao;

	@Mock
	private AuthorityUtils authorityUtils;

	@Mock
	private ApplicationContext applicationContext;

	private TestResource res1 = new TestResource(new byte[0], "zahlungen_1.xml", 0);
	private TestResource res2 = new TestResource(new byte[0], "zahlungen_2.xml", 0);
	private TestResource res3 = new TestResource(new byte[0], "zahlungen_3.xml", 0);

	@Captor
	ArgumentCaptor<Resource> resourceCaptor;

	@Captor
	ArgumentCaptor<String> stringCaptor;

	@SuppressWarnings("unchecked")
	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		when(sshUtils.getSession(any(String.class), any(String.class), any(Integer.class))).thenAnswer(i -> sshSession);

		MessageCollector dummy = MessageCollectorImpl.getInstance("", null);
		Object field1 = TestUtils.getField(dummy, "instancesByThreadId");
		((Map<Long, MessageCollector>) field1).put(Thread.currentThread().getId(), collector);
		Object field2 = TestUtils.getField(dummy, "instancesByName");
		((Map<String, MessageCollector>) field2).put(MainImpl.MESSAGE_COLLECTOR_NAME, collector);
	}

	@Test(expected = FileNotFoundException.class)
	public void getFileByName_checksForExistence() throws Exception {

		Resource[] resources = { res1 };

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/nirvana/");
		when(applicationContext.getResources(any())).then(i -> resources);
		when(applicationContext.getResource(any())).then(i -> res1);
		doAnswer(i -> false).when(res1.getFile()).exists();

		service.getFileByName("das da");
	}

	@Test(expected = SecurityException.class)
	public void getFileByName_checksForPathManipulation() throws Exception {

		Resource[] resources = { res2, res1, res3 };

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/nirvana/");
		when(applicationContext.getResources(any())).then(i -> resources);
		when(applicationContext.getResource(any())).then(i -> new InMemoryResource("das da"));

		service.getFileByName("das da");
	}

	@Test(expected = RuntimeException.class)
	public void getFileByName_requiresConfiguration() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "");

		service.getFileByName("das da");
	}

	@Test
	public void getFileByName_savesHistory() throws Exception {

		Resource[] resources = { res1 };

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/nirvana/");
		when(applicationContext.getResources(any())).then(i -> resources);
		when(applicationContext.getResource(any())).then(i -> res1);
		when(authorityUtils.getCurrentUserName()).then(i -> "Tester");
		doAnswer(i -> true).when(res1.getFile()).exists();

		service.getFileByName("das da");

		ArgumentCaptor<History> captor = ArgumentCaptor.forClass(History.class);
		verify(historyDao).insert(captor.capture());

		assertEquals("Tester", captor.getValue().getUsername());

	}

	private Document getTestDoc(String xml) throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}

	@Test(expected = RuntimeException.class)
	public void listLokaleDateien_requiresConfiguration() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "");

		service.listLokaleDateien();
	}

	@Test
	public void listLokaleDateien_sortiertFiles() throws Exception {

		Resource[] resources = { res2, res1, res3 }; // Falsch sortiert

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/nirvana/");
		when(applicationContext.getResources(any())).then(i -> resources);

		List<Resource> files = service.listLokaleDateien();
		assertEquals(res3, files.get(0));
		assertEquals(res2, files.get(1));
		assertEquals(res1, files.get(2));
	}

	@Test
	public void writeFile_collectsSshErrors() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.user", "", true)).then(i -> "nobody");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remoteHosts", "",
						true)).then(i -> "127.0.0.1,0.0.0.0, 192.168.1.1");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/test.txt");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.privKey", "", true)).then(i -> "id_rsa");
		String testContent = "<html>test</html>";

		doAnswer(i -> {
			if (i.getArguments()[1].equals("0.0.0.0")) {
				throw new JSchException();
			}
			return Mockito.mock(SshSession.class);
		}).when(sshUtils).getSession(any(String.class), any(String.class), any(Integer.class));

		service.writeFile(getTestDoc(testContent));

		verify(collector).addErrorMessage(any());
	}

	public void writeFile_collectsTransformErrors() throws Exception {
		// Scheibar nicht richtig testbar
	}

	@Test
	public void writeFile_success() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.user", "", true)).then(i -> "nobody");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remoteHosts", "",
						true)).then(i -> "127.0.0.1,0.0.0.0");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/var/beispiel/");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.privKey", "", true)).then(i -> "id_rsa");
		String testContent = "<html>test</html>";

		Resource outFile = service.writeFile(getTestDoc(testContent));

		verify(sshSession, times(2)).copyToRemote(resourceCaptor.capture(), stringCaptor.capture());
		verify(sshSession, times(2)).close();

		assertTrue(stringCaptor.getValue().startsWith("/var/beispiel/zahlungen_"));
		assertTrue(stringCaptor.getValue().endsWith(".xml"));
		assertEquals(	"Das übertragene File soll das selbe sein, wie das zurückgegebene", resourceCaptor.getValue(),
						outFile);

		String content = IOUtils.toString(resourceCaptor.getValue().getInputStream());
		assertEquals(testContent, content.trim());

	}

	@Test(expected = NullPointerException.class)
	public void writeFile_thorwsNPE() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.user", "", true)).then(i -> "nobody");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remoteHosts", "",
						true)).then(i -> "127.0.0.1,0.0.0.0, 192.168.1.1");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remotePath", "", true)).then(i -> "/test.txt");
		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.privKey", "", true)).then(i -> "id_rsa");

		service.writeFile(null);

	}

	@Test(expected = RuntimeException.class)
	public void writeFile_thorwsRuntimeException_whenUnconfigured() throws Exception {

		when(config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.user", "", true)).then(i -> "");

		service.writeFile(null);

	}
}
