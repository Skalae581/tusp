package de.tuhh.tusp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author rztjj
 *
 *         Spring Root-Konfigurationsklasse Konfigurirert den PropertyPlaceholderConfigurer der aus der 'Properties'
 *         Datei liest
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "de.tuhh.tusp" })
@Import({ PropertiesConfig.class })
@EnableScheduling
public class ApplicationRootConfig {

}