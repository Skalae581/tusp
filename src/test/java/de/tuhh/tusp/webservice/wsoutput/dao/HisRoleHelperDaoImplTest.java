package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Holder;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;

import de.his.ws.rightsandrolesservice.OrgRolesType;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.SearchedRole;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.NoRolesDefinedForOuException;
import de.tuhh.tusp.webservice.exception.RolesFromFileException;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

@RunWith(MockitoJUnitRunner.class)
public class HisRoleHelperDaoImplTest {

	@InjectMocks
	private HisRoleHelperDaoImpl dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private RightsAndRolesServicePortType webService;

	@Mock
	private RightsAndRolesDao rightsAndRolesDao;

	@Captor
	private ArgumentCaptor<Holder<Orgrole>> updateCaptor;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		when(factory.getWebservicePort(RightsAndRolesService.class)).thenAnswer(i -> webService);
	}

	@Test
	public void findValidRolesForPerson_withNullParam() throws Exception {

		assertNull(dao.findValidRolesForPerson(null));
	}

	@Test(expected = ServiceFault.class)
	public void findValidRolesForPerson_withServiceFault() throws Exception {

		when(webService.readRolesForPerson(6L)).thenThrow(new ServiceFault_Exception(null, null));

		dao.findValidRolesForPerson(6L);
	}

	@Test
	public void findValidRolesForPerson_withSuccess() throws Exception {

		OrgRolesType roleDefinition = new OrgRolesType();

		Orgrole role1 = new Orgrole();
		role1.setObjGuid("zu alt");
		role1.setValidFrom(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, -4));
		role1.setValidTo(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, -2));
		roleDefinition.getOrgRole().add(role1);

		Orgrole role2 = new Orgrole();
		role2.setObjGuid("genau richtig");
		role2.setValidFrom(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, -2));
		role2.setValidTo(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, 2));
		roleDefinition.getOrgRole().add(role2);

		Orgrole role3 = new Orgrole();
		role3.setObjGuid("zu neu");
		role3.setValidFrom(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, 4));
		role3.setValidTo(CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE, 6));
		roleDefinition.getOrgRole().add(role3);

		when(webService.readRolesForPerson(5L)).thenAnswer(i -> roleDefinition);

		List<Orgrole> result = dao.findValidRolesForPerson(5L);

		assertEquals(1, result.size());
		assertEquals("genau richtig", result.get(0).getObjGuid());
	}

	@Test
	public void getRoleForPerson_withEmptyRoles() throws Exception {

		when(webService.readRolesForPerson(4l)).then(i -> new OrgRolesType());

		assertNull(dao.getRoleForPerson(4, "mustermax"));
	}

	@Test
	public void getRoleForPerson_withNull() throws Exception {

		assertNull(dao.getRoleForPerson(0, null));
	}

	@Test
	public void getRoleForPerson_withWithEmptyOrgroles() throws Exception {

		SearchedRolesType answer1 = new SearchedRolesType();
		SearchedRole role = new SearchedRole();
		role.setRoleId(5);
		answer1.getSearchedRole().add(role);

		when(webService.findRoles60(null, "=mustermax", null, null, null)).thenAnswer(i -> answer1);
		when(webService.readRolesForPerson(4)).thenAnswer(i -> new OrgRolesType());

		assertNull(dao.getRoleForPerson(4, "mustermax"));
	}

	@Test(expected = ServiceFault.class)
	public void getRoleForPerson_withWithServiceFault1() throws Exception {

		RightsAndRoles role = new RightsAndRoles();
		role.setUniquename("mustermax");
		role.setRoleId(4l);
		when(rightsAndRolesDao.fetchByUniqename(role.getUniquename())).then(i -> role);

		when(webService.readRolesForPerson(4l)).thenThrow(new ServiceFault_Exception(null, null));

		assertEquals("will haben", dao.getRoleForPerson(4, "mustermax").getObjGuid());
	}

	@Test(expected = ServiceFault.class)
	public void getRoleForPerson_withWithServiceFault2() throws Exception {

		SearchedRolesType answer1 = new SearchedRolesType();
		SearchedRole role = new SearchedRole();
		role.setRoleId(5);
		answer1.getSearchedRole().add(role);

		when(webService.findRoles60(null, "=mustermax", null, null, null)).thenAnswer(i -> answer1);
		when(webService.readRolesForPerson(4)).thenThrow(new ServiceFault_Exception(null, null));

		assertEquals("will haben", dao.getRoleForPerson(4, "mustermax").getObjGuid());
	}

	@Test
	public void getRoleForPerson_withWithSuccess() throws Exception {

		RightsAndRoles role = new RightsAndRoles();
		role.setUniquename("mustermax");
		role.setRoleId(4l);
		when(rightsAndRolesDao.fetchByUniqename(role.getUniquename())).then(i -> role);

		OrgRolesType answer2 = new OrgRolesType();
		Orgrole orgrole = new Orgrole();
		orgrole.setObjGuid("will haben");
		orgrole.setRoleId(role.getRoleId());
		orgrole.setValidFrom(CalendarUtils.stringToXMLGregorianCalendar("2020-01-01",
																		CalendarUtils.DATEFORMAT_ENGLISH));
		orgrole.setValidTo(CalendarUtils.stringToXMLGregorianCalendar("2120-01-01", CalendarUtils.DATEFORMAT_ENGLISH));
		answer2.getOrgRole().add(orgrole);
		when(webService.readRolesForPerson(4)).thenAnswer(i -> answer2);

		assertEquals("will haben", dao.getRoleForPerson(4, "mustermax").getObjGuid());
	}

	@Test(expected = JSONException.class)
	public void getRolesFromFile_withJSONException() throws Exception {

		dao.getRolesFromFile(new ByteArrayResource("{}".getBytes()), null);
	}

	@Test(expected = RolesFromFileException.class)
	public void getRolesFromFile_withRolesFromFileException() throws Exception {

		dao.getRolesFromFile(new ClassPathResource("does not exists"), null);
	}

	@Test
	public void getRolesFromFile_withSuccess() throws Exception {

		String json =
				"{roles: [{\"key\":\"S3\",\"roleIdentifier\":\"sul.zul.bewerber\",\"orgaUnitIndentifier\":\"TUHH\"}]}";
		Map<String, List<HisRole>> result =
				dao.getRolesFromFile(new ByteArrayResource(json.getBytes()), new GregorianCalendar());

		assertEquals("sul.zul.bewerber", result.get("S3").get(0).getRollenUniquename());
		assertEquals("TUHH", result.get("S3").get(0).getOuUniquename());
	}

	@Test(expected = NoRolesDefinedForOuException.class)
	public void getRolesFromFileByOu_withNoRolesDefinedForOuException() throws Exception {

		String json =
				"{roles: [{\"key\":\"S3\",\"roleIdentifier\":\"sul.zul.bewerber\",\"orgaUnitIndentifier\":\"TUHH\"}]}";
		List<HisRole> result =
				dao.getRolesFromFileByOu(new ByteArrayResource(json.getBytes()), "S2", new GregorianCalendar());

		assertEquals("sul.zul.bewerber", result.get(0).getRollenUniquename());
		assertEquals("TUHH", result.get(0).getOuUniquename());
	}

	@Test
	public void getRolesFromFileByOu_withSuccess() throws Exception {

		GregorianCalendar cal = new GregorianCalendar();
		String json =
				"{roles: [{\"key\":\"S3\",\"roleIdentifier\":\"sul.zul.bewerber\",\"orgaUnitIndentifier\":\"TUHH\"}]}";
		List<HisRole> result = dao.getRolesFromFileByOu(new ByteArrayResource(json.getBytes()), "S3", cal);

		assertEquals("sul.zul.bewerber", result.get(0).getRollenUniquename());
		assertEquals("TUHH", result.get(0).getOuUniquename());
		assertEquals(cal.getTimeInMillis(), result.get(0).getValidTo().toGregorianCalendar().getTimeInMillis());
	}

	@Test
	public void roleForPersonWithNameExists_withWithSuccess() throws Exception {

		RightsAndRoles role = new RightsAndRoles();
		role.setUniquename("mustermax");
		role.setRoleId(4l);

		OrgRolesType answer2 = new OrgRolesType();
		Orgrole orgrole = new Orgrole();
		orgrole.setObjGuid("will haben");
		orgrole.setRoleId(role.getRoleId());
		orgrole.setValidFrom(CalendarUtils.stringToXMLGregorianCalendar("2020-01-01",
																		CalendarUtils.DATEFORMAT_ENGLISH));
		orgrole.setValidTo(CalendarUtils.stringToXMLGregorianCalendar("2120-01-01", CalendarUtils.DATEFORMAT_ENGLISH));
		answer2.getOrgRole().add(orgrole);

		when(rightsAndRolesDao.fetchByUniqename(role.getUniquename())).then(i -> role);
		when(webService.readRolesForPerson(4)).thenAnswer(i -> answer2);

		boolean result = dao.roleForPersonWithNameExists(4, "mustermax");

		assertTrue(result);
	}

	@Test
	public void roleForPersonWithNameExists_withWithSuccess2() throws Exception {

		SearchedRolesType answer1 = new SearchedRolesType();
		SearchedRole role = new SearchedRole();
		role.setRoleId(5);
		answer1.getSearchedRole().add(role);

		OrgRolesType answer2 = new OrgRolesType();

		when(webService.findRoles60(null, "=musterfail", null, null, null)).thenAnswer(i -> answer1);
		when(webService.readRolesForPerson(4)).thenAnswer(i -> answer2);

		assertFalse(dao.roleForPersonWithNameExists(4, "musterfail"));
	}

	@Test(expected = ServiceFault.class)
	public void setPersonToOrgunitWithRole_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(webService).setPersonToOrgunitWithRole(any());
		dao.setPersonToOrgunitWithRole(null);
	}

	@Test
	public void setPersonToOrgunitWithRole_withSuccess() throws Exception {

		SetPersonToOrgunitWithRole param = new SetPersonToOrgunitWithRole();
		dao.setPersonToOrgunitWithRole(param);
		verify(webService).setPersonToOrgunitWithRole(param);
	}

	@Test
	public void updateOrgrole_withNull() throws Exception {

		dao.updateOrgRole(null);
		verify(webService).updateOrgrole(any());
	}

	@Test(expected = ServiceFault.class)
	public void updateOrgrole_withServiceFault() throws Exception {

		doThrow(new ServiceFault_Exception(null, null)).when(webService).updateOrgrole(any());
		dao.updateOrgRole(new Orgrole());
	}

	@Test
	public void updateOrgrole_withSuccess() throws Exception {

		Orgrole orgrole = new Orgrole();
		dao.updateOrgRole(orgrole);

		verify(webService).updateOrgrole(updateCaptor.capture());

		assertEquals(orgrole, updateCaptor.getValue().value);
	}
}
