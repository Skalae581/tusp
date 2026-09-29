package de.tuhh.tusp.stu.gdm.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, HistoryDaoImpl.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryDaoImplTest {

	@Autowired
	private HistoryDaoImpl historyDao;

	private History history1 = TestUtils.manufacturePojo(History.class);
	private History history2 = TestUtils.manufacturePojo(History.class);
	private History history3 = TestUtils.manufacturePojo(History.class);

	@Before
	public void before() {

		history1.setFilename("TestFile");
		history2.setFilename("TestFile");

		history1.setId(historyDao.insert(history1));
		history2.setId(historyDao.insert(history2));
		history3.setId(historyDao.insert(history3));
	}

	@Test
	public void crudTest() {

		History pojo = new History();
		pojo.setId(0L);
		pojo.setUsername("username");
		pojo.setFilename("filename");
		pojo.setZeit(new Date());

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	historyDao, pojo, ignoreGetters,
										new Change("setUserName", "Name wurde geändert"));

	}

	@Test
	public void findByFilename_withExistingName() {

		List<History> history = historyDao.findByFilename("TestFile");

		assertTrue(history.contains(history1));
		assertTrue(history.contains(history2));
	}

	@Test
	public void findByFilename_withNotExistingName() {

		List<History> history = historyDao.findByFilename("asdfg");

		assertTrue(history.isEmpty());
	}

}
