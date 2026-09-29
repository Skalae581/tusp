package de.tuhh.tusp.webservice.wsoutput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.termservice.ServiceFault_Exception;
import de.his.ws.termservice.Term;
import de.tuhh.tusp.webservice.wsoutput.dao.HisTermHelperDao;

@Component
public class HisTermServiceOutputImpl implements HisTermServiceOutput {

	@Autowired
	private HisTermHelperDao hisTermHelperDao;

	@Override
	public Term getCurrentLectureTerm() throws ServiceFault_Exception {
		return hisTermHelperDao.getCurrentLectureTerm();
	}

	@Override
	public Term getCurrentTerm() throws ServiceFault_Exception {
		return hisTermHelperDao.getCurrentTerm();
	}

}
