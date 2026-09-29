package de.tuhh.tusp.config;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

// Hier nicht die DataBaseConfig laden!
@Configuration
public class PropertiesConfig {

	private static Logger logger = LogManager.getLogger(PropertiesConfig.class);

	/**
	 * Holt eine Resource mit den DB-Properties
	 *
	 * @return die Properties-Datei
	 * @throws IOException wenn die Datei nicht lesbar ist
	 */
	public static Resource getPropertiesFilePath() throws IOException {

		File path;
		String param = System.getProperty("tusp.dbconf.path");
		if (param != null) {
			path = new File(param);
			logger.info("try loading properties from " + path.getAbsolutePath());
			if (path.exists()) {
				return new FileSystemResource(path);
			}

			ClassPathResource res = new ClassPathResource(param);
			logger.info("try loading properties from classpath:" + res.getPath());
			if (res.exists()) {
				return res;
			}

			path = new File(System.getProperty("user.home") + "/" + param);
			logger.info("try loading properties from " + path.getAbsolutePath());
			if (path.exists()) {
				return new FileSystemResource(path);
			}

			throw new RuntimeException("File not found: " + param);
		}

		path = new File(System.getProperty("user.home") + "/tusp-db.properties");
		if (path.exists()) {
			logger.info("loading properties from " + path.getAbsolutePath());
			return new FileSystemResource(path);
		}
		logger.debug("Konfig nicht gefunden: " + path.getAbsolutePath());

		ClassPathResource res = new ClassPathResource("tusp-db.properties");
		if (res.exists()) {
			logger.info("loading properties from classpath:" + res.getPath());
			return res;
		}
		logger.debug("Konfig nicht gefunden: " + res.getFile().getAbsolutePath());

		String msg = "Es wurde keine Datenbank-Konfiguration gefunden. "
				+ "Testsysteme sollten eine tusp-db.properties Datei haben. "
				+ "Produktiv soll eine tusp-db.properties im Home des Users liegen. "
				+ " Es ist auch zulässig, den Pfad zur Datei als Startparameter anzugeben: -Dtusp.dbconf.path=/absoluter/pfad.properties";

		logger.error(msg);

		throw new RuntimeException(msg);
	}

	/**
	 * Erstellt den PropertyPlaceholderConfigurer
	 *
	 * @return Configurer
	 * @throws IOException wenn die Datei nicht lesbar ist
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public PropertyPlaceholderConfigurer getConfigurer() throws IOException {

		Resource props = getPropertiesFilePath();
		logger.info("Verwende Konfiguration aus " + props.getFile().getAbsolutePath());
		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		configurer.setLocation(props);
		return configurer;
	}

	/**
	 * PropertiesFactoryBean
	 *
	 * @return PropertiesFactoryBean
	 * @throws IOException wenn die Propertiesdatei nicht lesbar ist
	 */
	@Bean
	public PropertiesFactoryBean getPropFactoryBean() throws IOException {

		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(getPropertiesFilePath());
		return bean;
	}
}
