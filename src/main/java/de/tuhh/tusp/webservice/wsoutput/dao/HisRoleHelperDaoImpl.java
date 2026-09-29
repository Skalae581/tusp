package de.tuhh.tusp.webservice.wsoutput.dao;

import static de.tuhh.tusp.utils.CalendarUtils.isFuture;
import static de.tuhh.tusp.utils.CalendarUtils.toXMLGregorianCalendar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.xml.ws.Holder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.his.ws.rightsandrolesservice.OrgRolesType;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RemoveRoleFromPersonInOrgunit;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.webservice.exception.NoRolesDefinedForOuException;
import de.tuhh.tusp.webservice.exception.RolesFromFileException;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

@Component
public class HisRoleHelperDaoImpl implements HisRoleHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	@Autowired
	private RightsAndRolesDao rightsAndRolesDao;

	@Autowired
	private de.tuhh.tusp.webservice.sync.service.RightsAndRolesService rightsAndRolesService;

	@Override
	public Orgrole filterOrgrole(List<Orgrole> roles, String uniquename) {

		RightsAndRoles role = rightsAndRolesService.fetchByUniquename(uniquename);
		if (role == null) {
			throw new RuntimeException("Rolle (" + uniquename + ") kann nicht gefunden werden!");
		}

		for (Orgrole org : roles) {
			if (org.getRoleId() == role.getRoleId()) {
				return org;
			}
		}

		return null;
	}

	@Override
	public SearchedRolesType findRoles60(String id, String uname) throws ServiceFault {

		try {
			return getRandRPort().findRoles60(id, uname, "", "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<Orgrole> findValidRolesForPerson(Long personId) {

		if (personId == null) {
			return null;
		}

		return getAllRolesForPerson(personId).stream().filter(x ->

		!isFuture(x.getValidFrom().toGregorianCalendar())

				&& isFuture(x.getValidTo().toGregorianCalendar())

		).collect(Collectors.toCollection(ArrayList::new));
	}

	// TODO kann mehrere gleiche Rollen haben
	@Override
	public List<Orgrole> getAllRolesForPerson(long personId) throws NoSuchElementException {

		if (Long.valueOf(personId) == null) {
			return null;
		}
		OrgRolesType orgRolesType;
		try {
			orgRolesType = getRandRPort().readRolesForPerson(personId);
		} catch (ServiceFault_Exception e) {
			return null;
		}

		return orgRolesType.getOrgRole();

	}

	@Override
	public List<Orgrole> getAllRolesForPerson(Long personId) throws ServiceFault {

		try {
			return getRandRPort().readRolesForPerson(personId).getOrgRole();
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	/**
	 * Holt den Port
	 *
	 * @return der Port
	 */
	private RightsAndRolesServicePortType getRandRPort() {

		return serviceFactory.getWebservicePort(RightsAndRolesService.class);
	}

	@Override
	public Orgrole getRoleForPerson(long personId, String roleName) throws NoSuchElementException {

		if (roleName == null) {
			return null;
		}

		RightsAndRoles role = rightsAndRolesDao.fetchByUniqename(roleName);
		List<Orgrole> roleType = findValidRolesForPerson(personId);
		return roleType.stream().filter(x -> role.getId() == x.getRoleId()).findAny().orElse(null);
	}

	@Override
	public Map<String, List<HisRole>> getRolesFromFile(Resource file, GregorianCalendar validTo) {

		JSONObject jobj = null;
		try {
			Reader f = new InputStreamReader(file.getInputStream());
			JSONTokener tokener = new JSONTokener(f);
			jobj = new JSONObject(tokener);
		} catch (IOException e) {
			throw new RolesFromFileException(e);
		}

		Map<String, List<HisRole>> roleMap = new HashMap<>();
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
			hr.setValidTo(toXMLGregorianCalendar(validTo.getTime()));

			roleMap.get(key).add(hr);
		}

		return roleMap;
	}

	@Override
	public List<HisRole> getRolesFromFileByOu(Resource file, String ou, GregorianCalendar validTo)
			throws NoRolesDefinedForOuException {

		Map<String, List<HisRole>> fromFile = this.getRolesFromFile(file, validTo); // TODO
																					// optimierbar
																					// durch
																					// caching
		List<HisRole> ret = fromFile.get(ou);

		if (ret == null || ret.isEmpty()) {

			throw new NoRolesDefinedForOuException(
					"Für die OU " + ou + " sind keine Rollen in " + file + " hinterlegt.");
		}

		return ret;
	}

	@Override
	public SearchedRolesType readRoleById(String id) throws ServiceFault {

		try {
			return getRandRPort().findRoles60(id, "", "", "", "");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void removeRoleFromPersonInOrgunit(Long personId, Long orgunitLid, Long roleId) throws ServiceFault {

		try {
			RemoveRoleFromPersonInOrgunit parameters = new RemoveRoleFromPersonInOrgunit();
			parameters.setOrgroleId(roleId);
			parameters.setOrgunitLid(orgunitLid);
			parameters.setPersonId(personId);
			getRandRPort().removeRoleFromPersonInOrgunit(parameters);
		} catch (Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public boolean roleForPersonWithNameExists(long personId, String roleName) {

		return null != getRoleForPerson(personId, roleName);
	}

	@Override
	public void setPersonToOrgunitWithRole(SetPersonToOrgunitWithRole parameters) {

		try {
			getRandRPort().setPersonToOrgunitWithRole(parameters);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void updateOrgRole(Orgrole bestehendeRole) {

		try {
			Holder<Orgrole> orgrole = new Holder<>(bestehendeRole);
			getRandRPort().updateOrgrole(orgrole);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

}
