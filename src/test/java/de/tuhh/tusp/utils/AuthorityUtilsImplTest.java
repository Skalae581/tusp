package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityUtilsImplTest {

	@InjectMocks
	AuthorityUtilsImpl utils = new AuthorityUtilsImpl();

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		Authentication authentication = new TestingAuthenticationToken("tester", "testpwd", "Testrolle1", "Testrolle2");
		SecurityContext context = new SecurityContextImpl();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);

	}

	@Test
	public void getAdminRolesTest() {

		List<String> roles = utils.getAdminRoles();
		assertEquals("ROLE_ADMIN", roles.get(0));
	}

	@Test
	public void getCurrentUserNameTest() {

		assertEquals("tester", utils.getCurrentUserName());
	}

	@Test
	public void getRolesTest() {

		List<String> roles = utils.getRoles();
		assertEquals(2, roles.size());
		assertEquals("Testrolle1", roles.get(0));
	}

	@Test
	public void getUserAuthoritiesTest() {

		Collection<? extends GrantedAuthority> roles = utils.getUserAuthorities();

		assertEquals(2, roles.size());
		assertEquals("Testrolle1", roles.toArray(new GrantedAuthority[2])[0].getAuthority());
	}
}
