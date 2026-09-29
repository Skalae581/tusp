package de.tuhh.tusp.webservice.wsoutput.service;

import de.his.ws.termservice.ServiceFault_Exception;
import de.his.ws.termservice.Term;

public interface HisTermServiceOutput {

	Term getCurrentLectureTerm() throws ServiceFault_Exception;

	Term getCurrentTerm() throws ServiceFault_Exception;

}
