package de.tuhh.tusp.stu.tuhhstgs;

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
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.JobLogger4J;
import de.tuhh.tusp.stu.tuhhstgs.main.StgMain;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp" }, lazyInit = true, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class) })
@EnableTransactionManagement
@ContextConfiguration(classes = { ApplicationRunTestConfig.class, TuhhStgRun.class })
public class TuhhStgRun {

	private static final Logger logger = LogManager.getLogger(TuhhStgRun.class);

	@Autowired
	private StgMain stgMain;

	@Test
	public void run() throws Exception {

		Job job = new Job();
		job.setName("StgMain Test....");

		stgMain.start(new JobLogger4J(logger));
		logger.info("done");
	}

}
