package de.tuhh.tusp.stu.studcredentials.alle.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.facilityservice.SearchResult;
import de.his.ws.facilityservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisFacilityHelperDao;

@Component
public class TuhhRaumeDaoImpl implements TuhhRaumeDao {

	@Autowired
	private HisFacilityHelperDao hisFacilityHelperDao;

	@Override
	public Map<String, List<String>> getAllRoomsForAllBuilings() throws ServiceFault_Exception {

		Map<String, List<String>> list = new HashMap<>();

		List<Long> buldingsList = hisFacilityHelperDao.readAllBuildings();

		for (long b : buldingsList) {
			System.out.println("Buiding Id: " + b);
		}

		for (Long l : buldingsList) {

			String buildingName;
			try {
				buildingName = hisFacilityHelperDao.readBuilding(l).getDefaulttext();
				if (buildingName == null) {
					System.out.println("Gebäude ID: " + l + " kann nicht gelesen werden!");
				}
				List<String> roomList = new ArrayList<>();
				List<SearchResult> searchedRoomList = hisFacilityHelperDao.findAllRoomsForBuilding(l.toString())
						.getRoomAndTypeId();

				for (SearchResult result : searchedRoomList) {
					roomList.add(hisFacilityHelperDao.searchRoom(result.getRoomId()).getDefaulttext());
				}

				list.put(buildingName, roomList);

			} catch (Exception e) {
				continue;
			}

		}

		return list;

	}

}
