package de.tuhh.tusp.app.vorabcheck;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp.app.vorabcheck.dao", "de.tuhh.tusp.app.vorabcheck.service",
		"de.tuhh.tusp.utils" }, lazyInit = true)
public class VorabcheckConfig {

}
