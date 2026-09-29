package de.tuhh.tusp.app.fame.connector.pojo;

import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;

/**
 * Interface für die Verschiedenen Datentypen, die aus dem Connector kommen können (SingleLineText, MultiLineText,....)
 *
 * @author rztjm
 *
 */
public interface ConnectorPojo {

	/**
	 * Speichert den eigenen Wert in dem Input der als Parameter übergeben wurde
	 *
	 * @param input
	 * @throws PflichtfeldException
	 *             wenn ein Pflichtfeld nicht gesetzt ist
	 * @throws ImportException
	 *             Wenn die Schnittestelle keine Daten liefert
	 * @throws RegexException
	 *             Wenn eine Eingabe nicht dem geforderten Format entspricht
	 */
	void save(ExtendedInput input) throws ImportException, RegexException, PflichtfeldException;
}
