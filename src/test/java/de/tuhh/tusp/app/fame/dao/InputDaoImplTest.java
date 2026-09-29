package de.tuhh.tusp.app.fame.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.app.fame.FameConfig;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.config.ApplicationUnitTestConfig;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.utils.TestUtils.Change;

@ContextConfiguration(classes = { ApplicationUnitTestConfig.class, FameConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class InputDaoImplTest {

	@Autowired
	private InputDao inputDao;

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private ValueDao valueDao;

	@Autowired
	private VorgangDao vorgangDao;

	@Autowired
	private RollenDao rollenDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;

	@Test
	public void crudTest() {

		Input pojo = new Input();
		pojo.setId(0L);
		pojo.setPosition(1L);
		pojo.setWorkflowId(FameTestDaoConfig.workflowId);
		pojo.setBeschreibung("Testbeschreibung");
		pojo.setOptions("");
		pojo.setInputTypeId(FameTestDaoConfig.inputTypeId);
		pojo.setName("Test-Input");
		pojo.setKommentar("");
		pojo.setRegex("");
		pojo.setHidden(0);
		pojo.setPflicht(0);
		pojo.setConnectorMapping("");
		pojo.setTablePos(1L);

		TestUtils testUtils = new TestUtils();
		testUtils.assertNoFieldIsNull(pojo);

		String[] ignoreGetters = { "getId" };
		testUtils.simpleCrudTestForDao(inputDao, pojo, ignoreGetters, new Change("setName", "Name wurde geändert"));

	}

	@Test
	public void findByWorkflowInstanceVorgangAndRolesTest() {

		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		w1.setId(workflowDao.insert(w1));

		WorkflowInstance wi1 = TestUtils.manufacturePojo(WorkflowInstance.class);
		wi1.setWorkflowId(w1.getId());
		wi1.setParameter("Test");
		wi1.setId(workflowInstanceDao.insert(wi1));

		Input i1 = TestUtils.manufacturePojo(Input.class);
		i1.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i1.setWorkflowId(w1.getId());
		i1.setName("Vorname");
		i1.setPosition(1L);
		i1.setId(inputDao.insert(i1));

		Input i2 = TestUtils.manufacturePojo(Input.class);
		i2.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i2.setWorkflowId(w1.getId());
		i2.setName("Nachname");
		i2.setPosition(2L);
		i2.setId(inputDao.insert(i2));

		Vorgang v1 = TestUtils.manufacturePojo(Vorgang.class);
		v1.setStatusId(FameTestDaoConfig.statusId);
		v1.setWorkflowInstanceId(wi1.getId());
		v1.setId(vorgangDao.insert(v1));

		Rollen r1 = new Rollen();
		r1.setName("Role1");
		r1.setId(rollenDao.insert(r1));

		InputBerechtigung ib1 = TestUtils.manufacturePojo(InputBerechtigung.class);
		ib1.setInputId(i1.getId());
		ib1.setStatusId(FameTestDaoConfig.statusId);
		ib1.setRolleId(r1.getId());
		ib1.setRecht(InputRechteEnum.READ);
		ib1.setId(inputBerechtigungDao.insert(ib1));

		InputBerechtigung ib2 = TestUtils.manufacturePojo(InputBerechtigung.class);
		ib2.setInputId(i2.getId());
		ib2.setStatusId(FameTestDaoConfig.statusId);
		ib2.setRolleId(r1.getId());
		ib2.setRecht(InputRechteEnum.READ);
		ib2.setId(inputBerechtigungDao.insert(ib2));

		List<String> roles = new ArrayList<>();

		// Ohne Rechte abfragen sollte Werte liefern
		List<ExtendedInput> list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals(2, list.size());
		assertEquals(InputRechteEnum.NONE, list.get(0).getRecht());
		assertEquals(InputRechteEnum.NONE, list.get(1).getRecht());

		// Recht geben
		roles.add(r1.getName());

		// Die Eingabefelder sollten sichtbar sein, aber leer
		list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals(2, list.size());
		assertEquals(null, list.get(0).getEingabe());

		// Ohne Vorgang sollten nur Leere Eingebefelder erscheinen
		// Nichtmal das!
		// list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), null, roles);
		// assertEquals(2, list.size());
		// assertEquals(null, list.get(0).getEingabe());

		// Wert setzen
		Value val1 = new Value();
		val1.setInputId(i1.getId());
		val1.setVorgangId(v1.getId());
		val1.setEingabe("asdf");
		val1.setId(valueDao.insert(val1));

		// Die Eingabefelder sollten die richtigen Werte enthalten
		list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals(2, list.size());
		assertEquals("asdf", list.get(0).getEingabe());
		assertEquals(null, list.get(1).getEingabe());

		// Vorname sollte vor nachname sein
		assertEquals("Vorname", list.get(0).getName());

		// Input 2 darf jetzt auch geschrieben werden, daher bekommt er selbe Position
		ib2.setRecht(InputRechteEnum.WRITE);
		inputBerechtigungDao.update(ib2);
		i2.setPosition(i1.getPosition());
		inputDao.update(i2);

		// Jetzt muss i2 vor i1 sein
		list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals("Vorname", list.get(1).getName());

		// Value 1 create_time setzen damit muss das Feld wieder vorne sein
		val1.setCreateTime(new Date());
		valueDao.update(val1);
		list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals("Vorname", list.get(0).getName());

		// Recht fremd parametrisieren
		roles.clear();
		roles.add(r1.getName() + "(asdf)");
		list = inputDao.findByWorkflowInstanceVorgangAndRoles(wi1.getId(), v1.getId(), roles);
		assertEquals(InputRechteEnum.NONE, list.get(1).getRecht());
	}

	@Test
	public void findByWorkflowTest() {

		Workflow w1 = TestUtils.manufacturePojo(Workflow.class);
		Workflow w2 = TestUtils.manufacturePojo(Workflow.class);

		Long w1Id = workflowDao.insert(w1);
		Long w2Id = workflowDao.insert(w2);

		Input i1 = TestUtils.manufacturePojo(Input.class);
		i1.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i1.setWorkflowId(w1Id);
		i1.setName("bbbb");
		i1.setPosition(2L);
		inputDao.insert(i1);

		Input i2 = TestUtils.manufacturePojo(Input.class);
		i2.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i2.setWorkflowId(w1Id);
		i2.setName("aaaa");
		i2.setPosition(1L);
		inputDao.insert(i2);

		// Feld soll nicht gefunden werden
		Input i3 = TestUtils.manufacturePojo(Input.class);
		i3.setInputTypeId(FameTestDaoConfig.inputTypeId);
		i3.setWorkflowId(w2Id);
		inputDao.insert(i3);

		List<Input> list = inputDao.findByWorkflow(w1Id, InputDao.ORDER_BY_POSITION);

		// richtige Anzahl?
		assertEquals(2, list.size());

		// Richtige Elemente und richtige (umgekehrte) Reihenfolge?
		assertEquals(i1.getName(), list.get(1).getName());
		assertEquals(i2.getName(), list.get(0).getName());
	}
}
