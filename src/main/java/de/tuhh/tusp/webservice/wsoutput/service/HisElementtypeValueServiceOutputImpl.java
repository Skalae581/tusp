package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisElementtypeValueHelperDao;

@Component
public class HisElementtypeValueServiceOutputImpl implements HisElementtypeValueServiceOutput {

	@Autowired
	private HisElementtypeValueHelperDao hisElementtypeValueHelperDao;

	/**
	 * Gibt die Liste von TermTypes zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<KeyValue> getElementTypes() throws ServiceFault_Exception {

		return hisElementtypeValueHelperDao.getElementTypes();
	}

}
