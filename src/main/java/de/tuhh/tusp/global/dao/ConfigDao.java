package de.tuhh.tusp.global.dao;

import java.util.List;

import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.utils.BaseDao;

public interface ConfigDao extends BaseDao<Config> {

	/**
	 * Holt einen Konfigeintrag anhand des Keys. Gibt null zurück, wenn der Key nicht existert.
	 *
	 * @param key
	 * @return den Value oder null
	 */
	Config fetchByKey(String key);

	/**
	 * Holt alle Konfigeinträge, die mit dem Prefix beginnen
	 *
	 * @param prefix
	 *            Null-Werte werden zu "" (Empty String)
	 * @return Liste der Treffer
	 */
	List<Config> findByKeyPrefix(String prefix);

	/**
	 * Sucht nach Werten mit dem SQL LIKE-Operator
	 *
	 * @param keyPattern
	 * @param valuePattern
	 * @return Liste der Treffer
	 */
	List<Config> search(String keyPattern, String valuePattern);

	/**
	 * Versucht eine Abfrage auf der Datenbank auszuführen
	 */
	void testConnection();

}
