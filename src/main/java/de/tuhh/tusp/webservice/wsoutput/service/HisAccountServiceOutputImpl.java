package de.tuhh.tusp.webservice.wsoutput.service;

import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_ACCOUNTAUTH_ID;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_ACCOUNT_VALIDITY;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_BLOCKED_ID;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_PASSWORD_VALIDITY;
import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_PURPOSE_ID;

import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.CreateAccount;
import de.his.ws.accountservice.NewAccount;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

@Component
public class HisAccountServiceOutputImpl implements HisAccountServiceOutput {

	private static Logger logger = LogManager.getLogger(HisAccountServiceOutputImpl.class);

	@Autowired
	private HisAccountHelperDao hisAccountHelperDao;

	@Autowired
	private ConfigService config;

	/**
	 * Anlegen von einem Account
	 *
	 * @param user
	 * @return Die ID des neuen Account
	 * @throws ServiceFault Wenn Webservice Probleme macht
	 */
	@SuppressWarnings("deprecation")
	@Override
	public long addAccountForPerson(HisPerson user) {

		HisAccount personAccount = user.getAccount();
		CreateAccount request = new CreateAccount();
		NewAccount account = new NewAccount();
		request.setAccount(account);

		account.setPersonId(user.getIdPerson());
		account.setPasswordhash(personAccount.getPasswordhash());
		account.setUsername(personAccount.getUsername());
		account.setIsInitialpassword(
				personAccount.getIsInitialpassword() != null && personAccount.getIsInitialpassword());
		account.setIsLdapAccount(personAccount.getIsLdapAccount() != null && personAccount.getIsLdapAccount());
		account.setAccountauthId(personAccount.getAccountauthId() != null ? personAccount.getAccountauthId()
				: config.get(DEFAULT_ACCOUNTAUTH_ID, 1l, true));
		account.setBlockedId(personAccount.getBlockedId() != null ? personAccount.getBlockedId()
				: config.get(DEFAULT_BLOCKED_ID, 1l, true));
		account.setValidFrom(personAccount.getValidFrom() != null ? personAccount.getValidFrom()
				: new CalendarUtils().getNowAsXMLGregorianCalendar());

		// Authinfo: Feld beinhaltet ein X.509 Zertifikat oder eine Mifare-Id
		// account.setAuthinfo(personAccount.getAuthinfo() != null ?
		// personAccount.getAuthinfo()
		// : config.get(DEFAULT_AUTHINFO, "Auth Info", true));

		// Fremdsystem: Woher kommt der Account (z. B. SVA oder SOSPOS) bzw. für
		// welches Systeme ist er noch gültig?
		// account.setExternalsystemId(personAccount.getExternalsystemId() !=
		// null ? personAccount.getExternalsystemId()
		// : config.get(DEFAULT_EXTERNALSYSTEM_ID, 2L, true));

		// Haben wir nicht
		// account.setOptincookie(personAccount.getOptincookie() != null ?
		// personAccount.getOptincookie()
		// : config.get(DEFAULT_OPTINCOOKIE, "Optioncookie Value", true));

		// Sourcefilter: Kommaseparierte Liste von Rechnernamen / IP-Adressen.
		// Bitte beachten Sie, dass derzeit dieses
		// Feld in HISinOne NICHT ausgewertet wird.
		// account.setSourcefilter(personAccount.getSourcefilter() != null ?
		// personAccount.getSourcefilter()
		// : config.get(DEFAULT_SOURCEFILTER, "Source Filter Value", true));

		account.setPurposeId(personAccount.getPurposeId() != null ? personAccount.getPurposeId()
				: config.get(DEFAULT_PURPOSE_ID, 21L, true)); // employee_management

		account.setPasswordValidTo(personAccount.getPasswordValidTo() != null ? personAccount.getPasswordValidTo()
				: CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE,
						config.get(DEFAULT_PASSWORD_VALIDITY, 30, true)));

		account.setValidTo(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE,
				config.get(DEFAULT_ACCOUNT_VALIDITY, 30, true)));

		return hisAccountHelperDao.createAccount(account);
	}

	/**
	 * Aktualisieren von einem Account
	 *
	 * @param user
	 * @throws ServiceFault Wenn der Webservice Probleme macht
	 */
	@Override
	public void updateAccount(HisPerson user, Long accountId) throws ServiceFault {

		if (user == null || user.getAccount() == null) {
			return;
		}

		HisAccount accountSetting = user.getAccount();

		HisAccountWrapper account = hisAccountHelperDao.readAccount(accountId);

		account.setUsername(
				accountSetting.getUsername() != null ? accountSetting.getUsername() : account.getUsername());

		account.setValidTo(accountSetting.getValidTo() != null ? accountSetting.getValidTo() : account.getValidTo());
		account.setAccountauthId(accountSetting.getAccountauthId() != null ? accountSetting.getAccountauthId()
				: account.getAccountauthId());
		account.setBlockedId(
				accountSetting.getBlockedId() != null ? accountSetting.getBlockedId() : account.getBlockedId());
		account.setAuthinfo(
				accountSetting.getAuthinfo() != null ? accountSetting.getAuthinfo() : account.getAuthinfo());
		account.setExternalsystemId(accountSetting.getExternalsystemId() != null ? accountSetting.getExternalsystemId()
				: account.getExternalsystemId());
		account.setOptincookie(
				accountSetting.getOptincookie() != null ? accountSetting.getOptincookie() : account.getOptincookie());
		account.setPasswordValidTo(accountSetting.getPasswordValidTo() != null ? accountSetting.getPasswordValidTo()
				: account.getPasswordValidTo());
		account.setSourcefilter(accountSetting.getSourcefilter() != null ? accountSetting.getSourcefilter()
				: account.getSourcefilter());
		account.setPurposeId(
				accountSetting.getPurposeId() != null ? accountSetting.getPurposeId() : account.getPurposeId());
		account.setPasswordhash(accountSetting.getPasswordhash() != null ? accountSetting.getPasswordhash()
				: account.getPasswordhash());
		account.setIsInitialpassword(
				accountSetting.getIsInitialpassword() != null ? accountSetting.getIsInitialpassword()
						: account.getIsInitialpassword());
		account.setIsLdapAccount(accountSetting.getIsLdapAccount() != null ? accountSetting.getIsLdapAccount()
				: account.getIsLdapAccount());
		account.setId(accountId);

		hisAccountHelperDao.updateAccount(account);

	}

	@Override
	public Long updateOrCreateAccount(HisPerson user) {

		Long accountId = hisAccountHelperDao.getFirstAccountIdForPerson(user.getIdPerson());

		if (accountId == null) {

			accountId = addAccountForPerson(user);
			logger.info("Account(" + accountId + ") angelegt für " + user.toShortString());
		} else {

			updateAccount(user, accountId);
			logger.info("Account(" + accountId + ") aktualisiert für " + user.toShortString());
		}
		return accountId;

	}

}
