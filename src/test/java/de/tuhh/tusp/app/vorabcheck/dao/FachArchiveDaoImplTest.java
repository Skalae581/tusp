package de.tuhh.tusp.app.vorabcheck.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FachArchiveDaoImplTest {

	@Autowired
	private FachArchiveDao fachArchDao;

	@Test
	public void crudTest() {

		FachArchive fachArch = new FachArchive();
		fachArch.setId(0L);
		fachArch.setName("");
		fachArch.setPunkte(Integer.valueOf(0));
		fachArch.setBereichId(VorabcheckTestDaoConfig.archivBereiche.get(0).getId());
		fachArch.setZusatz("");
		fachArch.setSessionId(0L);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(fachArch);

		String[] ignoreGetters = { "getId", "getBereichId" };
		testUtils.simpleCrudTestForDao(fachArchDao, fachArch, ignoreGetters,
				new Change("setName", "Name wurde geändert"));
	}
}
