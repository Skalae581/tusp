package de.tuhh.tusp.app.fame.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;

/**
 * Tests für StatusServiceImpl
 *
 * @author rztjm
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusBerechtigungServiceImplTest {

	@Mock
	private StatusBerechtigungDao dao;

	@InjectMocks
	private StatusBerechtigungServiceImpl service;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void deleteTest() {

		service.delete(3L);
		verify(dao, times(1)).delete(3L);
	}

	@Test
	public void fetchByIdTest() {

		service.fetchById(19L);
		verify(dao).fetchById(19L);
	}

	@Test
	public void findAllTest() {

		service.findAll();
		verify(dao).findAll();
	}

	@Test
	public void findByWorkflowTest() {

		service.findByWorkflow(17L);
		verify(dao).findByWorkflow(17L);
	}

	@Test
	public void insertTest() {

		StatusBerechtigung statusBerechtigung = new StatusBerechtigung();
		service.insert(statusBerechtigung);
		verify(dao).insert(statusBerechtigung);
	}

	@Test
	public void updateTest() {

		StatusBerechtigung statusBerechtigung = new StatusBerechtigung();
		service.update(statusBerechtigung);
		verify(dao).update(statusBerechtigung);
	}
}
