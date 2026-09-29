package de.tuhh.tusp.app.precheck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import de.tuhh.tusp.utils.MessageContainer;

@Configuration
@ComponentScan(basePackages = { "de.tuhh.tusp.app.precheck.dao", "de.tuhh.tusp.app.precheck.utils",
		"de.tuhh.tusp.app.precheck.service" })
public class PrecheckWebConfig {

	@Bean
	public MessageContainer getMessageConteiner() {

		return new MessageContainer();
	}
}
