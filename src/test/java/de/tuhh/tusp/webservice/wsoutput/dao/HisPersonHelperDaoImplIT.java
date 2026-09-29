package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersonService;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.personservice.ServiceFault_Exception;
import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class HisPersonHelperDaoImplIT {

	private long personId;
	private String username = "rztjj";

	@Autowired
	private WebserviceFactory serviceFactory;
	private PersonServicePortType personService;

	@Before
	public void _serviceInit() throws de.his.ws.accountservice.ServiceFault_Exception {

		personService = serviceFactory.getWebservicePort(PersonService.class);

		AccountServicePortType accoutnPort = serviceFactory.getWebservicePort(AccountService.class);

		personId = accoutnPort	.findAccount("", "=" + username, "", "", "", "", "", "", "", "").getAccountInfo().get(0)
								.getPersonId();
	}

	@Test
	public void fetchPerson() throws ServiceFault_Exception {

		PersonExisting person = personService.readPerson(personId);
		assertNotNull("Person Null!", person);
	}

	@Test
	public void getPersonIdIfExists() throws ServiceFault_Exception {

		Long personId = personService
										.findPerson("", "", "", "", "", "", "", "", "", "", username, "", "", "", "",
													"", "", "", "", "", "", "")
										.getId().stream().findFirst().orElse(null);

		assertTrue("Person mit dieser Benutzername nicht gefunden", personId > 0);
	}

}
