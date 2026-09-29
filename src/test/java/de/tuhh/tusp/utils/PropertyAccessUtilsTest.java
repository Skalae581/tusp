package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.tuhh.tusp.global.pojo.Config;

public class PropertyAccessUtilsTest {

	@Test
	public void getProperty_fromMap_Test() {

		Map<String, String> map = new HashMap<>();
		map.put("testProp", "Test Value");

		Object value = PropertyAccessUtils.getProperty(map, "testProp");

		assertEquals("Test Value", value);
	}

	@Test
	public void getProperty_fromNull_Test() {

		PropertyAccessUtils.getProperty(null, "testProp");
	}

	@Test
	public void getProperty_fromObject_Test() {

		Config object = new Config();
		object.setKey("Test Value");

		Object value = PropertyAccessUtils.getProperty(object, "key");

		assertEquals("Test Value", value);
	}

	@Test(expected = RuntimeException.class)
	public void getProperty_withNotExistingPropName_Test() {

		PropertyAccessUtils.getProperty("Some String", "testProp");
	}
}
