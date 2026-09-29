package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import de.his.ws.facilityservice.Building;
import de.his.ws.facilityservice.Room;
import de.his.ws.facilityservice.ServiceFault_Exception;

public interface HisFacilityServiceOutput {

	Long getBuildingIdForRoom(Long id) throws ServiceFault_Exception;

	List<Long> readAllBuildings() throws ServiceFault_Exception;

	Building readBuilding(Long buildingId) throws ServiceFault_Exception;

	Room searchRoom(Long id) throws ServiceFault_Exception;

}
