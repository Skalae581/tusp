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

import de.tuhh.tusp.app.vorabcheck.dao.BereichArchiveDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;

@RunWith(MockitoJUnitRunner.class)
public class BereichArchiveServiceImplTest {

	@InjectMocks
	private BereichArchiveServiceImpl injectService;

	@Mock
	private BereichArchiveServiceImpl mockService;

	@Mock
	private BereichArchiveDaoImpl bereichDao;

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
	public void findAllTest() {

		List<BereichArchive> expectedList = new ArrayList<>();
		expectedList.add(new BereichArchive());
		when(bereichDao.findAll()).thenAnswer(i -> expectedList);

		List<BereichArchive> actualList = injectService.findAll();
		verify(bereichDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		BereichArchive bereichArchive = new BereichArchive();
		injectService.insert(bereichArchive);
		verify(bereichDao).insert(bereichArchive);
	}

	@Test
	public void setArchiveTest() {

		BereichArchive bereichArchive = new BereichArchive();
		Bereich bereich = new Bereich();
		mockService.setArchive(bereichArchive, bereich, 42L, 10L);
		verify(mockService, times(1)).setArchive(bereichArchive, bereich, 42L, 10L);
	}

	@Test
	public void updateTest() {

		BereichArchive bereichArchive = new BereichArchive();
		injectService.update(bereichArchive);
		verify(bereichDao).update(bereichArchive);
	}

}
