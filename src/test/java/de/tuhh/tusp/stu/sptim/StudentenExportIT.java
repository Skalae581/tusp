package de.tuhh.tusp.stu.sptim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.config.ApplicationWebConfig;
import de.tuhh.tusp.config.DatabaseConfig;
import de.tuhh.tusp.config.PropertiesConfig;
import de.tuhh.tusp.config.SecurityConfiguration;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.main.StudentenExport;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtils;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtilsImpl.CmdFaildException;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.webservice.sync.service.PeriodService;
import de.tuhh.tusp.webservice.sync.service.PeriodServiceImpl;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.utils.WebserviceFactoryMock;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@Import({ DatabaseConfig.class, PropertiesConfig.class, SecurityConfiguration.class, ApplicationWebConfig.class })
@ComponentScan(basePackages = { "de.tuhh.tusp" }, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SyscallUtils.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = PeriodServiceImpl.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebserviceFactory.class) }, lazyInit = true)
@EnableTransactionManagement
@ContextConfiguration(classes = { StudentenExportIT.class })
public class StudentenExportIT {

	private static Logger logger = LogManager.getLogger(StudentenExportIT.class);

	static Resource outFile;

	@Autowired
	private StudentenExport main;

	@Autowired
	private WebserviceFactoryMock factory;

	@Test
	public void exportTest() throws Exception {

		main.start(Mockito.mock(JobLogger.class));

		String pattern = "rzcsv-(So|Wi)Se\\d{2}-\\d{10}\\.txt";
		assertTrue("Dateiname entspricht nicht den Anforderungen " + outFile.getFilename() + " ~= " + pattern,
				outFile.getFilename().matches(pattern));

		String soll = IOUtils.toString(new ClassPathResource("sptim/musterExportResponse.csv").getInputStream());
		String ist = IOUtils.toString(outFile.getInputStream());

		for (String key : factory.getInvocations().keySet()) {
			int invos = factory.getInvocations().get(key);

			assertEquals(invos + " Aufrufe für " + key, 1, invos);
		}

		if (!StringUtils.equals(soll, ist)) {
			logDiff(soll, ist);
			fail("Daten in der exportierten CSV weichen ab vom soll.");
		}
	}

	@Bean
	public MessageCollector getMC() {

		return new MessageCollectorImpl(Mockito.mock(MailUtils.class), Mockito.mock(ConfigService.class));
	}

	@Bean
	public PeriodService getPeriodService() {

		return new PeriodServiceImpl() {

			@Override
			public Long getCurrentSemesterzeitraumId() {

				return 98L;
			}

			@Override
			public Long getNextSemesterzeitraumId() {

				return 99L;
			}
		};
	}

	@Bean
	public SyscallUtils getSyscallUtils() {

		return new SyscallUtils() {

			@Override
			public String runCmd(File workdir, String... cmd)
					throws IOException, InterruptedException, CmdFaildException {

				if (cmd[0].contains("studdata.sh")) {
					outFile = new FileSystemResource(workdir.getAbsolutePath() + "/" + cmd[1]);
				}

				System.out.println(cmd.toString());
				return "";
			}
		};
	}

	@Bean
	public WebserviceFactory getWebserviceFactory() {

		return new WebserviceFactoryMock(new ClassPathResource("sptim/TestResponses/StudentenExportITResponses.xml"));
	}

	private void logDiff(String soll, String ist) {

		String diff = StringUtils.difference(soll, ist);
		if (!diff.isEmpty()) {
			logger.info("Abweichungs position: " + StringUtils.indexOfDifference(soll, ist) + " Soll-Länge: "
					+ soll.length() + " Ist-Länge: " + ist.length());
			logger.info("Abweichung: " + diff);
		}
		logger.error("Soll: \r\n" + soll);
		logger.error("Ist: \r\n" + ist);
	}
}
