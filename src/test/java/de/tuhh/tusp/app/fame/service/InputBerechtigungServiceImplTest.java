package de.tuhh.tusp.app.fame.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;

/**
 * Tests für InputServiceImpl
 *
 * @author rztjm
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InputBerechtigungServiceImplTest {

	@Mock
	private InputBerechtigungDao dao;

	@InjectMocks
	private InputBerechtigungServiceImpl service;

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
	public void findByWorkflowTest() {

		service.findByWorkflow(17L);
		verify(dao).findByWorkflow(17L);
	}

	@Test
	public void insertTest() {

		InputBerechtigung inputBerechtigung = new InputBerechtigung();
		service.insert(inputBerechtigung);
		verify(dao).insert(inputBerechtigung);
	}

	@Test
	public void updateTest() {

		InputBerechtigung inputBerechtigung = new InputBerechtigung();
		service.update(inputBerechtigung);
		verify(dao).update(inputBerechtigung);
	}
}
