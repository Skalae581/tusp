package de.tuhh.tusp.webservice.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.his.ws.accountservice.AccountService;
import de.his.ws.accountservice.AccountServicePortType;
import de.his.ws.accountservice.FindAccountResultType;
import de.his.ws.accountservice.ServiceFault_Exception;
import de.his.ws.applicantservice.ApplicantService;
import de.his.ws.applicationservice.ApplicationService;
import de.his.ws.courseofstudyservice.CourseOfStudyService;
import de.his.ws.keyvalueservice.KeyvalueService;
import de.his.ws.mimedataservice.MimedataService;
import de.his.ws.orgunitservice.OrgUnitService;
import de.his.ws.periodservice.PeriodService;
import de.his.ws.personaddressservice.PersonAddressService;
import de.his.ws.personaddressservice.PersonAddressServicePortType;
import de.his.ws.personservice.PersonIdsType;
import de.his.ws.personservice.PersonService;
import de.his.ws.personservice.PersonServicePortType;
import de.his.ws.rightsandrolesservice.RightsAndRolesService;
import de.his.ws.studentservice.StudentService;
import de.his.ws.termservice.TermService;
import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

/**
 * Classe Testet ob die HIS-Webservices aktiviert sind und ob die nötigen Rechte existieren.
 *
 * @author rztjm
 *
 */
@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class WebserviceFactoryIT {

	@Autowired
	private WebserviceFactory factory;

	@Test
	public void getAccountServicePortTest() throws Exception {

		AccountServicePortType port = factory.getWebservicePort(AccountService.class);
		FindAccountResultType accounts = port.findAccount("", "=rztjm", "", "", "", "", "", "", "", "");
		assertTrue(!accounts.getAccountInfo().isEmpty());
	}

	@Test
	public void getApplicantServicePort() {

		factory.getWebservicePort(ApplicantService.class);
	}

	@Test
	public void getApplicationServicePort() {

		factory.getWebservicePort(ApplicationService.class);
	}

	@Test
	public void getCourseOfStudyPort() {

		factory.getWebservicePort(CourseOfStudyService.class);
	}

	@Test
	public void getKeyvalueServicePort() throws Exception {

		factory.getWebservicePort(KeyvalueService.class);
	}

	@Test
	public void getMimedataServicePortTest() throws ServiceFault_Exception {

		factory.getWebservicePort(MimedataService.class);

	}

	@Test
	public void getOrgUnitService() throws de.his.ws.orgunitservice.ServiceFault_Exception {

		factory.getWebservicePort(OrgUnitService.class);
	}

	@Test
	public void getPeriodServicePort() {

		factory.getWebservicePort(PeriodService.class);
	}

	@Test
	public void getPersonAddressServicePortTest() throws Exception {

		PersonAddressServicePortType port = factory.getWebservicePort(PersonAddressService.class);
		port.readAddresses(25);
	}

	@Test
	public void getPersonServicePort() throws Exception {

		PersonServicePortType port = factory.getWebservicePort(PersonService.class);
		PersonIdsType persons = port.findPerson60(	"", "", "", "", "", "", "", "", "", "", "=rztjm", "", "", "", "", "",
													"", "", "", "", "", "", "", "", "", "");
		assertTrue(!persons.getId().isEmpty());
	}

	@Test
	public void getRightsAndRolesService() {

		factory.getWebservicePort(RightsAndRolesService.class);
	}

	@Test
	public void getStudentServicePort() throws Exception {

		factory.getWebservicePort(StudentService.class);
	}

	@Test
	public void getTermServicePort() {

		factory.getWebservicePort(TermService.class);
	}
}
