package de.tuhh.tusp.webservice.wsoutput.service;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.his.ws.personservice.PersonExisting;
import de.his.ws.studentservice.ServiceFault_Exception;
import de.tuhh.tusp.psv.UpdateStudentFileWriter;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.StudentDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

@Component
public class HisDataServiceOutputImpl implements HisDataServiceOutput {

	private static Logger logger = LogManager.getLogger(HisDataServiceOutputImpl.class);

	@Autowired
	private HisPersonServiceOutput hisPersonService;
	@Autowired
	private HisAccountServiceOutput hisAccountService;
	@Autowired
	private HisRolesServiceOutput hisRolesService;
	@Autowired
	private HisStudentServiceOutput hisStudentService;
	@Autowired
	private HisAddressesServiceOutput hisAdressService;
	@Autowired
	private StudentDao studentDao;

	// Diagramm-Implementierug
	public Long checkForPersonData(HisPerson user) {

		// find mtknr local db
		Long personId = studentDao.getPersonIdFromMtknrId(user.getIdExtern());

		// student lokal vorhanden?
		if (personId == null) {
			personId = hisPersonService.getPersonIdIfExists(user.getSurname(), user.getFirstname(),
					user.getDateofbirth().toGregorianCalendar().getTime());

			logger.info("Person Suche: Name: " + user.getSurname() + " Vorname: " + user.getFirstname() + " GebDat: "
					+ CalendarUtils.dateToDatumsString(user.getDateofbirth().toGregorianCalendar().getTime()));

		} else {

			PersonExisting person = hisPersonService.fetchPerson(personId);

			CalendarUtils utils = new CalendarUtils();

			XMLGregorianCalendar personBirth = person.getDateofbirth();
			XMLGregorianCalendar userBirth = user.getDateofbirth();

			boolean datums = false;

			if (null != personBirth && null != userBirth) {
				datums = utils.dateCompare(person.getDateofbirth(), user.getDateofbirth()) == 0;
			}

			if (!(datums && (person.getFirstname().equals(user.getFirstname())
					|| person.getSurname().equals(user.getSurname())))) {
				throw new RuntimeException("Person vorhanden aber daten passen nicht überein: " + person.getId());
			}

		}

		return personId;
	}

	@Override
	public Long savePerson(HisPerson user, HisStudent student, MessageCollector collector, WritableResource file,
			UpdateStudentFileWriter updateStudentFileWriter) throws ServiceFault {

		if (collector == null) {
			collector = MessageCollectorImpl.getInstance(Thread.currentThread());
		}

		Long personId = checkForPersonData(user);

		// Wenn Person noch nicht existiert und kein Student ist => Person
		// anlegen
		if (personId == null && student == null) {
			try {
				personId = hisPersonService.createPerson(user);
				user.setIdPerson(personId);
				hisPersonService.updatePerson(user);
				hisRolesService.updateOrCreateRolesForPerson(user, personId);
				hisAdressService.updateOrCreateAddresses(user);
				hisAccountService.updateOrCreateAccount(user);
				collector.addCreated();
			} catch (ServiceFault e) {
				logger.error("Person kann nicht gespeichert werden! " + user.toShortString(), e);
				throw e;
			}
		}

		// Wenn Person noch nicht existiert und Student ist => Person und
		// Student anlegen
		else if (personId == null && student != null) {
			try {
				personId = hisStudentService.createStudent(student);
				user.setIdPerson(personId);
				hisStudentService.updateStudent(user, student, file, updateStudentFileWriter);
				hisPersonService.updatePerson(user);
				hisRolesService.updateOrCreateRolesForPerson(user, personId);
				hisAdressService.updateOrCreateAddresses(user);
				hisAccountService.updateOrCreateAccount(user);
				collector.addCreated();
			} catch (Exception e) {
				logger.error("Student kann nicht gespeichert werden! " + user.toShortString(), e);
				try {
					throw e;
				} catch (Exception e1) {
					logger.error("Fehler:  " + user.toShortString(), e1);
				}
			}
		}

		// Wenn Person existiert und kein Student ist => Update Person
		else if (student == null) {
			user.setIdPerson(personId);
			hisPersonService.updatePerson(user);
			hisRolesService.updateOrCreateRolesForPerson(user, personId);
			hisAdressService.updateOrCreateAddresses(user);
			hisAccountService.updateOrCreateAccount(user);
			collector.addUpdated();
		}

		// Wenn Person existiert und Student ist => Update Person und Student
		else {

			user.setIdPerson(personId);
			hisPersonService.updatePerson(user);
			try {
				hisStudentService.updateStudent(user, student, file, updateStudentFileWriter);

			} catch (ServiceFault e) {
				logger.error("Student kann nicht gespeichert werden! " + user.toShortString(), e);
				throw e;
			} catch (ServiceFault_Exception e) {
				e.printStackTrace();
			}
			hisRolesService.updateOrCreateRolesForPerson(user, personId);
			hisAdressService.updateOrCreateAddresses(user);
			hisAccountService.updateOrCreateAccount(user);
			collector.addUpdated();
		}

		return personId;
	}

}
