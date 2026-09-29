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

import de.tuhh.tusp.app.vorabcheck.dao.FachDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;

@RunWith(MockitoJUnitRunner.class)
public class FachServiceImplTest {

	@InjectMocks
	private FachServiceImpl injectService;

	@Mock
	private FachDaoImpl fachDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(fachDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(fachDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllByBereichId() {

		injectService.findAllByBereichId(22L);
		verify(fachDao, times(1)).findAllByBereichId(22L);
	}

	@Test
	public void findAllByBereichIds() {

		List<Long> bereichIds = new ArrayList<>();
		bereichIds.add(1L);
		bereichIds.add(3L);
		bereichIds.add(5L);

		injectService.findAllByBereichIds(bereichIds);
		verify(fachDao, times(1)).findAllByBereichIds(bereichIds);
	}

	@Test
	public void findAllTest() {

		List<Fach> expectedList = new ArrayList<>();
		expectedList.add(new Fach());
		when(fachDao.findAll()).thenAnswer(i -> expectedList);

		List<Fach> actualList = injectService.findAll();
		verify(fachDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		Fach fach = new Fach();
		injectService.insert(fach);
		verify(fachDao).insert(fach);
	}

	@Test
	public void updateTest() {

		Fach fach = new Fach();
		injectService.update(fach);
		verify(fachDao).update(fach);
	}

}
