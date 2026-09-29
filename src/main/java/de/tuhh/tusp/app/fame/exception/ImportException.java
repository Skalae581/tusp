package de.tuhh.tusp.app.fame.exception;

import de.tuhh.tusp.webservice.exception.ServiceFault;

public class ImportException extends Exception {

	private static final long serialVersionUID = -5661110646733337484L;

	/**
	 * Construct
	 * 
	 * @param e
	 */
	public ImportException(ServiceFault e) {
		super(e);
	}

	/**
	 * Construct
	 * 
	 * @param string
	 */
	public ImportException(String string) {
		super(string);
	}

}
