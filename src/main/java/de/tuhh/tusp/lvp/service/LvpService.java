package de.tuhh.tusp.lvp.service;

import java.text.ParseException;
import java.util.Date;

import com.jcraft.jsch.JSchException;

import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.MessageCollector;

public interface LvpService {

	void exportLvp(MessageCollector mc, Date date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.termservice.ServiceFault_Exception, ParseException, JSchException;

}
