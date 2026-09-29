package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.rightsandrolesservice.OrgRolesType;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.PersonDao;
import de.tuhh.tusp.webservice.sync.dao.PersonRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.Person;
import de.tuhh.tusp.webservice.sync.pojo.PersonRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class PersonRolesServiceImpl extends WebserviceSyncImpl implements PersonRolesService {

	private static final int ORDER_NUM = 9;

	@Autowired
	protected PersonRolesDao dao;

	@Autowired
	protected PersonDao personDao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	public void sync() {

		List<BasePojo> serviceData = new ArrayList<>();

		for (Person p : personDao.findAll()) {

			try {
				RightsAndRolesServicePortType port = webserviceFactory.getWebservicePort(RightsAndRolesService.class);
				OrgRolesType returnedType = port.readRolesForPerson(p.getId());
				for (Orgrole orgrole : returnedType.getOrgRole()) {
					PersonRoles c = new PersonRoles();
					c.setId(orgrole.getId());
					c.setOrgunitLid(orgrole.getOrgunitLid());
					c.setPersonId(orgrole.getPersonId());
					c.setRoleId(orgrole.getRoleId());
					c.setValidFrom(CalendarUtils.toDate(orgrole.getValidFrom()));
					c.setValidTo(CalendarUtils.toDate(orgrole.getValidTo()));
					c.setValid(true);

					serviceData.add(c);
				}
			}
			catch (de.his.ws.rightsandrolesservice.ServiceFault_Exception e) {
				throw new RuntimeException("Konnte Rollen der Person " + p.getId() + " nicht abrufen.", e);
			}

		}
		syncData(serviceData, dao);
	}

}
