package de.tuhh.tusp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.tuhh.tusp.utils.DatabaseUpdater;
import de.tuhh.tusp.utils.DatabaseUpdaterTestImpl;

/**
 * Konfiguration für die Datenbankbelange der UnitTests, inklusive JOOQ und
 * TransaktionManagement.
 *
 * @author rztjm
 *
 */
@Configuration
@EnableTransactionManagement
public class UnitTestDatabaseConfig {

	private static Logger logger = LogManager.getLogger(UnitTestDatabaseConfig.class);

	@Autowired
	ApplicationContext context;

	private String url = "jdbc:h2:mem:";
	private String username = "sa";
	private String password = "";
	private String driverClassName = "org.h2.Driver";

	@Bean
	public DataSourceConnectionProvider connectionProvider(TransactionAwareDataSourceProxy dataSource) {

		return new DataSourceConnectionProvider(dataSource);
	}

	/**
	 * Erzeugt die eigentliche DataSource.
	 *
	 * @return
	 */
	@Bean
	public BasicDataSource getDataSource() {

		logger.debug("Erstelle DataSource " + url);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	/**
	 * Erzeugt den JOOQ Context.
	 *
	 * @param configuration Die verwendete Konfiguration
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DSLContext getDslContext(org.jooq.Configuration configuration, DatabaseUpdater dbUpdater) throws Exception {

		DSLContext context = new DefaultDSLContext(configuration);

		logger.debug("Setze Testdatenbank auf...");
		dbUpdater.buildTestTables(context);

		return context;
	}

	/**
	 * Die JOOQ-Konfiguration
	 *
	 * @param connectionProvider
	 * @return
	 */
	@Bean
	public org.jooq.Configuration getJooqConfiguration(TransactionAwareDataSourceProxy proxy) {

		SQLDialect d = SQLDialect.H2;
		DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(proxy);
		org.jooq.Configuration configuration = new DefaultConfiguration();
		configuration.set(connectionProvider);
		configuration.set(d);

		return configuration;
	}

	/**
	 * TransaktionManager für Spring
	 *
	 * @param dataSource
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager getTransactionManager(BasicDataSource dataSource) {

		logger.debug("Erstelle TransaktionManager");
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public DatabaseUpdater getUpdater() {

		return new DatabaseUpdaterTestImpl();
	}

	/**
	 * DataSource für das JOOQ-Transaktionsmanagement
	 *
	 * @param dataSource
	 * @return
	 */
	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSource(BasicDataSource dataSource) {

		return new TransactionAwareDataSourceProxy(dataSource);
	}
}
