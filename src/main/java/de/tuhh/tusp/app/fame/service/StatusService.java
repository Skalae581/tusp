package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.dao.StatusDao;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.utils.CrudService;

public interface StatusService extends CrudService<Status> {

	/**
	 * Sucht den ersten sichtbaren Status eines Workflows
	 *
	 * @param workflowId
	 * @return den Status oder null wenn keiner existiert.
	 */
	Status fetchFirstStatusByWorkflow(Long workflowId);

	/**
	 * Sucht den letzten sichtbaren Status eines Workflows
	 *
	 * @param workflowId
	 * @return den Status oder null wenn keiner existiert.
	 */
	Status fetchLastStatusByWorkflow(Long workflowId);

	/**
	 * Holt alle Status in die der Vorgang mit den Rollen "roles" weitergegeben werden darf.
	 *
	 * @param vorgang
	 * @param roles
	 * @return Liste der Status
	 */
	List<Status> findByVorgangAndRoles(Vorgang vorgang, List<String> roles);

	/**
	 * {@link StatusDao#findByWorkflow(Long, boolean)}
	 *
	 * @param workflowId
	 * @param includeHidden
	 * @return Liste der Status
	 */
	List<Status> findByWorkflow(Long workflowId, boolean includeHidden);

	/**
	 * {@link StatusDao#findDetaildByWorkflowInstances(List)}
	 *
	 * @param userWorkflows
	 * @return Liste der Status
	 */
	List<ExtendedWorkflowInstance> findDetaildByWorkflowInstancesAndRoles(	List<WorkflowInstance> userWorkflows,
																			List<String> roles);
}
