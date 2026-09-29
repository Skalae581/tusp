package de.tuhh.tusp.app.fame.service;

import java.util.ArrayList;
import java.util.List;

import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.CrudService;
import lombok.Data;

public interface WorkflowInstanceService extends CrudService<WorkflowInstance> {

	public enum ImportFilter {
		NurNeue, Alle
	}

	@Data
	class ImportResult {

		private int aktualisiert = 0;
		private int angelegt = 0;
		private int resetted = 0;
		private List<String> errors = new ArrayList<>();
		private List<Vorgang> vorgaenge = new ArrayList<>();
	}

	/**
	 * Holt die WorkflowInstanzen anhand der Rollen eines Benutzers
	 *
	 * @param roles
	 * @return Liste der Instanzen
	 */
	List<WorkflowInstance> findByRoles(List<String> roles);

	/**
	 * Holt alle Instanzen eines Workflows
	 *
	 * @param id
	 * @return Liste der Instanzen
	 */
	List<WorkflowInstance> findByWorkflow(Long id);

	/**
	 * Sucht nach dem Wort in allen Connectoren von allen {@link WorkflowInstance}
	 *
	 * @param search
	 * @return das result
	 */
	ImportResult searchAndImportInConnectors(String search);

	/**
	 * Importiert / aktualisiert einen Vorgang einer WorkflowInstance
	 *
	 * @param workflowInstanceId
	 * @param connectorId
	 * @return Die Anzahl der aktualisierten und angelegten Fälle
	 * @throws ImportException
	 *             Wenn ein Datensatz nicht importiert werden konnte
	 * @throws PflichtfeldException
	 *             Wenn eine Pflichtfeld leer geblieben ist
	 * @throws RegexException
	 *             Wenn ein Wert nicht auf den regulären Ausdruck des Feldes passt
	 */
	ImportResult triggerConnector(Long workflowInstanceId, String connectorId)	throws ImportException,
																				PflichtfeldException, RegexException;
}
