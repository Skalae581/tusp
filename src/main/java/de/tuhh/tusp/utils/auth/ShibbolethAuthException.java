package de.tuhh.tusp.utils.auth;

import org.springframework.security.authentication.BadCredentialsException;

public class ShibbolethAuthException extends BadCredentialsException {

	/**
	 *
	 */
	private static final long serialVersionUID = 345255036538992660L;

	public ShibbolethAuthException(String msg) {
		super(msg);
	}

}
