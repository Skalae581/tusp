package de.tuhh.tusp.config;

import org.apache.logging.log4j.LogManager;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.DatabaseUpdater;

/**
 * Nach dem der ApplicationContext neugeladen wird ruf dieser Listener die
 * Start-Methode automatisch aus webservice Main Klasse auf. Ab dann wird die
 * Methode immer wieder über den Scheduled-Task aufgerufen.
 *
 * @author rztjj
 *
 */
@Component
public class ContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	DatabaseUpdater updater;

	@Autowired
	DSLContext context;

	@Autowired
	ApplicationContext appContext;

	/**
	 * Wird jedes mal aufgerufen, wenn der Spring-Kontext aktualisiert ist
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Starte den Datenbank-Updater synchron
		try {
			new Thread() {

				@Override
				public void run() {

					if (event.getSource() != appContext) {
						return;
					}

					try {
						updater.initDb(context);
					} catch (Exception e) {
						LogManager.getLogger(ContextListener.class).error("Datenbankupdate fehlgeschlagen", e);

					}
				}
			}.start();
		} catch (Exception e) {
			LogManager.getLogger(ContextListener.class).error("Datenbankupdate Threat fehlgeschlagen", e);

		}
	}
}
