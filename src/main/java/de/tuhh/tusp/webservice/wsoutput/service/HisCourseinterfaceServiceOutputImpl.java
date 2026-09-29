package de.tuhh.tusp.webservice.wsoutput.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.courseinterfaceservice.PersonPlanelementsType;
import de.his.ws.courseinterfaceservice.PlanelementsType;
import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.his.ws.courseinterfaceservice.UnitDto;
import de.tuhh.tusp.webservice.wsoutput.dao.HisCourseInterfaceHelperDao;

@Component
public class HisCourseinterfaceServiceOutputImpl implements HisCourseinterfaceServiceOutput {

	@Autowired
	private HisCourseInterfaceHelperDao hisCourseInterfaceHelperDao;

	/**
	 * gibt alle Unit Ids zurück
	 */
	@Override
	public List<Long> findUnit81(String termYear, String termTypeValueId, String date) throws ServiceFault_Exception {
		return hisCourseInterfaceHelperDao.findUnit81(termYear, termTypeValueId, date);
	}

	/**
	 * gibt zurück die Personen für diese Unit
	 */
	@Override
	public PersonPlanelementsType getPersonResponsibleForUnit(Long unitId, Long year, BigInteger termTypeId)
			throws ServiceFault_Exception {
		return hisCourseInterfaceHelperDao.getPersonResponsibleForUnit(unitId, year, termTypeId);
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

		return hisCourseInterfaceHelperDao.readPlanelementForUnit(unitId, termTypeId, termyear);
	}

	/**
	 * liest ein Unit -Daten
	 */
	@Override
	public UnitDto readUnit81(Long unitId) throws ServiceFault_Exception {
		return hisCourseInterfaceHelperDao.readUnit81(unitId);
	}
}
