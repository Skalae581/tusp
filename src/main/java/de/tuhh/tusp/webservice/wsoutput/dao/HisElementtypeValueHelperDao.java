package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;

public interface HisElementtypeValueHelperDao {

	List<KeyValue> getElementTypes() throws ServiceFault_Exception;

}
