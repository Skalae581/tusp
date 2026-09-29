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

import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.Degree;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class DegreeServiceImplTest {

	@InjectMocks
	private DegreeServiceImpl service;

	@Mock
	private DegreeDao dao;

	@Mock
	private WebserviceFactory factory;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllTest() {

		List<Degree> degrees = new ArrayList<>();
		when(dao.findAll()).thenAnswer(i -> degrees);
		assertEquals(degrees, service.findAll());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

}
