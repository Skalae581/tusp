package de.tuhh.tusp.td.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.appointmentseriesservice.AppointmentSeries;
import de.his.ws.courseinterfaceservice.PersonPlanelementsType;
import de.his.ws.courseinterfaceservice.PlanelementsType;
import de.his.ws.facilityservice.Room;
import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.termservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.td.pojo.Td;
import de.tuhh.tusp.td.utils.TdConverter;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.service.HisAppointmentSeriesServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisCourseinterfaceServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisElementtypeValueServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisFacilityServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisPersonServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermTypeValueServiceOutput;

@Component
public class TdDaoImpl implements TdDao {

	private static Logger logger = LogManager.getLogger(TdDaoImpl.class);

	@Autowired
	private HisElementtypeValueServiceOutput hisElementtypeValueServiceOutput;

	@Autowired
	private HisPersonServiceOutput hisPersonServiceOutput;
	@Autowired
	private HisAppointmentSeriesServiceOutput hisAppointmentSeriesServiceOutput;
	@Autowired
	private HisCourseinterfaceServiceOutput hisCourseinterfaceServiceOutput;
	@Autowired
	private HisTermServiceOutput hisTermServiceOutput;
	@Autowired
	private HisTermTypeValueServiceOutput hisTermTypeValueServiceOutput;
	@Autowired
	private HisFacilityServiceOutput hisFacilityserviceOutput;
	@Autowired
	private CsvWriter csvWriter;

	@Autowired
	private TdConverter tdConverter;

	/**
	 * Aktuelles Semester ID
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 */
	@SuppressWarnings("unused")
	private Long getCurrentSemester() throws ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception {

		Long id = null;
		String currentSem = hisTermServiceOutput.getCurrentTerm().getTermType().getDefaulttext();
		List<AbstractKeyValue> keyValue = hisTermTypeValueServiceOutput.getTermTypes();

		for (AbstractKeyValue key : keyValue) {
			if (currentSem.equals(key.getDefaulttext())) {
				id = key.getId();
				break;
			}
		}
		return id;
	}

	private List<Td> getData(String currentYear, String currentSemeserId, String tomorow, JobLogger jobLogger)
			throws de.his.ws.courseinterfaceservice.ServiceFault_Exception {

		List<Td> tdList = new ArrayList<>();

		// get data
		List<Long> unitList = hisCourseinterfaceServiceOutput.findUnit81(currentYear, currentSemeserId, tomorow);

		logger.debug("Found " + unitList.size() + "  Veranstaltungen für das Datum: " + tomorow);
		jobLogger.debug("Found " + unitList.size() + "  Veranstaltungen für das Datum: " + tomorow);

		if (unitList.size() == 0) {
			logger.debug("Keine Termine/Veranstaltungen für das Datum : " + tomorow);
			jobLogger.debug("Keine Termine/Veranstaltungen für das Datum : " + tomorow);

			return tdList;
		}

		for (Long unit : unitList) { // unit id
			try {

				Td td = new Td();

				// Name der Veranstaltung
				String veranstaltungName = readPlanElementName(unit, Long.parseLong(currentSemeserId),
						new BigInteger(currentYear));
				// Geplanntees Datum
				List<Long> plannedDates = getPlannedDatesIds(unit.toString(), currentYear, currentSemeserId);
				// Docent Name
				String personResponsible = getPersonForUnit(unit, Long.parseLong(currentSemeserId),
						new BigInteger(currentYear));

				String startDate = "";
				String endTime = "";
				String startTime = "";
				String roomName = "";
				String day = "";
				String month = "";

				LocalDate date = LocalDate.parse(tomorow);

				DayOfWeek dayOfWeek = date.getDayOfWeek();
				Month m = date.getMonth();

				day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN);
				month = m.getDisplayName(TextStyle.FULL, Locale.GERMAN);

				if (!plannedDates.isEmpty()) {

					Long plannedDate = plannedDates.get(0);
					if (null != plannedDate) {

						Long roomId = getRoomId(plannedDate);

						// Nur Räume die einen Raum haben
						if (null == roomId) {
							continue;
						}

						startDate = tomorow;
						endTime = null == getEndTime(plannedDate) ? "" : getEndTime(plannedDate);
						startTime = null == getStartTime(plannedDate) ? "" : getStartTime(plannedDate);

						Room room = hisFacilityserviceOutput.searchRoom(roomId);
						String currentRoomName = room.getDefaulttext();
						// change room nameroomName = changeRoomName(currentRoomName);
						// roomName = changeRoomName(currentRoomName);
						roomName = currentRoomName;

					}
				}

				td.setLv_nr_s(unit);
				td.setTitel(veranstaltungName);
				td.setNutzer(personResponsible);
				td.setDbeg(startDate);
				td.setBeginn(startTime);
				td.setEnde(endTime);
				td.setDay(day);
				td.setMonth(month);
				td.setRaum_s(roomName);

				tdList.add(td);

				logger.debug("td.csv--->" + unit + "," + roomName + "," + startDate + "," + veranstaltungName + ","
						+ personResponsible + "," + startTime + "," + endTime + "," + day + "," + month);
				jobLogger.debug("td.csv--->" + unit + "," + roomName + "," + startDate + "," + veranstaltungName + ","
						+ personResponsible + "," + startTime + "," + endTime + "," + day + "," + month);

			} catch (Exception e) {
				logger.debug("Fehler: " + e.getMessage());
				jobLogger.debug("Fehler: " + e.getMessage());

				continue;
			}

		}

