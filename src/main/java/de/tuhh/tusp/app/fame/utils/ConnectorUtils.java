package de.tuhh.tusp.app.fame.utils;

import java.util.List;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportResult;

public interface ConnectorUtils {

	/**
	 * Importiert die Vorgänge des Connector in die WorkflowInstanz
	 *
	 * @param connector
	 * @param workflowInstanceId
	 * @param filter
	 * @return Die importierbaren Fälle
	 * @throws ImportException
	 *             Wenn ein Datensatz nicht importiert werden konnte
	 */
	List<Case> findCases(Connector connector, Long workflowInstanceId, ImportFilter filter) throws ImportException;

	/**
	 * Speichert einen Case in die WorkflowInstanz
	 *
	 * @param c
	 * @param result
	 * @param workflowInstanceId
	 * @param startStatus
	 *            ID des ersten sichtbaren Status des Workflow
	 * @param endStatus
	 *            ID des letzten sichtbaren Status des Workflow. Ist dieser erreicht, wird keine aktualisierung
	 *            vorgenommen
	 * @return Den Vorgang
	 * @throws ImportException
	 *             bei Connectorfehlern
	 * @throws PflichtfeldException
	 *             bei Pflichtfeldverletzungen
	 * @throws RegexException
	 *             bei ungültigen Eingaben
	 */
	Vorgang importCase(	Case c, ImportResult result, Long workflowInstanceId, Long startStatus,
						Long endStatus) throws ImportException, RegexException, PflichtfeldException;

	/**
	 * Importiert einen Vorgang des Connector in die WorkflowInstanz
	 *
	 * @param connector
	 * @param workflowInstanceId
	 * @return Die Anzahl der aktualisierten und angelegten Fälle
	 * @throws ImportException
	 *             Wenn ein Datensatz nicht importiert werden konnte
	 * @throws PflichtfeldException
	 *             Wenn eine Pflichtfeld leer geblieben ist
	 * @throws RegexException
	 *             Wenn ein Wert nicht auf den regulären Ausdruck des Feldes passt
	 */
	ImportResult importiereVorgang(	Connector connector, Long workflowInstanceId,
									Case c) throws ImportException, PflichtfeldException, RegexException;

	/**
	 * Importiert einen Vorgang des Connector in die WorkflowInstanz
	 *
	 * @param connector
	 * @param workflowInstanceId
	 * @return Die Anzahl der aktualisierten und angelegten Fälle
	 * @throws ImportException
	 *             Wenn ein Datensatz nicht importiert werden konnte
	 * @throws PflichtfeldException
	 *             Wenn eine Pflichtfeld leer geblieben ist
	 * @throws RegexException
	 *             Wenn ein Wert nicht auf den regulären Ausdruck des Feldes passt
	 */
	ImportResult importiereVorgang(	Connector connector, Long workflowInstanceId,
									String connectorId) throws ImportException, PflichtfeldException, RegexException;

	/**
	 * Initialisert den Connector eines Workflows mit den Parametern der Instanz
	 *
	 * @param workflowInstanceId
	 * @return Den Connector
	 * @throws RuntimeException
	 *             Wenn der Connector keine Bean ist und nicht instanziert werden kann
	 */
	Connector initializeConnector(Long workflowInstanceId) throws RuntimeException;

	/**
	 * Initialisiert den Connector
	 *
	 * @param workflowId
	 * @return Den Connector
	 * @throws RuntimeException
	 *             Wenn der Connector keine Bean ist und nicht instanziert werden kann
	 */
	Connector initializeConnectorByWorkflow(Long workflowId) throws RuntimeException;

	/**
	 * Übergibt das Suchwort an alle Workflowinstanzen, lässt sie suchen und importiert ggf. die gefunden Cases
	 *
	 * @param searchword
	 * @return Das Result
	 */
	ImportResult sucheUndImportiereVorgaenge(String searchword);

}
