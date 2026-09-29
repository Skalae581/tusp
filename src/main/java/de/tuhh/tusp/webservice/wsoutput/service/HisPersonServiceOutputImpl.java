package de.tuhh.tusp.webservice.wsoutput.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.personservice.AccountsType;
import de.his.ws.personservice.Person;
import de.his.ws.personservice.PersonExisting;
import de.his.ws.personservice.PersoninfoDto;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.HisPersonHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

@Component
public class HisPersonServiceOutputImpl implements HisPersonServiceOutput {

	private static Logger logger = LogManager.getLogger(HisPersonServiceOutputImpl.class);

	@Autowired
	private HisPersonHelperDao hisPersonHelperDao;

	@Override
	public Long createPerson(HisPerson user) throws ServiceFault {

		logger.info("Erstelle " + user.toShortString());

		// Person object
		Person person = new Person();

		person.setObjGuid(user.getObjGuid());
		person.setFirstname(user.getFirstname());
		person.setSurname(user.getSurname());
		person.setAllfirstnames(user.getAllfirstnames());
		person.setDateofbirth(user.getDateofbirth());
		person.setGender(user.getGenderId() == null ? 0L : user.getGenderId());
		person.setBirthname(user.getBirthname());
		person.setArtistname(user.getArtistname());
		person.setNameprefix(user.getNameprefix());
		person.setNamesuffix(user.getNamesuffix());
		person.setAcademicdegreesuffix(user.getAcademicdegreesuffix());
		person.setAcademicdegreeId(user.getAcademicdegreeId());
		person.setTitleId(user.getTitleId());
		person.setBirthcity(user.getBirthcity());
		person.setCountryId(user.getCountryId());
		person.setSelfregistrationStatusId(user.getSelfregistrationStatusId());
		person.setCreatedAt(user.getCreatedAt());
		person.setUpdatedAt(user.getUpdatedAt());

		person.setAccounts(new AccountsType());

		Long id = hisPersonHelperDao.createPerson(person);
		logger.info("Neue PersonID " + id);
		return id;
	}

	@Override
	public void deletePerson(Long personId) throws ServiceFault {

		hisPersonHelperDao.deletePerson(personId);
	}

	@Override
	public PersonExisting fetchPerson(Long personId) {

		return hisPersonHelperDao.fetchPerson(personId);
	}

	@Override
	public Long getPersonIdIfExists(String lastname, String firstname, Date dateOfBirth) throws ServiceFault {

		return hisPersonHelperDao.getPersonIdIfExists(lastname, firstname, dateOfBirth);
	}

	@Override
	public void updatePerson(HisPerson user) throws ServiceFault {

		logger.info("Aktualisiere " + user.toShortString());
		PersonExisting person = hisPersonHelperDao.fetchPerson(user.getIdPerson());

		person.setFirstname(user.getFirstname() == null ? person.getFirstname() : user.getFirstname());
		person.setSurname(user.getSurname() == null ? person.getSurname() : user.getSurname());
		person.setAllfirstnames(user.getAllfirstnames() == null ? person.getAllfirstnames() : user.getAllfirstnames());
		person.setDateofbirth(user.getDateofbirth() == null ? person.getDateofbirth() : user.getDateofbirth());
		person.setGenderId(user.getGenderId() == null ? person.getGenderId() : user.getGenderId());
		person.setBirthname(user.getBirthname() == null ? person.getBirthname() : user.getBirthname());
		person.setArtistname(user.getArtistname() == null ? person.getArtistname() : user.getArtistname());
		person.setNameprefix(user.getNameprefix() == null ? person.getNameprefix() : user.getNameprefix());
		person.setNamesuffix(user.getNamesuffix() == null ? person.getNamesuffix() : user.getNamesuffix());
		person.setAcademicdegreesuffix(user.getAcademicdegreesuffix() == null ? person.getAcademicdegreesuffix()
				: user.getAcademicdegreesuffix());
		person.setAcademicdegreeId(
				user.getAcademicdegreeId() == null ? person.getAcademicdegreeId() : user.getAcademicdegreeId());
		person.setTitleId(user.getTitleId() == null ? person.getTitleId() : user.getTitleId());
		person.setSelfregistrationStatusId(
				user.getSelfregistrationStatusId() == null ? person.getSelfregistrationStatusId()
						: user.getSelfregistrationStatusId());
		person.setBirthcity(user.getBirthcity() == null ? person.getBirthcity() : user.getBirthcity());
		person.setCountryId(user.getCountryId() == null ? person.getCountryId() : user.getCountryId());
		person.setUpdatedAt(new CalendarUtils().getNowAsXMLGregorianCalendar());

		PersoninfoDto info = person.getPersoninfo();

		info.setNationalityId(user.getNationalityId() == null ? info.getNationalityId() : user.getNationalityId());
		info.setSecondNationalityId(
				user.getSecondNationalityId() == null ? info.getSecondNationalityId() : user.getSecondNationalityId());
		info.setUpdatedAt(new CalendarUtils().getNowAsXMLGregorianCalendar());
		person.setPersoninfo(info);

		hisPersonHelperDao.updatePerson(person);
	}

}
