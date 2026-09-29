package de.tuhh.tusp.stu.gdm.exception;

/**
 * Exception die geworfen wird, wenn keine Gebührendaten für den aktuellen Tag
 * vorhanden sind
 *
 * @author rztjm
 *
 */
public class KeineAktuellenFilesException extends Exception {

	private static final long serialVersionUID = -5270009767026845028L;

	public KeineAktuellenFilesException(String message) {
		super(message);
	}

}
