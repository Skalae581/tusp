package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EctsDaoImplTest {

	@Autowired
	private EctsDao ectsDao;

	@Test
	public void crudTest() {

		Ects ects = new Ects();
		ects.setBereichId(VorabcheckTestDaoConfig.bereichId);
		ects.setBereichPunkte(0.0);
		ects.setBewId(VorabcheckTestDaoConfig.bewerber.getId());
		ects.setErwerb("");
		ects.setFachId(VorabcheckTestDaoConfig.faecher.get(0).getId());
		ects.setGesamtPunkte(0.0);
		ects.setId(0L);
		ects.setModul("");
		ects.setNachweis("");
		ects.setPunkte(0.0);
		ects.setErgebnis("test");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(ects);

		String[] ignoreGetters = { "getId", "getName", "getBereichPunkte", "getGesamtPunkte", "getBewNr",
				"getBereichPunkte", "getCodenummer", "getErgebnis" };
		testUtils.simpleCrudTestForDao(ectsDao, ects, ignoreGetters, new Change("setErwerb", "Erwerb wurde geändert"));
	}

	@Test
	public void fetchByBewIdTest() {

		Ects ects = VorabcheckTestDaoConfig.ects;
		List<Ects> expectedEctsList = new ArrayList<>();
		ects.setErgebnis(null);
		expectedEctsList.add(ects);

		List<Ects> ectsList = ectsDao.fetchByBewId(VorabcheckTestDaoConfig.bewerber.getId());

		assertEquals(expectedEctsList, ectsList);
	}
}
