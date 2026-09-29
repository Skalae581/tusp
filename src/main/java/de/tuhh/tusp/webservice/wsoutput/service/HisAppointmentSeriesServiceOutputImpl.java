package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.appointmentseriesservice.AppointmentSeries;
import de.his.ws.appointmentseriesservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAppointmentSeriesHelperDao;

@Component
public class HisAppointmentSeriesServiceOutputImpl implements HisAppointmentSeriesServiceOutput {

	private static Logger logger = LogManager.getLogger(HisAppointmentSeriesServiceOutputImpl.class);

	@Autowired
	private HisAppointmentSeriesHelperDao hisAppointmentSeriesHelperDao;

	/**
	 * gibt alle plannedDatesIds zurück
	 *
	 * @param unitId
	 * @param termYear
	 * @param termTypeId
	 * @param planelementId wird leer sein ??
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<Long> findAppointmentSeriesIds(String unitId, String termYear, String termTypeId)
			throws ServiceFault_Exception {

		return hisAppointmentSeriesHelperDao.findAppointmentSeriesIds(unitId, termYear, termTypeId);
	}

	/**
	 * gibt AppointmentSeries Objekt zurück mit Raum
	 *
	 * @param plannedDatesId
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public AppointmentSeries readAppointmentSeries(Long plannedDatesId) throws ServiceFault_Exception {
		return hisAppointmentSeriesHelperDao.readAppointmentSeries(plannedDatesId);
	}
}
