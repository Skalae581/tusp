package de.tuhh.tusp.utils.auth;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RoleparametersType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPersonHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;

@RunWith(MockitoJUnitRunner.class)
public class RoleFetcherImplTest {

	@InjectMocks
	private RoleFetcherImpl roleFetcher;

	@Mock
	private LdapDao ldapDao;

	@Mock
	private Authentication authentification;

	@Mock
	private HisPersonHelperDao hisPersonDao;

	@Mock
	private HisAccountHelperDao hisAccountDao;

	@Mock
	private HisRoleHelperDao hisRoleDao;

	@Mock
	private RightsAndRolesDao rightsAndRolesDao;

	@Mock
	private ConfigService config;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAuthoritiesByUseranme_authtypeHio() throws ServiceFault_Exception {

		Collection<GrantedAuthority> authorities;

		authorities = roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.HIO);

		assertEquals("ROLE_NON_SHIBBOLETH_USER", authorities.toArray(new GrantedAuthority[1])[0].getAuthority());
	}

	@Test
	public void getAuthoritiesByUseranme_authtypeNull() throws ServiceFault_Exception {

		Collection<GrantedAuthority> authorities;

		authorities = roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.NULL);
		assertTrue(authorities.isEmpty());
	}

	@Test
	public	void
			getAuthoritiesByUseranme_authtypeShibboleth_nichtGefundeneRollenWerdenAbgefangen() throws ServiceFault_Exception {

		TestUtils.setField(roleFetcher, "dialect", "postgres");
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setPersonId(5L);
		when(hisAccountDao.fetchAccountByUsername("mustermann")).thenAnswer(i -> accountInfo);

		List<Orgrole> roles = Arrays.asList(new Orgrole());
		roles.get(0).setRoleId(15L);
		roles.get(0).setRoleparameters(new RoleparametersType());
		when(hisRoleDao.findValidRolesForPerson(5L)).thenAnswer(i -> roles);

		when(rightsAndRolesDao.fetchById(15L)).thenThrow(new NoSuchElementException());

		Collection<GrantedAuthority> authorities =
				roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.SHIBBOLETH);

		GrantedAuthority[] granted = authorities.toArray(new GrantedAuthority[1]);
		assertEquals("ROLE_HISINONE-ROLE-15", granted[0].toString());
	}

	@Test
	public void getAuthoritiesByUseranme_authtypeShibboleth_personNotFound() throws ServiceFault_Exception {

		TestUtils.setField(roleFetcher, "dialect", "postgres");
		when(hisAccountDao.fetchAccountByUsername("mustermann")).thenAnswer(i -> null);

		Collection<GrantedAuthority> authorities =
				roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.SHIBBOLETH);

		assertEquals(new GuestAuthority(), authorities.toArray(new GrantedAuthority[1])[0]);
	}

	@Test
	public void getAuthoritiesByUseranme_authtypeShibboleth2() throws ServiceFault_Exception {

		TestUtils.setField(roleFetcher, "dialect", "postgres");
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setPersonId(5L);
		when(hisAccountDao.fetchAccountByUsername("mustermann")).thenAnswer(i -> accountInfo);

		List<Orgrole> roles = Arrays.asList(new Orgrole());
		roles.get(0).setRoleId(17L);
		roles.get(0).setRoleparameters(new RoleparametersType());
		when(hisRoleDao.findValidRolesForPerson(5L)).thenAnswer(i -> roles);

		RightsAndRoles role = new RightsAndRoles();
		role.setUniquename("Testrolle");
		when(rightsAndRolesDao.fetchById(17L)).thenAnswer(i -> role);

		Collection<GrantedAuthority> authorities =
				roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.SHIBBOLETH);

		GrantedAuthority[] granted = authorities.toArray(new GrantedAuthority[1]);
		assertEquals("ROLE_TESTROLLE", granted[0].toString());
	}

	@Test
	public void getAuthoritiesByUseranme_imTestsystem() throws ServiceFault_Exception {

		TestUtils.setField(roleFetcher, "dialect", "h2");

		Collection<GrantedAuthority> authorities =
				roleFetcher.getAuthoritiersByUsername("mustermann", AuthTypes.SHIBBOLETH);

		assertEquals("ROLE_MUSTERMANN", authorities.toArray(new GrantedAuthority[1])[0].getAuthority());
	}

	@Test
	public void getAuthoritiesByUseranme_usernameNull() throws ServiceFault_Exception {

		Collection<GrantedAuthority> authorities;

		authorities = roleFetcher.getAuthoritiersByUsername("", AuthTypes.SHIBBOLETH);
		assertTrue(authorities.isEmpty());

		authorities = roleFetcher.getAuthoritiersByUsername(null, AuthTypes.SHIBBOLETH);
		assertTrue(authorities.isEmpty());
	}
}
