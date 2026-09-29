package de.tuhh.tusp.utils.ssh;

public class ConnectionClosedException extends RuntimeException {

	private static final long serialVersionUID = -2336050788080375990L;

	/**
	 * Construct
	 * 
	 * @param string
	 */
	public ConnectionClosedException(String string) {
		super(string);
	}

}
