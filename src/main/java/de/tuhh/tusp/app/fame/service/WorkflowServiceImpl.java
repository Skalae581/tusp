package de.tuhh.tusp.app.fame.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.dao.RollenDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.enums.FameConstants;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.app.fame.pojo.SerializeContainer;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;

@Component
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private WorkflowInstanceService workflowInstanceDao;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StatusBerechtigungDao statusBerechtigungDao;

	@Autowired
	private InputService inputService;

	@Autowired
	private RollenDao rollenDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;

	@Autowired
	private InputTypeService inputTypeService;

	@Override
	public int delete(Long id) {

		for (Status s : statusService.findByWorkflow(id, true)) {
			statusService.delete(s.getId());
		}

		for (Input i : inputService.findByWorkflow(id, InputDao.ORDER_BY_POSITION)) {
			inputService.delete(i.getId());
		}

		for (WorkflowInstance wi : workflowInstanceDao.findByWorkflow(id)) {
			workflowInstanceDao.delete(wi.getId());
		}

		return workflowDao.delete(id);
	}

	@Override
	public void deserialize(byte[] data) throws IOException, ClassNotFoundException {

		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(in);

		SerializeContainer c = (SerializeContainer) ois.readObject();
		HashMap<String, Long> idMapping = new HashMap<>();

		HashMap<String, Long> bestehendeRollenNamen = new HashMap<>();
		for (Rollen r : rollenDao.findAll()) {
			bestehendeRollenNamen.put(r.getName(), r.getId());
		}
		for (Rollen r : c.rs) {
			long id = 0;
			long oldId = r.getId();
			if (bestehendeRollenNamen.containsKey(r.getName())) {
				// Rolle besteht schon, also nimm die ID
				id = bestehendeRollenNamen.get(r.getName());
			}
			else {
				// Rolle anlegen und ID merken
				r.setId(null);
				id = rollenDao.insert(r);
			}
			idMapping.put("r" + oldId, id);
		}

		// Workflow erstellen
		long oldId = c.w.getId();
		c.w.setId(null);
		long newId = workflowDao.insert(c.w);
		idMapping.put("w" + oldId, newId);
		c.w.setId(newId);

		// InputTypes einlesen
		HashMap<InputTypeEnum, Long> its = new HashMap<>();
		for (InputType it : inputTypeService.findAll()) {
			its.put(it.getUniqName(), it.getId());
		}
		for (InputType it : c.its) {
			idMapping.put("it" + it.getId(), its.get(it.getUniqName()));
		}

		// Inputs erstellen
		for (Input i : c.is) {
			oldId = i.getId();
			i.setId(null);
			i.setWorkflowId(idMapping.get("w" + i.getWorkflowId()));
			i.setInputTypeId(idMapping.get("it" + i.getInputTypeId()));
			newId = inputService.insert(i);
			idMapping.put("i" + oldId, newId);
			if (c.w.getInputValidationCode() != null) {
				c.w.setInputValidationCode(c.w.getInputValidationCode().replaceAll("value_" + oldId, "value_" + newId));
			}
		}

		// Status erstellen
		for (Status i : c.ss) {
			oldId = i.getId();
			i.setId(null);
			i.setWorkflowId(idMapping.get("w" + i.getWorkflowId()));
			newId = statusService.insert(i);
			idMapping.put("s" + oldId, newId);
			if (c.w.getInputValidationCode() != null) {
				c.w.setInputValidationCode(c.w.getInputValidationCode().replaceAll(	"status_" + oldId,
																					"status_" + newId));
			}
		}

		// WorkflowInstances erstellen
		for (WorkflowInstance i : c.wis) {
			oldId = i.getId();
			i.setId(null);
			i.setWorkflowId(idMapping.get("w" + i.getWorkflowId()));
			idMapping.put("wi" + oldId, workflowInstanceDao.insert(i));
		}

		// Inputberechtigungen erstellen
		for (InputBerechtigung i : c.ibs) {
			i.setId(null);
			i.setInputId(idMapping.get("i" + i.getInputId()));
			i.setStatusId(idMapping.get("s" + i.getStatusId()));
			i.setRolleId(idMapping.get("r" + i.getRolleId()));
			inputBerechtigungDao.insert(i);
		}

		// Statusberechtigungen erstellen
		for (StatusBerechtigung i : c.sbs) {
			i.setId(null);
			i.setStatusFromId(idMapping.get("s" + i.getStatusFromId()));
			i.setStatusToId(idMapping.get("s" + i.getStatusToId()));
			i.setRolleId(idMapping.get("r" + i.getRolleId()));
			statusBerechtigungDao.insert(i);
		}

		workflowDao.update(c.w);
	}

	@Override
	public Workflow fetchById(Long id) {

		return workflowDao.fetchById(id);
	}

	@Override
	public List<Workflow> findAll() {

		return workflowDao.findAll();
	}

	@Override
	@Transactional
	public Long insert(Workflow w) {

		Long id = workflowDao.insert(w);

		Status status = new Status();
		status.setHidden(1);
		status.setWorkflowId(id);
		status.setId(0L);
		status.setName(FameConstants.ALL_STATUS);
		status.setKommentar("");
		statusService.insert(status);

		Input input = new Input();
		input.setId(0L);
		input.setWorkflowId(id);
		input.setName(FameConstants.ALL_INPUT);
		input.setBeschreibung("");
		input.setOptions("");
		input.setKommentar("");
		input.setPosition(0L);
		input.setInputTypeId(inputTypeService.fetchByName(FameConstants.ALL_INPUT_TYPE).getId());
		inputService.insert(input);

		return id;
	}

	@Override
	public byte[] serialize(Long workflowId) throws IOException {

		SerializeContainer c = new SerializeContainer();
		c.w = workflowDao.fetchById(workflowId);
		c.wis = workflowInstanceDao.findByWorkflow(workflowId);
		c.is = inputService.findByWorkflow(workflowId, InputDao.ORDER_BY_POSITION);
		c.ss = statusService.findByWorkflow(workflowId, true);
		c.ibs = inputBerechtigungDao.findByWorkflow(workflowId);
		c.sbs = statusBerechtigungDao.findByWorkflow(workflowId);
		c.rs = rollenDao.findAll();
		c.its = inputTypeService.findAll();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(out);
		oout.writeObject(c);

		return out.toByteArray();
	}

	@Override
	public void update(Workflow w) {

		workflowDao.update(w);
	}

}
