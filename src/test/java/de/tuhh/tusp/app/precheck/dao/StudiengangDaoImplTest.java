package de.tuhh.tusp.app.precheck.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@ContextConfiguration(
		classes = { ApplicationUnitTestConfig.class, StudiengangDaoImplTest.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = { "de.tuhh.tusp.app.precheck.dao" })
@Transactional
@Configuration
public class StudiengangDaoImplTest {

	@Autowired
	private StudiengangDao studiengangDao;

	@Autowired
	private CourseOfStudyDao courseOfStudyDao;

	@Autowired
	private DegreeDao degreeDao;

	private Degree d;
	private CourseOfStudy cos1;
	private CourseOfStudy cos2;
	private CourseOfStudy cos3;
	private Studiengang stg1;
	private Studiengang stg2;

	@Before
	public void before() {

		d = new Degree();
		d.setId(1L);
		d.setDefaulttextDe("B.Sc.");
		d.setDefaulttextEn("B.Sc.");
		d.setValid(true);
		degreeDao.insert(d);

		cos1 = new CourseOfStudy();
		cos1.setId(1L);
		cos1.setLid(1L);
		cos1.setValid(true);
		cos1.setDefaulttextDe("B.Sc. Künstlerisches Testen");
		cos1.setDefaulttextEn("B.Sc. Artistically Testing");
		cos1.setDegreeid(d.getId());
		courseOfStudyDao.insert(cos1);

		cos2 = new CourseOfStudy();
		cos2.setId(2L);
		cos2.setLid(2L);
		cos2.setValid(true);
		cos2.setDefaulttextDe("B.Sc. Künstlerisches Testen 2");
		cos2.setDefaulttextEn("B.Sc. Artistically Testing 2");
		cos2.setDegreeid(d.getId());
		courseOfStudyDao.insert(cos2);

		cos3 = new CourseOfStudy();
		cos3.setId(3L);
		cos3.setLid(3L);
		cos3.setValid(true);
		cos3.setDefaulttextDe("B.Sc. Künstlerisches Testen 3");
		cos3.setDefaulttextEn("B.Sc. Artistically Testing 3");
		cos3.setDegreeid(d.getId());
		courseOfStudyDao.insert(cos3);

		stg1 = new Studiengang();
		stg1.setCourseofstudyId(cos1.getId());
		stg1.setAktiv(1);
		stg1.setId(studiengangDao.insert(stg1));

		stg2 = new Studiengang();
		stg2.setCourseofstudyId(cos2.getId());
		stg2.setAktiv(0);
		stg2.setId(studiengangDao.insert(stg2));
	}

	@Test
	public void findByAktiv() {

		List<Studiengang> list = studiengangDao.findByAktiv();

		assertEquals(list.size(), 1);

		assertEquals(list.get(0), stg1);
	}

	@Test
	public void findByCoursOfStudyId() {

		Studiengang stg = studiengangDao.fetchByCoursOfStudyId(stg2.getCourseofstudyId());
		assertEquals(stg, stg2);
	}

	@Test
	public void findLabeldAll() {

		List<Map<String, Object>> list = studiengangDao.findLabeldAll();

		assertEquals(list.get(0).get("defaulttext_de"), cos1.getDefaulttextDe());

		assertNull(	"Methode muss bei Studiengängen ohne precheck_studiengang-Eintrag eine ID null haben",
					list.get(2).get("id"));

		assertNull(	"Methode muss bei Studiengängen ohne precheck_studiengang-Eintrag aktiv == null haben",
					list.get(2).get("aktiv"));
	}

}
