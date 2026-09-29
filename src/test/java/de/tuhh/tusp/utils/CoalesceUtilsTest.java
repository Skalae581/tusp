package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoalesceUtilsTest {

	@Test
	public void coalesce_threeParam() throws Exception {

		String s1 = "asdf";

		assertEquals(s1, CoalesceUtils.coalesce(s1, null, null));
		assertEquals(s1, CoalesceUtils.coalesce(null, s1, null));
		assertEquals(s1, CoalesceUtils.coalesce(s1, "wasd", null));
		assertNull(CoalesceUtils.coalesce(null, null, null));
	}

	@Test
	public void coalesce_twoParam() throws Exception {

		String s1 = "asdf";

		assertEquals(s1, CoalesceUtils.coalesce(s1, null));
		assertEquals(s1, CoalesceUtils.coalesce(null, s1));
		assertEquals(s1, CoalesceUtils.coalesce(s1, "wasd"));
		assertNull(CoalesceUtils.coalesce(null, null));
	}

	@Test
	public void coalesce_xParam() throws Exception {

		String s1 = "asdf";

		assertEquals(s1, CoalesceUtils.coalesce(s1, null, null, null));
		assertEquals(s1, CoalesceUtils.coalesce(null, null, s1, null));
		assertEquals(s1, CoalesceUtils.coalesce(null, null, s1, "wasd"));
		assertNull(CoalesceUtils.coalesce());
	}
}
