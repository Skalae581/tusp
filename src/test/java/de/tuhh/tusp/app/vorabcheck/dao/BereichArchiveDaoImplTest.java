package de.tuhh.tusp.app.vorabcheck.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BereichArchiveDaoImplTest {

	@Autowired
	private BereichArchiveDao berArchDao;

	// TODO
	@Test
	public void crudTest() {

		BereichArchive bereichArch = new BereichArchive();
		bereichArch.setId(0L);
		bereichArch.setName("");
		bereichArch.setPunkte(Integer.valueOf(0));
		bereichArch.setOriginId(VorabcheckTestDaoConfig.stgId);
		bereichArch.setStgId(VorabcheckTestDaoConfig.archivStudiengaenge.get(0).getId());
		bereichArch.setZusatz("");
		bereichArch.setSessionId(0L);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(bereichArch);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	berArchDao, bereichArch, ignoreGetters,
										new Change("setName", "Name wurde geändert"));

	}
}
