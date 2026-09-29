package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.ServiceFault_Exception;

public interface HisOrgunitHelperDao {

	/**
	 * Gibt die Liste von Orgunits zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	List<KeyValue> getOrgunit() throws ServiceFault_Exception;

}
