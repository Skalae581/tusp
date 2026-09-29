package de.tuhh.tusp.webservice.wsoutput.dao;

import de.his.ws.termservice.ServiceFault_Exception;
import de.his.ws.termservice.Term;

public interface HisTermHelperDao {

	Term getCurrentLectureTerm() throws ServiceFault_Exception;

	Term getCurrentTerm() throws ServiceFault_Exception;

}
