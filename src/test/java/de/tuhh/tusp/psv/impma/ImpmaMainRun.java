package de.tuhh.tusp.psv.impma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.config.ApplicationRunTestConfig;
import de.tuhh.tusp.config.UtilsTestConfig;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.JobLogger4J;
import de.tuhh.tusp.psv.PsvConfig;
import de.tuhh.tusp.psv.impma.main.ImpmaMainImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp" }, lazyInit = true, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class) })
@EnableTransactionManagement
@ContextConfiguration(classes = { ApplicationRunTestConfig.class, ImpmaMainRun.class, PsvConfig.class,
		UtilsTestConfig.class })
public class ImpmaMainRun {

	Logger logger = LogManager.getLogger(ImpmaMainRun.class);

	@Autowired
	private ImpmaMainImpl main;

	@Test
	public void run() throws Exception {

		Job job = new Job();
		job.setName("Test");

		main.start(new JobLogger4J(logger));
		logger.info("done");
	}
}
