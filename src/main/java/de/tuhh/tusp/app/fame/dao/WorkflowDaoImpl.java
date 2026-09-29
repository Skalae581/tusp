package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class WorkflowDaoImpl extends BaseDaoImpl<Workflow> implements WorkflowDao {

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private InputDao inputDao;

	/**
	 * Erstellt eine Instanz
	 */
	public WorkflowDaoImpl() {
		super(Workflow.class, FAME_WORKFLOW, FAME_WORKFLOW.ID);
	}

	@Override
	public int delete(Long id) {

		// Lösche Instanzen
		for (WorkflowInstance wi : workflowInstanceDao.findByWorkflow(id)) {
			workflowInstanceDao.delete(wi.getId());
		}

		// Lösche Stati
		for (Status status : statusDao.findByWorkflow(id, true)) {
			statusDao.delete(status.getId());
		}

		// Lösche Inputs
		for (Input input : inputDao.findByWorkflow(id)) {
			inputDao.delete(input.getId());
		}

		return super.delete(id);
	}

	@Override
	public List<Workflow> findWorkflowsByConnector(Class<?> c) {

		return context.select().from(table).where(FAME_WORKFLOW.CONNECTOR.equal(c.getName())).fetchInto(Workflow.class);
	}
}
