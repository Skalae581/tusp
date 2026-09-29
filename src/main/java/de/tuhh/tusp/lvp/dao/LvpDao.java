package de.tuhh.tusp.lvp.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.WritableResource;

import de.his.ws.termservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface LvpDao {

	List<Map<LvpColumns, String>> getLvpDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault, ParseException, ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.courseinterfaceservice.ServiceFault_Exception;

	void writeLvp(List<Map<LvpColumns, String>> lvp, WritableResource resource)
			throws IOException, NullPointerException;

}
