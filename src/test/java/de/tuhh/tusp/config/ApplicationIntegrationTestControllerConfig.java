package de.tuhh.tusp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

@Configuration
@Import({ UnitTestDatabaseConfig.class, PropertiesConfig.class, SecurityConfiguration.class, ApplicationWebConfig.class,
		WebserviceWebConfig.class })
@ComponentScan(basePackages = { "de.tuhh.tusp" }, excludeFilters = { @Filter(Configuration.class) }, lazyInit = true)
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationIntegrationTestControllerConfig {

}
