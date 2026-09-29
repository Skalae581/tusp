package de.tuhh.tusp.stu.sptim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.config.ApplicationWebConfig;
import de.tuhh.tusp.config.DatabaseConfig;
import de.tuhh.tusp.config.PropertiesConfig;
import de.tuhh.tusp.config.SecurityConfiguration;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.global.service.JobService;
import de.tuhh.tusp.stu.sptim.main.Ruecklaeufer;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtils;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtilsImpl.CmdFaildException;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.schedule.JobLoggerImpl;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.utils.WebserviceFactoryMock;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@Import({ DatabaseConfig.class, PropertiesConfig.class, SecurityConfiguration.class, ApplicationWebConfig.class })
@ComponentScan(basePackages = { "de.tuhh.tusp" }, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SyscallUtils.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebserviceFactory.class) }, lazyInit = true)
@EnableTransactionManagement
@ContextConfiguration(classes = { RuecklaeuferIT.class })
@Ignore
public class RuecklaeuferIT {

	private static Logger logger = LogManager.getLogger(RuecklaeuferIT.class);

	@Autowired
	private Ruecklaeufer main;

	private MailUtils mailUtils;

	@Autowired
	private WebserviceFactoryMock webserviceFactory;

	@Before
	public void before() throws Exception {

		mailUtils = Mockito.mock(MailUtils.class);
		new MessageCollectorImpl(mailUtils, Mockito.mock(ConfigService.class));

	}

	@Test
	public void exportTest() throws Exception {

		// Bericht auswerten (läuft erst am Ende)
		doAnswer(i -> {
			String subject = (String) i.getArguments()[2];
			if (!subject.contains("Fehler: 0 Warnungen: 0 Datensätze bearbeitet: 2")) {
				String body = (String) i.getArguments()[3];

				logger.error(subject);
				logger.error(body);

				fail(subject);
			}
			return null;
		}).when(mailUtils).sendPlainTextMail(any(), any(), any(), any());

		// Test starten
		Job job = new Job();
		job.setClassName(getClass().getName());
		JobLogger joblogger = new JobLoggerImpl(job, Mockito.mock(JobService.class));
		main.start(joblogger);

		for (String key : webserviceFactory.getInvocations().keySet()) {
			int invos = webserviceFactory.getInvocations().get(key);
			assertEquals(key + " wurde " + invos + " mal aufgerufen.", 1, invos);
		}
	}

	@Bean
	public SyscallUtils getSyscallUtils() {

		return new SyscallUtils() {

			@Override
			public String runCmd(File workdir, String... cmd)
					throws IOException, InterruptedException, CmdFaildException {

				if (cmd[0].contains("getData")) {
					Resource r = new ClassPathResource("sptim");
					File from = new File(r.getFile().getAbsolutePath() + "/ruecklaeuferTest.csv");

					File to = new File(r.getFile().getAbsolutePath() + "/" + cmd[cmd.length - 1].split("=")[1]);
					FileUtils.copyFile(from, to);
				}

				System.out.println(cmd);
				return "";
			}
		};
	}

	@Bean
	public WebserviceFactory getWebserviceFactory() {

		return new WebserviceFactoryMock(new ClassPathResource("sptim/TestResponses/RuecklaeuferIT.xml"));
	}
}
