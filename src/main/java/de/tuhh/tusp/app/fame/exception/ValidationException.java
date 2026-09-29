package de.tuhh.tusp.app.fame.exception;

import java.util.ArrayList;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 7207756556838211782L;

	private ArrayList<String> errors;

	/**
	 * Erzeugt eine neue Instanz
	 *
	 * @param errors
	 *            Liste der Regelverstöße
	 */
	public ValidationException(ArrayList<String> errors) {
		this.errors = errors;
	}

	public ArrayList<String> getErrors() {

		return errors;
	}

}
