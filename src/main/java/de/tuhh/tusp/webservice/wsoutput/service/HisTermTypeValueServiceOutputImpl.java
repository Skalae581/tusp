package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisTermTypeValueHelperDao;

@Component
public class HisTermTypeValueServiceOutputImpl implements HisTermTypeValueServiceOutput {

	@Autowired
	private HisTermTypeValueHelperDao hisTermTypeValueDao;

	/**
	 * Gibt die Liste von TermTypes zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<AbstractKeyValue> getTermTypes() throws ServiceFault_Exception {

		return hisTermTypeValueDao.getTermTypes();

	}

}
