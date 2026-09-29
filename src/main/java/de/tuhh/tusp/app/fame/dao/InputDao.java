package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.jooq.Field;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.BaseDao;

public interface InputDao extends BaseDao<Input> {

	Field<?> ORDER_BY_POSITION = FAME_INPUT.POSITION;

	Field<?> ORDER_BY_TABLE_POSITION = FAME_INPUT.TABLE_POS;

	/**
	 * Holt die Eingabefelder eines Workflows
	 *
	 * @param workflowId
	 * @return Liste der Inputs
	 */
	List<Input> findByWorkflow(Long workflowId);

	/**
	 * Holt die Eingabefelder eines Workflows, für die eine Position vorgesehen ist
	 *
	 * @param workflowId
	 * @param order
	 *            Spalte nach der sortiert werden soll
	 * @return Liste der Inputs
	 */
	List<Input> findByWorkflow(Long workflowId, Field<?> order);

	/**
	 * Holt die Eingabefelder eines Workflows entsprechend den Rollen des Nutzers
	 *
	 * Sortierung nach: input.position, nutzer berechtigung
	 *
	 * @param workflowId
	 * @param roles
	 * @return Liste der Inputs
	 */
	List<Input> findByWorkflowAndRoles(Long workflowId, List<String> roles);

	/**
	 * Holt die Inputs eines Vorgangs
	 *
	 * @param vorgangId
	 * @return Inputs mit Values
	 */
	List<ExtendedInput> findByWorkflowAndVorgang(Long workflowId, Long vorgangId);

	/**
	 * Holt die Eingabefelder eines Workflows aber anhand eines Konkreten Status und den Rollen des Nutzers
	 *
	 * Sortierung nach: input.position
	 *
	 * @param workflowInstanceId
	 * @param roles
	 * @return Liste der Inputs ohne den Werten des Vorgangs
	 */
	List<ExtendedInput> findByWorkflowInstanceStatusAndRoles(Long instanceId, Long statusId, List<String> roles);

	/**
	 * Holt die Eingabefelder eines Workflows aber anhand eines Konkreten Vorgangs und den Rollen des Nutzers
	 *
	 * Sortierung nach: input.position, value.create_time, nutzer berechtigung
	 *
	 * @param workflowInstanceId
	 * @param vorgangId
	 *            kann null sein wenn noch kein Vorgang existiert
	 * @param roles
	 * @return Liste der Inputs mit den Werten des Vorgangs
	 */
	List<ExtendedInput> findByWorkflowInstanceVorgangAndRoles(	Long workflowInstanceId, Long vorgangId,
																List<String> roles);

}
