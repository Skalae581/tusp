package de.tuhh.tusp.stu.gdm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.stu.gdm.pojo.ProcessedFile;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, ProcessedFileDaoImpl.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessedFileDaoImplTest {

	@Autowired
	private ProcessedFileDaoImpl dao;

	@Before
	public void before() {

		ProcessedFile file = new ProcessedFile();
		file.setInName("Test1");
		dao.insert(file);

		file.setInName("Test2");
		file.setOutName("Out");
		dao.insert(file);

	}

	@Test
	@Ignore
	public void crudTest() throws ParseException {

		ProcessedFile pojo = new ProcessedFile();
		pojo.setId(0L);
		pojo.setHash("asdf");
		pojo.setInName("inName");
		pojo.setOutName("outName");
		pojo.setVerarbeitet(CalendarUtils.stringToDate("2018-03-07"));

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setInName", "Name wurde geändert"));

	}

	@Test
	@Ignore
	public void fetchByInName_withExistingName() {

		ProcessedFile file = dao.fetchByInName("Test2");
		assertEquals("Out", file.getOutName());
	}

	@Test
	@Ignore
	public void fetchByInName_withNotExistingName() {

		assertNull(dao.fetchByInName("Not existing"));
	}

}
