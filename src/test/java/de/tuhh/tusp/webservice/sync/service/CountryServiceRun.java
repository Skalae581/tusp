package de.tuhh.tusp.webservice.sync.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationRunTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

@ContextConfiguration(classes = { ApplicationRunTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
// @Transactional // Dieser Test kann auch genutzt werden um die Datenbank zu
// aktualisieren. Dann soll kein Rollback gemacht werden.
public class CountryServiceRun {

	@Autowired
	CountryService service;

	@Test
	public void syncTest() {

		service.sync();
	}
}
