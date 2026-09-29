package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.facilityservice.Building;
import de.his.ws.facilityservice.Room;
import de.his.ws.facilityservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisFacilityHelperDao;

@Component
public class HisFacilityServiceOutputImpl implements HisFacilityServiceOutput {

	@Autowired
	private HisFacilityHelperDao hisFacilityHelperDao;

	/**
	 * Gebeude id
	 *
	 * @param id
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Long getBuildingIdForRoom(Long id) throws ServiceFault_Exception {
		return hisFacilityHelperDao.getBuildingIdForRoom(id);
	}

	/**
	 * gibt alle Gebaeuden zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<Long> readAllBuildings() throws ServiceFault_Exception {
		return hisFacilityHelperDao.readAllBuildings();
	}

	/**
	 * Lese eine Gebeude
	 *
	 * @param buildingId
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Building readBuilding(Long buildingId) throws ServiceFault_Exception {
		return hisFacilityHelperDao.readBuilding(buildingId);
	}

	/**
	 * Lese ein Raum
	 *
	 * @param id
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Room searchRoom(Long id) throws ServiceFault_Exception {
		return hisFacilityHelperDao.searchRoom(id);
	}

}
