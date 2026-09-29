package de.tuhh.tusp.app.vorabcheck.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ExtraDaoImplTest {

	@Autowired
	private ExtraDao extraDao;

	@Test
	public void crudTest() {

		Extra extra = new Extra();
		extra.setId(0L);
		extra.setName("");
		extra.setStgId(VorabcheckTestDaoConfig.stgId);
		extra.setTitel("");
		extra.setZusatzAusstehend("");
		extra.setZusatzErbracht("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(extra);

		String[] ignoreGetters = { "getId", "getZusatzAusstehend", "getZusatzErbracht" };
		testUtils.simpleCrudTestForDao(extraDao, extra, ignoreGetters, new Change("setName", "Name wurde geändert"));
	}
}
