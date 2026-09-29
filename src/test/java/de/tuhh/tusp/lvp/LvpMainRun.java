package de.tuhh.tusp.lvp;

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
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger4J;
import de.tuhh.tusp.lvp.main.LvpMain;
import de.tuhh.tusp.stu.sptim.StudentenExportRun;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageCollectorImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp" }, lazyInit = true, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class) })
@EnableTransactionManagement
@ContextConfiguration(classes = { ApplicationRunTestConfig.class, StudentenExportRun.class })
public class LvpMainRun {

	private final static Logger logger = LogManager.getLogger(LvpMainRun.class);

	@Autowired
	private LvpMain main;

	@Autowired
	private MailUtils mailUtils;

	@Autowired
	private ConfigService configService;

	@Test
	public void exportTest() throws Exception {

		new MessageCollectorImpl(mailUtils, configService);

		main.start(new JobLogger4J(logger));
	}

}
