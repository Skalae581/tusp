package de.tuhh.tusp.webservice.sync;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationRunTestConfig;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.main.Main;

@ContextConfiguration(classes = { ApplicationRunTestConfig.class, WebserviceWebConfig.class, SyncRun.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = "de.tuhh.tusp.utils")
public class SyncRun {

	private static Logger logger = LogManager.getLogger(SyncRun.class);

	@Autowired
	private Main main;

	@Bean
	public Main getMain() {

		return new Main();
	}

	@Test
	public void main() {

		Job job = new Job();
		job.setName("Syncjob");

		main.start(new JobLogger() {

			@Override
			public void debug(String message) {

				logger.debug(message);
			}

			@Override
			public void error(String message) {

				logger.error(message);
			}

			@Override
			public void error(String message, Throwable e) {

				logger.error(message, e);
			}

			@Override
			public Job getJob() {

				return job;
			}

			@Override
			public String getText() {
				return null;
			}

			@Override
			public void info(String message) {

				logger.info(message);
			}

			@Override
			public void warn(String message) {

				logger.warn(message);
			}
		});
		logger.info("Done");
	}
}
