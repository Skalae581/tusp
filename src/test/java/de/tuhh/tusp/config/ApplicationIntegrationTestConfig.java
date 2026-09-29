package de.tuhh.tusp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

@Configuration
@Import({ DatabaseConfig.class, PropertiesConfig.class, SecurityConfiguration.class, ApplicationWebConfig.class,
		WebserviceWebConfig.class })
@ComponentScan(basePackages = { "de.tuhh.tusp" },
		excludeFilters = { @Filter(Configuration.class), @Filter(Controller.class) }, lazyInit = true)
@EnableTransactionManagement
public class ApplicationIntegrationTestConfig {

}
