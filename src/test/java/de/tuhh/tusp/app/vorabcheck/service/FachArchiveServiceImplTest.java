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

import de.tuhh.tusp.app.vorabcheck.dao.FachArchiveDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;

@RunWith(MockitoJUnitRunner.class)
public class FachArchiveServiceImplTest {

	@InjectMocks
	private FachArchiveServiceImpl injectService;

	@Mock
	private FachArchiveServiceImpl mockService;

	@Mock
	private FachArchiveDaoImpl fachDao;

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
	public void findAllTest() {

		List<FachArchive> expectedList = new ArrayList<>();
		expectedList.add(new FachArchive());
		when(fachDao.findAll()).thenAnswer(i -> expectedList);

		List<FachArchive> actualList = injectService.findAll();
		verify(fachDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		FachArchive fachArchive = new FachArchive();
		injectService.insert(fachArchive);
		verify(fachDao).insert(fachArchive);
	}

	@Test
	public void setArchiveTest() {

		FachArchive fachArchive = new FachArchive();
		Fach fach = new Fach();
		mockService.setArchive(fachArchive, fach, 42L, 10L);
		verify(mockService, times(1)).setArchive(fachArchive, fach, 42L, 10L);
	}

	@Test
	public void updateTest() {

		FachArchive fachArchive = new FachArchive();
		injectService.update(fachArchive);
		verify(fachDao).update(fachArchive);
	}

}
