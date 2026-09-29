package de.tuhh.tusp.stu.studcredentials.alle.dao;

import java.util.List;
import java.util.Map;

import de.his.ws.facilityservice.ServiceFault_Exception;

public interface TuhhRaumeDao {

	/**
	 * Gibt eine Liste raus von allen Gebäuden und Räume
	 * 
	 * @return
	 * @throws ServiceFault_Exception
	 */
	Map<String, List<String>> getAllRoomsForAllBuilings() throws ServiceFault_Exception;

}
