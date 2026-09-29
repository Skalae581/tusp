package de.tuhh.tusp.app.fame.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.utils.ConnectorUtils;

/**
 * Tests für WorkflowInstanceServiceImpl
 *
 * @author rztjm
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkflowInstanceServiceImplTest {

	@Mock
	private WorkflowInstanceDao dao;

	@Mock
	private ConnectorUtils connectorUtils;

	@InjectMocks
	private WorkflowInstanceServiceImpl service;

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

		WorkflowInstance instance = new WorkflowInstance();
		service.insert(instance);
		verify(dao).insert(instance);
	}

	@Test
	public void updateTest() {

		WorkflowInstance instance = new WorkflowInstance();
		service.update(instance);
		verify(dao).update(instance);
	}
}
