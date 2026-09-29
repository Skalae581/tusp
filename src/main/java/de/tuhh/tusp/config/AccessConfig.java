package de.tuhh.tusp.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.tuhh.tusp.global.Rollen;

/**
 * Diese Annotation ermöglicht es in den Controller-Klassen an den entsprechenden Request-Mappings oder der Klasse
 * selbst zu definieren welche Zugriffsbedingungen für den jeweiligen Pfad gelten sollen.
 *
 * @author rztjm
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessConfig {

	/**
	 * Benötigte Rollen
	 *
	 * @return Array mit Rollen
	 */
	Rollen[] access();

	/**
	 * Zu schützender Pfad. Im Spring-AntMatcher-Format also z.B. /precheck/** = Alle Ressourcen unterhalb von
	 * /precheck/ oder /precheck = Die Ressource Precheck oder *.css = Alle css Files (Das kann Probleme machen!)
	 *
	 * @return Den Pfad
	 */
	String path();
}
