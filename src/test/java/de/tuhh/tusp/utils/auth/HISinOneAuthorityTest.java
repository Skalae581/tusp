package de.tuhh.tusp.utils.auth;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.his.ws.rightsandrolesservice.RoleParameter;
import de.his.ws.rightsandrolesservice.RoleparametersType;
import de.tuhh.tusp.utils.TestUtils;

@RunWith(JUnit4.class)
public class HISinOneAuthorityTest {

	private HISinOneAuthority authority;
	private RoleparametersType params;

	@Before
	public void before() {

		RoleParameter param = new RoleParameter();
		param.setId(3L);
		param.setParametervalue("Darf nix");
		param.setRoleId(5L);

		params = new RoleparametersType();
		params.getRoleParameter().add(param);

		authority = new HISinOneAuthority(5L, "Tester", params);
	}

	@Test
	public void equalsTest() {

		assertFalse(authority.equals(null));
		assertFalse(authority.equals(""));
		assertFalse(authority.equals(new LocalAuthority()));
		assertTrue(authority.equals(authority));
		assertTrue(authority.equals(new HISinOneAuthority(5L, "Tester", params)));
		assertFalse(authority.equals(new HISinOneAuthority(7L, "Tester", params)));
	}

	@Test
	public void getAtomarAuthorities_mitParametern() {

		// Test mit Parametern
		params.getRoleParameter().get(0).setParametervalue("a,b,c");
		authority = new HISinOneAuthority(8L, "Test", params);
		List<String> result = authority.getAtomarAuthorities();
		assertEquals("ROLE_TEST(a)", result.get(0));
		assertEquals("ROLE_TEST(b)", result.get(1));
		assertEquals("ROLE_TEST(c)", result.get(2));
	}

	@Test
	public void getAtomarAuthorities_ohneParameter() {

		// Test ohne Parameter
		params.getRoleParameter().clear();
		authority = new HISinOneAuthority(8L, "Test", params);
		List<String> result = authority.getAtomarAuthorities();
		assertEquals("ROLE_TEST", result.get(0));
	}

	@Test
	public void getAuthorityTest() {

		assertEquals("ROLE_TESTER", authority.getAuthority());
	}

	@Test
	public void innerPojoTest() throws Exception {

		Class<?> clazz = de.tuhh.tusp.utils.auth.HISinOneAuthority.RoleParameter.class;

		TestUtils utils = new TestUtils();
		utils.toStringTest(clazz);
		utils.hashCodeTest(clazz);
		utils.touchGetterAndSetterTest(clazz);
		utils.equalsTest(clazz);
	}

	@Test
	public void toStringTest() {

		assertEquals("ROLE_TESTER(Darf nix)", authority.toString());
	}
}
