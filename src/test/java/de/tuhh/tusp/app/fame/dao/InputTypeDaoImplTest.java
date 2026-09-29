package de.tuhh.tusp.app.fame.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class InputTypeDaoImplTest {

	@Autowired
	private InputTypeDao dao;

	@Test
	public void crudTest() {

		InputType pojo = new InputType();
		pojo.setId(0L);
		pojo.setName("Test-InputType");
		pojo.setUniqName(InputTypeEnum.CONNECTOR_TEXT);
		pojo.setKommentar("");

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(dao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}
}
