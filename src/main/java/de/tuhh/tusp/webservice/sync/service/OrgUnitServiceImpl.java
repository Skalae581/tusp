package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.orgunitservice.OrgUnitChildren;
import de.his.ws.orgunitservice.OrgUnitServicePortType;
import de.his.ws.orgunitservice.SearchedOrgunit60;
import de.his.ws.orgunitservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.OrgUnitDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class OrgUnitServiceImpl extends WebserviceSyncImpl implements OrgUnitService {

	private static final int ORDER_NUM = 4;

	@Autowired
	protected OrgUnitDao dao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Override
	public OrgUnit fetchByShorttextDe(String name) {

		return dao.fetchByShorttextDe(name);
	}

	@Override
	public OrgUnit fetchByUniquename(String ouUniquename) {

		return dao.fetchByUniquename(ouUniquename);
	}

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	public void sync() {

		List<BasePojo> serviceData = new ArrayList<>();
		List<SearchedOrgunit60> units;
		OrgUnitServicePortType port =
				webserviceFactory.getWebservicePort(de.his.ws.orgunitservice.OrgUnitService.class);
		try {
			units = port.findOrgUnit61("", "", "", "", "", "", "", "", "", "", "", "").getSearchedOrgunit();
		}
		catch (ServiceFault_Exception e) {
			throw new RuntimeException("Konnte OrgUnits nicht abrufen.", e);
		}
		for (SearchedOrgunit60 hisUnit : units) {

			OrgUnit orgUnit = new OrgUnit();
			orgUnit.setId(hisUnit.getId());
			orgUnit.setLid(hisUnit.getId());
			orgUnit.setUniquename(hisUnit.getUniquename());
			try {
				OrgUnitChildren de = port.readOrgunitById(hisUnit.getId());
				orgUnit.setDisplaytextDe(de.getDisplaytext());
				orgUnit.setShorttextDe(de.getShorttext());
				orgUnit.setLongtextDe(de.getLongtext());

				orgUnit.setParentId(de.getParentId());
				orgUnit.setType(de.getType());
			}
			catch (ServiceFault_Exception e) {
				throw new RuntimeException(e);
			}

			orgUnit.setValid(true);

			serviceData.add(orgUnit);
		}

		syncData(serviceData, dao);

	}

}
