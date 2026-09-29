package de.tuhh.tusp.stu.timsl.service;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface TimSonderStudentService {

	int delete(Long id);

	TimSonderStudent fetchById(Long id);

	List<TimSonderStudent> findAll();

	/**
	 * Sucht alle Sonderstudenten zusammen
	 *
	 * @param mc
	 * @param logger
	 * @return Liste der Studenten
	 * @throws ServiceFault
	 *             Wenn die Webservices abspacken
	 */
	List<Map<StudentColumns, String>> getStudentenDaten(MessageCollector mc, JobLogger logger) throws ServiceFault;

	Long insert(TimSonderStudent timStudent);

	void update(TimSonderStudent object);

}
