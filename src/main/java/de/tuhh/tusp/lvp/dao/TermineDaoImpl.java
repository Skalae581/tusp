package de.tuhh.tusp.lvp.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.appointmentseriesservice.AppointmentSeries;
import de.his.ws.facilityservice.Building;
import de.his.ws.facilityservice.Room;
import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.termservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.lvp.pojo.Termine;
import de.tuhh.tusp.lvp.service.TermineConverter;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.service.HisAppointmentSeriesServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisCourseinterfaceServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisFacilityServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermTypeValueServiceOutput;

@Component
public class TermineDaoImpl implements TermineDao {

	private static Logger logger = LogManager.getLogger(TermineDaoImpl.class);

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
	private TermineConverter termineConverter;

	@Autowired
	private CsvWriter csvWriter;

	/**
	 * Schneidet die Name einer Room
	 *
	 * @param currebtName
	 * @return
	 */
	private String changeRoomName(String currentName) {

		String changedName = "";

		if (currentName.indexOf("-") == -1) {
			return currentName;
		}

		String firstThreeChars = currentName.substring(0, 3);
		char c;
		String buildingLetter = "";

		if (firstThreeChars.equals("VER")) {

			c = currentName.charAt(4);
			buildingLetter = String.valueOf(c);

			logger.debug("Building: " + buildingLetter);
			if (buildingLetter.equals("O")) {
				System.out.println("O Building");
			}

			// check buildings
			if (buildingLetter.equals("N") || buildingLetter.equals("O") || buildingLetter.equals("M")
					|| buildingLetter.equals("K") || buildingLetter.equals("L") || buildingLetter.equals("I")) {

				return changedName = currentName.substring(currentName.indexOf("-") + 1).trim();
			}
		}

		changedName = currentName.substring(currentName.indexOf("-") + 1);
		logger.debug("Current Name: " + currentName);
		logger.debug("Changed Room name 1: " + changedName.trim());
		if (changedName.trim().equals("Audimax1")) {
			return changedName.trim();
		}

		if (buildingLetter != "") {
			StringBuilder _sb = new StringBuilder(changedName);
			changedName = _sb.insert(1, buildingLetter).toString();
		}

		logger.debug("Changed Room name 2: " + changedName.trim());

		return changedName.trim();
	}

	/**
	 * Aktuelles Semester ID
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 */
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
	 * Termine - Date Liste
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

