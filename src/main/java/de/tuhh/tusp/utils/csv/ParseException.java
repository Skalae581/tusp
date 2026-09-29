package de.tuhh.tusp.utils.csv;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = -4157250138502008480L;

	/**
	 *
	 * @param message
	 */
	public ParseException(String message) {
		super(message);
	}

	/**
	 *
	 * @param message
	 * @param e
	 */
	public ParseException(String message, Exception e) {
		super(message, e);
	}

}
