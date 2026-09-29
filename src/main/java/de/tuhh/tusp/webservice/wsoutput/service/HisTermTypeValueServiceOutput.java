package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;

public interface HisTermTypeValueServiceOutput {

	List<AbstractKeyValue> getTermTypes() throws ServiceFault_Exception;

}
