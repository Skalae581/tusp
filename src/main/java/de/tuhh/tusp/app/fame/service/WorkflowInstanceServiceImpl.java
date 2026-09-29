package de.tuhh.tusp.app.fame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.utils.ConnectorUtils;

@Component
public class WorkflowInstanceServiceImpl implements WorkflowInstanceService {

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private ConnectorUtils connectorUtils;

	@Override
	public int delete(Long id) {

		return workflowInstanceDao.delete(id);
	}

	@Override
	public WorkflowInstance fetchById(Long id) {

		return workflowInstanceDao.fetchById(id);
	}

	@Override
	public List<WorkflowInstance> findAll() {

		return workflowInstanceDao.findAll();
	}

	@Override
	public List<WorkflowInstance> findByRoles(List<String> roles) {

		return workflowInstanceDao.findByRoles(roles);
	}

	@Override
	public List<WorkflowInstance> findByWorkflow(Long id) {

		return workflowInstanceDao.findByWorkflow(id);
	}

	@Override
	public Long insert(WorkflowInstance w) {

		return workflowInstanceDao.insert(w);
	}

	@Override
	public ImportResult searchAndImportInConnectors(String search) {

		return connectorUtils.sucheUndImportiereVorgaenge(search);
	}

	@Override
	public ImportResult triggerConnector(Long workflowInstanceId, String connectorId)	throws ImportException,
																						PflichtfeldException,
																						RegexException {

		Connector connector = connectorUtils.initializeConnector(workflowInstanceId);
		return connectorUtils.importiereVorgang(connector, workflowInstanceId, connectorId);
	}

	@Override
	public void update(WorkflowInstance w) {

		workflowInstanceDao.update(w);
	}

}
