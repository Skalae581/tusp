package de.tuhh.tusp.psv.tmabs.service;

import static de.tuhh.tusp.psv.tmabs.dao.TmabsColumns.*;
import static de.tuhh.tusp.webservice.utils.NullBehavior.*;

import java.nio.charset.UnsupportedCharsetException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.tmabs.dao.TmabsColumns;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.sync.pojo.Gender;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisStudentWrapper;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;

@Component
public class TmabsConverterImpl implements TmabsConverter {

	private static final NotificationCategory[] ncs =
			new NotificationCategory[] { NotificationCategory.STU, NotificationCategory.STUALL };

	private static final AddressTag[] ats = new AddressTag[] { AddressTag.HOME, AddressTag.PRIVAT, AddressTag.RZ };

	@Autowired
	private GenderService genderService;

	@Override
	public Map<TmabsColumns, String> studentToMap(HisStudentWrapper student)	throws PflichtfeldException,
																				ParseException,
																				UnsupportedCharsetException {

		Map<TmabsColumns, String> data = new HashMap<>();

		Gender gender = genderService.fetchByString(student.getGender());
		switch (gender.getUniquename()) {
			case "W":
				data.put(Anrede, "Frau");
				break;
			case "M":
				data.put(Anrede, "Herr");
				break;

			default:
				data.put(Anrede, "");
				break;
		}

		data.put(Titel, student.getTitel());
		data.put(Semester, CalendarUtils.getSemester(new GregorianCalendar()) + "");
		data.put(Status, student.getStatus());
		data.put(Nachname, student.getSurname(THROW_EXCEPTION));
		data.put(Vorname, student.getGivenname(THROW_EXCEPTION));
		data.put(Geschl, gender.getUniquename());
		data.put(Staat, student.getNationality(RETURN_EMPTY).getUniquename());
		data.put(Gebdat, CalendarUtils.toString(student.getDateOfBirth(THROW_EXCEPTION), "d.M.y 00:00:00"));
		data.put(Gebname, student.getGeburtsName());
		data.put(Gebort, student.getGeburtsOrt());
		data.put(Gebland, student.getCountry());

		HisPostAddress addr = student.getPostAddres(RETURN_EMPTY, ncs, ats);

		data.put(Pozusatz, addr.getAddressaddition());
		data.put(Postrasse, addr.getStreet());
		data.put(Poplz, addr.getPostcode());
		data.put(Poort, addr.getCity());
		data.put(Pokfz, addr.getCountry());

		data.put(Potel, student.getTelephoneNumber(RETURN_EMPTY, EAdressType.PHONE, ats, ncs));
		data.put(Email, student.getEmail(RETURN_EMPTY, AddressTag.RZ, ncs));
		data.put(Mobiltelefon, student.getTelephoneNumber(RETURN_EMPTY, EAdressType.MOBILE, ats, ncs));

		data.put(Stg, student.getStgName(THROW_EXCEPTION, 1));
		data.put(Stgsem, "" + student.getStgSemester(THROW_EXCEPTION, 1));

		data.put(Mtknr, "TUHH_" + student.getRegistrationNumber(THROW_EXCEPTION));

		String uhhNr = student.getUhhRegistrationNumber(RETURN_NULL);
		data.put(Mtknr_uhh, uhhNr == null ? "" : "UHH_" + uhhNr);

		return data;
	}

}
