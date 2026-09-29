package de.tuhh.tusp.webservice.exception;

public class ServiceFault extends RuntimeException {

	private static final long serialVersionUID = -8096706046575864248L;

	/**
	 * Erstellt eine RuntimeException
	 * 
	 * @param e
	 */
	public ServiceFault(Exception e) {
		super(e);
	}

	/**
	 * Erstellt eine RuntimeException
	 * 
	 * @param message
	 * @param e
	 */
	public ServiceFault(String message, Exception e) {
		super(message, e);
	}
}
