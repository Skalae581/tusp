package de.tuhh.tusp.webservice.wsoutput.dao;

import java.math.BigInteger;
import java.util.List;

import de.his.ws.courseinterfaceservice.PersonPlanelementsType;
import de.his.ws.courseinterfaceservice.PlanelementsType;
import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.his.ws.courseinterfaceservice.UnitDto;

public interface HisCourseInterfaceHelperDao {

	List<Long> findUnit81(String termYear, String termTypeValueId, String date) throws ServiceFault_Exception;

	PersonPlanelementsType getPersonResponsibleForUnit(Long unitId, Long year, BigInteger termTypeId)
			throws ServiceFault_Exception;

	PlanelementsType readPlanelementForUnit(Long unitId, Long termTypeId, BigInteger termyear)
			throws ServiceFault_Exception;

	UnitDto readUnit81(Long unitId) throws ServiceFault_Exception;
}
