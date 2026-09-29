package de.tuhh.tusp.stu.studcredentials.alle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.facilityservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.alle.dao.TuhhRaumeDao;

@Component
public class TuhhRaumeServiceImpl implements TuhhRaumeService {

	@Autowired
	private TuhhRaumeDao tuhhRaumeDao;

	@Override
	public void getList(JobLogger joblogger) throws ServiceFault_Exception {
		Map<String, List<String>> list = tuhhRaumeDao.getAllRoomsForAllBuilings();

		for (Map.Entry<String, List<String>> entry : list.entrySet()) {
			String key = entry.getKey();
			List<String> value = entry.getValue();

			System.out.println("-------------Gebäude: " + key + "   ----------------");

			for (String roomName : value) {
				System.out.println(roomName);
			}
		}
	}
}
