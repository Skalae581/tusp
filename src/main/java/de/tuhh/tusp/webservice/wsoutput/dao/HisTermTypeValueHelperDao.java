package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;

public interface HisTermTypeValueHelperDao {

	List<AbstractKeyValue> getTermTypes() throws ServiceFault_Exception;

}
