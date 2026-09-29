package de.tuhh.tusp.app.fame.connector;

import java.util.List;

import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;

public interface Connector {

	/**
	 * Holt einen Vorgang zu gegebenen Parametern
	 *
	 * <p>
	 * Für das klassische Fame wäre z.B. {stg=ENT,abschl=MS,bewsem=20172} ein möglicher Parameter
	 * </p>
	 *
	 * @param caseId
	 *            Die ID des Falles
	 * @return den Fall
	 * @throws ImportException
	 *             Wenn der Datensatz nicht gefunden wurde
	 */
	Case findCase(String caseId) throws ImportException;

	/**
	 * Holt alle verfügbaren Vorgänge zu gegebenen Parametern
	 *
	 * <p>
	 * Für das klassische Fame wäre z.B. {stg=ENT,abschl=MS,bewsem=20172} ein möglicher Parameter
	 * </p>
	 *
	 * @param filter
	 *
	 * @return die Fälle
	 * @throws ImportException
	 *             Wenn der Import fehlschlägt
	 */
	List<Case> findCases(ImportFilter filter) throws ImportException;

	/**
	 * Erzeugt einen Beispielfall. Dieser Fall muss alle Eigenschaften besitzen, die ein späterer Fall haben kann;
	 * natürlich mit den entsprechenden Datentypen!
	 *
	 * @return einen Beispielfall
	 */
	Case readExapleCase();

	/**
	 * Sucht in der Schnittstelle nach Cases
	 *
	 * @param searchword
	 * @return Liste der Cases
	 */
	List<Case> search(String searchword);

	/**
	 * Übergibt die Parameter der WorkflowInstanz an den Connector
	 *
	 * @param parameter
	 *            Ein ConnectorParameter
	 */
	void setParameter(String parameter);

	/**
	 * Übergibt die Instanz der {@link WorkflowInstance}
	 * 
	 * @param id
	 */
	void setWorkflowInstanceId(Long id);

	/**
	 * Speichert die Änderungen eines Vorgangs
	 *
	 * @param c
	 *            der Fall
	 */
	void writeCase(Case c);
}
