package de.tuhh.tusp.utils.auth;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.MessageContainer;

@RunWith(MockitoJUnitRunner.class)
public class CustomAuthentificationProviderImplTest {

	@InjectMocks
	private CustomAuthenticationProviderImpl authProvider;

	@Mock
	private RoleFetcher roleFetcher;

	@Mock
	private LdapDao ldapDao;

	@Mock
	private MessageContainer messageContainer;

	@Mock
	private Authentication authentification;

	@Test
	public void authentificate_guestWithShibboleth() throws ServiceFault_Exception {

		when(authentification.getCredentials()).thenAnswer(i -> new ShibbolethTooken());
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");
		when(roleFetcher.getAuthoritiersByUsername(any(), any())).thenAnswer(i -> Arrays.asList(new GuestAuthority()));

		Authentication auth = authProvider.authenticate(authentification);

		assertEquals(PreAuthenticatedAuthenticationToken.class, auth.getClass());
		assertEquals(new GuestAuthority(), auth.getAuthorities().toArray(new GrantedAuthority[1])[0]);
		assertEquals("TestUser", auth.getPrincipal());

		verify(roleFetcher, times(1)).getAuthoritiersByUsername("TestUser", AuthTypes.SHIBBOLETH);
	}

	@Test(expected = HisInOneAuthException.class)
	public void authentificate_meldetServiceFault() throws ServiceFault_Exception {

		when(authentification.getCredentials()).thenAnswer(i -> new ShibbolethTooken());
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");
		when(roleFetcher.getAuthoritiersByUsername(any(), any())).thenThrow(new ServiceFault_Exception("test", null));

		authProvider.authenticate(authentification);
	}

	@Test
	public void authentificate_withLdap() throws ServiceFault_Exception { // Sollte nicht LDAP sondern HIO sein

		when(authentification.getCredentials()).thenAnswer(i -> "pa55w0rd");
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");
		when(roleFetcher.getAuthoritiersByUsername(any(), any())).thenAnswer(i -> Arrays.asList(new LocalAuthority()));
		when(ldapDao.auth("TestUser", "pa55w0rd")).thenReturn(true);

		Authentication auth = authProvider.authenticate(authentification);

		assertEquals(PreAuthenticatedAuthenticationToken.class, auth.getClass());
		assertEquals(new LocalAuthority(), auth.getAuthorities().toArray(new GrantedAuthority[1])[0]);
		assertEquals("TestUser", auth.getPrincipal());
		assertEquals("pa55w0rd", auth.getCredentials());

		verify(roleFetcher, times(1)).getAuthoritiersByUsername("TestUser", AuthTypes.HIO);
	}

	@Test(expected = BadCredentialsException.class)
	public void authentificate_withLdap_missingPassword() { // Sollte nicht LDAP sondern HIO sein

		when(authentification.getCredentials()).thenAnswer(i -> null);
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");

		authProvider.authenticate(authentification);
	}

	@Test(expected = BadCredentialsException.class)
	public void authentificate_withLdap_wrongPassword() { // Sollte nicht LDAP sondern HIO sein

		when(authentification.getCredentials()).thenAnswer(i -> "pa55w0rd");
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");
		when(ldapDao.auth("TestUser", "pa55w0rd")).thenReturn(false);

		authProvider.authenticate(authentification);
	}

	@Test(expected = BadCredentialsException.class)
	public void authentificate_withLdap_wrongPasswordClass() { // Sollte nicht LDAP sondern HIO sein

		when(authentification.getCredentials()).thenAnswer(i -> Long.valueOf(1));
		when(authentification.getPrincipal()).thenAnswer(i -> "TestUser");

		authProvider.authenticate(authentification);
	}

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}
}
