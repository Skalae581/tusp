package de.tuhh.tusp.stu.timsl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.sptim.service.StudentConverter;
import de.tuhh.tusp.stu.timsl.dao.TimSonderStudentDao;
import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.service.HisStudentServiceOutput;

@Component
public class TimSonderStudentServiceImpl implements TimSonderStudentService {

	private static Logger logger = LogManager.getLogger(TimSonderStudentServiceImpl.class);
	@Autowired
	private TimSonderStudentDao timSonderStudentDao;
	@Autowired
	private StudentConverter studentConverter;
	@Autowired
	private HisStudentServiceOutput hisStudentServiceOutput;

	@Override
	public int delete(Long id) {

		return timSonderStudentDao.delete(id);
	}

	@Override
	public TimSonderStudent fetchById(Long id) {

		return timSonderStudentDao.fetchById(id);
	}

	@Override
	public List<TimSonderStudent> findAll() {

		return timSonderStudentDao.findAll();
	}

	@Override
	public List<Map<StudentColumns, String>> getStudentenDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault {

		List<Map<StudentColumns, String>> studierende = new ArrayList<>();

		for (TimSonderStudent timSonderStudent : timSonderStudentDao.findValid()) {

			try {
				HisStudentWrapper student = hisStudentServiceOutput
						.fetchStudentByRegistrationNumber(timSonderStudent.getMtknr());

				logger.debug("Konvertiere " + timSonderStudent);
				Map<StudentColumns, String> convertedStudent = studentConverter.studentToMap(student);

				jobLogger.info("Ergänze Sonderstudenten: " + student);
				studierende.add(convertedStudent);
			} catch (Exception e) {
				logger.error(timSonderStudent, e);
				String msg = "Student mit MTKNR " + timSonderStudent.getMtknr() + " konnte nicht umgewandelt werden: "
						+ e.getMessage();
				jobLogger.error(msg);
				mc.addErrorMessage(msg);
			}
		}

		return studierende;
	}

	@Override
	public Long insert(TimSonderStudent timStudent) {

		return timSonderStudentDao.insert(timStudent);
	}

	@Override
	public void update(TimSonderStudent object) {

		timSonderStudentDao.update(object);
	}

}
