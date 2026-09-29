package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class StudiengangsauswahlArchiveDaoImplTest {

	@Autowired
	private StudiengangsauswahlArchiveDao stgArchDao;

	@Test
	public void crudTest() {

		StudiengangsauswahlArchive stgArch = new StudiengangsauswahlArchive();
		stgArch.setId(0L);
		stgArch.setAktivVorab(0);
		stgArch.setAktivVervollst(0);
		stgArch.setName("Test");
		stgArch.setStandardkz("");
		stgArch.setProzentsatz(75);
		stgArch.setCourseofstudyId(VorabcheckTestDaoConfig.cosId);
		stgArch.setSessionId(0L);
		stgArch.setBearbeiter("Test");
		stgArch.setOriginId(VorabcheckTestDaoConfig.stgId);
		stgArch.setGrund("");
		stgArch.setZeitstempel(CalendarUtils.toLocalDateTime(new Date()));
		stgArch.setExtrakz("0");

		TestUtils testUtils = new TestUtils();

		String[] ignoreGetters = { "getId", "getCourseofstudyId", "getName", "getShorttextDe", "getDe", "getExtrakz" };

		testUtils.simpleCrudTestForDao(	stgArchDao, stgArch, ignoreGetters,
										new Change("setStandardkz", "Standardkz wurde geändert"));
	}

	@Test
	public void fetchByOriginIdTest() {

		List<StudiengangsauswahlArchive> tempStgArch = VorabcheckTestDaoConfig.archivStudiengaenge;
		StudiengangsauswahlArchive expectedStgArch = tempStgArch.get(0);
		expectedStgArch.setName(null);
		Long originId = expectedStgArch.getOriginId();

		StudiengangsauswahlArchive stgArch = stgArchDao.fetchByOriginId(originId);

		assertEquals(expectedStgArch, stgArch);
	}

	@Test
	public void findAllByOriginIdTest() {

		List<StudiengangsauswahlArchive> tempStgArch = VorabcheckTestDaoConfig.archivStudiengaenge;
		List<Map<String, Object>> expectedStgArch = new ArrayList<>();

		for (StudiengangsauswahlArchive stgArchive : tempStgArch) {
			Map<String, Object> tmp = new HashMap<>();

			if (stgArchive.getOriginId().equals(VorabcheckTestDaoConfig.stgId)) {
				tmp.put("id", stgArchive.getId());
				tmp.put("origin_id", stgArchive.getOriginId());
				tmp.put("session_id", stgArchive.getSessionId());
				tmp.put("standardkz", stgArchive.getStandardkz());
				tmp.put("aktiv_vorab", stgArchive.getAktivVorab());
				tmp.put("aktiv_vervollst", stgArchive.getAktivVervollst());
				tmp.put("courseofstudy_id", stgArchive.getCourseofstudyId());
				tmp.put("prozentsatz", stgArchive.getProzentsatz());
				tmp.put("zeitstempel", stgArchive.getZeitstempel());
				tmp.put("bearbeiter", stgArchive.getBearbeiter());
				tmp.put("grund", stgArchive.getGrund());
				tmp.put("extrakz", stgArchive.getExtrakz());
			}
			expectedStgArch.add(tmp);
		}

		List<Map<String, Object>> stgArch = stgArchDao.findAllByOriginId(VorabcheckTestDaoConfig.stgId);

		assertEquals(expectedStgArch, stgArch);
	}

	@Test
	public void findArchiveOriginIdsTest() {

		List<StudiengangsauswahlArchive> tempStgArch = VorabcheckTestDaoConfig.archivStudiengaenge;
		List<Map<String, Object>> expectedStgArch = new ArrayList<>();

		for (StudiengangsauswahlArchive temp : tempStgArch) {
			Map<String, Object> map = new HashMap<>();
			map.put("origin_id", temp.getOriginId());
			expectedStgArch.add(map);
		}

		List<Map<String, Object>> stgArch = stgArchDao.findArchiveOriginIds();

		assertEquals(expectedStgArch, stgArch);
		assertTrue("Enthält origin_id", expectedStgArch.get(0).containsKey("origin_id"));
	}

	@Test
	public void getNewDataTest() {

		List<StudiengangsauswahlArchive> tempStgArch = VorabcheckTestDaoConfig.archivStudiengaenge;
		StudiengangsauswahlArchive expectedStgArch = tempStgArch.get(0);
		Long expectedSessionId = expectedStgArch.getSessionId();

		Long sessionId = expectedStgArch.getSessionId();

		assertEquals(expectedSessionId, sessionId);
	}
}
