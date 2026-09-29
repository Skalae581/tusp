package de.tuhh.tusp.stu.sptim.service;

import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Abschl;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Anmerkung;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.GebDat;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Geschlecht;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Land;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.MTKNR;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Nachname;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.PersonId;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Plz;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.PoVersion;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Semester;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Sortierungsname;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Staatsang;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Stadt;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Stg;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Strasse;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Tel;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Vertiefung;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Vorname;
import static de.tuhh.tusp.stu.sptim.dao.StudentColumns.Zusatz;
import static de.tuhh.tusp.utils.CalendarUtils.DATEFORMAT_GERMAN;
import static de.tuhh.tusp.webservice.utils.NullBehavior.RETURN_EMPTY;
import static de.tuhh.tusp.webservice.utils.NullBehavior.THROW_EXCEPTION;

import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.sptim.utils.TimStringUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;

@Component
public class StudentConverterImpl implements StudentConverter {

	private static Logger logger = LogManager.getLogger(StudentConverterImpl.class);

	@Autowired
	private GenderService genderService;
	@Autowired
	private TimStringUtils sani;
	@Autowired
	private CountryDao countryDao;

	@Override
	public Map<StudentColumns, String> studentToMap(HisStudentWrapper student)
			throws PflichtfeldException, ParseException, UnsupportedCharsetException {

		Map<StudentColumns, String> data = new HashMap<>();

		data.put(MTKNR, sani.sanitize(student.getRegistrationNumber(THROW_EXCEPTION)));
		data.put(Semester, "" + student.getStgSemester(THROW_EXCEPTION, 1));
		data.put(Nachname, sani.sanitize(student.getSurname(THROW_EXCEPTION)));
		data.put(Vorname, sani.sanitize(student.getGivenname(THROW_EXCEPTION)));
		data.put(GebDat, CalendarUtils.toString(student.getDateOfBirth(THROW_EXCEPTION), DATEFORMAT_GERMAN));
		data.put(Sortierungsname, "");
		data.put(Tel, "");

		HisPostAddress addr = student.getPostAddres(RETURN_EMPTY,
				new NotificationCategory[] { NotificationCategory.STU, NotificationCategory.STUALL },
				new AddressTag[] { AddressTag.HOME, AddressTag.PRIVAT, AddressTag.RZ });

		Country country = countryDao.fetchByUniquename(addr.getCountry());
		if (country == null) {
			data.put(Land, "");
			logger.warn("Studenten Adresse hat kein Country. MTKRN: " + student.getRegistrationNumber(THROW_EXCEPTION));
		} else {
			data.put(Land, sani.sanitize(country.getIso3166_1_alpha()));
		}
		data.put(Plz, sani.sanitize(addr.getPostcode()));
		data.put(Stadt, sani.sanitize(addr.getCity()));
		data.put(Strasse, sani.sanitize(addr.getStreet()));
		data.put(Zusatz, sani.sanitize(addr.getAddressaddition()));
		data.put(Geschlecht, sani.sanitize(genderService.fetchByString(student.getGender()).getUniquename()));
		data.put(Abschl, sani.sanitize(student.getDegree(THROW_EXCEPTION, 1)));
		String stg = sani.sanitize(student.getStgName(THROW_EXCEPTION, 1));
		if ("IWA".equals(stg) || "IWB".equals(stg)) {
			stg = "IWI";
		}
		data.put(Stg, stg);
		data.put(Vertiefung, sani.sanitize(student.getVertiefung(RETURN_EMPTY, 1)));
		data.put(PoVersion, sani.sanitize(student.getPoVersion(THROW_EXCEPTION, 1)));
		data.put(Staatsang, sani.sanitize(student.getNationality(RETURN_EMPTY).getIso3166_1_alpha()));
		data.put(Anmerkung, sani.sanitize(student.getAnmerkung(RETURN_EMPTY)));
		data.put(PersonId, sani.sanitize(student.getPersonId() + ""));

//		if (student.getExmaDatum() != null && !student.getExmaDatum().isEmpty()) {
//
//			data.put(StudentColumns.ExmaDat,
//					CalendarUtils.formatStringDate("yyyy-mm-dd", "dd.mm.yyyy", student.getExmaDatum()));
//		} else {
//			data.put(StudentColumns.ExmaDat, "");
//		}

		return data;
	}

}
