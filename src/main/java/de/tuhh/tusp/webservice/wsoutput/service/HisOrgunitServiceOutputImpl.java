package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisOrgunitHelperDao;

@Component
public class HisOrgunitServiceOutputImpl implements HisOrgunitServiceOutput {

	@Autowired
	private HisOrgunitHelperDao hisOrgunitHelperDao;

	/**
	 * Gibt die Liste von Orgunits zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<KeyValue> getOrgunit() throws ServiceFault_Exception {

		return hisOrgunitHelperDao.getOrgunit();
	}

}