		return tdList;
	}

	/**
	 * gibt elemttype name zurück
	 *
	 * @param unitId
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 */
	@SuppressWarnings("unused")
	private String getElementtypeName(Long unitId) throws de.his.ws.courseinterfaceservice.ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception {
		Long elemntTypeId = hisCourseinterfaceServiceOutput.readUnit81(unitId).getElementtypeId();

		List<KeyValue> elementTypes = hisElementtypeValueServiceOutput.getElementTypes();
		String elementTYpeName = "";
		for (KeyValue keyValue : elementTypes) {
			if (keyValue.getId() == elemntTypeId) {
				elementTYpeName = keyValue.getUniquename();
				break;
			}
		}

		return elementTYpeName;
	}

	/**
	 * End Time
	 *
	 * @param plannedDateId
	 * @return
	 *
	 */
	private String getEndTime(Long plannedDateId) {
		AppointmentSeries appointmentSeries = null;
		try {
			appointmentSeries = hisAppointmentSeriesServiceOutput.readAppointmentSeries(plannedDateId);

		} catch (Exception e) {
			logger.error("Zeit Eintrag Fehlt " + e.getMessage());
			throw new RuntimeException(e);
		}
		return appointmentSeries.getTime().getEnd().toString();
	}

	/**
	 * liefert vorname + nachnaem einer Person
	 *
	 * @param unitId
	 * @param currentSemesterId
	 * @param currentYear
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 */
	private String getPersonForUnit(Long unitId, Long currentSemesterId, BigInteger currentYear)
			throws de.his.ws.courseinterfaceservice.ServiceFault_Exception {

		String personDisplayName = "";
		Long personId = null;

		PersonPlanelementsType personPlanElement = hisCourseinterfaceServiceOutput.getPersonResponsibleForUnit(unitId,
				currentSemesterId, currentYear);

		if (personPlanElement != null) {
			if (!personPlanElement.getPersonPlanelement().isEmpty()) {

				personId = personPlanElement.getPersonPlanelement().get(0).getAbstractPersonId();

				PersonExisting person = null;

				if (personId != null) {
					person = hisPersonServiceOutput.fetchPerson(personId);

					if (null != person) {
						personDisplayName = person.getFirstname() + "  " + person.getSurname();
					}
				}
			}
		}

		return personDisplayName;
	}

	/**
	 * get all planned dates
	 *
	 * @param unitId
	 * @param termYear
	 * @param termTypeId
	 * @return
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 */
	private List<Long> getPlannedDatesIds(String unitId, String termYear, String termTypeId)
			throws de.his.ws.appointmentseriesservice.ServiceFault_Exception {

		return hisAppointmentSeriesServiceOutput.findAppointmentSeriesIds(unitId, termYear, termTypeId);
	}

	/**
	 * get room id
	 *
	 * @param plannedDateId
	 * @return
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 */
	private Long getRoomId(Long plannedDateId) throws de.his.ws.appointmentseriesservice.ServiceFault_Exception {
		AppointmentSeries appointmentSeries = hisAppointmentSeriesServiceOutput.readAppointmentSeries(plannedDateId);
		return appointmentSeries.getDefaultRoomId();
	}

	/**
	 * Start Time
	 *
	 * @param plannedDateId
	 * @return
	 *
	 */
	private String getStartTime(Long plannedDateId) {
		AppointmentSeries appointmentSeries = null;
		try {

			appointmentSeries = hisAppointmentSeriesServiceOutput.readAppointmentSeries(plannedDateId);
		} catch (Exception e) {
			logger.error("Zeit Eintrag Fehlt " + e.getMessage());
			throw new RuntimeException(e);
		}
		return appointmentSeries.getTime().getStart().toString();
	}

	/**
	 * Td - Veranstaltungen
	 *
	 * @param date
	 * @return
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 * @throws de.his.ws.facilityservice.ServiceFault_Exception
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 * @throws ParseException
	 */

	private List<Td> getTdDataForNext14Days(LocalDate date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.courseinterfaceservice.ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, ParseException {

		List<Td> tdList = new ArrayList<>();
		List<Td> tempList = new ArrayList<>();

		String currentDate = date.toString();
		// String currentDate = "2021-09-16"; // zum testen
		String tomorow = CalendarUtils.getNextDate(currentDate, CalendarUtils.DATEFORMAT_ENGLISH);

		int y = Calendar.getInstance().get(Calendar.YEAR);
		String currentYear = String.valueOf(y); // hier das einschalten im PRod
		// betrieb
		// String currentYear = "2020"; // TODO test case
		String currentSemeserId = getCurrentSemester().toString();
		String sem = currentSemeserId.equals("30") ? "SoSe" : "WiSe";

		logger.debug("Suche-Parameter: TD Daten Suchen Ab: " + tomorow + " Jahr: " + currentYear + " Semster: " + sem);

		String tempDate = "";
		// für 14 Tagen
		for (int i = 0; i < 14; i++) {

			tempDate = tomorow;

			logger.debug("Daten für das Datum : " + tempDate);
			jobLogger.debug("Daten für das Datum : " + tempDate);

			tempList = getData(currentYear, currentSemeserId, tempDate, jobLogger);

			tdList.addAll(tempList);
			logger.debug("Elemente hinzugefügt, Elemente: " + tdList.size());
			jobLogger.debug("Elemente hinzugefügt, Elemente: " + tdList.size());
			currentDate = tempDate;
			tomorow = CalendarUtils.getNextDate(currentDate, CalendarUtils.DATEFORMAT_ENGLISH);
		}

		return tdList;

	}

	/**
	 * Konvertiere Termin Daten
	 *
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 * @throws de.his.ws.facilityservice.ServiceFault_Exception
	 * @throws de.his.ws.appointmentseriesservice.ServiceFault_Exception
	 * @throws ParseException
	 */
	@Override
	public List<Map<TdColumns, String>> getTdDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault, de.his.ws.courseinterfaceservice.ServiceFault_Exception, ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, ParseException {

		List<Map<TdColumns, String>> result = new ArrayList<>();

		List<Td> tdList = getTdDataForNext14Days(java.time.LocalDate.now(), jobLogger);

		logger.debug("Konvertiere TD- Daten...");
		jobLogger.debug("Konvertiere TD- Daten...");

		for (Td td : tdList) {

			try {
				logger.debug("TD-Convertieren: " + td.getTitel());
				jobLogger.debug("TD-Convertieren: " + td.getTitel());
				result.add(tdConverter.tdToMap(td, jobLogger));
			} catch (Exception e) {
				logger.error("TD VearnstaltungsId  " + td.getLv_nr_s() + " konnte nicht umgewandelt werden: "
						+ e.getMessage());
				jobLogger.error("TD VearnstaltungsId  " + td.getLv_nr_s() + " konnte nicht umgewandelt werden: "
						+ e.getMessage());
				mc.addErrorMessage("TD VearnstaltungsId  " + td.getLv_nr_s() + " konnte nicht umgewandelt werden: "
						+ e.getMessage());
			}

		}

		return result;
	}

	/**
	 * default name für eine Veranstaltung
	 *
	 * @param unitId
	 * @param currentSemesterId
	 * @param currentYear
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 */
	@SuppressWarnings("unused")
	private String readPlanElementName(Long unitId, Long currentSemesterId, BigInteger currentYear)
			throws de.his.ws.courseinterfaceservice.ServiceFault_Exception {
		PlanelementsType planType = hisCourseinterfaceServiceOutput.readPlanelementForUnit(unitId, currentSemesterId,
				currentYear);
		if (planType.getPlanelement().size() != 0) {
			return planType.getPlanelement().get(0).getDefaulttext();
		}

		return "";

	}

	@Override
	public void writeTd(List<Map<TdColumns, String>> termine, WritableResource resource)
			throws IOException, NullPointerException {

		logger.debug("TD - Daten werden in .csv Datei geschreiben");
		System.out.println("TD - Daten werden in .csv Datei geschreiben");

		csvWriter.write(resource, termine, Charset.forName("UTF-8"), TdColumns.class, ';', false, QuoteMode.MINIMAL);

	}

}
