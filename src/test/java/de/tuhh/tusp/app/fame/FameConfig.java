package de.tuhh.tusp.app.fame;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp.app.fame.dao", "de.tuhh.tusp.app.fame.service", "de.tuhh.tusp.utils",
		"de.tuhh.tusp.global" }, lazyInit = true)
public class FameConfig {

}
