package de.tuhh.tusp.utils.auth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GuestAuthorityTest {

	private GuestAuthority authority = new GuestAuthority();

	@Test
	public void equalsTest() {

		assertFalse(authority.equals(null));
		assertFalse(authority.equals(""));
		assertFalse(authority.equals(new LocalAuthority()));
		assertTrue(authority.equals(authority));
		assertTrue(authority.equals(new GuestAuthority()));
	}

	@Test
	public void getAuthorityTest() {

		assertEquals("ROLE_GUEST", authority.getAuthority());
	}

	@Test
	public void toStringTest() {

		assertEquals("ROLE_GUEST", authority.toString());
	}
}
