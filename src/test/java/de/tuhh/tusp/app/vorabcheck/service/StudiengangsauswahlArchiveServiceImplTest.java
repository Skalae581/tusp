package de.tuhh.tusp.app.vorabcheck.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlArchiveDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;

@RunWith(MockitoJUnitRunner.class)
public class StudiengangsauswahlArchiveServiceImplTest {

	@InjectMocks
	private StudiengangsauswahlArchiveServiceImpl injectService;

	@Mock
	private StudiengangsauswahlArchiveServiceImpl mockService;

	@Mock
	private StudiengangsauswahlArchiveDaoImpl stgDao;

	@Test
	public void archiveTest() {

		mockService.archive(42L, "grund");
		verify(mockService, times(1)).archive(42L, "grund");
	}

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
	public void findAllTest() {

		injectService.findAll();
		verify(stgDao, times(1)).findAll();
	}

	@Test
	public void insertTest() {

		StudiengangsauswahlArchive stgArchive = new StudiengangsauswahlArchive();
		injectService.insert(stgArchive);
		verify(stgDao).insert(stgArchive);
	}

	@Test
	public void setArchiveTest() {

		StudiengangsauswahlArchive stgArchive = new StudiengangsauswahlArchive();
		Studiengangsauswahl stg = new Studiengangsauswahl();
		mockService.setArchive(stgArchive, stg, "bearbeiter", "grund");
		verify(mockService, times(1)).setArchive(stgArchive, stg, "bearbeiter", "grund");
	}

	@Test
	public void updateTest() {

		StudiengangsauswahlArchive stgArch = new StudiengangsauswahlArchive();
		injectService.update(stgArch);
		verify(stgDao).update(stgArch);
	}
}
