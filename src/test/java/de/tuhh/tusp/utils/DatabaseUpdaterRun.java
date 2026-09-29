package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationRunTestConfig;
import de.tuhh.tusp.config.DatabaseConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

@ContextConfiguration(classes = { ApplicationRunTestConfig.class, DatabaseConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseUpdaterRun {

	@Autowired
	private DatabaseUpdater databaseUpdater;

	@Autowired
	private DSLContext context;

	@Test
	public void runTest() throws Exception {

		try {
			databaseUpdater.initDb(context);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
