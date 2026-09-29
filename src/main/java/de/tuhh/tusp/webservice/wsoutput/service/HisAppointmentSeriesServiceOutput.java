package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import de.his.ws.appointmentseriesservice.AppointmentSeries;
import de.his.ws.appointmentseriesservice.ServiceFault_Exception;

public interface HisAppointmentSeriesServiceOutput {

	List<Long> findAppointmentSeriesIds(String unitId, String termYear, String termTypeId)
			throws ServiceFault_Exception;

	AppointmentSeries readAppointmentSeries(Long plannedDatesId) throws ServiceFault_Exception;

}
