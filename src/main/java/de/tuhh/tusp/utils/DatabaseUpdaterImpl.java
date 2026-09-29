package de.tuhh.tusp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.global.service.JobLogger4J;
import de.tuhh.tusp.webservice.sync.main.Main;

public class DatabaseUpdaterImpl implements DatabaseUpdater {

	private class DbVersion {

		int version;
		boolean isStructure;
		boolean requireSync;
		String sql = "";
		DbVersion nextVersion = null;
		public boolean ignoreFailure;

		/**
		 * Fügt eine Zeile aus der sql-Datei dieser Version hinzu
		 *
		 * @param line
		 */
		public void addLine(String line) {

			sql += line;
		}
	}

	private static final String DB_FILE = "Database.sql";
	private static final String CONFIG_KEY_STRUCT_VERSION = "de.tuhh.tusp.global.db_version";

	private static final String CONFIG_KEY_DATA_VERSION = "de.tuhh.tusp.global.db_version_data";

	private static Logger logger = LogManager.getLogger(DatabaseUpdaterImpl.class);

	@Value("${url}")
	private String url;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Value("${dialect}")
	private String dialect;

	// @Value("${driverClass}")
	// private String driverClassName;

	@Autowired
	private ApplicationContext appContext;

	private DbVersion startVersion;

	@Override
	public void buildTestTables(DSLContext ctx) throws Exception {

	}

	/**
	 * Erzeugt eine neue Verbingeng zur Datenbank
	 *
	 * @return die Con
	 * @throws ClassNotFoundException wenn der Treiber fehlt
	 */
	private Connection createConnectionForced() throws ClassNotFoundException {

		Connection con = null;

		// Versuche meine DB zu erreichen
		try {
			logger.debug("Connect to " + url);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			con = null;
		}

		// Wenn meine DB nicht erreichbar versuche sie Anzulegen
		if (con == null) {
			try {
				String postgresUrl = url.substring(0, url.lastIndexOf("/")) + "/postgres";
				logger.debug("Connect to " + postgresUrl);
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(postgresUrl, username, password);

				String postgresDb = url.substring(url.lastIndexOf("/") + 1);
				logger.debug("Check if " + postgresDb + " exists");
				ResultSet result = con.createStatement()
						.executeQuery("SELECT true FROM pg_database WHERE datname='" + postgresDb + "'");

				boolean dbExists = result.next() && result.getBoolean(1);

				if (!dbExists) {
					logger.debug("Create DB");
					con.createStatement().execute("CREATE DATABASE " + postgresDb
							+ " WITH TEMPLATE template0 ENCODING = 'UTF8' LC_COLLATE = 'de_DE.UTF8' LC_CTYPE = 'de_DE.UTF8' ");
				}
				// Superverbindung schließen
				try {
					con.close();
				} catch (Exception e) {
					/* ok */ } // NOPMD

				// Versuche jetzt wieder mit meiner DB weiter zu machen...
				logger.debug("Connect to " + url + " second time");
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				try {
					con.close();
				} catch (Exception e1) {
					/* ok */ } // NOPMD
				throw new RuntimeException(e);
			}
		}

		return con;
	}

	/**
	 * Sucht die Version aus der Datenbank
	 *
	 * @param con
	 * @param isStrukturUpdate
	 * @return Verisonsnummer
	 */
	private int discoverDbVersion(Connection con, boolean isStrukturUpdate) {

		String configKey = isStrukturUpdate ? CONFIG_KEY_STRUCT_VERSION : CONFIG_KEY_DATA_VERSION;

		logger.debug("Frage Version aus Datenbank ab");
		int db = -1;
		try {
			ResultSet result = con.createStatement()
					.executeQuery("SELECT \"value\" FROM global_config WHERE \"key\"='" + configKey + "'");
			if (result.next()) {
				db = result.getInt(1);
			}
		} catch (SQLException e) {
			/* Tabelle existiert wohl nicht */
			logger.debug("Die Tabelle global_config wurde nicht gefunden");
		}
		logger.debug("Datenbank ist in Version " + db);
		return db;

	}

	/**
	 * Synchronisiert die TUSP mit den Schnlüsseltabellen von TUNE
	 */
	private void doSync() {

		logger.debug("Es wird ein Sync benötigt, bevor die SQL-Daten importiert werden können");

		Main main = appContext.getBean(Main.class);
		main.start(new JobLogger4J(logger));
		logger.debug("Sync abgeschlossen");
	}

