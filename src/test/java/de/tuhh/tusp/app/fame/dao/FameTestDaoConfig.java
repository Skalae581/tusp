package de.tuhh.tusp.app.fame.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.utils.TestUtils;

@Configuration
public class FameTestDaoConfig {

	public static Long inputTypeId;
	public static Long vorgangId;
	public static Long inputId;
	public static Long workflowId;
	public static Long workflowInstanceId;
	public static Long statusId;
	public static Long rolleId;
	public static Long valueId;

	@Autowired
	private ValueDao valueDao;

	@Autowired
	private InputDao inputDao;

	@Autowired
	private InputTypeDao inputTypeDao;

	@Autowired
	private VorgangDao vorgangDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private RollenDao rolleDao;

	@Bean
	public MessageContainer getMessageContainer() {

		return new MessageContainer();
	}

	/**
	 * Funktion bereitet die Testdaten vor
	 *
	 * @param event
	 */
	@EventListener
	public void init(ContextRefreshedEvent event) {

		workflowId = manufactureWorkflow("initialWF").getId();
		workflowInstanceId = manufactureWorkflowInstance("initialWI", workflowId).getId();
		statusId = manufactureStatus("initialS", workflowId, 1L).getId();
		vorgangId = manufactureVorgang("initialV", workflowInstanceId, statusId).getId();
		inputTypeId = manufactureInputType("initialIT").getId();
		inputId = manufactureInput("initialI", workflowId, inputTypeId, 1L).getId();
		rolleId = manufactureRollen("initialR").getId();
		valueId = manufactureValue("initialVal", inputId, vorgangId).getId();
	}

	/**
	 * Erstellt einen Input
	 *
	 * @param l
	 *
	 * @return das Objekt
	 */
	public Input manufactureInput(String name, Long workflowId, Long inputTypeId, Long position) {

		Input w = TestUtils.manufacturePojo(Input.class);
		w.setWorkflowId(workflowId);
		w.setPosition(position);
		w.setTablePos(position);
		w.setInputTypeId(inputTypeId);
		w.setName(name);
		w.setId(inputDao.insert(w));

		return w;
	}

	/**
	 * Erstellt einen InputType
	 *
	 * @return das Objekt
	 */
	public InputType manufactureInputType(String name) {

		InputType w = TestUtils.manufacturePojo(InputType.class);
		w.setName(name);
		w.setId(inputTypeDao.insert(w));

		return w;
	}

	/**
	 * Erstellt eine Rolle
	 *
	 * @return das Objekt
	 */
	public Rollen manufactureRollen(String name) {

		Rollen w = TestUtils.manufacturePojo(Rollen.class);
		w.setName(name);
		w.setId(rolleDao.insert(w));

		return w;
	}

	/**
	 * Erstellt einen Status
	 *
	 * @return das Objekt
	 */
	public Status manufactureStatus(String name, Long workflowId, Long position) {

		Status w = TestUtils.manufacturePojo(Status.class);
		w.setWorkflowId(workflowId);
		w.setPosition(position);
		w.setName(name);
		w.setId(statusDao.insert(w));

		return w;
	}

	/**
	 * Erstellt einen Value
	 *
	 * @return das Objekt
	 */
	public Value manufactureValue(String value, Long inputId, Long vorgangId) {

		Value val1 = new Value();
		val1.setInputId(inputId);
		val1.setVorgangId(vorgangId);
		val1.setEingabe(value);
		val1.setId(valueDao.insert(val1));

		return val1;
	}

	/**
	 * Erstellt einen Vorgang
	 *
	 * @return das Objekt
	 */
	public Vorgang manufactureVorgang(String connectorKey, Long workflowInstanceId, Long statusId) {

		Vorgang w = TestUtils.manufacturePojo(Vorgang.class);
		w.setConnectorKey(connectorKey);
		w.setWorkflowInstanceId(workflowInstanceId);
		w.setStatusId(statusId);
		w.setId(vorgangDao.insert(w));

		return w;
	}

	/**
	 * Erstellt einen Workflow
	 *
	 * @return das Objekt
	 */
	public Workflow manufactureWorkflow(String name) {

		Workflow w = TestUtils.manufacturePojo(Workflow.class);
		w.setName(name);
		w.setId(workflowDao.insert(w));

		return w;
	}

	/**
	 * Erstellt eine WorkflowInstanz
	 *
	 * @return das Objekt
	 */
	public WorkflowInstance manufactureWorkflowInstance(String name, Long workflowId) {

		WorkflowInstance w = TestUtils.manufacturePojo(WorkflowInstance.class);
		w.setName(name);
		w.setWorkflowId(workflowId);
		w.setId(workflowInstanceDao.insert(w));

		return w;
	}
}
