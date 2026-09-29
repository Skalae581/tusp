package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FachDaoImplTest {

	@Autowired
	private FachDao fachDao;

	@Test
	public void crudTest() {

		Fach fach = new Fach();
		fach.setId(0L);
		fach.setName("");
		fach.setPunkte(Integer.valueOf(0));
		fach.setBereichId(VorabcheckTestDaoConfig.bereichId);
		fach.setZusatz("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(fach);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(fachDao, fach, ignoreGetters, new Change("setName", "Name wurde geändert"));
	}

	@Test
	public void findAllByBereichIdsTest() {

		List<Long> bereichArray = new ArrayList<>();
		bereichArray = VorabcheckTestDaoConfig.bereichIdArray;

		List<Long> arr = new ArrayList<>();
		arr.add(bereichArray.get(0));
		arr.add(bereichArray.get(1));

		List<Fach> faecher = VorabcheckTestDaoConfig.faecher;

		List<Fach> expectedFachList1 = new ArrayList<>();
		for (Fach fach : faecher) {
			if (fach.getBereichId() == bereichArray.get(0) || fach.getBereichId() == bereichArray.get(1)) {
				expectedFachList1.add(fach);
			}
		}

		List<Fach> expectedFachList2 = new ArrayList<>();
		for (Fach fach : faecher) {
			if (fach.getBereichId() != bereichArray.get(0) && fach.getBereichId() != bereichArray.get(1)) {
				expectedFachList2.add(fach);
			}
		}

		List<Fach> fachList = fachDao.findAllByBereichIds(arr);

		assertEquals(expectedFachList1, fachList);
		assertFalse(expectedFachList2.equals(fachList));
	}

	@Test
	public void findAllByBereichIdTest() {

		List<Fach> tempFachList = VorabcheckTestDaoConfig.faecher;
		List<Fach> expectedFachList = new ArrayList<>();

		for (Fach fach : tempFachList) {
			if (fach.getBereichId() == VorabcheckTestDaoConfig.bereichId) {
				expectedFachList.add(fach);
			}
		}

		List<Fach> fachList = fachDao.findAllByBereichId(VorabcheckTestDaoConfig.bereichId);

		assertEquals(expectedFachList, fachList);
	}

	@Test
	public void findAllTest() {

		List<Fach> expectedFachList = VorabcheckTestDaoConfig.faecher;
		Collections.sort(expectedFachList, new Comparator<Fach>() {

			@Override
			public int compare(Fach f1, Fach f2) {

				return f1.getId().compareTo(f2.getId());
			}
		});

		List<Fach> fachList = fachDao.findAll();

		assertEquals(expectedFachList, fachList);
	}

}