	/**
	 * Aktualisiert die Datenbank
	 *
	 * @param con
	 * @param sql
	 * @param toVersion
	 * @param isStrukturUpdate
	 * @param ignoreFailure
	 * @throws SQLException wenn das SQL fehlerhaft ist
	 */
	void doUpdate(Connection con, String sql, int toVersion, boolean isStrukturUpdate, boolean ignoreFailure)
			throws SQLException {

		if (isStrukturUpdate && ignoreFailure) {
			throw new RuntimeException("Struktur-Updates dürfen nicht optional sein!!!");
		}

		String configKey = isStrukturUpdate ? CONFIG_KEY_STRUCT_VERSION : CONFIG_KEY_DATA_VERSION;

		logger.info("Execute SQL für Update auf Version " + toVersion + ": \n" + sql);

		con.setAutoCommit(false);
		try {
			con.createStatement().execute(sql);
		} catch (Exception e) {
			if (ignoreFailure) {
				logger.warn("Optionaler Versionssprung ist fehlgeschlagen und wird daher ignoriert:\n" + sql, e);
				con.rollback();
			} else {
				throw e;
			}
		}

		if (toVersion >= 1) {
			con.createStatement().execute("DELETE FROM global_config WHERE \"key\"='" + configKey + "'");
			con.createStatement().execute(
					"INSERT INTO global_config (\"key\", \"value\") VALUES ('" + configKey + "', '" + toVersion + "')");
		}
		con.commit();
	}

	/**
	 * Parst einen SQL String
	 *
	 * @param sql
	 */
	private void getFileVersions(String sql) {

		// Pattern compilation
		Pattern versionPattern = Pattern.compile("\\s*--\\s*\\[\\s*Version\\s*(\\d+).*]");

		// Rootelement anlegen
		DbVersion version = new DbVersion();
		version.version = 0;
		version.requireSync = false;
		version.isStructure = true;

		// Rootelement bekannt machen
		startVersion = version;

		for (String line : sql.split("\n")) {
			Matcher versionMatcher = versionPattern.matcher(line);

			// Neue Version gefunden
			if (versionMatcher.find()) {
				String match = versionMatcher.group();
				version.nextVersion = new DbVersion(); // nächste Version erstellen
				version = version.nextVersion; // springe zur Nächsten

				Matcher nrMatcher = Pattern.compile("\\d+").matcher(match);
				nrMatcher.find();
				version.version = Integer.parseInt(nrMatcher.group());
				version.isStructure = !match.matches(".*data.*");
				version.requireSync = match.matches(".*require sync.*");
				version.ignoreFailure = match.matches(".*try.*");
			}

			version.addLine(line + "\n");
		}
	}

	@Override
	@Transactional
	public void initDb(DSLContext context) throws Exception {

		logger.debug("Starte DB-Versions-Check");

		if (!"postgres".equals(dialect)) {
			throw new RuntimeException("Der SQL-Dialekt '" + dialect + "' wird nicht unterstützt!");
		}

		Connection con = null;
		try {
			// Stelle sicher, dass die DB existiert
			con = this.createConnectionForced();

			String sql = readClasspathFile(DB_FILE);
			getFileVersions(sql);

			// Struktur aktualisieren
			DbVersion version = startVersion;
			int dbVersion = discoverDbVersion(con, true);
			while (version != null) {
				if (version.isStructure && version.version > dbVersion) {
					doUpdate(con, version.sql, version.version, version.isStructure, version.ignoreFailure);
					dbVersion = version.version;
					logger.debug("Datenbank-Struktur ist jetzt in Version " + version.version);
				}
				version = version.nextVersion;
			}

			// Wenn nötig einen Sync durchführen
			version = startVersion;
			dbVersion = discoverDbVersion(con, false);
			while (version != null) {
				if (!version.isStructure && version.version > dbVersion && version.requireSync) {
					doSync();
					break;
				}
				version = version.nextVersion;
			}

			// Daten aktualisieren
			version = startVersion;
			dbVersion = discoverDbVersion(con, false);
			while (version != null) {
				if (!version.isStructure && version.version > dbVersion) {
					doUpdate(con, version.sql, version.version, version.isStructure, version.ignoreFailure);
					dbVersion = version.version;
					logger.debug("Datenbank-Inhalt ist jetzt in Version " + version.version);
				}
				version = version.nextVersion;
			}

		} catch (Exception e) {
			logger.error("Datenbankupdate ist fehlgeschlagen", e);
			throw e;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				/* ok */ } // NOPMD
		}

		logger.debug("DB-Versions-Check beendet");
	}

	/**
	 * Liest eine Datei aus dem ClassPath
	 *
	 * @param path
	 * @return Dateiinhalt
	 * @throws IOException bei IO Problemen
	 */
	private String readClasspathFile(String path) throws IOException {

		Resource file = new ClassPathResource(path);
		logger.debug("Read file " + file.getFile().getAbsolutePath());
		InputStream in = file.getInputStream();

		return IOUtils.toString(in, "UTF-8");
	}

}
