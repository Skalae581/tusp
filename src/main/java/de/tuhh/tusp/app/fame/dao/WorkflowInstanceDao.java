package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.BaseDao;

public interface WorkflowInstanceDao extends BaseDao<WorkflowInstance> {

	/**
	 * Holt die WorkflowInstanzen, die mit den Rollen bearbeitet werden dürfen.
	 * 
	 * @param roles
	 * @return Liste der Instanzen
	 */
	List<WorkflowInstance> findByRoles(List<String> roles);

	/**
	 * Holt die Instanzen eines Workflows
	 *
	 * @param id
	 * @return Liste der Instanzen
	 */
	List<WorkflowInstance> findByWorkflow(Long id);
}
