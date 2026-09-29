package de.tuhh.tusp.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;

@Configuration
@SuppressWarnings("rawtypes")
public class MailTestConfig implements ApplicationListener {

	private static final int PORT = 2525;
	private SmtpServer server = null;

	@Bean
	public SmtpServer getMailServer() {

		ServerOptions options = new ServerOptions();
		options.withSmtpPort(PORT);
		server = SmtpServerFactory.startServer(options);

		return server;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof ContextClosedEvent && server != null) {
			server.clearMessages();
			try {
				server.stop();
			}
			catch (InterruptedException e) {}
		}
	}

}
