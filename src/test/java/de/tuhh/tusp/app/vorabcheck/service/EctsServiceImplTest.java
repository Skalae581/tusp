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

import de.tuhh.tusp.app.vorabcheck.dao.EctsDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;

@RunWith(MockitoJUnitRunner.class)
public class EctsServiceImplTest {

	@InjectMocks
	private EctsServiceImpl injectService;

	@Mock
	private EctsDaoImpl ectsDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(ectsDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByBewId() {

		injectService.fetchByBewId(21356789L);
		verify(ectsDao, times(1)).fetchByBewId(21356789L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(ectsDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllTest() {

		List<Ects> expectedList = new ArrayList<>();
		expectedList.add(new Ects());
		when(ectsDao.findAll()).thenAnswer(i -> expectedList);

		List<Ects> actualList = injectService.findAll();
		verify(ectsDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		Ects ects = new Ects();
		injectService.insert(ects);
		verify(ectsDao).insert(ects);
	}

	@Test
	public void updateTest() {

		Ects ects = new Ects();
		injectService.update(ects);
		verify(ectsDao).update(ects);
	}

}
