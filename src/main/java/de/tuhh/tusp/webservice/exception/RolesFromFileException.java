package de.tuhh.tusp.webservice.exception;

public class RolesFromFileException extends RuntimeException {

	private static final long serialVersionUID = -2551782951096042632L;

	/**
	 * Erstellt eine neue Instanz
	 * 
	 * @param e
	 */
	public RolesFromFileException(Exception e) {
		super(e);
	}
}
