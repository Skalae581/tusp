package de.tuhh.tusp.webservice.sync.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.utils.schedule.Scheduler;
import de.tuhh.tusp.webservice.sync.service.WebserviceSync;

/**
 * Befehl zum Importieren eines Webservices (Ohne Zeilenumbrüche): C:\Program Files\Java\jdk1.8.0_31\bin\wsimport.exe
 * -XadditionalHeaders -verbose -s ./src/main/java -d ./target/classes/
 * https://portal-tst.rz.tuhh.de/qisserver/services2/RightsAndRolesService?wsdl
 *
 * Falls die Daten, die von WebService augerufen werden sich ändern, wird ein Update durchgeführt. Die Daten die zur
 * Zeit geladen werden sind Länder, Studiengänge, Abschlüsse, Benutzer-Rollen, und Zeiträume
 *
 *
 * @author rztjm
 *
 */
@Component
public class Main implements Schedulable {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private CalendarUtils calendar;

	@Override
	public void start(JobLogger jobLogger) {

		Map<String, WebserviceSync> beans = applicationContext.getBeansOfType(WebserviceSync.class);
		ArrayList<WebserviceSync> beansList = new ArrayList<>();
		beansList.addAll(beans.values());

		Collections.sort(beansList, new Comparator<WebserviceSync>() {

			@Override
			public int compare(WebserviceSync e1, WebserviceSync e2) {

				if (e1.orderNum() < e2.orderNum()) {
					return -1;
				}
				else if (e1.orderNum() > e2.orderNum()) {
					return 1;
				}
				else {
					return 0;
				}

			}
		});

		for (WebserviceSync bean : beansList) {

			StopWatch stopWatch = new StopWatch();
			jobLogger.debug("Starte Webserviceimport für Bean " + bean.getClass().getName());
			stopWatch.start();

			try {

				bean.sync();
			}
			catch (Exception e) {
				jobLogger.error("Fehler im Sync von Bean " + bean.getClass().getName(), e);
			}

			stopWatch.stop();
			jobLogger.debug("Beende Webserviceimport für Bean " + bean.getClass().getName() + " nach "
					+ stopWatch.getTime() + "ms");

		}

	}

	@Override
	public String suggestName() {

		String now = CalendarUtils.toString(calendar.getNowAsXMLGregorianCalendar(), "yyyy-MM-dd kk:mm");
		return this.getClass().getName() + " " + now;
	}

	/**
	 * Führt regelmäßig einen Sync aller gecacheten Daten aus HIO durch.
	 */
	@Scheduled(cron = "0 0 2 * * *")
	public void trigger() {

		scheduler.schedule(getClass(), suggestName());
	}

}
