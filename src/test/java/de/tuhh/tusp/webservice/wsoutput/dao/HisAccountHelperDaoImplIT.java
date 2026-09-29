package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.accountservice.CompleteAccount60;
import de.his.ws.accountservice.ServiceFault_Exception;
import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class HisAccountHelperDaoImplIT {

	@Autowired
	WebserviceFactory serviceFactory;
	AccountServicePortType accountService;
	private AccountInfo account;

	@Before
	public void _serviceInit() throws Exception {

		accountService = serviceFactory.getWebservicePort(AccountService.class);

		List<AccountInfo> accounts =
				accountService.findAccount("", "=rztjj", "", "", "", "", "", "", "", "").getAccountInfo();
		if (accounts.size() != 1) {
			throw new Exception("Zum initialisieren der Tests muss ein Benutzername mit eindeutigem und existeirenden Accoutn verwendet werden.");
		}

		account = accounts.get(0);
	}

	@Test
	public void accountExistsTest() throws ServiceFault_Exception {

		AccountInfo accInfo = accountService.findAccount("", "rztjj", "", "", "", "", "", "", "", "").getAccountInfo()
											.stream().findAny().orElse(null);

		assertNotNull("Benutzer sollte vorhanden sein!", accInfo);
	}

	@Test
	public void fetchAccountbyAccountIdTest() throws ServiceFault_Exception {

		AccountInfo accInfo = null;
		accInfo = accountService.findAccount(account.getId() + "", "", "", "", "", "", "", "", "", "").getAccountInfo()
								.get(0);

		assertNotNull("Account sollte vorhanden sein!", accInfo);

	}

	@Test
	public void fetchAccountbyUidTest() throws ServiceFault_Exception {

		AccountInfo accInfo = null;
		accInfo = accountService.findAccount("", "=rztjj", "", "", "", "", "", "", "", "").getAccountInfo().get(0);

		assertNotNull("Account sollte vorhanden sein!", accInfo);
	}

	@Test
	public void getAccountIdForPersonTest() throws ServiceFault_Exception, ParseException {

		long accId =
				accountService.searchAccountForPerson61(account.getPersonId()).getCompleteAccount60().get(0).getId();

		assertTrue("Account id-> 0 ist nicht vorhanden!", accId > 0);

	}

	@Test
	public void readAccountTest() throws ServiceFault_Exception {

		CompleteAccount60 result = accountService.readAccount(account.getId());

		assertNotNull("Account sollte vorhanden sein!", result);
	}

}
