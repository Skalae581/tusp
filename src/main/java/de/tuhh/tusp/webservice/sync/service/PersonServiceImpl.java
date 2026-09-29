package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersonIdsType;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.personservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.PersonDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.Person;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class PersonServiceImpl extends WebserviceSyncImpl implements PersonService {

	private static Logger logger = LogManager.getLogger(PersonServiceImpl.class);

	private static final int ORDER_NUM = 7;

	@Autowired
	private PersonDao dao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	public void sync() {

		PersonServicePortType port = webserviceFactory.getWebservicePort(de.his.ws.personservice.PersonService.class);

		List<Long> ids;
		try {

			Date lastChanged = dao.getMaxUpdated();

			String filter = lastChanged == null ? "" : ">=" + lastChanged.toString();

			PersonIdsType updated = port.findPerson60("", "", "", "", "", "", "", "", "", "", "", "", filter, "", "",
					"", "", "", "", "", "", "", "", "", "", "");

			ids = updated.getId();
		} catch (ServiceFault_Exception e) {
			throw new RuntimeException("Konnte Personen nicht abrufen.", e);
		}

		List<BasePojo> serviceData = new ArrayList<>();

		for (Long personId : ids) {
			try {
				PersonExisting returnedType = port.readPerson(personId);

				Person p = new Person();
				p.setFirstname(returnedType.getFirstname());
				p.setSurname(returnedType.getSurname());
				p.setAcademicdegreeId(returnedType.getAcademicdegreeId());
				p.setAcademicdegreeSuffix(returnedType.getAcademicdegreesuffix());
				p.setUpdatedAt(CalendarUtils.toDate(returnedType.getUpdatedAt()));
				p.setId(personId);
				p.setValid(true);

				serviceData.add(p);

			} catch (ServiceFault_Exception e) {
				logger.error("Konnte Person " + personId + " nicht laden", e);
				throw new RuntimeException("Konnte Person " + personId + " nicht laden", e);

			}
		}
		syncDataWithoutExpiery(serviceData, dao);
	}
}
