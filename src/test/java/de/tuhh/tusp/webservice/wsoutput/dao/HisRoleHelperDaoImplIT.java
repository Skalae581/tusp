package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.accountservice.FindAccountResultType;
import de.his.ws.rightsandrolesservice.OrgRoleInfo;
import de.his.ws.rightsandrolesservice.OrgRolesType;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class HisRoleHelperDaoImplIT {

	// test data
	private Long personId;
	private List<Orgrole> personRoles;
	private String uniqueName = "mitarbeiter";
	private String roleFileLoc = "uniHH\\uniHHImportRoles\\uniHHImportRoles.JSON";

	@Autowired
	WebserviceFactory serviceFactory;

	RightsAndRolesServicePortType rightsAndRolesService;

	@Before
	public void _serviceInit() throws de.his.ws.accountservice.ServiceFault_Exception, ServiceFault_Exception {

		rightsAndRolesService = serviceFactory.getWebservicePort(RightsAndRolesService.class);
		AccountServicePortType accountPort = serviceFactory.getWebservicePort(AccountService.class);

		// Accoutn "rztjm" müsste auf allen Systemen existieren
		FindAccountResultType account = accountPort.findAccount("", "rztjm", "", "", "", "", "", "", "", "");

		// Testdaten entsprechend der Umgebung initialisieren
		personId = account.getAccountInfo().get(0).getPersonId();
		personRoles = rightsAndRolesService.readRolesForPerson(personId).getOrgRole();
	}

	@Test
	public void anyRoleForPersonExistsTest() {

		OrgRolesType roleType = null;

		try {
			roleType = rightsAndRolesService.readRolesForPerson(personId);
		}
		catch (ServiceFault_Exception e) {
			fail("Rollen konnten für Person " + personId + " nicht abgerufen werden.");
			throw new RuntimeException(e);
		}

		assertTrue("Keine Rolle Vorhanden!", !roleType.getOrgRole().isEmpty());
	}

	@Test
	public void fetchRoleByNameTest() {

		long roleId = 0;
		try {
			roleId = rightsAndRolesService.findRoles60("", uniqueName, "", "", "").getSearchedRole().get(0).getRoleId();
		}
		catch (de.his.ws.rightsandrolesservice.ServiceFault_Exception e1) {
			fail("Mitarbeiter Rolle muss vorhanden sein! Fehler!");
			throw new RuntimeException(e1);
		}
		assertTrue("Mitarbeiter Rolle in komischen Zustand", roleId > 0);
	}

	@Test
	public void getOrgRoleLidTest() {

		Long orgUnitLid = null;
		try {
			Long roleId = personRoles.get(0).getId();
			List<OrgRoleInfo> roles =
					rightsAndRolesService.findOrgRole60(roleId + "", "", "", "", "", "", "").getOrgRoleInfo();
			orgUnitLid = roles.get(0).getOrgunitLid();
		}
		catch (ServiceFault_Exception e) {
			fail("OrgUnitLid gibt es nicht! Fehler!");
			throw new RuntimeException(e);
		}

		assertTrue("OrgUnitLid nicht gleich!", orgUnitLid == personRoles.get(0).getOrgunitLid());
	}

	@Test
	public void getRolesFromFileTest() {

		JSONObject jobj = null;
		Map<String, List<HisRole>> roleMap = new HashMap<>();
		try {
			Reader f = new FileReader(new ClassPathResource(roleFileLoc).getFile());
			JSONTokener tokener = new JSONTokener(f);
			jobj = new JSONObject(tokener);
		}
		catch (IOException e) {
			fail("Rollen-Datei ist nicht vorhanden! Fehler!");
			throw new RuntimeException(e);
		}

		if (jobj != null) {
			JSONArray roles = jobj.getJSONArray("roles");

			for (int i = 0; i < roles.toList().size(); i++) {
				JSONObject searchedElements = roles.getJSONObject(i);
				String key = searchedElements.getString("key");
				String roleIdentifier = searchedElements.getString("roleIdentifier");
				String orgaUnitIndentifier = searchedElements.getString("orgaUnitIndentifier");

				if (!roleMap.containsKey(key)) {
					roleMap.put(key, new ArrayList<HisRole>());
				}
				HisRole hr = new HisRole();
				hr.setOuUniquename(orgaUnitIndentifier);
				hr.setRollenUniquename(roleIdentifier);
				hr.setValidTo(CalendarUtils.stringToXMLGregorianCalendar(	"2017-11-15",
																			CalendarUtils.DATEFORMAT_ENGLISH));

				roleMap.get(key).add(hr);

			}
		}
		assertFalse("Rollen Map ist nicht gleich!", roleMap.entrySet().isEmpty());

	}

}
