package de.tuhh.tusp.webservice.exception;

public class NoRolesDefinedForOuException extends Exception {

	private static final long serialVersionUID = 8888087169334304351L;

	/**
	 * instanziert das Objkt
	 *
	 * @param string
	 */
	public NoRolesDefinedForOuException(String string) {
		super(string);
	}
}