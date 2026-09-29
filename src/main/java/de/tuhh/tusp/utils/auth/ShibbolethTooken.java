package de.tuhh.tusp.utils.auth;

import java.io.Serializable;

/**
 * Klasse dient als Token, damit kein Stringpasswort zwischen dem {@link ShibbolethAuthenticationFilter} und dem
 * {@link CustomAuthenticationProvider} übergeben werdne muss.
 *
 * @author rztjm
 *
 */
public class ShibbolethTooken implements Serializable {

	private static final long serialVersionUID = -6568607307791516882L;

}
