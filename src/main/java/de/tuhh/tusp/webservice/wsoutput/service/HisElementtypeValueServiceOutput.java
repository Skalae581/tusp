package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;

public interface HisElementtypeValueServiceOutput {

	List<KeyValue> getElementTypes() throws ServiceFault_Exception;

}
