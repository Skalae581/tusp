package de.tuhh.tusp.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.global.service.ConfigService;

@Configuration
@Import({ ApplicationIntegrationTestConfig.class })
@ComponentScan(basePackages = "de.tuhh.tusp.global")
@EnableTransactionManagement
public class ApplicationPerformanceTestConfig {

	@Bean
	public URI getSiteBase(ConfigService config) throws URISyntaxException, IOException {

		String url = config.get("de.tuhh.tusp.test.selenium.url");

		if (url == null) {
			throw new RuntimeException("de.tuhh.tusp.test.selenium.url ist nicht gesetzt. "
					+ "Er sollte vermutlich http://127.0.0.1:8080/tusp/ sein.");
		}

		return new URI(url);
	}
}
