package de.tuhh.tusp.webservice.sync.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Country;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryDaoImplTest {

	@Autowired
	CountryDao countryDao;
	private Country country1;
	private Country country2;

	@Before
	public void before() {

		countryDao.deleteAll();

		country1 = TestUtils.manufacturePojo(Country.class);
		country1.setDefaulttextDe("ZZZ");
		country1.setId(countryDao.insert(country1));

		country2 = TestUtils.manufacturePojo(Country.class);
		country2.setDefaulttextDe("AAA");
		country2.setId(countryDao.insert(country2));
	}

	@Test
	public void crudTest() {

		Country pojo = new Country();

		pojo.setId(0L);
		pojo.setLid(0L);
		pojo.setDefaulttextDe("Deutschland");
		pojo.setDefaulttextEn("Germany");
		pojo.setEuMember(1);
		pojo.setEwrMember(1);
		pojo.setIso3166_1_alpha("DE");
		pojo.setLongtextDe("Deutschland");
		pojo.setLongtextEn("Germany");
		pojo.setShorttextDe("Deutschl.");
		pojo.setShorttextEn("Germany");
		pojo.setUniquename("D");
		pojo.setSortorder("1");
		pojo.setValid(true);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	countryDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}

	@Test
	public void fetchByDefaultnameDe_existing() {

		Country country = countryDao.fetchByDefaultnameDe(country1.getDefaulttextDe());

		assertEquals(country1.getId(), country.getId());
	}

	@Test
	public void fetchByDefaultnameDe_nonExisting() {

		Country country = countryDao.fetchByDefaultnameDe("Gibts nicht");

		assertNull(country);
	}

	@Test
	public void findAllAscendingTest() {

		List<Country> countries = countryDao.findAllAscending();
		assertTrue(countries.size() >= 2);
		assertEquals("AAA", countries.get(0).getDefaulttextDe());
	}
}
