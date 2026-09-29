package de.tuhh.tusp.webservice.wsoutput.pojo;

import javax.xml.datatype.XMLGregorianCalendar;

import lombok.Data;

@Data
public class HisAccount {

	private String username;
	private String passwordhash;

	private XMLGregorianCalendar validFrom;
	private XMLGregorianCalendar validTo;
	private Long accountauthId;
	private Long blockedId;
	private String authinfo;
	private Long externalsystemId;
	private Boolean isInitialpassword;
	private Boolean isLdapAccount;
	private String optincookie;
	private XMLGregorianCalendar passwordValidTo;
	private String sourcefilter;
	private Long purposeId;

	/**
	 * Erstellt einen leeren Account
	 */
	public HisAccount() {

	}

	/**
	 * erstellt einen einfachen Account
	 *
	 * @param uname
	 * @param password
	 */
	public HisAccount(String uname, String password) {
		this.username = uname;
		this.passwordhash = password;
	}

	@Override
	public String toString() {

		return "HisAccount(" + username + ")";
	}
}
