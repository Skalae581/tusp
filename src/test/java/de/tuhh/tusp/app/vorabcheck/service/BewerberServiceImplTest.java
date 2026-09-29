package de.tuhh.tusp.app.vorabcheck.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.vorabcheck.dao.BewerberDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;

@RunWith(MockitoJUnitRunner.class)
public class BewerberServiceImplTest {

	@InjectMocks
	private BewerberServiceImpl injectService;

	@Mock
	private BewerberDaoImpl bewDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(bewDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(bewDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllTest() {

		List<Bewerber> expectedList = new ArrayList<>();
		expectedList.add(new Bewerber());
		when(bewDao.findAll()).thenAnswer(i -> expectedList);

		List<Bewerber> actualList = injectService.findAll();
		verify(bewDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		Bewerber bewerber = new Bewerber();
		injectService.insert(bewerber);
		verify(bewDao).insert(bewerber);
	}

	@Test
	public void updateTest() {

		Bewerber bewerber = new Bewerber();
		injectService.update(bewerber);
		verify(bewDao).update(bewerber);
	}

}
