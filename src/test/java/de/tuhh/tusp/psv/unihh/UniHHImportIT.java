package de.tuhh.tusp.psv.unihh;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import de.tuhh.tusp.config.ApplicationWebConfig;
import de.tuhh.tusp.config.DatabaseConfig;
import de.tuhh.tusp.config.PropertiesConfig;
import de.tuhh.tusp.config.SecurityConfiguration;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.psv.unihh.main.UniHHImport;
import de.tuhh.tusp.psv.unihh.util.CSVDownload;
import de.tuhh.tusp.psv.unihh.util.CSVDownloadImpl;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.ssh.ConnectionClosedException;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.utils.WebserviceFactoryMock;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDao;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDaoImpl;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@Import({ DatabaseConfig.class, PropertiesConfig.class, SecurityConfiguration.class, ApplicationWebConfig.class })
@ComponentScan(basePackages = { "de.tuhh.tusp" }, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CSVDownloadImpl.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = StudentDaoImpl.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebserviceFactory.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SshUtils.class) }, lazyInit = true)
@EnableTransactionManagement
@ContextConfiguration(classes = { UniHHImportIT.class })
public class UniHHImportIT {

	private static Logger logger = LogManager.getLogger(UniHHImportIT.class);

	@Autowired
	private UniHHImport main;

	private MailUtils mailUtils;

	@Autowired
	private WebserviceFactoryMock webserviceFactory;

	@Before
	public void before() throws Exception {

		mailUtils = Mockito.mock(MailUtils.class);
		new MessageCollectorImpl(mailUtils, Mockito.mock(ConfigService.class));

	}

	@Ignore
	@Test
	public void exportTest() throws Exception {

		// Bericht auswerten (läuft erst am Ende)
		doAnswer(i -> {
			String subject = (String) i.getArguments()[2];
			if (!subject.contains("Fehler: 0 Warnungen: 0 Datensätze bearbeitet: 4")) {
				String body = (String) i.getArguments()[3];

				logger.error(subject);
				logger.error(body);

				fail(subject);
			}
			return null;
		}).when(mailUtils).sendPlainTextMail(any(), any(), any(), any());

		// Test starten
		((Schedulable) main).start(Mockito.mock(JobLogger.class));

		for (String key : webserviceFactory.getInvocations().keySet()) {
			int invos = webserviceFactory.getInvocations().get(key);
			logger.debug(invos + " Aufrufe für " + key);
		}

		for (String key : webserviceFactory.getInvocations().keySet()) {
			int invos = webserviceFactory.getInvocations().get(key);
			assertTrue(key + " wurde " + invos + " mal aufgerufen.", 0 < invos);
		}
	}

	@Bean
	public CSVDownload getCsvDownload() {

		return new CSVDownload() {

			@Override
			public Map<String, Resource> download() throws JSchException, SftpException, IOException, RuntimeException {

				Map<String, Resource> map = new HashMap<>();
				for (String file : new ClassPathResource("uhh/").getFile().list()) {
					map.put(file, new ClassPathResource("uhh/" + file));
				}
				return map;
			}
		};
	}

	@Bean
	public SshUtils getSShUtils() {

		return new SshUtils() {

			@Override
			public SshSession getSession(String user, String host, int port) throws JSchException {

				return new SshSession() {

					@Override
					public void close() {

						// TODO Auto-generated method stub

					}

					@Override
					public void copyFromRemote(WritableResource outfile, String remoteWorkingDir, String remoteFileName)
							throws SftpException, JSchException, IOException, RuntimeException {

						// TODO Auto-generated method stub

					}

					@Override
					public void copyToRemote(Resource inFile, String outFolder)
							throws ConnectionClosedException, JSchException, IOException {

						// TODO Auto-generated method stub

					}

					@Override
					public List<String> listRemoteFiles(String remoteWorkingDir) throws JSchException, SftpException {

						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String sendCommand(String command) throws JSchException, IOException {

						// TODO Auto-generated method stub
						return null;
					}
				};
			}
		};
	}

	@Bean
	public StudentDao getStudentDao() {

		StudentDao dao = mock(StudentDaoImpl.class);
		HashMap<Long, Long> mtknrs2pid = new HashMap<>();

		when(dao.insert(any())).then(i -> {
			Student s = (Student) i.getArguments()[0];
			mtknrs2pid.put(s.getMtknr(), s.getPersonId());
			return 0l;
		});
		when(dao.getPersonIdFromMtknrId(any())).then(i -> {
			Long s = (Long) i.getArguments()[0];
			return mtknrs2pid.get(s);
		});

		return dao;

	}

	@Bean
	public WebserviceFactory getWebserviceFactory() {

		return new WebserviceFactoryMock(new ClassPathResource("uhh/TestResponses/UniHHImportIT.xml"));
	}
}
