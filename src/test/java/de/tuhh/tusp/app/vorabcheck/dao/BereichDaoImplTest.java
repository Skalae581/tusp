package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BereichDaoImplTest {

	@Autowired
	private BereichDao bereichDao;

	@Autowired
	private StudiengangsauswahlDao stgDao;

	@Test
	public void crudTest() {

		Bereich bereich = new Bereich();
		bereich.setId(0L);
		bereich.setName("");
		bereich.setPunkte(Integer.valueOf(0));
		bereich.setStgId(VorabcheckTestDaoConfig.stgId);
		bereich.setZusatz("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(bereich);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	bereichDao, bereich, ignoreGetters,
										new Change("setName", "Name wurde geändert"));

	}

	@Test
	public void findAllByStgIdTest() {

		Bereich bereich1 = TestUtils.manufacturePojo(Bereich.class);
		bereich1.setStgId(VorabcheckTestDaoConfig.stgId);
		bereich1.setId(bereichDao.insert(bereich1));

		Bereich bereich2 = TestUtils.manufacturePojo(Bereich.class);
		bereich2.setStgId(VorabcheckTestDaoConfig.stgId);
		bereich2.setId(bereichDao.insert(bereich2));

		Studiengangsauswahl stg = TestUtils.manufacturePojo(Studiengangsauswahl.class);
		stg.setStandardkz("N");
		stg.setExtrakz(null);
		stg.setCourseofstudyId(VorabcheckTestDaoConfig.cosId);
		stg.setId(stgDao.insert(stg));

		Bereich bereich3 = TestUtils.manufacturePojo(Bereich.class);
		bereich3.setStgId(stg.getId());
		bereich3.setId(bereichDao.insert(bereich3));

		List<Bereich> expectedListBereich = VorabcheckTestDaoConfig.bereiche;
		expectedListBereich.add(bereich1);
		expectedListBereich.add(bereich2);

		List<Bereich> listBereich = bereichDao.findAllByStgId(VorabcheckTestDaoConfig.stgId);

		assertEquals(expectedListBereich, listBereich);
	}

	@Test
	public void findAllTest() {

		List<Bereich> expectedBereichArray = VorabcheckTestDaoConfig.bereiche;

		Collections.sort(expectedBereichArray, new Comparator<Bereich>() {

			@Override
			public int compare(Bereich b1, Bereich b2) {

				return b1.getId().compareTo(b2.getId());
			}
		});

		List<Bereich> bereichArray = bereichDao.findAll();

		assertEquals(expectedBereichArray, bereichArray);
	}

	@Test
	public void getBereichIdsByIdTest() {

		List<Long> expectedBereichArray = VorabcheckTestDaoConfig.bereichIdArray;

		List<Long> bereichArray = bereichDao.getBereichIdsById(VorabcheckTestDaoConfig.stgId);

		assertEquals(expectedBereichArray, bereichArray);
	}
}
