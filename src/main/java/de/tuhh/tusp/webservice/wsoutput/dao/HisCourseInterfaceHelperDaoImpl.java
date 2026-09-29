package de.tuhh.tusp.webservice.wsoutput.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.courseinterfaceservice.CourseInterfaceService;
import de.his.ws.courseinterfaceservice.CourseInterfaceServicePortType;
import de.his.ws.courseinterfaceservice.PersonPlanelementsType;
import de.his.ws.courseinterfaceservice.PlanelementsType;
import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.his.ws.courseinterfaceservice.UnitDto;
import de.his.ws.courseinterfaceservice.UnitIdsType;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisCourseInterfaceHelperDaoImpl implements HisCourseInterfaceHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;

	private CourseInterfaceServicePortType courseInterfaceService;

	/**
	 * gibt alle Unit Ids zurück
	 */
	@Override
	public List<Long> findUnit81(String termYear, String termTypeValueId, String date) throws ServiceFault_Exception {

		UnitIdsType type = getPort().findUnit81("", "", "", "", "", "", "", "", termYear, termTypeValueId, "", date, "",
				"", "", "", "", "", "", "", "");

		List<Long> units = type.getUnitId();

		return units;
	}

	/**
	 * gibt zurück die Personen für diese Unit
	 */
	@Override
	public PersonPlanelementsType getPersonResponsibleForUnit(Long unitId, Long year, BigInteger termTypeId)
			throws ServiceFault_Exception {
		return getPort().getPersonResponsibleForUnit(unitId, year, termTypeId);
	}

	/**
	 * ServicePort Initialisierung
	 *
	 * @return der Port
	 */
	private CourseInterfaceServicePortType getPort() {

		if (courseInterfaceService == null) {
			courseInterfaceService = serviceFactory.getWebservicePort(CourseInterfaceService.class);
		}

		return courseInterfaceService;
	}

	/**
	 * Lese Plan-Elemente Für eine Veranstaltung
	 *
	 * @param unitId
	 * @param termTypeId
	 * @param termyear
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public PlanelementsType readPlanelementForUnit(Long unitId, Long termTypeId, BigInteger termyear)
			throws ServiceFault_Exception {
		return getPort().readPlanelementsForUnit(unitId, termTypeId, termyear);
	}

	/**
	 * liest ein Unit -Daten
	 */
	@Override
	public UnitDto readUnit81(Long unitId) throws ServiceFault_Exception {
		return getPort().readUnit81(unitId);
	}

}
