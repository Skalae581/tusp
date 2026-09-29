package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoImplTest {

	@Autowired
	StudentDao studentDao;

	@Test
	public void crudTest() {

		Student pojo = new Student();

		pojo.setId(0L);
		pojo.setMtknr(0L);
		pojo.setPersonId(0L);
		pojo.setStudentId(0L);
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };

		testUtils.simpleCrudTestForDao(studentDao, pojo, ignoreGetters, new Change("setHisid", 2L));

	}

	@Test
	public void getHisIdFromLocalId_existing() {

		Student student = TestUtils.manufacturePojo(Student.class);
		student.setPersonId(1000L);
		student.setMtknr(54321L);
		student.setId(studentDao.insert(student));

		assertEquals(Long.valueOf(1000L), studentDao.getPersonIdFromMtknrId(student.getMtknr()));
	}

	@Test
	public void getHisIdFromLocalId_notExisting() {

		assertNull(studentDao.getPersonIdFromMtknrId(5000L));
	}
}
