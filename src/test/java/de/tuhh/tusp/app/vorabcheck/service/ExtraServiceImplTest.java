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

import de.tuhh.tusp.app.vorabcheck.dao.ExtraDaoImpl;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;

@RunWith(MockitoJUnitRunner.class)
public class ExtraServiceImplTest {

	@InjectMocks
	private ExtraServiceImpl injectService;

	@Mock
	private ExtraDaoImpl extraDao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {

		injectService.delete(21L);
		verify(extraDao, times(1)).delete(21L);
	}

	@Test
	public void fetchByIdTest() {

		injectService.fetchById(21L);
		verify(extraDao, times(1)).fetchById(21L);
	}

	@Test
	public void findAllTest() {

		List<Extra> expectedList = new ArrayList<>();
		expectedList.add(new Extra());
		when(extraDao.findAll()).thenAnswer(i -> expectedList);

		List<Extra> actualList = injectService.findAll();
		verify(extraDao, times(1)).findAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void insertTest() {

		Extra extra = new Extra();
		injectService.insert(extra);
		verify(extraDao).insert(extra);
	}

	@Test
	public void updateTest() {

		Extra extra = new Extra();
		injectService.update(extra);
		verify(extraDao).update(extra);
	}

}
