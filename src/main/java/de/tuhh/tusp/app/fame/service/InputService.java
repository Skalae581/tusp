package de.tuhh.tusp.app.fame.service;

import java.util.List;

import org.jooq.Field;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.CrudService;
import lombok.Data;

public interface InputService extends CrudService<Input> {

	@Data
	class OptionsType {

		String id;
		String name;
		boolean disabled;
	}

	/**
	 * Holt die Inputs eines Workflows
	 *
	 * @param id
	 * @param order
	 * @return Liste der Inputs
	 */
	List<Input> findByWorkflow(Long id, Field<?> order);

	/**
	 * Holt die Inputs entsprechend der Rollen des Benutzers
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
	 * Holt die Inputs eines Status entsprechend der Rollen des Benutzers
	 *
	 * @param wokflowInstanceId
	 * @param vorgangId
	 * @param roles
	 * @return Liste der Inputs
	 */
	List<ExtendedInput> findByWorkflowInstanceStatusAndRoles(Long instanceId, Long statusId, List<String> roles);

	/**
	 * Holt die Inputs eines Vorgangs entsprechend der Rollen des Benutzers
	 *
	 * @param wokflowInstanceId
	 * @param vorgangId
	 * @param roles
	 * @return Liste der Inputs
	 */
	List<ExtendedInput> findByWorkflowInstanceVorgangAndRoles(	Long wokflowInstanceId, Long vorgangId,
																List<String> roles);

	/**
	 * Erzeugt eine der Options aus den Daten eines Inputs
	 *
	 * @param input
	 * @return Liste der Inputs
	 */
	List<OptionsType> parseOptions(Input input);
}
