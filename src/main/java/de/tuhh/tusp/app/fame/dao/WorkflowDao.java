package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.utils.BaseDao;

public interface WorkflowDao extends BaseDao<Workflow> {

	/**
	 * Holt alle Workflows die den Connector verwenden
	 *
	 * @param class1
	 * @return Liste der Workflows
	 */
	List<Workflow> findWorkflowsByConnector(Class<?> c);

}
