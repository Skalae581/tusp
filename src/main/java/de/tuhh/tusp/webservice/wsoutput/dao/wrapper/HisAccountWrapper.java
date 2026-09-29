package de.tuhh.tusp.webservice.wsoutput.dao.wrapper;

import javax.xml.datatype.XMLGregorianCalendar;

import de.his.ws.accountservice.Account201712;
import de.his.ws.accountservice.CompleteAccount60;
import de.his.ws.accountservice.NewAccount;
import lombok.Data;

@Data
public class HisAccountWrapper {

	private Long id;

	private String username;

	private Long accountauthId;

	private String authinfo;

	private Long blockedId;

	private Long externalsystemId;

	private Boolean isInitialpassword;

	private Boolean isLdapAccount;

	private String optincookie;

	private Long personId;

	private String passwordhash;

	private XMLGregorianCalendar passwordValidTo;

	private XMLGregorianCalendar validTo;

	private XMLGregorianCalendar validFrom;

	private Long purposeId;

	private String sourcefilter;

	/**
	 * Erstellt einen leeren Accoutn
	 */
	public HisAccountWrapper() {}

	/**
	 * Erstellt einen Wrapper für den Account
	 *
	 * @param readAccount201712
	 */
	public HisAccountWrapper(Account201712 readAccount201712) {

		this.id = readAccount201712.getId();

		this.username = readAccount201712.getUsername();

		this.accountauthId = readAccount201712.getAccountauthId();

		this.authinfo = readAccount201712.getAuthinfo();

		this.blockedId = readAccount201712.getBlockedId();

		this.externalsystemId = readAccount201712.getExternalsystemId();

		this.isInitialpassword = readAccount201712.isIsInitialpassword();

		this.isLdapAccount = readAccount201712.isIsLdapAccount();

		this.optincookie = readAccount201712.getOptincookie();

		this.personId = readAccount201712.getPersonId();

		this.passwordhash = readAccount201712.getPasswordhash();

		this.passwordValidTo = readAccount201712.getPasswordValidTo();

		this.validTo = readAccount201712.getValidTo();

		this.validFrom = readAccount201712.getValidFrom();

		this.purposeId = readAccount201712.getPurposeId();

		this.sourcefilter = readAccount201712.getSourcefilter();
	}

	/**
	 * Liefert einen {@link Account201712} mit den Werten des Accounts
	 *
	 * @return Account-Clone
	 */
	public Account201712 asAccount201712() {

		Account201712 ret = new Account201712();
		ret.setAccountauthId(getAccountauthId());
		ret.setAuthinfo(getAuthinfo());
		ret.setBlockedId(getBlockedId());
		ret.setExternalsystemId(getExternalsystemId());
		ret.setId(getId());
		ret.setIsInitialpassword(getIsInitialpassword());
		ret.setIsLdapAccount(getIsLdapAccount());
		ret.setOptincookie(getOptincookie());
		ret.setPasswordhash(getPasswordhash());
		ret.setPasswordValidTo(getPasswordValidTo());
		ret.setPersonId(getPersonId());
		ret.setPurposeId(getPurposeId());
		ret.setSourcefilter(getSourcefilter());
		ret.setUsername(getUsername());
		ret.setValidFrom(getValidFrom());
		ret.setValidTo(getValidTo());
		return ret;
	}

	/**
	 * Liefert einen {@link CompleteAccount60} mit den Werten des Accounts
	 *
	 * @return Account-Clone
	 */
	public CompleteAccount60 asCompleteAccount60() {

		CompleteAccount60 ret = new CompleteAccount60();
		ret.setAccountauthId(getAccountauthId());
		ret.setAuthinfo(getAuthinfo());
		ret.setBlockedId(getBlockedId());
		ret.setExternalsystemId(getExternalsystemId());
		ret.setId(getId());
		ret.setIsInitialpassword(getIsInitialpassword());
		ret.setIsLdapAccount(getIsLdapAccount());
		ret.setOptincookie(getOptincookie());
		ret.setPasswordhash(getPasswordhash());
		ret.setPasswordValidTo(getPasswordValidTo());
		ret.setPersonId(getPersonId());
		ret.setPurposeId(getPurposeId());
		ret.setSourcefilter(getSourcefilter());
		ret.setUsername(getUsername());
		ret.setValidFrom(getValidFrom());
		ret.setValidTo(getValidTo());
		return ret;
	}

	public NewAccount asNewAccount() {

		NewAccount ret = new NewAccount();
		ret.setAccountauthId(getAccountauthId());
		ret.setAuthinfo(getAuthinfo());
		ret.setBlockedId(getBlockedId());
		ret.setExternalsystemId(getExternalsystemId());
		ret.setIsInitialpassword(getIsInitialpassword());
		ret.setIsLdapAccount(getIsLdapAccount());
		ret.setOptincookie(getOptincookie());
		ret.setPasswordhash(getPasswordhash());
		ret.setPasswordValidTo(getPasswordValidTo());
		ret.setPersonId(getPersonId());
		ret.setPurposeId(getPurposeId());
		ret.setSourcefilter(getSourcefilter());
		ret.setUsername(getUsername());
		ret.setValidFrom(getValidFrom());
		ret.setValidTo(getValidTo());
		return ret;
	}

}