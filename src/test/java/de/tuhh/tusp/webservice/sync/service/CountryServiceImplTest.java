package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTest {

	@InjectMocks
	private CountryServiceImpl service;

	@Mock
	private CountryDao dao;

	@Mock
	private WebserviceFactory factory;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllAscendingTest() {

		List<Country> countries = new ArrayList<>();
		when(dao.findAllAscending()).thenAnswer(i -> countries);
		assertEquals(countries, service.findAllAscending());
		verify(dao, times(1)).findAllAscending();
	}

	@Test
	public void findAllTest() {

		List<Country> countries = new ArrayList<>();
		when(dao.findAll()).thenAnswer(i -> countries);
		assertEquals(countries, service.findAll());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}
}
