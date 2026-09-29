package de.tuhh.tusp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadConfig {

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

		// Größe ist kritisch! Wenn zu klein, laufen Tasks unregelmäßig
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}
}