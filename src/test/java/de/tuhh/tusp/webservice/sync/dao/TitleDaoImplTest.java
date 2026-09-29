package de.tuhh.tusp.webservice.sync.dao;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
import de.tuhh.tusp.webservice.sync.pojo.Title;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, WebserviceWebConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TitleDaoImplTest {

	@Autowired
	TitleDao degreeDao;

	@Test
	public void crudTest() throws ParseException {

		Title pojo = new Title();

		pojo.setId(1L);
		pojo.setDefaulttextDe("Test Title");
		pojo.setDefaulttextEn("Test Title");
		pojo.setLongtextDe("Test Title");
		pojo.setLongtextEn("Test Title");
		pojo.setShorttextDe("Test Title");
		pojo.setShorttextEn("Test Title");
		pojo.setLettersalutationDe("setLettersalutationDe");
		pojo.setLettersalutationEn("setLettersalutationEn");
		pojo.setDefaulttextFemaleDe("Test Title");
		pojo.setDefaulttextFemaleEn("Test Title");
		pojo.setLongtextFemaleDe("Test Title");
		pojo.setLongtextFemaleEn("Test Title");
		pojo.setShorttextFemaleDe("Test Title");
		pojo.setShorttextFemaleEn("Test Title");
		pojo.setLettersalutationFemaleDe("setLettersalutationDe");
		pojo.setLettersalutationFemaleEn("setLettersalutationEn");
		pojo.setValid(true);
		pojo.setSortorder("1");
		pojo.setUniquename("unique");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(	degreeDao, pojo, ignoreGetters,
										new Change("setDefaulttextDe", "Name wurde geändert"));

	}
}
