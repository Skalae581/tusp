package de.tuhh.tusp.app.fame.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileDaoImplTest {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private ValueDao valueDao;

	@Test
	public void crudTest() {

		File pojo = new File();
		pojo.setId(0L);
		pojo.setValueId(FameTestDaoConfig.valueId);
		pojo.setMimeType("mimetype");
		pojo.setContent("file content".getBytes());

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId", "getContent" };
		testUtils.simpleCrudTestForDao(fileDao, pojo, ignoreGetters, new Change("setMimeType", "Name wurde geändert"));

	}

	@Test
	public void extendedFileEqualsTest() throws Exception {

		ExtendedFile file = TestUtils.manufacturePojo(ExtendedFile.class);
		File equal = TestUtils.manufacturePojo(File.class);

		assertFalse("Die Dateien sind noch ungleich!", file.equals(equal));

		equal.setContent(file.getContent());
		equal.setId(file.getId());
		equal.setMimeType(file.getMimeType());
		equal.setValueId(file.getValueId());

		assertTrue("Die Files müssen glecih sein!", file.equals(equal));
	}

	@Test
	public void fetchByValueIdTest() {

		Value v = TestUtils.manufacturePojo(Value.class);
		v.setVorgangId(FameTestDaoConfig.vorgangId);
		v.setInputId(FameTestDaoConfig.inputId);
		v.setId(valueDao.insert(v));

		File f = TestUtils.manufacturePojo(File.class);
		f.setValueId(v.getId());
		f.setId(fileDao.insert(f));

		ExtendedFile file = fileDao.fetchByValueId(v.getId());

		assertEquals(v.getEingabe(), file.getFilename());
		assertEquals(f.getMimeType(), file.getMimeType());
	}

	@Test
	public void persistenceTest() {

		String mime = "test/mime";
		byte[] content = { -1, 13, 65, -15 };

		File file = TestUtils.manufacturePojo(File.class);
		file.setValueId(FameTestDaoConfig.valueId);
		file.setMimeType(mime);
		file.setContent(content);

		Long id = fileDao.insert(file);
		file = fileDao.fetchById(id);

		assertEquals(mime, file.getMimeType());
		assertEquals(content.length, file.getContent().length);
		for (int i = 0; i < content.length; i++) {
			assertEquals(content[i], file.getContent()[i]);
		}
	}

	@Test
	public void updateTestMitAenderung() {

		String mime = "test/mime";
		byte[] content = { -1, 13, 65, -15 };

		File file = TestUtils.manufacturePojo(File.class);
		file.setValueId(FameTestDaoConfig.valueId);
		file.setMimeType(mime);
		file.setContent(content);

		file.setId(fileDao.insert(file));

		content[2] = 75;
		fileDao.update(file);

		file = fileDao.fetchById(file.getId());

		assertEquals(75, file.getContent()[2]);
	}

	@Test
	public void updateTestMitNull() {

		// Soll keinen Fehler werfen
		fileDao.update(null);
	}

	@Test
	public void updateTestOhneAenderung() {

		String mime = "test/mime";
		byte[] content = { -1, 13, 65, -15 };

		File file = TestUtils.manufacturePojo(File.class);
		file.setValueId(FameTestDaoConfig.valueId);
		file.setMimeType(mime);
		file.setContent(content);

		file.setId(fileDao.insert(file));
		// Kann leider nicht sehen ob was passiert ist
	}

	@Test
	public void updateTestOhneAltenDatensatz() {

		String mime = "test/mime";
		byte[] content = { -1, 13, 65, -15 };

		File file = TestUtils.manufacturePojo(File.class);
		file.setValueId(FameTestDaoConfig.valueId);
		file.setMimeType(mime);
		file.setContent(content);

		fileDao.update(file);
		// Soll keinen Fehler werfen
	}
}
