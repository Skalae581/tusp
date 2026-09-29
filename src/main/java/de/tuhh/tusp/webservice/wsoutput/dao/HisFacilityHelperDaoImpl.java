package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.facilityservice.Building;
import de.his.ws.facilityservice.FacilityService;
import de.his.ws.facilityservice.FacilityServicePortType;
import de.his.ws.facilityservice.Room;
import de.his.ws.facilityservice.RoomsType;
import de.his.ws.facilityservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisFacilityHelperDaoImpl implements HisFacilityHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;

	private FacilityServicePortType facilityService;

	/**
	 * lese alle Räume für die Gebäude
	 */
	@Override
	public RoomsType findAllRoomsForBuilding(String id) throws ServiceFault_Exception {
		return getPort().search("", "", "", "", "", "", "", "", "", "", "", "", "", id, "", "", "", "");
	}

	/**
	 * Gebeude id
	 *
	 * @param id
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Long getBuildingIdForRoom(Long id) throws ServiceFault_Exception {
		return getPort().readRoomLarge201706(id).getBuildingId();
	}

	/**
	 * ServicePort Initialisierung
	 *
	 * @return der Port
	 */
	private FacilityServicePortType getPort() {

		if (facilityService == null) {
			facilityService = serviceFactory.getWebservicePort(FacilityService.class);
		}

		return facilityService;
	}

	/**
	 * gibt alle Gebaeuden zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<Long> readAllBuildings() throws ServiceFault_Exception {
		return getPort().readAllBuildings().getId();
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
		return getPort().readBuilding(buildingId);
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
		return getPort().readRoomLarge201706(id);
	}

}
