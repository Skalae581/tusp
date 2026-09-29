package de.tuhh.tusp.webservice.wsoutput.service;

import static de.tuhh.tusp.utils.CalendarUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.sync.service.OrgUnitService;
import de.tuhh.tusp.webservice.sync.service.RightsAndRolesService;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

@RunWith(MockitoJUnitRunner.class)
public class HisRolesServiceOutputImplTest {

	@InjectMocks
	private HisRolesServiceOutputImpl service;

	@Mock
	private OrgUnitService ouService;

	@Mock
	private RightsAndRolesService rarService;

	@Mock
	private HisRoleHelperDao dao;

	@Mock
	private ConfigService config;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void updateOrCreateRolesForPerson_rollenCreateSuccessfull() throws Exception {

		long personId = 4L;

		OrgUnit ou = new OrgUnit();
		ou.setLid(4L);
		when(ouService.fetchByUniquename("testou")).thenAnswer(i -> ou);

		RightsAndRoles role = new RightsAndRoles();
		role.setRoleId(56L);
		when(rarService.fetchByUniquename("tester")).thenAnswer(i -> role);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2016-12-31"));
		user.getRoles().get(0).setRollenUniquename("tester");
		user.getRoles().get(0).setOuUniquename("testou");
		user.getRoles().get(0).setValidFrom(CalendarUtils.strToXMLGregCal("2016-01-01"));
		service.updateOrCreateRolesForPerson(user, personId);

		verify(dao, times(0)).updateOrgRole(any());

		ArgumentCaptor<SetPersonToOrgunitWithRole> captor = ArgumentCaptor.forClass(SetPersonToOrgunitWithRole.class);
		verify(dao).setPersonToOrgunitWithRole(captor.capture());

		assertEquals(0, CalendarUtils.strToXMLGregCal("2016-12-31").compare(captor.getValue().getValidTo()));
		assertEquals(0, CalendarUtils.strToXMLGregCal("2016-01-01").compare(captor.getValue().getValidFrom()));
		assertEquals(56, captor.getValue().getOrgroleId());
		assertEquals(4, captor.getValue().getOrgunitLid());
		assertEquals(personId, captor.getValue().getPersonId());
	}

	@Test
	public void updateOrCreateRolesForPerson_rollenCreateWithoutDates() throws Exception {

		long personId = 4L;

		OrgUnit ou = new OrgUnit();
		ou.setLid(4L);
		when(ouService.fetchByUniquename("testou")).thenAnswer(i -> ou);

		RightsAndRoles role = new RightsAndRoles();
		role.setRoleId(56L);
		when(rarService.fetchByUniquename("tester")).thenAnswer(i -> role);

		when(config.get(any(String.class), any(Integer.class), any(Boolean.class))).then(i -> 5);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		// user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2016-12-31"));
		user.getRoles().get(0).setRollenUniquename("tester");
		user.getRoles().get(0).setOuUniquename("testou");
		// user.getRoles().get(0).setValidFrom(CalendarUtils.strToXMLGregCal("2016-01-01"));
		service.updateOrCreateRolesForPerson(user, personId);

		verify(dao, times(0)).updateOrgRole(any());

		ArgumentCaptor<SetPersonToOrgunitWithRole> captor = ArgumentCaptor.forClass(SetPersonToOrgunitWithRole.class);
		verify(dao).setPersonToOrgunitWithRole(captor.capture());

		assertTrue(2000 > absoluteDifferenceInMillis(	getXMLGregorianCalendar(GregorianCalendar.DATE, 5),
														captor.getValue().getValidTo()));
		assertTrue(2000 > absoluteDifferenceInMillis(	new CalendarUtils().getNowAsXMLGregorianCalendar(),
														captor.getValue().getValidFrom()));
		assertEquals(56, captor.getValue().getOrgroleId());
		assertEquals(4, captor.getValue().getOrgunitLid());
		assertEquals(personId, captor.getValue().getPersonId());
	}

