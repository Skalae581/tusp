package de.tuhh.tusp.lvp.dao;

import java.io.IOException;
import java.math.BigInteger;
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

import de.his.ws.courseinterfaceservice.PersonPlanelementsType;
import de.his.ws.courseinterfaceservice.PlanelementsType;
import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.termservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.lvp.pojo.Lvp;
import de.tuhh.tusp.lvp.service.LvpConverter;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.CsvWriter;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.service.HisCourseinterfaceServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisElementtypeValueServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisOrgunitServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisPersonServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisTermTypeValueServiceOutput;

@Component
public class LvpDaoImpl implements LvpDao {

	private static Logger logger = LogManager.getLogger(LvpDaoImpl.class);

	@Autowired
	private HisCourseinterfaceServiceOutput hisCourseinterfaceServiceOutput;
	@Autowired
	private HisTermServiceOutput hisTermServiceOutput;
	@Autowired
	private HisTermTypeValueServiceOutput hisTermTypeValueServiceOutput;
	@Autowired
	private HisElementtypeValueServiceOutput hisElementtypeValueServiceOutput;
	@Autowired
	private LvpConverter lvpConverter;
	@Autowired
	private HisOrgunitServiceOutput hisOrgunitServiceOutput;
	@Autowired
	private HisPersonServiceOutput hisPersonServiceOutput;
	@Autowired
	private CsvWriter csvWriter;

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
	 * gibt elemttype name zurück
	 *
	 * @param unitId
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 */
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
	 * lese alle daten hier
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 * @throws ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 */
	private List<Lvp> getLvpDataForNextDay(LocalDate date, JobLogger jobLogger)
			throws ParseException, ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.courseinterfaceservice.ServiceFault_Exception {

		String currentDate = date.toString();
		// String currentDate = "2022-04-10"; // zum testen

		String tomorow = CalendarUtils.getNextDate(currentDate, CalendarUtils.DATEFORMAT_ENGLISH);

		List<Lvp> lvpList = new ArrayList<>();

		// Semester
		String currentSemeserId = getCurrentSemester().toString();
		String sem = currentSemeserId.equals("30") ? "SoSe" : "WiSe";

		// Jahr
		int y = Calendar.getInstance().get(Calendar.YEAR);
		String currentYear = String.valueOf(y);

		logger.debug("Suche Veranstaltungen mit Parameter: Datum: " + tomorow + " Jahr: " + currentYear + " Semester: "
				+ sem);
		jobLogger.debug("Suche Veranstaltungen mit Parameter: Datum: " + tomorow + " Jahr: " + currentYear
				+ " Semester: " + sem);

		List<Long> unitList = hisCourseinterfaceServiceOutput.findUnit81(currentYear, currentSemeserId, tomorow);

		if (unitList.size() == 0) {

			logger.debug("Keine Veranstaltungen für das Datum: " + tomorow + " gefunden!");
			jobLogger.debug("Keine Veranstaltungen für das Datum: " + tomorow + " gefunden!");

		} else {

			logger.debug(unitList.size() + " Veranstaltungen für das Datum: " + tomorow + " gefunden!");
			jobLogger.debug(unitList.size() + " Veranstaltungen für das Datum: " + tomorow + " gefunden!");

			for (Long unit : unitList) {

				String veranstaltungName = readPlanElementName(unit, Long.parseLong(currentSemeserId),
						new BigInteger(currentYear));
				String temp;
				if (veranstaltungName.length() > 70) {
					System.out.println("Veranstaltungs -Name verkürzt");
					temp = veranstaltungName.substring(0, veranstaltungName.length() - 25); // String
																							// to
																							// long
					veranstaltungName = temp;
				}
				String elementName = getElementtypeName(unit); // DATA { unitId ,
																// element name ( V
																// ) }
				String bereich = getOrgunitName(unit); // bereich
				String personResponsible = getPersonForUnit(unit, Long.parseLong(currentSemeserId),
						new BigInteger(currentYear));

				Lvp lvp = new Lvp();

				lvp.setLv_nr_s(unit);
				lvp.setTitel(veranstaltungName);
				lvp.setNutzer(personResponsible);
				lvp.setLv_typ(elementName);
				lvp.setBereich(bereich);

				lvpList.add(lvp);

				logger.debug("lvp.csv--->" + unit + ";" + veranstaltungName + "," + personResponsible + ","
						+ elementName + "," + bereich);
				jobLogger.debug("lvp.csv--->" + unit + ";" + veranstaltungName + "," + personResponsible + ","
						+ elementName + "," + bereich);
			}
		}

		return lvpList;
	}

	@Override
	public List<Map<LvpColumns, String>> getLvpDaten(MessageCollector mc, JobLogger jobLogger)
			throws ServiceFault, ParseException, ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception, de.his.ws.courseinterfaceservice.ServiceFault_Exception {

		List<Map<LvpColumns, String>> lvpCsv = new ArrayList<>();

		List<Lvp> lvpList = getLvpDataForNextDay(java.time.LocalDate.now(), jobLogger);

		for (Lvp lvp : lvpList) {

			try {
				logger.debug("Konvertiere " + lvp);
				jobLogger.debug("Konvertiere " + lvp);
				Map<LvpColumns, String> convertedLvp = lvpConverter.lvpToMap(lvp);
				lvpCsv.add(convertedLvp);

			} catch (Exception e) {
				logger.error(lvp, e);
				jobLogger.error("Lvp VearnstaltungsId  " + lvp.getLv_nr_s() + " konnte nicht umgewandelt werden: "
						+ e.getMessage());

			}
		}

		return lvpCsv;
	}

	/**
	 * finde orgunit für veranstaltung
	 *
	 * @param unitId
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 * @throws de.his.ws.keyvalueservice.ServiceFault_Exception
	 */
	private String getOrgunitName(Long unitId) throws de.his.ws.courseinterfaceservice.ServiceFault_Exception,
			de.his.ws.keyvalueservice.ServiceFault_Exception {

		Long orgunitLid = hisCourseinterfaceServiceOutput.readUnit81(unitId).getUnitOrgunits().getUnitOrgunit().get(0)
				.getOrgunitLid();
		List<KeyValue> orgUnits = hisOrgunitServiceOutput.getOrgunit();
		String orgunitName = "";
		for (KeyValue keyValue : orgUnits) {
			if (orgunitLid.equals(keyValue.getLid())) {
				orgunitName = keyValue.getUniquename();
				break;
			}
		}

		return orgunitName;

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
	 * default name für eine Veranstaltung
	 *
	 * @param unitId
	 * @param currentSemesterId
	 * @param currentYear
	 * @return
	 * @throws de.his.ws.courseinterfaceservice.ServiceFault_Exception
	 */
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
	public void writeLvp(List<Map<LvpColumns, String>> lvp, WritableResource resource)
			throws IOException, NullPointerException {

		if (lvp != null && !lvp.isEmpty()) {
			logger.debug("Lvp - Daten werden geschribnen!!!!");

			csvWriter.writeWithNewLineSeparator(resource, lvp, Charset.forName("UTF-8"), LvpColumns.class, ',', false,
					QuoteMode.NON_NUMERIC);
		}

	}

}
