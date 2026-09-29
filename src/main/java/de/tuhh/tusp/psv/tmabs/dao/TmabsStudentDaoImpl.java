package de.tuhh.tusp.psv.tmabs.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.tmabs.service.TmabsConverter;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.service.HisStudentServiceOutput;

@Component
public class TmabsStudentDaoImpl implements TmabsStudentDao {

	private static Logger logger = LogManager.getLogger(TmabsStudentDaoImpl.class);
	@Autowired
	private HisStudentServiceOutput hisStudentServiceOutput;
	@Autowired
	private CsvWriter csvWriter;

	@Autowired
	private TmabsConverter cobverter;

	@Override
	public List<Map<TmabsColumns, String>> getStudentenDaten(MessageCollector mc) throws ServiceFault {

		List<HisStudentWrapper> students = hisStudentServiceOutput.findValidStudents(mc, "TMA", "BS");
		List<Map<TmabsColumns, String>> result = new ArrayList<>();

		for (HisStudentWrapper student : students) {

			boolean stg1isTMA = student.getCourseOfStudy(1l) != null
					&& student.getCourseOfStudy(1l).getUniquename() != null
					&& student.getCourseOfStudy(1l).getUniquename().startsWith("BS|TMA|");
			boolean stg2isTMA = student.getCourseOfStudy(2l) != null
					&& student.getCourseOfStudy(2l).getUniquename() != null
					&& student.getCourseOfStudy(2l).getUniquename().startsWith("BS|TMA|");

			// FIXME nur cos in aktueller und nächster Period beachten?

			if (!stg1isTMA && !stg2isTMA) {
				continue;
			}

			try {
				result.add(cobverter.studentToMap(student));
			} catch (UnsupportedCharsetException | PflichtfeldException | ParseException e) {
				String msg = "Student " + student + " konnte nicht konvertiert werden.";
				logger.error(msg, e);
				mc.addErrorMessage(msg);
			}
		}

		return result;
	}

	@Override
	public void writeStudenten(List<Map<TmabsColumns, String>> studenten, WritableResource resource)
			throws IOException, NullPointerException {

		csvWriter.write(resource, studenten, Charset.forName("Cp1252"), TmabsColumns.class, ';', true, QuoteMode.ALL);

	}

}
