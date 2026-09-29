package de.tuhh.tusp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp.utils" }, lazyInit = true)
public class UtilsTestConfig {

}
