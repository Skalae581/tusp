package de.tuhh.tusp.app.vorabcheck.dao;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BewerberDaoImplTest {

	@Autowired
	private BewerberDao bewerberDao;

	@Mock
	private DSLContext context;

	@Test
	@Ignore
	public void crudTest() {

		Bewerber bew = new Bewerber();
		bew.setId(0L);
		bew.setCodenummer("12345");
		bew.setStgId(VorabcheckTestDaoConfig.stgId);
		bew.setAnmerk("test");
		bew.setStgName(VorabcheckTestDaoConfig.cosShorttextDe);
		bew.setZusatzAusstehend("");
		bew.setZusatzErbracht("");
		bew.setSemester("MD-20191");
		bew.setVorabcheckZeitstempel(new Timestamp(System.currentTimeMillis()));

		String[] ignoreGettersNoDB = { "getExtra", "getExtratitel", "getEcts", "getUnittotal", "getUnittype",
				"getUnitduration", "getErgebnis", "setVorabcheckZeitstempel" };
		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(bew, ignoreGettersNoDB);

		String[] ignoreGetters = { "getId", "getStgName", "getStgId", "getExtra", "getExtratitel", "getEcts",
				"getUnittotal", "getUnittype", "getUnitduration", "getErgebnis" };

		testUtils.simpleCrudTestForDao(	bewerberDao, bew, ignoreGetters,
										new Change("setAnmerk", "Anmerkung wurde geändert"));
	}

	@Test
	public void fetchAllCodenummerTest() {

		List<String> expectedCodeArray = new ArrayList<>();
		expectedCodeArray = VorabcheckTestDaoConfig.codeArray;

		List<String> codeArray = bewerberDao.fetchAllCodenummer();

		assertEquals(expectedCodeArray, codeArray);
	}

	@Test
	@Ignore
	public void fetchByCodeTest() {

		Bewerber expectedBew = VorabcheckTestDaoConfig.bewerber;
		// Folgende Attribute kommen nicht in der DB vor; nur für PDF
		expectedBew.setStgName(null);
		expectedBew.setExtra(null);
		expectedBew.setExtratitel(null);
		expectedBew.setEcts(null);
		expectedBew.setErgebnis(null);
		expectedBew.setUnitduration(null);
		expectedBew.setUnittotal(null);
		expectedBew.setUnittype(null);

		Bewerber bew = bewerberDao.fetchByCode(VorabcheckTestDaoConfig.codeArray.get(0));

		assertEquals(expectedBew, bew);

	}

	// @Test
	// public void findStgNameByCodeTest() {
	//
	// Object expectedShorttext = VorabcheckTestDaoConfig.master_studiengaenge.get(1).getShorttextDe();
	// String bewHash = VorabcheckTestDaoConfig.bewerber.getCodenummer();
	// Object shorttext = bewerberDao.findStgNameByCode(bewHash);
	//
	// assertThat(expectedShorttext, instanceOf(String.class));
	// assertEquals(expectedShorttext, shorttext);
	// }
}
