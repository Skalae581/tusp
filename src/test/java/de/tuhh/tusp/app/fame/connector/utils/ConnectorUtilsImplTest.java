package de.tuhh.tusp.app.fame.connector.utils;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.connector.test.TestConnectorImpl;
import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.utils.ConnectorUtilsImpl;

@RunWith(MockitoJUnitRunner.class)
public class ConnectorUtilsImplTest {

	@Mock
	private WorkflowDao workflowDao;

	@Mock
	private WorkflowInstanceDao workflowInstanceDao;

	@Mock
	private ApplicationContext applicationContext;

	@InjectMocks
	private ConnectorUtilsImpl utils;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

	}

	@SuppressWarnings({ "serial", "unchecked" })
	@Test
	public void initializeConnector_ConstructsExistingClass_Test() {

		Workflow w = new Workflow();
		w.setConnector("de.tuhh.tusp.app.fame.connector.test.TestConnectorImpl");
		when(workflowDao.fetchById(anyLong())).then(x -> w);

		when(workflowInstanceDao.fetchById(anyLong())).then(x -> new WorkflowInstance());

		when(applicationContext.getBean(any(Class.class))).then(x -> null);
		when(applicationContext.getBean(TestConnectorImpl.class)).thenThrow(new BeansException("") {});

		Connector connector = utils.initializeConnector(5L);

		assertNotNull(connector);
	}

	@Test
	public void initializeConnector_WithNotExistingClassNameThrowsException_Test() {

		Workflow w = new Workflow();
		w.setConnector("TestConnector");
		when(workflowDao.fetchById(anyLong())).then(x -> w);

		when(workflowInstanceDao.fetchById(anyLong())).then(x -> new WorkflowInstance());

		try {
			utils.initializeConnector(5L);
			fail();
		}
		catch (RuntimeException e) {}
	}
}
