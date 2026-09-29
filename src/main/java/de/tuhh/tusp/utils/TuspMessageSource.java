
package de.tuhh.tusp.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.Getter;

/**
 * Klasse überschreibt das Standardverhalten der Spring-Localization.
 *
 * Es soll wenn möglich der Text in der Benutzer-Sprache ausgegeben werden, zur
 * Not auch in der Anwendungs-Defaultsprache und wenn auch dieser nicht
 * existiert der Code.
 *
 * @author rztjm
 *
 */
public class TuspMessageSource extends ReloadableResourceBundleMessageSource {

	private static Logger logger = LogManager.getLogger(TuspMessageSource.class);

	private Locale defaultLocale;

	@Getter
	private String ressourceFolder;

	public TuspMessageSource(Locale defaultLocale) {
		super();
		this.defaultLocale = defaultLocale;
	}

	@Override
	protected MessageFormat resolveCode(final String code, final Locale locale) {

		if ("".equals(code)) {
			return new MessageFormat("");
		}

		MessageFormat result = super.resolveCode(code, locale);

		if (result == null || result.toPattern().isEmpty() && !locale.equals(this.defaultLocale)) {
			result = super.resolveCode(code, this.defaultLocale);
		}

		return result;
	}

	@Override
	protected String resolveCodeWithoutArguments(final String code, final Locale locale) {

		// in Benutzersprache suchen
		String result = super.resolveCodeWithoutArguments(code, locale);

		// in default-Sprache suchen
		if (result == null || result.isEmpty() && !locale.equals(this.defaultLocale)) {
			logger.debug(code + " fehlt in " + locale);
			result = super.resolveCodeWithoutArguments(code, this.defaultLocale);
		}

		// Key ausgeben
		if (result == null || result.isEmpty()) {
			logger.warn(code + " key nicht aufgelöst!");
			result = code;
		}

		return result;
	}

	/**
	 * Sucht ausgehend von der eigenen Binary nach dem i18-Folder und sucht alle
	 * Sprach-Files darin.
	 *
	 * @return Arraay mit den Sprachdateien
	 */
	public String[] scanForBasenames() {

		// Leider muss zum Ansprechen des Verzeichnisses einige Magie laufen:

		// Folder suchen
		String currentClassLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		logger.debug("Suche message.properties ausgehend von: " + currentClassLocation);
		String i18Folder;
		if (currentClassLocation.lastIndexOf("WEB-INF") != -1) {
			// Tomcat-Umgebung
			i18Folder = currentClassLocation.substring(0, currentClassLocation.lastIndexOf("WEB-INF")) + "i18/";
		} else if (currentClassLocation.lastIndexOf("tusp") != -1) {
			// Test-Umgebung
			i18Folder = currentClassLocation.substring(0, currentClassLocation.lastIndexOf("tusp"))
					+ "tusp/src/main/webapp/i18/";
		} else if (currentClassLocation.lastIndexOf("TUSP") != -1) {
			// Buildserver
			i18Folder = currentClassLocation.substring(0, currentClassLocation.lastIndexOf("TUSP"))
					+ "TUSP/src/main/webapp/i18/";
		} else {
			throw new RuntimeException("/i18/-Folder konnte nicht gefunden werden! " + currentClassLocation);
		}
		ressourceFolder = i18Folder;
		logger.debug("i18-Folder ist: " + i18Folder);
		File folder = new File(i18Folder);
		if (!folder.exists()) {
			throw new RuntimeException("i18-Folder not found: " + i18Folder);
		}

		// Schauen, welche Files eingelesen werden können
		ArrayList<String> files = new ArrayList<>();
		for (File f : folder.listFiles()) {
			String filename = f.getName();
			filename = filename.replace("_de.properties", "");
			filename = filename.replace("_en.properties", "");
			if (!files.contains(filename)) {
				files.add("/i18/" + filename);
				logger.debug("Add messageSource path: /i18/" + filename);
			}
		}

		if (files.isEmpty()) {
			logger.warn("Keine Message-Files gefunden in " + i18Folder);
		}

		return files.toArray(new String[files.size()]);
	}
}
