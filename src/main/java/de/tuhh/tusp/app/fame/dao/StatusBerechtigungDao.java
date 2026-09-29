package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.utils.BaseDao;
import lombok.Data;
import lombok.EqualsAndHashCode;

public interface StatusBerechtigungDao extends BaseDao<StatusBerechtigung> {

	@Data
	@EqualsAndHashCode(callSuper = true)
	class ExtendedStatusBerechtigung extends StatusBerechtigung {

		private static final long serialVersionUID = -6718547688343198086L;
		private String statusFromName;
		private String statusToName;
		private Long statusFromPosition;
		private Long statusToPosition;
		private Integer statusToHidden;
	}

	/**
	 * Löscht alle StatusBerechtigungen die mit einem Status assoziiert sind
	 * 
	 * @param id
	 */
	void deleteByStatus(Long id);

	/**
	 * Holt alle Statusübergänge eines Benutzers anhand seiner Rollen innerhalb eines Workflows
	 *
	 * @param roles
	 * @param workflowInstanceId
	 * @return
	 */
	List<ExtendedStatusBerechtigung> findByRollenAndWorkflowInstance(List<String> roles, Long workflowInstanceId);

	/**
	 * Holt alle Berechtigungen für Statusübergänge in einem Workflow
	 *
	 * @param id
	 * @return
	 */
	List<StatusBerechtigung> findByWorkflow(Long id);

}
