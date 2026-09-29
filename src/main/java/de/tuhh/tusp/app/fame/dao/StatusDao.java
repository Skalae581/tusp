package de.tuhh.tusp.app.fame.dao;

import java.io.Serializable;
import java.util.List;

import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.utils.BaseDao;
import lombok.Data;

public interface StatusDao extends BaseDao<Status> {

	@Data
	class ExtendedStatus implements Serializable {

		private static final long serialVersionUID = -8686657646822238876L;
		Long id;
		String name;
		Integer numVorgaenge;
	}

	/**
	 * Holt alle Status deren ID in der Liste ist
	 *
	 * @param ids
	 * @return
	 */
	List<Status> findByIds(List<Long> ids);

	/**
	 * Holt alle Status eines Workflows
	 *
	 * @param workflowId
	 * @param includeHidden
	 *            Wenn Flag auf true gesetzt ist, werden auch die versteckten Status mit ausgegeben.
	 * @return
	 */
	List<Status> findByWorkflow(Long workflowId, boolean includeHidden);

	/**
	 * Holt alle Status eines Workflows anhand einer WorkflowInstanz
	 *
	 * @param workflowId
	 * @param includeHidden
	 *            Wenn Flag auf true gesetzt ist, werden auch die versteckten Status mit ausgegeben.
	 * @return
	 */
	List<Status> findByWorkflowInstance(Long workflowInstanceId, boolean includeHidden);

	/**
	 * Holt Statusnamen, IDs und Anzahl der Vorgänge anhand von WorkflowInstanzen
	 *
	 * @param userWorkflows
	 * @return
	 */
	List<ExtendedWorkflowInstance> findDetaildByWorkflowInstancesAndRoles(	List<WorkflowInstance> userWorkflows,
																			List<String> roles);

}
