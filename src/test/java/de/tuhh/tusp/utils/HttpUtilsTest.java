package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class HttpUtilsTest {

	private HttpUtils utils = new HttpUtils();

	@Test
	public void executeGetTest() {

		String daten = utils.executeGet("http://httpbin.org/get?param=test");

		JSONObject obj = new JSONObject(daten);
		JSONObject args = obj.getJSONObject("args");

		assertEquals("test", args.get("param"));
	}

	@Test
	public void executePostTest() {

		String daten = utils.executePost("http://httpbin.org/post", "param2=test2");

		JSONObject obj = new JSONObject(daten);
		JSONObject args = obj.getJSONObject("form");

		assertEquals("test2", args.get("param2"));
	}
}
