package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.accountservice.CompleteAccount;
import de.his.ws.accountservice.CompleteAccount60;
import de.his.ws.accountservice.CompleteAccountsType;
import de.his.ws.accountservice.FindAccountResultType;
import de.his.ws.accountservice.NewAccount;
import de.his.ws.accountservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;

/**
 *
 * @author jjakus Implementierung des HisAccountHelperDao Interfaces
 */
@Component
public class HisAccountHelperDaoImpl implements HisAccountHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	private AccountServicePortType accountService;

	@Override
	public long createAccount(HisAccountWrapper account) {

		long id = createAccount(account.asNewAccount());
		account.setId(id);
		return id;
	}

	@Override
	public long createAccount(NewAccount account) throws ServiceFault {

		try {
			return getPort().createAccount(account);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public AccountInfo fetchAccountByAccountId(Long accountId) {

		FindAccountResultType account;
		try {
			account = getPort().findAccount(accountId + "", "", "", "", "", "", "", "", "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (account.getAccountInfo().size() != 1) {

			throw new IllegalStateException("No unique Account found for ID " + accountId);
		}

		return account.getAccountInfo().get(0);

	}

	@Override
	public AccountInfo fetchAccountByUsername(String uid) {

		String now = CalendarUtils.dateToDatumsString(new Date(), Locale.ENGLISH);
		return fetchAccountByUsername(uid, now);
	}

	private AccountInfo fetchAccountByUsername(String uid, String now) {

		String from = now != null ? "<=" + now : "";
		String to = now != null ? ">=" + now : "";

		FindAccountResultType account;
		try {
			account = getPort().findAccount("", "=" + uid, from, to, "", "1|3", "", "", "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (account.getAccountInfo().size() == 0) {
			return null;
		}

		if (account.getAccountInfo().size() > 1) {
			long pid = 0;
			for (AccountInfo accInfo : account.getAccountInfo()) {
				if (pid == 0) {
					pid = accInfo.getPersonId();
				} else if (pid != accInfo.getPersonId()) {
					throw new IllegalStateException("Multiple accounts found for =" + uid + ": person IDs " + pid
							+ " und " + accInfo.getPersonId());
				}
			}
		}

		return account.getAccountInfo().get(0);
	}

	@Override
	public AccountInfo fetchAccountByUsernameIgnoreDate(String uid) {

		return fetchAccountByUsername(uid, null);
	}

	@Override
	public List<AccountInfo> findAccountFromDate(String date) {

		List<AccountInfo> accounts = new ArrayList<>();

		FindAccountResultType account;
		try {
			account = getPort().findAccount("", "", date + "", "", "", "", "", "", "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (account != null && !account.getAccountInfo().isEmpty()) {
			accounts.addAll(account.getAccountInfo());
		}

		return accounts;
	}

	@Override
	public Long getFirstAccountIdForPerson(Long personId) {

		if (personId == null) {
			return null;
		}

		Long id = null;

		CompleteAccountsType account;
		try {
			account = getPort().searchAccountForPerson(personId);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (account != null && !account.getCompleteAccount().isEmpty()) {
			id = account.getCompleteAccount().get(0).getId();
		}

		return id;
	}

	/**
	 * Service Port Initialisierung
	 *
	 * @return den Port
	 */
	private AccountServicePortType getPort() {

		if (accountService == null) {
			accountService = serviceFactory.getWebservicePort(AccountService.class);
		}

		return accountService;
	}

	@Override
	public HisAccountWrapper readAccount(Long id) {

		if (id == null) {
			return null;
		}

		try {
			return new HisAccountWrapper(getPort().readAccount201712(id));
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public List<CompleteAccount> searchAccountForPerson(Long personId) {

		if (personId == null) {
			return null;
		}

		try {
			return getPort().searchAccountForPerson(personId).getCompleteAccount();
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<CompleteAccount60> searchAccountForPerson60(Long personId) {

		if (personId == null) {
			return null;
		}

		try {
			return getPort().searchAccountForPerson61(personId).getCompleteAccount60();
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void updateAccount(HisAccountWrapper account) throws ServiceFault {

		try {
			long id = getPort().updateAccount201712(account.asAccount201712());
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void updateAccount60(CompleteAccount60 account) throws ServiceFault {

		try {
			getPort().updateAccount(account);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

}
