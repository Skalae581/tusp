package de.tuhh.tusp.utils;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface für die grundlegenden Dao-Operationen.
 *
 * @author rztjm
 *
 * @param <T> Eine beliebige Pojo-Klasse
 */
public interface BaseDao<T> {

	/**
	 * Löscht einen Datensatz aus der Datenbank mit eingegebene ID
	 *
	 * @param id ID des Datensatzes
	 *
	 * @return Anzahl der gelöschten Datensätze (1 wenn Datensatz existierte, sonst
	 *         0)
	 */
	int delete(Long id);

	/**
	 * Löscht alle Datensätze einer Tabelle.
	 *
	 * Ist nur für Testzwecke gedacht.
	 */
	void deleteAll();

	/**
	 * Liefert einen Datensatz falls vorhanden
	 *
	 * @param id ID des Datensatzes
	 *
	 *
	 * @throws NoSuchElementException wenn kein Objekt gefunden
	 *
	 * @return result Pojo-Objekt
	 */
	T fetchById(Long id);

	/**
	 * Liefert alle Datensätze aus einer Tabelle
	 *
	 * @return Liste aller gefundenen Datensätze
	 */
	List<T> findAll();

	/**
	 * Liefert die Anzahl der gefundenen Datensätze
	 *
	 * @return Anzahl der Datensätze
	 */
	int getCount();

	/**
	 * Speichert ein Objekt in der Datenbank als neuen Datensatz.
	 *
	 * @param object Das Objekt
	 * @return generierte ID des neuen Datensatzes
	 */
	Long insert(T object);

	/**
	 * Speichert die Änderungen an einem bestehenden Objekt in der Datenbank.
	 *
	 * @param object Das Objekt.
	 */
	void update(T object);

}
