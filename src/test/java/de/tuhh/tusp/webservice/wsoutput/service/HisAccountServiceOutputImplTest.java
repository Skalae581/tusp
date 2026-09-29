package de.tuhh.tusp.webservice.wsoutput.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.accountservice.NewAccount;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

@RunWith(MockitoJUnitRunner.class)
public class HisAccountServiceOutputImplTest {

	@InjectMocks
	private HisAccountServiceOutputImpl service;

	@Mock
	private HisAccountHelperDao hisAccountHelperDao;

	@Mock
	private ConfigService configService;

	@SuppressWarnings("deprecation")
	@Test
	public void addAccountForPerson_withNulls() {

		HisAccount account = new HisAccount();
		account.setAccountauthId(1l);
		account.setAuthinfo("2");
		account.setBlockedId(3l);
		account.setExternalsystemId(4l);
		account.setIsInitialpassword(true);
		account.setIsLdapAccount(false);
		account.setOptincookie("8");
		account.setPasswordhash("9");
		account.setPasswordValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());
		account.setPurposeId(12l);
		account.setSourcefilter("13");
		account.setUsername("14");
		account.setValidFrom(new CalendarUtils().getNowAsXMLGregorianCalendar());
		account.setValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());

		HisPerson user = new HisPerson(null, null);
		user.setIdPerson(12L);
		user.setAccount(account);
		service.addAccountForPerson(user);

		ArgumentCaptor<NewAccount> accountCaptor = ArgumentCaptor.forClass(NewAccount.class);
		verify(hisAccountHelperDao).createAccount(accountCaptor.capture());

		new TestUtils().assertNoFieldIsNull(accountCaptor.getValue(), "getExternalsystemId", "getSourcefilter",
				"getAuthinfo", "getOptincookie");
		assertEquals("14", accountCaptor.getValue().getUsername());
		assertEquals(12l, accountCaptor.getValue().getPersonId());
	}

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void updateAccount_withNoNulls() {

		HisAccountWrapper oldAccount = new HisAccountWrapper();
		oldAccount.setId(5L);
		oldAccount.setPersonId(11L);
		oldAccount.setIsInitialpassword(false); // Muss gesetzt sein, son NPE
		oldAccount.setIsLdapAccount(false); // Muss gesetzt sein, son NPE
		oldAccount.setValidFrom(new CalendarUtils().getNowAsXMLGregorianCalendar()); // wird
																						// nicht
																						// geupdated

		HisAccount account = new HisAccount();
		account.setAccountauthId(1l);
		account.setAuthinfo("2");
		account.setBlockedId(3l);
		account.setExternalsystemId(4l);
		// account.setId(5L);
		account.setIsInitialpassword(true);
		account.setIsLdapAccount(true);
		account.setOptincookie("8");
		account.setPasswordhash("9");
		account.setPasswordValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());
		// account.setPersonId(11l);
		account.setPurposeId(12l);
		account.setSourcefilter("13");
		account.setUsername("14");
		account.setValidFrom(new CalendarUtils().getNowAsXMLGregorianCalendar());
		account.setValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());

		when(hisAccountHelperDao.readAccount(5L)).thenAnswer(i -> oldAccount);

		HisPerson user = new HisPerson(null, null);
		user.setAccount(account);
		service.updateAccount(user, 5L);

		ArgumentCaptor<HisAccountWrapper> accountCaptor = ArgumentCaptor.forClass(HisAccountWrapper.class);
		verify(hisAccountHelperDao).updateAccount(accountCaptor.capture());

		new TestUtils().assertNoFieldIsNull(accountCaptor.getValue());
		assertEquals(5L, (long) accountCaptor.getValue().getId());
		assertEquals("14", accountCaptor.getValue().getUsername());
	}

	@Test
	public void updateAccount_withNulls() {

		HisAccountWrapper account = new HisAccountWrapper();
		account.setAccountauthId(1l);
		account.setAuthinfo("2");
		account.setBlockedId(3l);
		account.setExternalsystemId(4l);
		account.setId(5L);
		account.setIsInitialpassword(true);
		account.setIsLdapAccount(false);
		account.setOptincookie("8");
		account.setPasswordhash("9");
		account.setPasswordValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());
		account.setPersonId(11l);
		account.setPurposeId(12l);
		account.setSourcefilter("13");
		account.setUsername("14");
		account.setValidFrom(new CalendarUtils().getNowAsXMLGregorianCalendar());
		account.setValidTo(new CalendarUtils().getNowAsXMLGregorianCalendar());

		when(hisAccountHelperDao.readAccount(5L)).thenAnswer(i -> account);

		HisPerson user = new HisPerson(null, null);
		user.setAccount(new HisAccount());
		service.updateAccount(user, 5L);

		ArgumentCaptor<HisAccountWrapper> accountCaptor = ArgumentCaptor.forClass(HisAccountWrapper.class);
		verify(hisAccountHelperDao).updateAccount(accountCaptor.capture());

		new TestUtils().assertNoFieldIsNull(accountCaptor.getValue());
		assertEquals(5L, (long) accountCaptor.getValue().getId());
		assertEquals("14", accountCaptor.getValue().getUsername());
	}

	@Test
	public void updateOrCreateAccount_insert() throws Exception {

		when(hisAccountHelperDao.getFirstAccountIdForPerson(1L)).thenAnswer(i -> null);

		HisPerson person = new HisPerson(null, null);
		person.setIdPerson(1L);
		person.setAccount(new HisAccount());
		service.updateOrCreateAccount(person);

		verify(hisAccountHelperDao).createAccount(any(NewAccount.class));
	}

	@Test
	public void updateOrCreateAccount_update() throws Exception {

		when(hisAccountHelperDao.getFirstAccountIdForPerson(1L)).thenAnswer(i -> 25L);
		when(hisAccountHelperDao.readAccount(25L)).thenAnswer(i -> new HisAccountWrapper());
		when(configService.getIfNull(any(Long.class), any(String.class), any(Long.class), any(Boolean.class)))
				.then(i -> 0l);

		HisPerson person = new HisPerson(null, null);
		person.setIdPerson(1L);
		person.setAccount(new HisAccount());
		service.updateOrCreateAccount(person);

		verify(hisAccountHelperDao).updateAccount(any());
	}
}
