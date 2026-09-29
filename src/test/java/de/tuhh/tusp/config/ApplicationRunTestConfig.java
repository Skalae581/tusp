package de.tuhh.tusp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DatabaseConfig.class, PropertiesConfig.class })
public class ApplicationRunTestConfig {

}
