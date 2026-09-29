package de.tuhh.tusp.lvp.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.WritableResource;

import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface TermineDao {

	/**
	 * get alle Termin Daten
	 *
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 * @throws de.his.ws.facilityservice.ServiceFault_Exception
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 * @throws ParseException
	 */
	List<Map<TermineColumns, String>> getTermineDaten(JobLogger jobLogger)
			throws ServiceFault, ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.termservice.ServiceFault_Exception, ParseException;

	void writeTermine(List<Map<TermineColumns, String>> termine, WritableResource resource)
			throws IOException, NullPointerException;
}
