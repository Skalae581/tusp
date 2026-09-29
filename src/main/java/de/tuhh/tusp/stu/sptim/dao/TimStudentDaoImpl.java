package de.tuhh.tusp.stu.sptim.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.pojo.TimPerson;
import de.tuhh.tusp.stu.sptim.service.StudentConverter;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.CsvReader;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.NullBehavior;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.service.HisStudentServiceOutput;

@Component
public class TimStudentDaoImpl implements TimStudentDao {

	private static Logger logger = LogManager.getLogger(TimStudentDaoImpl.class);
	@Autowired
	private HisStudentServiceOutput hisStudentServiceOutput;
	@Autowired
	private CsvWriter csvWriter;
	@Autowired
	private CsvReader csvReader;

	@Autowired
	private StudentConverter studentConverter;

	@Override
	public List<Map<StudentColumns, String>> getStudentenDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault {

		List<Map<StudentColumns, String>> studierende = new ArrayList<>();

		// List<HisStudentWrapper> studentsValid = new ArrayList<>();
		List<HisStudentWrapper> studentsValid = hisStudentServiceOutput.findValidStudents(mc, jobLogger);
		List<HisStudentWrapper> studentsExma = new ArrayList<>(); // noch nicht bereit
		// List<HisStudentWrapper> studentsExma =
		// hisStudentServiceOutput.findExmaStudents(mc, jobLogger);

		List<HisStudentWrapper> students = Stream.of(studentsValid, studentsExma).flatMap(x -> x.stream())
				.collect(Collectors.toList());

		for (HisStudentWrapper student : students) {

			try {
				String degree1 = student.getDegree(NullBehavior.RETURN_EMPTY, 1);
				String degree2 = student.getDegree(NullBehavior.RETURN_EMPTY, 2);
				if (("MP".equals(degree1) || "".equals(degree1)) && ("MP".equals(degree2) || "".equals(degree2))) {
					jobLogger.debug(student + " wird wegen Abschluss MP ignoriert");
					continue;
				}

				logger.debug("Konvertiere " + student);
				Map<StudentColumns, String> convertedStudent = studentConverter.studentToMap(student);

				if (studierende.contains(convertedStudent)) {
					jobLogger.debug(student + " weil er bereits in der Lieferung ist");
					continue;
				}

				studierende.add(convertedStudent);
			} catch (Exception e) {
				jobLogger.error(student.toString(), e);
				mc.addErrorMessage(
						"Student " + student.getPersonId() + " konnte nicht umgewandelt werden: " + e.getMessage());
			}
		}

		return studierende;
	}

	@Override
	public List<TimPerson> readRuecklaeufer(Resource ruecklaeuferFile)
			throws de.tuhh.tusp.utils.csv.ParseException, IOException {

		List<Map<Enum<?>, String>> data = csvReader.readRecords(ruecklaeuferFile, RuecklaeuferColumns.class, ':',
				Charset.forName("UTF-8"), false);

		List<TimPerson> personList = new ArrayList<>();
		for (Map<Enum<?>, String> map : data) {
			TimPerson person = new TimPerson();

			person.setStatus(map.get(RuecklaeuferColumns.Status));
			person.setMtknr(map.get(RuecklaeuferColumns.MTKNR));
			person.setMailAddr(map.get(RuecklaeuferColumns.Mail));
			person.setUname(map.get(RuecklaeuferColumns.Username));
			person.setPassword(map.get(RuecklaeuferColumns.Kennwort));
			person.setPersonId(map.get(RuecklaeuferColumns.PersonId));
			personList.add(person);
		}

		return personList;
	}

	@Override
	public void writeBewerber(List<Map<StudentColumns, String>> bewerber, WritableResource file)
			throws IOException, NullPointerException {

		csvWriter.write(file, bewerber, Charset.forName("UTF-8"), StudentColumns.class, ';', true, QuoteMode.ALL);

	}

	@Override
	public void writeStudenten(List<Map<StudentColumns, String>> studenten, WritableResource file)
			throws IOException, NullPointerException {

		csvWriter.write(file, studenten, Charset.forName("UTF-8"), StudentColumns.class, ';', false, QuoteMode.ALL);
	}

}