	@Test(expected = IllegalStateException.class)
	public void updateOrCreateRolesForPerson_rollenCreateWithoutNullOu() throws Exception {

		long personId = 4L;

		// OrgUnit ou = new OrgUnit();
		// ou.setLid(4L);
		// when(ouService.fetchByShorttextDe("testou")).thenAnswer(i -> ou);

		RightsAndRoles role = new RightsAndRoles();
		role.setRoleId(56L);
		when(rarService.fetchByUniquename("tester")).thenAnswer(i -> role);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2016-12-31"));
		user.getRoles().get(0).setRollenUniquename("tester");
		user.getRoles().get(0).setOuUniquename("testou");
		user.getRoles().get(0).setValidFrom(CalendarUtils.strToXMLGregCal("2016-01-01"));
		service.updateOrCreateRolesForPerson(user, personId);
	}

	@Test(expected = IllegalStateException.class)
	public void updateOrCreateRolesForPerson_rollenCreateWithoutNullRole() throws Exception {

		long personId = 4L;

		OrgUnit ou = new OrgUnit();
		ou.setLid(4L);
		when(ouService.fetchByShorttextDe("testou")).thenAnswer(i -> ou);

		// RightsAndRoles role = new RightsAndRoles();
		// role.setRoleId(56L);
		// when(rarService.fetchByUniquename("tester")).thenAnswer(i -> role);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2016-12-31"));
		user.getRoles().get(0).setRollenUniquename("tester");
		user.getRoles().get(0).setOuUniquename("testou");
		user.getRoles().get(0).setValidFrom(CalendarUtils.strToXMLGregCal("2016-01-01"));
		service.updateOrCreateRolesForPerson(user, personId);
	}

	@Test
	public void updateOrCreateRolesForPerson_rollenUpdate() throws Exception {

		Long personId = 4L;
		String roleName = "tester";

		List<Orgrole> roles = new ArrayList<>();
		when(dao.getAllRolesForPerson(personId)).then(i -> roles);

		Orgrole rolle = new Orgrole();
		rolle.setValidTo(CalendarUtils.strToXMLGregCal("2017-01-01"));

		when(dao.filterOrgrole(roles, roleName)).then(i -> rolle);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2017-12-31"));
		user.getRoles().get(0).setRollenUniquename(roleName);
		service.updateOrCreateRolesForPerson(user, personId);

		ArgumentCaptor<Orgrole> captor = ArgumentCaptor.forClass(Orgrole.class);
		verify(dao).updateOrgRole(captor.capture());

		assertEquals(0, CalendarUtils.strToXMLGregCal("2017-12-31").compare(captor.getValue().getValidTo()));
	}

	@Test
	public void updateOrCreateRolesForPerson_rollenUpdateNotNessisary() throws Exception {

		Long personId = 4L;
		String roleName = "tester";

		List<Orgrole> roles = new ArrayList<>();
		when(dao.getAllRolesForPerson(personId)).then(i -> roles);

		Orgrole rolle = new Orgrole();
		rolle.setValidTo(CalendarUtils.strToXMLGregCal("2017-01-01"));

		when(dao.filterOrgrole(roles, roleName)).then(i -> rolle);

		HisPerson user = new HisPerson(null, null);
		user.setRoles(new ArrayList<>());
		user.getRoles().add(new HisRole());
		user.getRoles().get(0).setValidTo(CalendarUtils.strToXMLGregCal("2016-12-31"));
		user.getRoles().get(0).setRollenUniquename(roleName);
		service.updateOrCreateRolesForPerson(user, personId);

		verify(dao, times(0)).updateOrgRole(any());

	}

	@Test
	public void updateOrCreateRolesForPerson_withNulls() throws Exception {

		service.updateOrCreateRolesForPerson(null, 5l);
		service.updateOrCreateRolesForPerson(new HisPerson(null, null), 5l);

		// Nothing happens
	}
}
