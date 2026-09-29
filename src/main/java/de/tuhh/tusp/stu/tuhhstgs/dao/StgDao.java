package de.tuhh.tusp.stu.tuhhstgs.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.WritableResource;

import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;

public interface StgDao {

	/**
	 * hole all Studiengänge die TUHH gehören
	 *
	 * @param jobLogger
	 * @throws ServiceFault_Exception
	 */
	// void getAllStgs(JobLogger jobLogger) throws ServiceFault_Exception;

	List<Map<StgColumns, String>> getCourseOfStudiesData(JobLogger jobLogger) throws ServiceFault_Exception;

	/**
	 * schreibe Ergebnisse in die cvs Datei
	 *
	 * @param stgs
	 * @param resource
	 * @param jobLogger
	 * @throws IOException
	 * @throws NullPointerException
	 */
	void writeStgs(List<Map<StgColumns, String>> stgs, WritableResource resource, JobLogger jobLogger)
			throws IOException, NullPointerException;

}
