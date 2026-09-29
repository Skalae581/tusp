package de.tuhh.tusp.app.fame.utils;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.app.fame.connector.pojo.ConnectorPojo;
import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.VorgangService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportResult;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.TestUtils;

public class ConnectorUtilsImplTest {

	private static boolean saveOnMappingCalled = false;
	private static final Long NEW_VORGANG_ID = 5L;
	private static final Long WORKFLOW_INSTANCE_TEST_ID = 7L;
	private static final Long VORGANG_TEST_ID = 9L;
	private static final Long FIRST_STATUS_ID = 4L;
	private static final Long LAST_STATUS_ID = 666L;
	private static final Case TEST_CASE = new Case() {

		public String id;

		@Override
		public String getId() {

			return id;
		}

		@Override
		public String getImportErrors() {

			return "";
		}

		@Override
		public Map<String, ConnectorPojo> getProperties() {

			Map<String, ConnectorPojo> map = new HashMap<>();
			map.put("existierendes_connector_mapping", new ConnectorPojo() {

				@Override
				public void save(ExtendedInput input) throws ImportException, RegexException, PflichtfeldException {

					saveOnMappingCalled = true;
				}
			});
			return map;
		}

		@Override
		public Validity getValid() {

			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void lazyLoad() {

		}

		@Override
		public void setValid(Validity v) {

			// TODO Auto-generated method stub

		}
	};

	@InjectMocks
	private ConnectorUtilsImpl connectorUtilsImpl;

	@Mock
	private WorkflowInstanceDao workflowInstanceDao;

	@Mock
	private WorkflowDao workflowDao;

	@Mock
	private ApplicationContext appContext;

	@Mock
	private StatusService statusService;

	@Mock
	private InputService inputService;

	@Mock
	private VorgangService vorgangService;

	@Mock
	private AuthorityUtils authorityUtils;

	@Mock
	private Connector connector;

	@Mock
	private WorkflowInstanceService workflowInstanceService;

	private Vorgang testVorgang;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		saveOnMappingCalled = false;

		List<ExtendedInput> inputs = new ArrayList<>();
		ExtendedInput i1 = new ExtendedInput();
		i1.setConnectorMapping("existierendes_connector_mapping");
		inputs.add(i1);
		ExtendedInput i2 = new ExtendedInput();
		inputs.add(i2);
		when(inputService.findByWorkflowInstanceVorgangAndRoles(any(Long.class), any(Long.class),
																any())).then(x -> inputs);

		when(vorgangService.insert(any(Vorgang.class))).then(x -> NEW_VORGANG_ID);

		TestUtils.setField(TEST_CASE, "id", "some_id");

		testVorgang = new Vorgang();
		testVorgang.setId(VORGANG_TEST_ID);
		when(vorgangService.fetchByWorkflowIntsanceAndConnectorKey(any(), any()))
																					.then(x -> x.getArguments()[1].equals("new_id")
																							? null : testVorgang);

		Status s = new Status();
		s.setId(FIRST_STATUS_ID);
		when(statusService.fetchFirstStatusByWorkflow(any(Long.class))).then(x -> s);

		Status last = new Status();
		last.setId(LAST_STATUS_ID);
		when(statusService.fetchLastStatusByWorkflow(any(Long.class))).then(x -> last);

		when(workflowInstanceService.fetchById(any(Long.class))).then(x -> new WorkflowInstance());
	}

	@Test
	public void importCase_createsVorgang_Test() throws ImportException, RegexException, PflichtfeldException {

		ImportResult result = new ImportResult();
		TestUtils.setField(TEST_CASE, "id", "new_id");
		connectorUtilsImpl.importCase(TEST_CASE, result, WORKFLOW_INSTANCE_TEST_ID, FIRST_STATUS_ID, LAST_STATUS_ID);

		ArgumentCaptor<Vorgang> argCaptor = ArgumentCaptor.forClass(Vorgang.class);
		verify(vorgangService).insert(argCaptor.capture());

		assertEquals(NEW_VORGANG_ID, argCaptor.getValue().getId());
		assertEquals(FIRST_STATUS_ID, argCaptor.getValue().getStatusId());
		assertEquals(WORKFLOW_INSTANCE_TEST_ID, argCaptor.getValue().getWorkflowInstanceId());
		assertEquals(1, result.getAngelegt());
	}

	@Test
	public void importCase_IgnoresLastStatus_Test() throws ImportException, RegexException, PflichtfeldException {

		testVorgang.setStatusId(LAST_STATUS_ID);
		connectorUtilsImpl.importCase(TEST_CASE, null, WORKFLOW_INSTANCE_TEST_ID, FIRST_STATUS_ID, LAST_STATUS_ID);
		if (saveOnMappingCalled) {
			fail("Connector soll keinen Value zu speichern");
		}
	}

	@Test
	public void importCase_savesValues_Test() throws ImportException, RegexException, PflichtfeldException {

		testVorgang.setStatusId(FIRST_STATUS_ID);
		connectorUtilsImpl.importCase(TEST_CASE, null, WORKFLOW_INSTANCE_TEST_ID, FIRST_STATUS_ID, LAST_STATUS_ID);
		if (!saveOnMappingCalled) {
			fail("Connector hat nicht einmal versucht einen Value zu speichern");
		}
	}

	@Test
	public void importiereVorgang_setsValues_Test() throws ImportException, PflichtfeldException, RegexException {

		TestUtils.setField(TEST_CASE, "id", "new_id");
		when(connector.findCase(any())).then(x -> TEST_CASE);

		connectorUtilsImpl.importiereVorgang(connector, WORKFLOW_INSTANCE_TEST_ID, "some_key");

		ArgumentCaptor<Vorgang> argCaptor = ArgumentCaptor.forClass(Vorgang.class);
		verify(vorgangService).insert(argCaptor.capture());

		assertEquals(NEW_VORGANG_ID, argCaptor.getValue().getId());
		assertEquals(FIRST_STATUS_ID, argCaptor.getValue().getStatusId());
		assertEquals(WORKFLOW_INSTANCE_TEST_ID, argCaptor.getValue().getWorkflowInstanceId());
		assertEquals("new_id", argCaptor.getValue().getConnectorKey());
	}
}
