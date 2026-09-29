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
public class GenderServiceRun {

	@Autowired
	GenderService service;

	@Test
	public void syncTest() {

		try {
			service.sync();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
