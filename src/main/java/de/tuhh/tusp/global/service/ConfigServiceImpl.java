package de.tuhh.tusp.global.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.global.pojo.Config;

@Component
public class ConfigServiceImpl implements ConfigService {

	private static Logger logger = LogManager.getLogger(ConfigServiceImpl.class);

	private Properties propertiesFromFile;

	@Autowired
	private ConfigDao dao;

	/**
	 * Erstellt eine neue Instanz
	 *
	 * @param propFactory
	 * @throws IOException Wenn die Properties nicht eingelesen werden können
	 */
	@Autowired
	public ConfigServiceImpl(PropertiesFactoryBean propFactory) throws IOException {
		propertiesFromFile = propFactory.getObject();
	}

	@Override
	public int delete(Long id) {
		return dao.delete(id);
	}

	@Override
	public Config fetchById(Long id) {
		return dao.fetchById(id);
	}

	@Override
	public List<Config> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Config> findByKeyPrefix(String prefix) {

		return dao.findByKeyPrefix(prefix);
	}

	@Override
	public String get(String key) {

		return get(key, null, false);
	}

	@Override
	public int get(String key, int defaolt) {

		return get(key, defaolt, false);
	}

	@Override
	public int get(String key, int defaolt, boolean create) {

		String value = get(key, defaolt + "", create);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			logger.warn("Der Configeintrag " + key + " = " + value
					+ " konnte nicht geparst werden, verwende daher den Default-Wert " + defaolt);
			return defaolt;
		}
	}

	@Override
	public long get(String key, long defaolt) {

		return get(key, defaolt, false);
	}

	@Override
	public long get(String key, long defaolt, boolean create) {

		String value = get(key, defaolt + "", create);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			logger.warn("Der Configeintrag " + key + " = " + value
					+ " konnte nicht geparst werden, verwende daher den Default-Wert " + defaolt);
			return defaolt;
		}
	}

	@Override
	public String get(String key, String defaolt) {

		return get(key, defaolt, false);
	}

	@Override
	public String get(String key, String defaolt, boolean create) {

		String propery = System.getProperty(key);
		if (propery != null) {
			logger.debug("Nutze Konfigwert " + key + " aus der System-Property, nicht der DB!");
			return propery;
		}

		if (propertiesFromFile.containsKey(key)) {
			logger.debug("Nutze Konfigwert " + key + " aus der Konfigdatei, nicht der DB!");
			return propertiesFromFile.getProperty(key);
		}

		Config value = dao.fetchByKey(key);
		if (value == null) {
			if (create) {
				value = new Config();
				value.setId(0L);
				value.setKey(key);
				value.setValue(defaolt);
				dao.insert(value);
			}

			return defaolt;
		}
		return value.getValue();
	}

	@Override
	public Long getIfNull(Long value, String key, Long defaolt, boolean create) {

		if (value != null) {
			return value;
		}
		return get(key, defaolt, create);
	}

	@Override
	public String getIfNull(String value, String key, String defaolt, boolean create) {

		if (value != null) {
			return value;
		}
		return get(key, defaolt, create);
	}

	@Override
	public Properties getPropertiesFromConfigfile() {

		return propertiesFromFile;
	}

	/**
	 * Speichert einen Config Eintrag
	 *
	 * Achtung: Es wird ein HTML-Unescape durchgeführt!
	 */
	@Override
	public Long insert(Config object) {

		object.setKey(object.getKey().trim());
		return dao.insert(object);
	}

	@Override
	public void set(String key, String value) {

		key = key.trim();

		Config config = dao.fetchByKey(key);
		if (config == null) {
			logger.info("Kein Konfigeintrag für " + key + " gefunden. Erstelle neuen.");
			config = new Config();
			config.setKey(key);
			config.setValue(value);
			dao.insert(config);
		} else {
			config.setValue(value);
			dao.update(config);
			propertiesFromFile.put(key, value);
		}
	}

	@Override
	public void testConnection() {

		dao.testConnection();
	}

	/**
	 * Speichert einen Config Eintrag
	 *
	 * Achtung: Es wird ein HTML-Unescape durchgeführt!
	 */
	@Override
	public void update(Config object) {

		object.setKey(object.getKey().trim());
		dao.update(object);
	}

}
