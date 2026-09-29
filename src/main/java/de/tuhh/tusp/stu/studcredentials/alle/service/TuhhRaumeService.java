package de.tuhh.tusp.stu.studcredentials.alle.service;

import de.his.ws.facilityservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;

public interface TuhhRaumeService {

	void getList(JobLogger joblogger) throws ServiceFault_Exception;

}
