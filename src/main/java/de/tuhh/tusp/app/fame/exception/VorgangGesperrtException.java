package de.tuhh.tusp.app.fame.exception;

import de.tuhh.tusp.app.fame.pojo.Vorgang;

public class VorgangGesperrtException extends Exception {

	private static final long serialVersionUID = -3310166072315786280L;

	private Vorgang vorgang;

	public VorgangGesperrtException(Vorgang vorgang) {
		this.vorgang = vorgang;
	}

	public Vorgang getVorgang() {

		return vorgang;
	}

}
