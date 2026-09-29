package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.service.InputService.OptionsType;

public class InputServiceImplTest {

	InputServiceImpl inputService = new InputServiceImpl();

	@Test
	public void parseOptionsTest() {

		List<OptionsType> list;

		Input input = new Input();
		assertNull(inputService.parseOptions(input));

		input.setOptions("");
		assertNull(inputService.parseOptions(input));

		input.setOptions("A;B");
		list = inputService.parseOptions(input);
		assertTrue("A".equals(list.get(0).getId()));
		assertTrue("B".equals(list.get(0).getName()));
		assertFalse(list.get(0).isDisabled());

		input.setOptions("A;B;disabled");
		list = inputService.parseOptions(input);
		assertTrue("A".equals(list.get(0).getId()));
		assertTrue("B".equals(list.get(0).getName()));
		assertTrue(list.get(0).isDisabled());

		input.setOptions("A;B\nC;D\nE;F;G");
		list = inputService.parseOptions(input);
		assertTrue("E".equals(list.get(2).getId()));
		assertTrue("FG".equals(list.get(2).getName()));
		assertFalse(list.get(2).isDisabled());
	}
}
