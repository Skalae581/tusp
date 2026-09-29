package de.tuhh.tusp.psv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.unihh.util.UnihhColumns;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

@Component
public class UpdateStudentFileWriterImpl implements UpdateStudentFileWriter {

	@Autowired
	private CsvWriter csvWriter;

	@Override
	public void logStudentData(HisStudent student, WritableResource outFile) throws UnsupportedCharsetException,
			PflichtfeldException, ParseException, NullPointerException, IOException {

		List<Map<UnihhColumns, String>> data = new ArrayList<>();
		data.add(studentToMap(student));

		csvWriter.write(outFile, data, Charset.forName("UTF-8"), UnihhColumns.class, ';', !outFile.exists(),
				QuoteMode.ALL);

	}

	private Map<UnihhColumns, String> studentToMap(HisStudent student)
			throws PflichtfeldException, ParseException, UnsupportedCharsetException {

		Map<UnihhColumns, String> data = new HashMap<>();

		data.put(UnihhColumns.Mtknr, student.getExterneMtknr().toString());
		data.put(UnihhColumns.Stg, student.getCourseOfStudyUniquename());
		data.put(UnihhColumns.Semester, student.getSemester());
		data.put(UnihhColumns.Firstname, student.getFirstname());
		data.put(UnihhColumns.Surename, student.getSurname());
		data.put(UnihhColumns.Status, student.getTodo());
		data.put(UnihhColumns.details, "TODO");

		return data;
	}

}
