package de.tuhh.tusp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Getter;

/**
 * Klasse dient als Container für Benachrichtigungen, die vom Controller in die
 * View übermittelt werden sollen. Es werden verschiedene Arten von
 * Benachrichtigungen unterstützt. Bei der Anzeige können diese dann nur
 * gruppiert nach Art dargestellt werden.
 *
 * Die Erläuterung der Formatierung ist hier zu finden: see
 * http://docs.oracle.com/javase/6/docs/api/java/text/MessageFormat.html
 *
 * @author rztjm
 *
 */
@Component
public class MessageContainer {

	@Autowired
	private MessageSource messageSource;

	private Locale locale = LocaleContextHolder.getLocale();

	private @Getter List<String> errors = new ArrayList<>();
	private @Getter List<String> warnings = new ArrayList<>();
	private @Getter List<String> successes = new ArrayList<>();
	private @Getter List<String> infos = new ArrayList<>();

	/**
	 * Fügt die Ergebnisse eines Binding-Results dem MessageContainer hinzu
	 *
	 * @param prefix        Der Prefix, mit dem der Feldname in einen Anzeigenamen
	 *                      umgewandelt wird z.B. "precheck."
	 * @param bindingResult das BindingResult
	 */
	public void addBindingResult(String prefix, BindingResult bindingResult) {

		if (bindingResult == null) {
			return;
		}

		Locale locale = LocaleContextHolder.getLocale();

		for (FieldError error : bindingResult.getFieldErrors()) {
			String field = messageSource.getMessage(prefix + error.getField(), null, locale);
			errors.add(field + ": " + error.getDefaultMessage());
		}
	}

	/**
	 * Fügt einen Fehler zur interen Liste hinzu. Fehler sind Exceptions und
	 * Probleme, die die Verarbeitung der Nutzereingaben verhindern.
	 *
	 * @param key  Key der Message
	 * @param args Argumente, die in die Nachricht eingefügt werden sollen.
	 *             Nachricht muss dafür {0} enthalten.
	 */
	public void addErrorKey(String key, Object... args) {

		errors.add(messageSource.getMessage(key, args, locale));
	}

	/**
	 * Fügt eine Information zur interen Liste hinzu. Informationen sind Nachrichten
	 * die für den Benutzer interessant sein können aber keine Aktion bestätigen
	 * oder Probleme melden.
	 *
	 * @param info Key der Message
	 * @param args Argumente, die in die Nachricht eingefügt werden sollen.
	 *             Nachricht muss dafür {0} enthalten.
	 */
	public void addInfosKey(String info, Object... args) {

		infos.add(messageSource.getMessage(info, args, locale));
	}

	/**
	 * Fügt eine Erfolgsmeldung zur interen Liste hinzu. Erfolgsmeldung sind
	 * Meldungen die eine Aktion bestätigen.
	 *
	 * @param success Key der Message
	 * @param args    Argumente, die in die Nachricht eingefügt werden sollen.
	 *                Nachricht muss dafür {0} enthalten.
	 */
	public void addSuccessKey(String success, Object... args) {

		successes.add(messageSource.getMessage(success, args, locale));
	}

	/**
	 * Fügt eine Warning zur interen Liste hinzu. Warnungen sind Meldungen, die
	 * nicht kritisch sind, aber auf einen Missstand hinweisen.
	 *
	 * @param warning Key der Message
	 * @param args    Argumente, die in die Nachricht eingefügt werden sollen.
	 *                Nachricht muss dafür {0} enthalten.
	 */
	public void addWarningKey(String warning, Object... args) {

		warnings.add(messageSource.getMessage(warning, args, locale));
	}

	/**
	 * Fragt einen String aus der MessageSource ab
	 *
	 * @param key  Key der Message
	 * @param args Argumente, die in die Nachricht eingefügt werden sollen.
	 *             Nachricht muss dafür {0} enthalten.
	 * @return den String
	 */
	public String getString(String key, Object... args) {

		return messageSource.getMessage(key, args, locale);
	}
}
