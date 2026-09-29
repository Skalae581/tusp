package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.xml.soap.SOAPException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.accountservice.Account201712;
import de.his.ws.accountservice.AccountInfo;
import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.accountservice.CompleteAccount;
import de.his.ws.accountservice.CompleteAccountsType;
import de.his.ws.accountservice.FindAccountResultType;
import de.his.ws.accountservice.NewAccount;
import de.his.ws.accountservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;

@RunWith(MockitoJUnitRunner.class)
public class HisAccountHelperDaoImplTest {

	@InjectMocks
	private HisAccountHelperDaoImpl candidate;

	@Mock
	private WebserviceFactory webserviceFactory;

	@Mock
	private AccountServicePortType accountService;

	private String today;

	@Before
	public void before() throws SOAPException {

		MockitoAnnotations.initMocks(this);
		today = CalendarUtils.format(new Date(), CalendarUtils.DATEFORMAT_ENGLISH);
		when(webserviceFactory.getWebservicePort(AccountService.class)).thenAnswer(i -> accountService);
	}

	@Test(expected = ServiceFault.class)
	public void createAccount_withServiceFault() throws Exception {

		NewAccount account = new NewAccount();
		doThrow(new ServiceFault_Exception(null, null)).when(accountService).createAccount(account);

		candidate.createAccount(account);
	}

	@Test
	public void createAccount_withSuccess() throws Exception {

		NewAccount account = new NewAccount();
		candidate.createAccount(account);
		verify(accountService).createAccount(account);
	}

	@Test(expected = IllegalStateException.class)
	public void fetchAccountByAccountId_withMultipleResults() throws ServiceFault_Exception {

		FindAccountResultType answer = new FindAccountResultType();
		answer.getAccountInfo().add(new AccountInfo());
		answer.getAccountInfo().add(new AccountInfo());

		when(accountService.findAccount("2", "", "", "", "", "", "", "", "", "")).thenAnswer(i -> answer);

		candidate.fetchAccountByAccountId(2l);
	}

	@Test(expected = IllegalStateException.class)
	public void fetchAccountByAccountId_withNull() throws ServiceFault_Exception {

		when(accountService.findAccount("null", "", "", "", "", "", "", "", "",
										"")).thenAnswer(i -> new FindAccountResultType());

		candidate.fetchAccountByAccountId(null);
	}

	@Test(expected = ServiceFault.class)
	public void fetchAccountByAccountId_withServiceFault() throws ServiceFault_Exception {

		when(accountService.findAccount("null", "", "", "", "", "", "", "", "",
										"")).thenThrow(new ServiceFault_Exception(null, null));

		candidate.fetchAccountByAccountId(null);
	}

	@Test
	public void fetchAccountByAccountId_withSuccess() throws ServiceFault_Exception {

		FindAccountResultType answer = new FindAccountResultType();
		AccountInfo accInfo = new AccountInfo();
		accInfo.setUsername("testine");
		answer.getAccountInfo().add(accInfo);

		when(accountService.findAccount("5", "", "", "", "", "", "", "", "", "")).thenAnswer(i -> answer);

		AccountInfo result = candidate.fetchAccountByAccountId(5L);

		assertEquals("testine", result.getUsername());
	}

	@Test
	public void fetchAccountByUsername_withNoResult() throws Exception {

		when(accountService.findAccount("", "=test", "<=" + today, ">=" + today, "", "1|3", "", "", "",
										"")).thenAnswer(i -> new FindAccountResultType());

		assertNull(candidate.fetchAccountByUsername("test"));

		verify(accountService).findAccount("", "=test", "<=" + today, ">=" + today, "", "1|3", "", "", "", "");
	}

	@Test
	public void fetchAccountByUsername_withNull() throws Exception {

		when(accountService.findAccount("", "=null", "<=" + today, ">=" + today, "", "1|3", "", "", "",
										"")).thenAnswer(i -> new FindAccountResultType());
		assertNull(candidate.fetchAccountByUsername(null));
	}

	@Test(expected = ServiceFault.class)
	public void fetchAccountByUsername_withServiceFault() throws Exception {

		when(accountService.findAccount("", "=test", "<=" + today, ">=" + today, "", "1|3", "", "", "",
										"")).thenThrow(new ServiceFault_Exception(null, null));
		candidate.fetchAccountByUsername("test");
	}

	@Test
	public void fetchAccountByUsername_withSuccess() throws Exception {

		FindAccountResultType answer = new FindAccountResultType();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setUsername("test");
		answer.getAccountInfo().add(accountInfo);

		when(accountService.findAccount("", "=test", "<=" + today, ">=" + today, "", "1|3", "", "", "",
										"")).thenAnswer(i -> answer);

		AccountInfo result = candidate.fetchAccountByUsername("test");

		assertEquals("test", result.getUsername());
	}

	@Test
	public void getFirstAccountIdForPerson_withEmptyAnswer() throws Exception {

		when(accountService.searchAccountForPerson(5L)).thenAnswer(i -> new CompleteAccountsType());

		assertNull(candidate.getFirstAccountIdForPerson(5L));
	}

	@Test
	public void getFirstAccountIdForPerson_withNull() throws Exception {

		assertNull(candidate.getFirstAccountIdForPerson(null));
	}

	@Test(expected = ServiceFault.class)
	public void getFirstAccountIdForPerson_withServiceFault() throws Exception {

		when(accountService.searchAccountForPerson(5L)).thenThrow(new ServiceFault_Exception(null, null));

		candidate.getFirstAccountIdForPerson(5L);
	}

	@Test
	public void getFirstAccountIdForPerson_withSuccess() throws Exception {

		CompleteAccountsType answer = new CompleteAccountsType();
		CompleteAccount account = new CompleteAccount();
		account.setId(6L);
		answer.getCompleteAccount().add(account);
		answer.getCompleteAccount().add(new CompleteAccount());

		when(accountService.searchAccountForPerson(5L)).thenAnswer(i -> answer);

		assertEquals(6L, candidate.getFirstAccountIdForPerson(5L).longValue());
	}

	@Test
	public void readAccount_withNull() throws Exception {

		assertNull(candidate.readAccount(null));
	}

	@Test
	public void readAccount_withResult() throws Exception {

		Account201712 answer = new Account201712();
		answer.setId(4L);

		when(accountService.readAccount201712(4L)).thenAnswer(i -> answer);

		assertTrue(answer.getId() == candidate.readAccount(4L).getId());
	}

	@Test(expected = ServiceFault.class)
	public void readAccount_withServiceFault() throws Exception {

		when(accountService.readAccount201712(4L)).thenThrow(new ServiceFault_Exception(null, null));

		candidate.readAccount(4L);
	}

	@Test(expected = ServiceFault.class)
	public void updateAccount_withServiceFault() throws Exception {

		HisAccountWrapper account = new HisAccountWrapper();
		doThrow(new ServiceFault_Exception(null, null)).when(accountService).updateAccount201712(any());

		candidate.updateAccount(account);
	}
}
