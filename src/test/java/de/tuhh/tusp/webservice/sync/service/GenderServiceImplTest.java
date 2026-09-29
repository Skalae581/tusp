package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.webservice.sync.dao.GenderDao;
import de.tuhh.tusp.webservice.sync.pojo.Gender;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class GenderServiceImplTest {

	@InjectMocks
	private GenderServiceImpl service;

	@Mock
	private GenderDao dao;

	@Mock
	private WebserviceFactory factory;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchByString_d() throws Exception {

		service.fetchByString("d");
		verify(dao).fetchByUniqename("D");
	}

	@Test
	public void fetchByString_D() throws Exception {

		service.fetchByString("D");
		verify(dao).fetchByUniqename("D");
	}

	@Test
	public void fetchByString_f() throws Exception {

		service.fetchByString("f");
		verify(dao).fetchByUniqename("W");
	}

	@Test
	public void fetchByString_F() throws Exception {

		service.fetchByString("F");
		verify(dao).fetchByUniqename("W");
	}

	@Test
	public void fetchByString_m() throws Exception {

		service.fetchByString("m");
		verify(dao).fetchByUniqename("M");
	}

	@Test
	public void fetchByString_M() throws Exception {

		service.fetchByString("M");
		verify(dao).fetchByUniqename("M");
	}

	@Test
	public void fetchByString_null() throws Exception {

		assertNull(service.fetchByString(null));
	}

	@Test(expected = NoSuchElementException.class)
	public void fetchByString_nunExistent() throws Exception {

		service.fetchByString("alien");
	}

	@Test
	public void fetchByString_u() throws Exception {

		service.fetchByString("u");
		verify(dao).fetchByUniqename("U");
	}

	@Test
	public void fetchByString_U() throws Exception {

		service.fetchByString("U");
		verify(dao).fetchByUniqename("U");
	}

	@Test
	public void fetchByString_w() throws Exception {

		service.fetchByString("w");
		verify(dao).fetchByUniqename("W");
	}

	@Test
	public void fetchByString_W() throws Exception {

		service.fetchByString("W");
		verify(dao).fetchByUniqename("W");
	}

	@Test
	public void findAllTest() {

		List<Gender> genders = new ArrayList<>();
		when(dao.findAll()).thenAnswer(i -> genders);
		assertEquals(genders, service.findAll());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

}
