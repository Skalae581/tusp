package de.tuhh.tusp.global.service;

import java.util.List;
import java.util.Properties;

import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.utils.CrudService;

public interface ConfigService extends CrudService<Config> {

	static final String ROLES_KEY = "de.tuhh.tusp.app.fame.roles.";
	static final String USERPREFS_KEY = "de.tuhh.tusp.userprefs.";

	/**
	 * Holt alle Konfigeinträge, die mit dem Prefix beginnen
	 *
	 * @param prefix
	 * @return Liste der Einträge
	 */
	List<Config> findByKeyPrefix(String prefix);

	/**
	 * Holt einen Konfigeintrag aus der DB. Gibt null zurück, key nicht existert.
	 *
	 * @param key
	 * @return den Eintrag oder null
	 */
	String get(String key);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert. Sollte der Wert in
	 * der DB nicht convertierbar sein, wird auch der defaolt zurückgegeben.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @return den Eintrag oder defaolt
	 */
	int get(String key, int defaolt);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert und legt ihn in der
	 * DB an. Sollte der Wert in der DB nicht convertierbar sein, wird auch der defaolt zurückgegeben.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @param create
	 *            Erstellt einen Konfigeintrag, wenn er noch nicht existert
	 * @return den Eintrag oder defauolt
	 */
	int get(String key, int defaolt, boolean create);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert. Sollte der Wert in
	 * der DB nicht convertierbar sein, wird auch der defaolt zurückgegeben.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @return den Eintrag oder defauolt
	 */
	long get(String key, long defaolt);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert und legt ihn in der
	 * DB an. Sollte der Wert in der DB nicht convertierbar sein, wird auch der defaolt zurückgegeben.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @param create
	 *            Erstellt einen Konfigeintrag, wenn er noch nicht existert
	 * @return den Eintrag oder defauolt
	 */
	long get(String key, long defaolt, boolean create);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @return den Eintrag oder defauolt
	 */
	String get(String key, String defaolt);

	/**
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert und legt ihn in der
	 * DB an.
	 *
	 * @param key
	 * @param defaolt
	 *            Ja, mit o weil "default" ein Keyword ist
	 * @param create
	 *            Erstellt einen Konfigeintrag, wenn er noch nicht existert
	 * @return den Eintrag oder defauolt
	 */
	String get(String key, String defaolt, boolean create);

	/**
	 * Gibt den Wert von value zurück, wenn dieser nicht null ist. Sonst:
	 *
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert und legt ihn in der
	 * DB an.
	 *
	 * @param value
	 *            Der Wert der primär gewünscht ist
	 * @param key
	 *            Der Konfigkey
	 * @param defaolt
	 *            Ein Defaultwert, fall in der Config nichts zu finden ist
	 * @param create
	 *            Sollen ein neuer Eintrag in die Config, wenn dort keiner ist?
	 * @return Den Wert von value oder den Wert aus der Config, wenn value null ist.
	 */
	Long getIfNull(Long value, String key, Long defaolt, boolean create);

	/**
	 * Gibt den Wert von value zurück, wenn dieser nicht null ist. Sonst:
	 *
	 * Holt einen Konfigeintrag aus der Datenbank und gibt defaolt zurück, wenn key nicht existert und legt ihn in der
	 * DB an.
	 *
	 * @param value
	 *            Der Wert der primär gewünscht ist
	 * @param key
	 *            Der Konfigkey
	 * @param defaolt
	 *            Ein Defaultwert, fall in der Config nichts zu finden ist
	 * @param create
	 *            Sollen ein neuer Eintrag in die Config, wenn dort keiner ist?
	 * @return Den Wert von value oder den Wert aus der Config, wenn value null ist.
	 */
	String getIfNull(String value, String key, String defaolt, boolean create);

	/**
	 * Liefert alle Properties, die beim Systemstart eingelesen wurden.
	 *
	 * @return
	 */
	Properties getPropertiesFromConfigfile();

	/**
	 * Aktualisiert einen Config eintrag @param value @param key @throws
	 */
	void set(String key, String value);

	/**
	 * @see ConfigDao#testConnection()
	 */
	void testConnection();
}
