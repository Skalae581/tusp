package de.tuhh.tusp.app.fame.connector;

import java.util.Map;
import java.util.Set;

import de.tuhh.tusp.app.fame.connector.pojo.ConnectorPojo;

public interface Case {

	/**
	 * Gibt einen eindeutigen Bezeichner des Datensatzes auf dem QuellSystem zurück.
	 *
	 * @return ID
	 */
	String getId();

	/**
	 * Gibt an, ob der Vorgang richtig importiert werden konnte
	 *
	 * @return leer, wenn richtig importiert
	 */
	String getImportErrors();

	/**
	 * Gibt die Eigenschaften des Datensatzes zurück.
	 *
	 * @return Map mit Eigenschaften in {@link ConnectorPojo}
	 */
	Map<String, ConnectorPojo> getProperties();

	/**
	 * Holt eine Eigenschaft anhand ihres Namen.
	 *
	 * @param name
	 *            Key
	 * @return Value
	 */
	default ConnectorPojo getPropertyByName(String name) {

		return getProperties().get(name);
	}

	/**
	 * Gibt ein Set aller vorhandenen Keys zurück.
	 *
	 * @return Set mit Keys
	 */
	default Set<String> getPropertyNames() {

		return getProperties().keySet();
	}

	Validity getValid();

	/**
	 * Läd alles Felder, die unter Umständen noch nicht geladen wurden.
	 *
	 * Wenn Exceptions auftreten, werden die nur in die Import-Errors schrieben
	 */
	void lazyLoad();

	void setValid(Validity v);
}
