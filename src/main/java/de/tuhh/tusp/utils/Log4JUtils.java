package de.tuhh.tusp.utils;

import org.apache.logging.log4j.core.Appender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;

@Component
public class Log4JUtils {

	@Autowired
	private ConfigService config;

	public void addAppenderToLogger(String logger, Appender appender) {
//
//		Logger log = LogManager.getLogger(logger);
//		log.addAppender(appender);
//		log.setLevel(Level.DEBUG);
	}

	public Appender createJDBCAppenderIfNotExists(String name, String tableName) {

//		Logger root = Logger.getRootLogger();
//		Appender existingAppender = root.getAppender(name);
//
//		if (existingAppender == null) {
//
//			JDBCAppender appender = new JDBCAppender();
//			appender.setName(name);
//			appender.setDriver(config.get("driverClass"));
//			appender.setURL(config.get("url"));
//			appender.setUser(config.get("username"));
//			appender.setPassword(config.get("password"));
//			appender.setLayout(new PatternLayout());
//			appender.setSql("INSERT INTO " + tableName + " (message, class, priority, log_date) "
//					+ "VALUES ('%m', '%c', '%p', to_timestamp('%d', 'YYYY-MM-DD HH24:MI:SS,MS'))");
//			existingAppender = appender;
//		}
//		return existingAppender;
		return null;
	}
}