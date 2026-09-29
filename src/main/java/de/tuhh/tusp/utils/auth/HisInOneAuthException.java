package de.tuhh.tusp.utils.auth;

import org.springframework.security.authentication.BadCredentialsException;

public class HisInOneAuthException extends BadCredentialsException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7299460750018031036L;

	public HisInOneAuthException(String msg, Throwable t) {
		super(msg, t);
	}

}
