package de.tuhh.tusp.webservice.sync.dao;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Gender;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class GenderDaoImplTest {

	@Autowired
	GenderDao genderDao;

	@Test
	public void crudTest() {

		Gender pojo = new Gender();

		pojo.setId(0L);
		pojo.setUniquename("Male");
		pojo.setDefaulttextDe("Male");
		pojo.setDefaulttextEn("Male");
		pojo.setLongtextDe("Male");
		pojo.setLongtextEn("Male");
		pojo.setShorttextDe("Male");
		pojo.setShorttextEn("Male");
		pojo.setValid(true);
		pojo.setSortorder("1");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getLid" };
		testUtils.simpleCrudTestForDao(	genderDao, pojo, ignoreGetters,
										new Change("setUniquename", "Name wurde geändert"));

	}

	@Test
	public void fetchByUniqueName_existing() {

		Gender g = TestUtils.manufacturePojo(Gender.class);
		g.setUniquename("F");
		g.setId(genderDao.insert(g));

		assertEquals(g.getId(), genderDao.fetchByUniqename("F").getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void fetchByUniqueName_notExisting() {

		genderDao.fetchByUniqename("M");
	}
}
