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

import de.tuhh.tusp.app.vorabcheck.dao.BereichDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;

@RunWith(MockitoJUnitRunner.class)
public class BereichServiceImplTest {

	@InjectMocks
	private BereichServiceImpl injectService;

	@Mock
	private BereichDaoImpl bereichDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(bereichDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(bereichDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllByStgIdTest() {

		injectService.findAllByStgId(22L);
		verify(bereichDao, times(1)).findAllByStgId(22L);
	}

	@Test
	public void findAllTest() {

		List<Bereich> expectedList = new ArrayList<>();
		expectedList.add(new Bereich());
		when(bereichDao.findAll()).thenAnswer(i -> expectedList);

		List<Bereich> actualList = injectService.findAll();
		verify(bereichDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void getBereichIdsByIdTest() {

		List<Long> expectedList = new ArrayList<>();
		expectedList.add(42L);
		when(bereichDao.getBereichIdsById(42L)).thenAnswer(i -> expectedList);

		List<Long> actualList = injectService.getBereichIdsById(42L);
		verify(bereichDao, times(1)).getBereichIdsById(42L);

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		Bereich bereich = new Bereich();
		injectService.insert(bereich);
		verify(bereichDao).insert(bereich);
	}

	@Test
	public void updateTest() {

		Bereich bereich = new Bereich();
		injectService.update(bereich);
		verify(bereichDao).update(bereich);
	}

}
