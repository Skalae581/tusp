package de.tuhh.tusp.utils.auth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LocalAuthorityTest {

	private LocalAuthority authority = new LocalAuthority();

	@Test
	public void equalsTest() {

		assertFalse(authority.equals(null));
		assertFalse(authority.equals(""));
		assertFalse(authority.equals(new GuestAuthority()));
		assertTrue(authority.equals(authority));
		assertTrue(authority.equals(new LocalAuthority()));
	}

	@Test
	public void getAuthorityTest() {

		assertEquals("ROLE_NON_SHIBBOLETH_USER", authority.getAuthority());
	}

	@Test
	public void toStringTest() {

		assertEquals("ROLE_NON_SHIBBOLETH_USER", authority.toString());
	}
}
