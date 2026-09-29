package de.tuhh.tusp.webservice.wsoutput.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.HisCourseOfStudyHelperDao;

@Component
public class HisCourseOfStudyServiceOutputImpl implements HisCourseOfStudyServiceOutput {

	private static Logger logger = LogManager.getLogger(HisCourseOfStudyServiceOutputImpl.class);

	@Autowired
	private HisCourseOfStudyHelperDao dao;

	@Override
	public String findUniquenameByUniHHAstat(String astatUnihh) throws ServiceFault, NumberFormatException {

		String result = dao.findUniquenameByUniHHAstat(astatUnihh);

		logger.info("Studiengang " + result + " gefunden für AstatID" + astatUnihh);

		return result;
	}

	@Override
	public String findUniquenameForMobilityOnline() throws ServiceFault, IllegalStateException, NumberFormatException {

		String result = dao.findUniquenameForMobilityOnline();

		logger.info("Studiengang " + result + " gefunden für MobilityOnline");

		return result;
	}

}
