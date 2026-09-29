package de.tuhh.tusp.app.fame.service;

import java.util.List;
import java.util.Map;

import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.VorgangGesperrtException;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.CrudService;

public interface VorgangService extends CrudService<Vorgang> {

	/**
	 * Holt einen Vorgang anhand der WorkflowInstanz und dem Eindeutigen Bezeichner des Datensatzes im Connector.
	 *
	 * @param workflowId
	 * @param connectorId
	 * @return Den Vorgang oder null wenn nicht gefunden
	 */
	Vorgang fetchByWorkflowIntsanceAndConnectorKey(Long workflowId, String connectorId);

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
	 * Gibt den Namen des aktuellen Bearbeiter des Vorgangs zurück. Ist der Vorgang nicht in Bearbeitung wird null
	 * zurück gegeben. Ist der Fall bei mir in Bearbeitung wird die Zeit aktualisiert und "me" zurück gegeben.
	 *
	 * @param vorgang
	 * @return Name des Bearbeiters
	 */
	String inBearbeitungBei(Vorgang vorgang);

	/**
	 * Sperrt den Fall zur Bearbeitung
	 *
	 * @param vorgang
	 * @throws VorgangGesperrtException
	 *             Wenn der Vorgang durch jemand anderes gesperrt ist
	 */
	void inBearbeitungNehmen(Vorgang vorgang) throws VorgangGesperrtException;

	/**
	 * Markiert den Vorgang, dass er nicht weiter bearbeitet werden braucht. (Gesperrt durch wird auf "none" gesetzt)
	 *
	 * @param vorgang
	 * @throws VorgangGesperrtException
	 *             Wenn der Vorgang durch jemand anderes gesperrt ist
	 */
	void keineWeitereBearbeitung(Vorgang vorgang) throws VorgangGesperrtException;

	/**
	 * Sucht nach Vorgängen.
	 *
	 * Prüft, dass nur Innerhalb der berechtigten Vorgänge gesucht wird.
	 *
	 * Prüft, dass alle WorkflowInstances zum selben Workflow gehören.
	 *
	 * @param workflowId
	 *            ID {@link Workflow} in dem gesucht werden soll
	 * @param searchFields
	 *            Zuordnung InputID zu Wert
	 * @return Liste der Vorgänge
	 * @throws RuntimeException
	 *             wenn die {@link WorkflowInstance}s zu verschiedenen {@link Workflow}s gehören
	 */
	List<Map<String, String>> search(Long workflowId, Map<Long, String> searchFields) throws RuntimeException;

	/**
	 * Sucht in allen Eingabefeldern nach dem Suchwort und liefert alle Treffer die der Benutzer sehen darf.
	 *
	 * @param word
	 * @return Liste der Vorgänge
	 */
	List<Suchergebnis> search(String word);

	/**
	 * Überführt einen Vorgang in eienen neuen Status.
	 *
	 * @param vorgang
	 * @param newStatusId
	 * @throws StatusuebergangVerbotenException
	 *             Wenn der Nutzer nicht die nötigen Rechte besitzt
	 */
	void statusUebergang(Vorgang vorgang, Long newStatusId) throws StatusuebergangVerbotenException;

	/**
	 * Gibt einen Vorgang zur Bearbeitung frei
	 *
	 * @param vorgang
	 * @throws VorgangGesperrtException
	 *             wenn jemand anderes den Vorgang gesperrt hat
	 */
	void zurBearbeitungFreigeben(Vorgang vorgang) throws VorgangGesperrtException;
}
