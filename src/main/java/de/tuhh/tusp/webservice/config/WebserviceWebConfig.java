package de.tuhh.tusp.webservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp.webservice", "de.tuhh.tusp.global.service", "de.tuhh.tusp.global.dao",
		"de.tuhh.tusp.global.pojo" }, excludeFilters = {
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "de.tuhh.tusp.webservice.sync.main.*"),
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class WebserviceWebConfig {

	public WebserviceWebConfig() {

		// Deaktiviere die nerfigen Logausgeben der Webservice-API
		// Suboptimal policy alternative selected on the client side with
		// fitness "UNKNOWN"

		// LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);

		// Configurator.setLevel("de.tuhh.tusp.webservice.config", Level.OFF);
	}
}
