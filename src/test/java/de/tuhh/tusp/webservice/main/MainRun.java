package de.tuhh.tusp.webservice.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationRunTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.main.Main;

@ContextConfiguration(classes = { ApplicationRunTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
// @Transactional
public class MainRun {

	@Autowired
	private Main main;

	@Test
	public void insertOrgUnitTest() throws Exception {

		main.start(null);
	}
}
