package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.appointmentseriesservice.AppointmentSeries;
import de.his.ws.appointmentseriesservice.AppointmentSeriesService;
import de.his.ws.appointmentseriesservice.AppointmentSeriesServicePortType;
import de.his.ws.appointmentseriesservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisAppointmentSeriesHelperDaoImpl implements HisAppointmentSeriesHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;

	private AppointmentSeriesServicePortType appointmentSeriesService;

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

		return getPort().findAppointmentSeriesIds(unitId, termYear, termTypeId, "", "", "", "", "").getPlannedDatesId();
	}

	/**
	 * ServicePort Initialisierung
	 *
	 * @return der Port
	 */
	private AppointmentSeriesServicePortType getPort() {

		if (appointmentSeriesService == null) {
			appointmentSeriesService = serviceFactory.getWebservicePort(AppointmentSeriesService.class);
		}

		return appointmentSeriesService;
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
		return getPort().readAppointmentSeries(plannedDatesId);
	}

}