	private List<Termine> getTermineDataForNextDay(LocalDate date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.courseinterfaceservice.ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, ParseException {

		String currentDate = date.toString(); // ab heute
		// String currentDate = "2022-04-10";

		String tomorow = CalendarUtils.getNextDate(currentDate, CalendarUtils.DATEFORMAT_ENGLISH);

		List<Termine> termineList = new ArrayList<>();

		// Semestar
		String currentSemeserId = getCurrentSemester().toString();
		String sem = currentSemeserId.equals("30") ? "SoSe" : "WiSe";

		// Jahr
		int y = Calendar.getInstance().get(Calendar.YEAR);
		String currentYear = String.valueOf(y);

		logger.debug("Suche Termine mit Parameter: Datum: " + tomorow + " Jahr: " + currentYear + " Semester: " + sem);
		jobLogger.debug(
				"Suche Termine mit Parameter: Datum: " + tomorow + " Jahr: " + currentYear + " Semester: " + sem);

		List<Long> unitList = hisCourseinterfaceServiceOutput.findUnit81(currentYear, currentSemeserId, tomorow);

		if (unitList.size() == 0) {

			logger.debug("Keine Termine für das Datum: " + tomorow + " gefunden!");
			jobLogger.debug("Keine Termine für das Datum: " + tomorow + " gefunden!");

		} else {

			logger.debug("Gefunden " + unitList.size() + "  Termine für das Datum: " + tomorow);
			jobLogger.debug("Gefunden " + unitList.size() + "  Termine für das Datum: " + tomorow);

			// for every element in the list get details
			for (Long unit : unitList) {

				String endDate = "";
				String startDate = "";
				String endTime = "";
				String startTime = "";
				String roomName = "";
				String buildingName = "";

				try {
					List<Long> plannedDates = getPlannedDatesIds(unit.toString(), currentYear, currentSemeserId);

					if (!plannedDates.isEmpty()) {

						Long plannedDate = plannedDates.get(0);

						startDate = tomorow;
						endDate = tomorow;
						endTime = null == getEndTime(plannedDate) ? "" : getEndTime(plannedDate);
						startTime = null == getStartTime(plannedDate) ? "" : getStartTime(plannedDate);

						Long roomId = getRoomId(plannedDate);

						if (null != roomId) {
							Room room = hisFacilityserviceOutput.searchRoom(roomId);

							Long buldingId = hisFacilityserviceOutput.getBuildingIdForRoom(roomId);
							if (null != buldingId) {
								Building building = hisFacilityserviceOutput.readBuilding(buldingId);
								buildingName = building.getDefaulttext();
							}

							String currentRoomName = room.getDefaulttext();
							// change room name
							roomName = changeRoomName(currentRoomName);
						}

					}

					Termine termine = new Termine();

					termine.setLv_nr_s(unit);
					termine.setRaum_s(roomName);
					termine.setDbeg(startDate);
					termine.setDend(endDate);
					termine.setGbd_s(buildingName);
					termine.setBeginn(startTime);
					termine.setEnde(endTime);

					termineList.add(termine);

					logger.debug("termine.csv : " + unit + "," + buildingName + "," + roomName + "," + startDate + ","
							+ endDate + "," + startTime + "," + endTime);
					jobLogger.debug("termine.csv : " + unit + "," + buildingName + "," + roomName + "," + startDate
							+ "," + endDate + "," + startTime + "," + endTime);

				} catch (RuntimeException e) {
					logger.error("Fehlerhafter Datensatzt: Unit ID: " + unit);
					// lerres Objekt weiter geben
					Termine termine = new Termine();

					termine.setLv_nr_s(unit);
					termine.setRaum_s("");
					termine.setDbeg(startDate);
					termine.setDend(endDate);
					termine.setGbd_s("");
					termine.setBeginn("");
					termine.setEnde("");

					termineList.add(termine);

					logger.debug("termine.csv : " + unit + "," + buildingName + "," + roomName + "," + startDate + ","
							+ endDate + "," + startTime + "," + endTime);
					jobLogger.debug("termine.csv : " + unit + "," + buildingName + "," + roomName + "," + startDate
							+ "," + endDate + "," + startTime + "," + endTime);
					continue;
				}

			}

		}
		return termineList;

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
	public List<Map<TermineColumns, String>> getTermineDaten(JobLogger jobLogger)
			throws ServiceFault, de.his.ws.courseinterfaceservice.ServiceFault_Exception, ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, ParseException {

		List<Map<TermineColumns, String>> result = new ArrayList<>();

		List<Termine> termineList = getTermineDataForNextDay(java.time.LocalDate.now(), jobLogger);

		if (termineList.size() == 0) {
			return result;
		}
		logger.debug("Konvertiere Termine- Daten...");
		jobLogger.debug("Konvertiere Termine- Daten...");
		logger.debug("Termine Liste: " + termineList.size());
		jobLogger.debug("Termine Liste: " + termineList.size());
		for (Termine termine : termineList) {

			try {
				result.add(termineConverter.termineToMap(termine));
			} catch (Exception e) {
				logger.error(termine, e);
				jobLogger.error("Termine VearnstaltungsId  " + termine.getLv_nr_s()
						+ " konnte nicht umgewandelt werden: " + e.getMessage());

			}

		}

		return result;
	}

	@Override
	public void writeTermine(List<Map<TermineColumns, String>> termine, WritableResource resource)
			throws IOException, NullPointerException {

		if (termine != null && !termine.isEmpty()) {

			csvWriter.writeWithNewLineSeparator(resource, termine, Charset.forName("UTF-8"), TermineColumns.class, ',',
					false, QuoteMode.NON_NUMERIC);
			logger.debug("Termine - Daten sind in die ''..csv Datei geschreiben");
		} else {
			logger.debug("Keine Daten zum schreiben...");
		}

	}

}
