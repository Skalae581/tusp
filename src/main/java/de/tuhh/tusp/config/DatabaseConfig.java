package de.tuhh.tusp.config;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StopWatch;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import de.tuhh.tusp.utils.DatabaseUpdater;
import de.tuhh.tusp.utils.DatabaseUpdaterImpl;
import de.tuhh.tusp.utils.DatabaseUpdaterTestImpl;

/**
 * Konfiguration für die gesamten Datenbankbelange, inklusive JOOQ und
 * TransaktionManagement.
 *
 * @author rztjm
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	private class TuspDataSource extends HikariDataSource {

		public TuspDataSource(HikariConfig configuration) {
			super(configuration);
		}

		@Override
		public Connection getConnection() throws SQLException {

			Connection c = super.getConnection();
			Exception ex = new Exception("ConnectionOpenCaptureException :)");
			StopWatch watch = new StopWatch();
			watch.start();

			Connection proxy = new Connection() {

				@Override
				public void abort(Executor executor) throws SQLException {

					c.abort(executor);
				}

				@Override
				public void clearWarnings() throws SQLException {

					c.clearWarnings();
				}

				@Override
				public void close() throws SQLException {

					c.close();
					watch.stop();
					logger.trace("Schließe Transaktion: " + this);
					if (watch.getLastTaskTimeMillis() > 5000) {
						logger.warn("Connection war " + watch.getLastTaskTimeMillis() / 1000 + " Sekunden offen", ex);
					}
				}

				@Override
				public void commit() throws SQLException {

					c.commit();
				}

				@Override
				public Array createArrayOf(String typeName, Object[] elements) throws SQLException {

					return c.createArrayOf(typeName, elements);
				}

				@Override
				public Blob createBlob() throws SQLException {

					return c.createBlob();
				}

				@Override
				public Clob createClob() throws SQLException {

					return c.createClob();
				}

				@Override
				public NClob createNClob() throws SQLException {

					return c.createNClob();
				}

				@Override
				public SQLXML createSQLXML() throws SQLException {

					return c.createSQLXML();
				}

				@Override
				public Statement createStatement() throws SQLException {

					return c.createStatement();
				}

				@Override
				public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {

					return c.createStatement(resultSetType, resultSetConcurrency);
				}

				@Override
				public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
						throws SQLException {

					return c.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
				}

				@Override
				public Struct createStruct(String typeName, Object[] attributes) throws SQLException {

					return c.createStruct(typeName, attributes);
				}

				@Override
				public boolean getAutoCommit() throws SQLException {

					return c.getAutoCommit();
				}

				@Override
				public String getCatalog() throws SQLException {

					return c.getCatalog();
				}

				@Override
				public Properties getClientInfo() throws SQLException {

					return c.getClientInfo();
				}

				@Override
				public String getClientInfo(String name) throws SQLException {

					return c.getClientInfo(name);
				}

				@Override
				public int getHoldability() throws SQLException {

					return c.getHoldability();
				}

				@Override
				public DatabaseMetaData getMetaData() throws SQLException {

					return c.getMetaData();
				}

				@Override
				public int getNetworkTimeout() throws SQLException {

					return c.getNetworkTimeout();
				}

				@Override
				public String getSchema() throws SQLException {

					return c.getSchema();
				}

				@Override
				public int getTransactionIsolation() throws SQLException {

					return c.getTransactionIsolation();
				}

				@Override
				public Map<String, Class<?>> getTypeMap() throws SQLException {

					return c.getTypeMap();
				}

				@Override
				public SQLWarning getWarnings() throws SQLException {

					return c.getWarnings();
				}

				@Override
				public boolean isClosed() throws SQLException {

					return c.isClosed();
				}

				@Override
				public boolean isReadOnly() throws SQLException {

					return c.isReadOnly();
				}

				@Override
				public boolean isValid(int timeout) throws SQLException {

					return c.isValid(timeout);
				}

				@Override
				public boolean isWrapperFor(Class<?> iface) throws SQLException {

					return c.isWrapperFor(iface);
				}

				@Override
				public String nativeSQL(String sql) throws SQLException {

					return c.nativeSQL(sql);
				}

				@Override
				public CallableStatement prepareCall(String sql) throws SQLException {

					return c.prepareCall(sql);
				}

				@Override
				public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
						throws SQLException {

					return c.prepareCall(sql, resultSetType, resultSetConcurrency);
				}

				@Override
				public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
						int resultSetHoldability) throws SQLException {

					return c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
				}

				@Override
				public PreparedStatement prepareStatement(String sql) throws SQLException {

					return c.prepareStatement(sql);
				}

				@Override
				public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {

					return c.prepareStatement(sql, autoGeneratedKeys);
				}

				@Override
				public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
						throws SQLException {

					return c.prepareStatement(sql, resultSetType, resultSetConcurrency);
				}

				@Override
				public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
						int resultSetHoldability) throws SQLException {

					return c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
				}

				@Override
				public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {

					return c.prepareStatement(sql, columnIndexes);
				}

				@Override
				public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {

					return c.prepareStatement(sql, columnNames);
				}

				@Override
				public void releaseSavepoint(Savepoint savepoint) throws SQLException {

					c.releaseSavepoint(savepoint);
				}

				@Override
				public void rollback() throws SQLException {

					c.rollback();
				}

				@Override
				public void rollback(Savepoint savepoint) throws SQLException {

					c.rollback(savepoint);
				}

				@Override
				public void setAutoCommit(boolean autoCommit) throws SQLException {

					c.setAutoCommit(autoCommit);
				}

				@Override
				public void setCatalog(String catalog) throws SQLException {

					c.setCatalog(catalog);
				}

				@Override
				public void setClientInfo(Properties properties) throws SQLClientInfoException {

					c.setClientInfo(properties);
				}

				@Override
				public void setClientInfo(String name, String value) throws SQLClientInfoException {

					c.setClientInfo(name, value);
				}

				@Override
				public void setHoldability(int holdability) throws SQLException {

					c.setHoldability(holdability);
				}

				@Override
				public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

					c.setNetworkTimeout(executor, milliseconds);
				}

				@Override
				public void setReadOnly(boolean readOnly) throws SQLException {

					c.setReadOnly(readOnly);
				}

				@Override
				public Savepoint setSavepoint() throws SQLException {

					return c.setSavepoint();
				}

				@Override
				public Savepoint setSavepoint(String name) throws SQLException {

					return c.setSavepoint(name);
				}

				@Override
				public void setSchema(String schema) throws SQLException {

					c.setSchema(schema);
				}

				@Override
				public void setTransactionIsolation(int level) throws SQLException {

					c.setTransactionIsolation(level);
				}

				@Override
				public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

					c.setTypeMap(map);
				}

				@Override
				public <T> T unwrap(Class<T> iface) throws SQLException {

					return c.unwrap(iface);
				}
			};

			logger.trace("Starte Transaktion: " + proxy);

			return proxy;
		}
	}

	static final Logger logger = LogManager.getLogger(DatabaseConfig.class);

	@Value("${url}")
	private String url;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Value("${driverClass}")
	private String driverClassName;

	@Value("${dialect}")
	private String dialect;

	@PreDestroy
	public void closeConnections() {

		// This manually deregisters JDBC driver, which prevents Tomcat from complaining
		// about memory leaks wrto this
		// class
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.info(String.format("deregistering jdbc driver: %s", driver));
			} catch (SQLException e) {
				logger.error(String.format("Error deregistering driver %s", driver), e);
			}

		}
	}

	/**
	 * Erzeugt DatabaseUpdater
	 *
	 * @return DatabaseUpdater
	 */
	@Bean
	public DatabaseUpdater getDatabaseUpdater() {

		if ("h2".equals(dialect)) {
			return new DatabaseUpdaterTestImpl();
		}

		return new DatabaseUpdaterImpl();
	}

	/**
	 * Erzeugt die eigentliche DataSource.
	 *
	 * @return DataSource
	 * @throws ClassNotFoundException Wenn der Datenbank-Treiber fehlt
	 */
	@Bean
	public DataSource getDataSource() throws ClassNotFoundException {

		logger.info("Baue Datenbankverbindung auf: " + url);
		Class.forName(driverClassName);
		HikariConfig configuration = new HikariConfig();
		configuration.setUsername(username);
		configuration.setJdbcUrl(url);
		configuration.setPassword(password);
		configuration.setMaximumPoolSize(10);
		configuration.setDriverClassName(driverClassName);
		configuration.setLeakDetectionThreshold(60 * 1000);

		return new TuspDataSource(configuration);
	}

	/**
	 * Erzeugt den JOOQ Context.
	 *
	 * @param configuration Die verwendete Konfiguration
	 * @return DSLContext
	 */
	@Bean
	public DSLContext getDslContext(org.jooq.Configuration configuration) {

		return new DefaultDSLContext(configuration);
	}

	/**
	 * Die JOOQ-Konfiguration
	 *
	 * @param connectionProvider
	 * @return DefaultConfiguration
	 */
	@Bean
	public DefaultConfiguration getJooqConfiguration(TransactionAwareDataSourceProxy proxy) {

		SQLDialect d;
		if ("postgres".equals(dialect)) {
			d = SQLDialect.POSTGRES;
		} else if ("h2".equals(dialect)) {
			d = SQLDialect.H2;
		} else if ("mariadb".equals(dialect)) {
			d = SQLDialect.MARIADB;
		} else if ("mysql".equals(dialect)) {
			d = SQLDialect.MYSQL;
		} else if ("sqlite".equals(dialect)) {
			d = SQLDialect.SQLITE;
		} else if ("derby".equals(dialect)) {
			d = SQLDialect.DERBY;
		} else {
			throw new RuntimeException("Unknown SQLDialect configured: " + dialect);
		}

		DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(proxy);
		DefaultConfiguration configuration = new DefaultConfiguration();
		configuration.set(connectionProvider);
		configuration.set(d);

		return configuration;
	}

	/**
	 * TransaktionManager für Spring
	 *
	 * @param dataSource
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager getTransactionManager(TransactionAwareDataSourceProxy dataSource) {

		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * DataSource für das JOOQ-Transaktionsmanagement
	 *
	 * @param dataSource
	 * @return TransactionAwareDataSourceProxy
	 */
	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSource(DataSource dataSource) {

		return new TransactionAwareDataSourceProxy(dataSource);
	}
}
