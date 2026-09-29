package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class StudiengangsauswahlDaoImplTest {

	@Autowired
	private StudiengangsauswahlDao stgDao;

	@Test
	public void crudTest() {

		Studiengangsauswahl stg = new Studiengangsauswahl();
		stg.setId(0L);
		stg.setAktivVorab(0);
		stg.setAktivVervollst(0);
		stg.setAos(false);
		stg.setName("Test");
		stg.setStandardkz("");
		stg.setProzentsatz(75);
		stg.setShorttextDe("");
		stg.setExtrakz("");
		stg.setExtratitel("");
		stg.setCourseofstudyId(VorabcheckTestDaoConfig.cosId);

		TestUtils testUtils = new TestUtils();

		String[] ignoreGetters = { "getId", "getCourseofstudyId", "getName", "getShorttextDe", "getDe" };

		testUtils.simpleCrudTestForDao(stgDao, stg, ignoreGetters,
				new Change("setStandardkz", "Standardkz wurde geändert"));

	}

	@Test
	public void findAllActiveVervollstWithoutStandardkzTest() {

		List<Studiengangsauswahl> tempStgList = VorabcheckTestDaoConfig.master_studiengaenge;
		List<Map<String, Object>> expectedStgList = new ArrayList<>();

		for (int i = 0; i < tempStgList.size(); i++) {
			Map<String, Object> tmp = new HashMap<>();

			if (tempStgList.get(i).getAktivVervollst() == 1 && tempStgList.get(i).getStandardkz() == null) {
				tmp.put("id", tempStgList.get(i).getId());
				tmp.put("aktiv_vervollst", tempStgList.get(i).getAktivVervollst());
				tmp.put("shorttext_de", tempStgList.get(i).getShorttextDe());
				expectedStgList.add(tmp);
			}
		}

		List<Map<String, Object>> stgList = stgDao.findAllActiveVervollstWithoutStandardkz();
		assertEquals(expectedStgList, stgList);

		List<Integer> listActive = new ArrayList<>();
		for (int i = 0; i < expectedStgList.size(); i++) {
			Integer active = (Integer) expectedStgList.get(i).get("aktiv");
			listActive.add(active);
		}

		List<String> listStandardkz = new ArrayList<>();
		for (int i = 0; i < expectedStgList.size(); i++) {
			String standardNull = (String) expectedStgList.get(i).get("standardkz");
			listStandardkz.add(standardNull);
		}

		assertFalse(listActive.contains(0));
		assertFalse(listStandardkz.contains("N"));
	}

	@Test
	public void findAllBereichByIdTest() {

		List<Bereich> tempBereichList = VorabcheckTestDaoConfig.bereiche;
		List<Map<String, Object>> expectedBereichList = new ArrayList<>();

		for (Bereich bereich : tempBereichList) {
			Map<String, Object> tmp = new HashMap<>();
			tmp.put("id", bereich.getId());
			tmp.put("stg_id", bereich.getStgId());
			tmp.put("name", bereich.getName());
			tmp.put("punkte", bereich.getPunkte());
			tmp.put("zusatz", bereich.getZusatz());
			expectedBereichList.add(tmp);
		}

		List<Map<String, Object>> bereichList = stgDao.findAllBereichById(VorabcheckTestDaoConfig.stgId);

		assertEquals(expectedBereichList, bereichList);
	}

	@Test
	public void findAllFachByIdsTest() {

		List<Fach> tempFachList = VorabcheckTestDaoConfig.faecher;
		List<Map<String, Object>> expectedFachList = new ArrayList<>();

		for (int i = 0; i < tempFachList.size(); i++) {
			Map<String, Object> tmp = new HashMap<>();
			tmp.put("id", tempFachList.get(i).getId());
			tmp.put("bereich_id", tempFachList.get(i).getBereichId());
			tmp.put("name", tempFachList.get(i).getName());
			tmp.put("punkte", tempFachList.get(i).getPunkte());
			tmp.put("zusatz", tempFachList.get(i).getZusatz());
			expectedFachList.add(tmp);
		}

		List<Long> bereichIdList = VorabcheckTestDaoConfig.bereichIdArray;
		List<Map<String, Object>> fachList = stgDao.findAllFachByIds(bereichIdList);

		assertEquals(expectedFachList, fachList);
	}

	@Test
	public void findAllStgAOSTest() {

		List<Map<String, Object>> expectedStgAOSlist = new ArrayList<>();
		List<Studiengangsauswahl> stgs = VorabcheckTestDaoConfig.master_studiengaenge;
		Collections.sort(stgs, new Comparator<Studiengangsauswahl>() {
			@Override
			public int compare(Studiengangsauswahl s1, Studiengangsauswahl s2) {
				return s1.getShorttextDe().compareTo(s2.getShorttextDe());
			}
		});

		for (int i = 0; i < stgs.size(); i++) {
			if (stgs.get(i).getAos() == true) {
				Map<String, Object> s = new HashMap<>();
				s.put("shorttext_de", stgs.get(i).getShorttextDe());
				s.put("id", stgs.get(i).getId());
				s.put("courseofstudy_id", stgs.get(i).getCourseofstudyId());
				s.put("standardkz", stgs.get(i).getStandardkz());
				s.put("extrakz", stgs.get(i).getExtrakz());
				s.put("extratitel", stgs.get(i).getExtratitel());
				s.put("aktiv_vorab", stgs.get(i).getAktivVorab());
				s.put("aktiv_vervollst", stgs.get(i).getAktivVervollst());
				s.put("prozentsatz", stgs.get(i).getProzentsatz());
				s.put("aos", stgs.get(i).getAos());
				expectedStgAOSlist.add(s);
			}
		}

		List<Map<String, Object>> stgAOSlist = stgDao.findAllStgAOS();

		assertEquals(expectedStgAOSlist, stgAOSlist);
	}

	@Test
	public void findAllStgMDTest() {

		List<Map<String, Object>> expectedStgMDlist = new ArrayList<>();
		List<Studiengangsauswahl> stgs = VorabcheckTestDaoConfig.master_studiengaenge;
		Collections.sort(stgs, new Comparator<Studiengangsauswahl>() {
			@Override
			public int compare(Studiengangsauswahl s1, Studiengangsauswahl s2) {
				return s1.getShorttextDe().compareTo(s2.getShorttextDe());
			}
		});

		for (int i = 0; i < stgs.size(); i++) {
			if (stgs.get(i).getAos() == false) {
				Map<String, Object> s = new HashMap<>();
				s.put("shorttext_de", stgs.get(i).getShorttextDe());
				s.put("id", stgs.get(i).getId());
				s.put("courseofstudy_id", stgs.get(i).getCourseofstudyId());
				s.put("standardkz", stgs.get(i).getStandardkz());
				s.put("extrakz", stgs.get(i).getExtrakz());
				s.put("extratitel", stgs.get(i).getExtratitel());
				s.put("aktiv_vorab", stgs.get(i).getAktivVorab());
				s.put("aktiv_vervollst", stgs.get(i).getAktivVervollst());
				s.put("prozentsatz", stgs.get(i).getProzentsatz());
				s.put("aos", stgs.get(i).getAos());
				expectedStgMDlist.add(s);
			}
		}

		List<Map<String, Object>> stgMDlist = stgDao.findAllStgMD();

		assertEquals(expectedStgMDlist, stgMDlist);
	}

	@Test
	public void findAllStgTest() {

		List<Studiengangsauswahl> list = VorabcheckTestDaoConfig.master_studiengaenge;
		List<Studiengangsauswahl> tempList = new ArrayList<>();
		tempList.addAll(list);
		List<Map<String, Object>> expectedStgList = new ArrayList<>();

		Collections.sort(tempList, new Comparator<Studiengangsauswahl>() {
			@Override
			public int compare(Studiengangsauswahl s1, Studiengangsauswahl s2) {
				return s1.getShorttextDe().compareTo(s2.getShorttextDe());
			}
		});

		for (Studiengangsauswahl stg : tempList) {
			Map<String, Object> tmp = new HashMap<>();
			tmp.put("shorttext_de", stg.getShorttextDe());
			tmp.put("id", stg.getId());
			tmp.put("courseofstudy_id", stg.getCourseofstudyId());
			tmp.put("aktiv_vorab", stg.getAktivVorab());
			tmp.put("aktiv_vervollst", stg.getAktivVervollst());
			tmp.put("aos", stg.getAos());
			tmp.put("prozentsatz", stg.getProzentsatz());
			tmp.put("standardkz", stg.getStandardkz());
			tmp.put("extrakz", stg.getExtrakz());
			tmp.put("extratitel", stg.getExtratitel());

			expectedStgList.add(tmp);
		}

		List<Map<String, Object>> stgList = stgDao.findAllStg();

		assertEquals(expectedStgList, stgList);
	}

	@Test
	public void findBereichStgIdTest() {

		List<Bereich> bereichList = VorabcheckTestDaoConfig.bereiche;

		List<Map<String, Object>> expectedStgIdList = new ArrayList<>();

		for (Bereich bereich : bereichList) {
			Map<String, Object> tmp = new HashMap<>();
			tmp.put("stg_id", bereich.getStgId());

			expectedStgIdList.add(tmp);
		}

		List<Map<String, Object>> stgIdList = stgDao.findBereichStgId();

		assertEquals(expectedStgIdList, stgIdList);
	}
}
