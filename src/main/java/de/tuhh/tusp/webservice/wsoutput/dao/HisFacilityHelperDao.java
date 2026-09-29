package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.facilityservice.Building;
import de.his.ws.facilityservice.Room;
import de.his.ws.facilityservice.RoomsType;
import de.his.ws.facilityservice.ServiceFault_Exception;

public interface HisFacilityHelperDao {

	RoomsType findAllRoomsForBuilding(String id) throws ServiceFault_Exception;

	Long getBuildingIdForRoom(Long id) throws ServiceFault_Exception;

	List<Long> readAllBuildings() throws ServiceFault_Exception;

	Building readBuilding(Long buildingId) throws ServiceFault_Exception;

	Room searchRoom(Long id) throws ServiceFault_Exception;

}
