package de.tuhh.tusp.exa.massenmails;

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
import de.tuhh.tusp.exa.massmails.service.ReceiverSyncService;
import de.tuhh.tusp.global.service.JobLogger4J;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp" }, lazyInit = true, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class) })
@EnableTransactionManagement
@ContextConfiguration(classes = { ApplicationRunTestConfig.class, ReceiverSyncServiceRun.class })
public class ReceiverSyncServiceRun {

	private static Logger logger = LogManager.getLogger(ReceiverSyncServiceRun.class);

	@Autowired
	private ReceiverSyncService service;

	@Test
	public void importTest() throws Exception {

		service.sync(new JobLogger4J(logger));
	}
}
