package de.tuhh.tusp.app.vorabcheck.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;

@RunWith(MockitoJUnitRunner.class)
public class StudiengangsauswahlServiceImplTest {

	@InjectMocks
	private StudiengangsauswahlServiceImpl injectService;

	@Mock
	private StudiengangsauswahlServiceImpl mockService;

	@Mock
	private StudiengangsauswahlDaoImpl stgDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(stgDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(stgDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllBereichByIdTest() {

		injectService.findAllBereichById(42L);
		verify(stgDao, times(1)).findAllBereichById(42L);
	}

	@Test
	public void findAllFachByIdsTest() {

		List<Long> bereichIds = new ArrayList<>();
		bereichIds.add(2L);
		bereichIds.add(3L);
		bereichIds.add(5L);

		injectService.findAllFachByIds(bereichIds);
		verify(stgDao, times(1)).findAllFachByIds(bereichIds);
	}

	@Test
	public void findAllStgTest() {

		injectService.findAllStg();
		verify(stgDao, times(1)).findAllStg();
	}

	@Test
	public void findAllTest() {

		injectService.findAll();
		verify(stgDao, times(1)).findAll();
	}

	@Test
	public void findBereichStgIdTest() {

		injectService.findBereichStgId();
		verify(stgDao, times(1)).findBereichStgId();
	}

	@Test
	public void getColorTest() {

		mockService.getColor(42.0, 40.0, 21L);
		verify(mockService, times(1)).getColor(42.0, 40.0, 21L);
	}

	@Test
	public void insertTest() {

		Studiengangsauswahl stg = new Studiengangsauswahl();
		injectService.insert(stg);
		verify(stgDao).insert(stg);
	}

	@Test
	public void updateTest() {

		Studiengangsauswahl stg = new Studiengangsauswahl();
		injectService.update(stg);
		verify(stgDao).update(stg);
	}

}
