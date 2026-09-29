package de.tuhh.tusp.webservice.sync.service;

public interface WebserviceSync {

	/**
	 * Gibt eine Nummer zurück nach der die Import-Services sortiert werden. Mit hilfe dieser Nummer kann sichergestellt
	 * werden, dass alle Abhängigkeiten in den importierten Daten erfüllt werden.
	 *
	 * @return die Nummer
	 */
	int orderNum();

	/**
	 * Liest die Daten des Webservices ein, wandelt sie in TUSP-Objekte um und erstellt oder aktualisiert damit die
	 * TUSP-Cachetabellen.
	 */
	void sync();
}
