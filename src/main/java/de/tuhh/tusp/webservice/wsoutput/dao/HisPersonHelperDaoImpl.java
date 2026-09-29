package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.personservice.DeletePerson;
import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersonIdsType;
import de.his.ws.personservice.PersonService;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.personservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.service.HisPersonServiceOutputImpl;

@Component
public class HisPersonHelperDaoImpl implements HisPersonHelperDao {

	private static Logger logger = LogManager.getLogger(HisPersonServiceOutputImpl.class);

	@Autowired
	private WebserviceFactory serviceFactory;

	@Override
	public Long createPerson(Person person) {

		if (person == null) {
			throw new ServiceFault("Person darf nicht null sein!", null);
		}
		try {
			return getPort().savePerson(person);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public void deletePerson(Long personId) {

		if (personId == null) {
			return;
		}

		DeletePerson param = new DeletePerson();
		param.setId(personId);

		try {
			getPort().deletePerson(param);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public PersonExisting fetchPerson(Long personId) {

		if (personId == null) {
			return null;
		}
		try {
			return getPort().readPerson(personId);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<Long> findPersonsByRole(String roleIdFilter) throws ServiceFault {

		return findPersonsByRoleAndStudystatus(roleIdFilter, "");
	}

	@Override
	public List<Long> findPersonsByRoleAndStudystatus(String roleIdFilter, String studystatusFilter)
			throws ServiceFault {

		try {
			PersonIdsType personTypes = getPort().findPerson60("", "", "", "", "", "", "", "", "", "", "", "", "", "",
					"", "", studystatusFilter, "", "", "", "", "", roleIdFilter, "", "", "");
			return personTypes.getId();
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

	}

	@Override
	public Long getPersonIdIfExists(String lastname, String firstname, Date dateOfBirth) {

		PersonIdsType ids;
		String date = CalendarUtils.dateToDatumsString(dateOfBirth, Locale.ENGLISH);
		try {
			ids = getPort().findPerson60("=" + lastname, "=" + firstname, "", "", "", "", "=" + date, "", "", "", "",
					"", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		if (ids.getId().isEmpty()) {
			logger.debug("Person {" + lastname + " " + firstname + "} ist nicht vorhanden!");
			return null;
		}

		if (ids.getId().size() >= 2) {
			throw new IllegalStateException("Es existieren mehrere Peronen mit Vorname=" + firstname + ", Nachname="
					+ lastname + " und Geburtsdatum=" + dateOfBirth);
		}

		Long personId = ids.getId().get(0);
		logger.debug("Person {" + lastname + " " + firstname + "} gefunden! ID -> {" + personId + "}");
		return personId;
	}

	/**
	 * liefert den PersonServicePort
	 *
	 * @return den port
	 */
	private PersonServicePortType getPort() {

		return serviceFactory.getWebservicePort(PersonService.class);
	}

	@Override
	public List<Long> searchFuzzy(String lastname, String firstname, Date dateOfBirth) {

		PersonIdsType ids;
		String date = dateOfBirth != null ? "=" + CalendarUtils.dateToDatumsString(dateOfBirth, Locale.ENGLISH) : "";
		try {
			ids = getPort().findPerson60(lastname, firstname, "", "", "", "", date, "", "", "", "", "", "", "", "", "",
					"", "", "", "", "", "", "", "", "", "");

		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
		return ids.getId();
	}

	@Override
	public void updatePerson(PersonExisting person) {

		if (person == null) {
			throw new ServiceFault("Person darf nicht null sein!", null);
		}

		try {
			getPort().updatePerson(person);
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}
}
