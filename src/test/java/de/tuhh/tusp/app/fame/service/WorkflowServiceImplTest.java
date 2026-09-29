package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Field;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.WorkflowDaoImpl;
import de.tuhh.tusp.app.fame.enums.FameConstants;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Workflow;

@RunWith(MockitoJUnitRunner.class)
public class WorkflowServiceImplTest {

	private static final Long TEST_ID = 5L;
	private static final Long INPUT_TEST_ID = 6L;
	private static final Long STATUS_TEST_ID = 7L;
	private static final Long INPUT_TYPE_TEST_ID = 8L;
	private static final Workflow TEST_WORKFLOW = new Workflow();

	@InjectMocks
	private WorkflowServiceImpl service;

	@Mock
	private InputServiceImpl inputService;

	@Mock
	private StatusServiceImpl statusService;

	@Mock
	private WorkflowInstanceService workflowInstanceService;

	@Mock
	private WorkflowDaoImpl workflowDao;

	@Mock
	private InputTypeService inputTypeService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		List<Input> inputs = new ArrayList<>();
		Input i = new Input();
		i.setId(INPUT_TEST_ID);
		inputs.add(i);
		when(inputService.findByWorkflow(same(TEST_ID), any(Field.class))).then(x -> inputs);

		List<Status> stati = new ArrayList<>();
		Status s = new Status();
		s.setId(STATUS_TEST_ID);
		stati.add(s);
		when(statusService.findByWorkflow(TEST_ID, true)).then(x -> stati);

		InputType inputType = new InputType();
		inputType.setId(INPUT_TYPE_TEST_ID);
		when(inputTypeService.fetchByName(FameConstants.ALL_INPUT_TYPE)).then(x -> inputType);

		when(workflowDao.insert(any(Workflow.class))).then(x -> TEST_ID);
	}

	@Test
	public void delete_deletsInputs_Test() {

		service.delete(TEST_ID);

		ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
		verify(inputService, times(1)).delete(argumentCaptor.capture());
		assertEquals(INPUT_TEST_ID, argumentCaptor.getValue());
	}

	@Test
	public void delete_deletsStatus_Test() {

		service.delete(TEST_ID);

		ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
		verify(statusService, times(1)).delete(argumentCaptor.capture());
		assertEquals(STATUS_TEST_ID, argumentCaptor.getValue());
	}

	@Test
	public void delete_deletsWorkflow_Test() {

		service.delete(TEST_ID);

		ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
		verify(workflowDao, times(1)).delete(argumentCaptor.capture());
		assertEquals(TEST_ID, argumentCaptor.getValue());
	}

	@Test
	public void insert_createsInput_Test() {

		service.insert(TEST_WORKFLOW);

		ArgumentCaptor<Input> argumentCaptor = ArgumentCaptor.forClass(Input.class);
		verify(inputService).insert(argumentCaptor.capture());
		argumentCaptor.getValue().getWorkflowId().equals(TEST_ID);
	}

	@Test
	public void insert_createsStatus_Test() {

		service.insert(TEST_WORKFLOW);

		ArgumentCaptor<Status> argumentCaptor = ArgumentCaptor.forClass(Status.class);
		verify(statusService).insert(argumentCaptor.capture());
		argumentCaptor.getValue().getWorkflowId().equals(TEST_ID);
	}

	@Test
	public void insert_createsWorkflow_Test() {

		Long id = service.insert(TEST_WORKFLOW);

		assertEquals(TEST_ID, id);
	}
}
