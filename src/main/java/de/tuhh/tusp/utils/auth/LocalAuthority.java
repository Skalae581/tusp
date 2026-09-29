package de.tuhh.tusp.utils.auth;

import org.springframework.security.core.GrantedAuthority;

import de.tuhh.tusp.global.Rollen;

public class LocalAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 5708416695118482160L;

	private String name;

	/**
	 * Construct
	 */
	public LocalAuthority() {
		name = "ROLE_" + Rollen.NON_SHIBBOLETH_USER.name();
	}

	/**
	 * Construct
	 */
	public LocalAuthority(String name) {
		this.name = "ROLE_" + name;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		return getAuthority().equals(((GrantedAuthority) o).getAuthority());
	}

	@Override
	public String getAuthority() {

		return name;
	}

	@Override
	public String toString() {

		return getAuthority();
	}
}
