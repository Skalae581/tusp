package de.tuhh.tusp.td.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.WritableResource;

import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface TdDao {

	/**
	 *
	 *
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 * @throws de.his.ws.facilityservice.ServiceFault_Exception
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 * @throws ParseException
	 */
	List<Map<TdColumns, String>> getTdDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault, ServiceFault_Exception, de.his.ws.termservice.ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, ParseException;

	void writeTd(List<Map<TdColumns, String>> termine, WritableResource resource)
			throws IOException, NullPointerException;

}
