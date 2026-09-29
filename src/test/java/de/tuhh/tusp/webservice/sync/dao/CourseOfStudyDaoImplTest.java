package de.tuhh.tusp.webservice.sync.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseOfStudyDaoImplTest {

	@Autowired
	CourseOfStudyDao courseOfStudyDao;

	@Autowired
	DegreeDao degreeDao;

	@Test
	public void crudTest() {

		Degree degree = TestUtils.manufacturePojo(Degree.class);
		Long degreeId = degreeDao.insert(degree);

		CourseOfStudy pojo = new CourseOfStudy();

		pojo.setId(1L);
		pojo.setLid(1L);
		pojo.setUniquename("COS");
		pojo.setDegreeid(degreeId);
		pojo.setDefaulttextDe("COS");
		pojo.setDefaulttextEn("COS");
		pojo.setLongtextDe("COS");
		pojo.setLongtextEn("COS");
		pojo.setShorttextDe("COS");
		pojo.setShorttextEn("Cos");
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getLid" };
		testUtils.simpleCrudTestForDao(	courseOfStudyDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
