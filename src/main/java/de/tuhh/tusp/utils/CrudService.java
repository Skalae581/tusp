package de.tuhh.tusp.utils;

import java.util.List;
import java.util.NoSuchElementException;

public interface CrudService<T> {

	/**
	 * Löscht einen Datensatz aus der Datenbank mit eingegebene ID
	 *
	 * @see BaseDao#delete(Long)
	 *
	 * @param id
	 *            ID des Datensatzes
	 * @return Anzahl der gelöschten Datensätze (1 wenn Datensatz existierte, sonst 0)
	 */
	int delete(Long id);

	/**
	 * Liefert einen Datensatz falls vorhanden
	 *
	 * @see BaseDao#fetchById(Long)
	 *
	 * @param id
	 *            ID des Datensatzes
	 *
	 *
	 * @throws NoSuchElementException
	 *             wenn kein Objekt gefunden
	 *
	 * @return result Pojo-Objekt
	 */
	T fetchById(Long id);

	/**
	 * Liefert alle Datensätze aus einer Tabelle
	 *
	 * @see BaseDao#findAll()
	 *
	 * @return Liste aller gefundenen Datensätze
	 */
	List<T> findAll();

	/**
	 * Speichert ein Objekt in der Datenbank als neuen Datensatz.
	 *
	 * @see BaseDao#insert(Object)
	 *
	 * @param object
	 *            Das Objekt
	 * @return generierte ID des neuen Datensatzes
	 */
	Long insert(T object);

	/**
	 * Speichert die Änderungen an einem bestehenden Objekt in der Datenbank.
	 *
	 * @see BaseDao#update(Object)
	 *
	 * @param object
	 *            Das Objekt.
	 */
	void update(T object);
}
