package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.SearchedRole;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class RightsAndRolesServiceImpl extends WebserviceSyncImpl implements RightsAndRolesService {

	private static final int ORDER_NUM = 8;

	@Autowired
	protected RightsAndRolesDao dao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Override
	public RightsAndRoles fetchByRoleId(Long roleId) {

		return dao.fetchById(roleId);
	}

	@Override
	public RightsAndRoles fetchByUniquename(String uniquename) {

		return dao.fetchByUniqename(uniquename);
	}

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	public void sync() {

		SearchedRolesType returnedType;
		try {
			RightsAndRolesServicePortType port =
					webserviceFactory.getWebservicePort(de.his.ws.rightsandrolesservice.RightsAndRolesService.class);
			returnedType = port.findRoles60("", "", "", "", "");
		}
		catch (de.his.ws.rightsandrolesservice.ServiceFault_Exception e) {
			throw new RuntimeException("Konnte RightsAndRoles nicht abrufen.", e);
		}

		List<BasePojo> serviceData = new ArrayList<>();

		for (SearchedRole hisRole : returnedType.getSearchedRole()) {

			RightsAndRoles c = new RightsAndRoles();
			c.setRoleId(hisRole.getRoleId());
			c.setUniquename(hisRole.getUniquename());
			c.setDefaulttext(hisRole.getDefaulttext());
			c.setShorttext(hisRole.getShorttext());
			c.setLongtext(hisRole.getLongtext());
			c.setValid(true);

			serviceData.add(c);

		}
		syncData(serviceData, dao);
	}

}
