package de.tuhh.tusp.utils.auth;

import org.springframework.security.core.GrantedAuthority;

import de.tuhh.tusp.global.Rollen;

public class GuestAuthority implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = -6438299258562680772L;

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

		return "ROLE_" + Rollen.GUEST.name();
	}

	@Override
	public String toString() {

		return getAuthority();
	}
}
