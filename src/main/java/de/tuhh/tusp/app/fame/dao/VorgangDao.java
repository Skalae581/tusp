package de.tuhh.tusp.app.fame.dao;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.BaseDao;
import lombok.Data;
import lombok.NonNull;

public interface VorgangDao extends BaseDao<Vorgang> {

	@Data
	class Suchergebnis {

		@Data
		public class InputValue {

			@NonNull
			private String inputName;
			@NonNull
			private String eingabe;
		}

		private String inputName;
		private Vorgang vorgang;
		private Value value;
		private InputValue[] description;
	}

	@Data
	class Suchparameter {

		@NonNull
		private Input input;
		@NonNull
		private String searchword;
	}

	/**
	 * Holt einen Vorgang anhand der WorkflowInstanz und dem Eindeutigen Bezeichner des Datensatzes im Connector.
	 *
	 * @param workflowId
	 * @param connectorId
	 * @return Den Vorgang oder null wenn nicht gefunden
	 */
	Vorgang fetchByWorkflowInstanceAndConnectorKey(Long workflowId, String connectorId);

	/**
	 * Holt eine Liste der Fälle als HashMap codiert. Der Key der HashMap ist "value_{inputId}" value ist der Wert des
	 * Feldes
	 *
	 * @param statusId
	 * @param instanceId
	 * @return Liste der Fälle
	 */
	List<Map<String, String>> findByStatusAndWorkflowInstance(Long statusId, Long instanceId);

	/**
	 * Holt eine Liste aller Vorgänge einer Webserviceinstanz
	 *
	 * @param instanceId
	 * @return Liste der Fälle
	 */
	List<Vorgang> findByWorkflowInstance(Long instanceId);

	/**
	 * Sucht alle Vorgänge die die Suchparameter erfüllen
	 *
	 * @param suchparameter
	 * @return Ergebnisse
	 */
	List<Suchergebnis> search(List<Suchparameter> suchparameter);

	/**
	 * Sucht nach Vorgängen.
	 *
	 * @param workflowInstances
	 *            alle {@link WorkflowInstance} in denen gesucht werden soll. Sie müssen alle zum selben Workflow
	 *            gehören!
	 * @param searchFields
	 *            Zuordnung InputID zu Wert
	 * @return Liste der Vorgänge oder null, wenn keine Vorgänge auffindbar sind
	 */
	List<Map<String, String>> search(List<WorkflowInstance> workflowInstances, Map<Long, String> searchFields);

}
