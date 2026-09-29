package de.tuhh.tusp.exa.massmails.pojo;

import lombok.Data;

@Data
public class Receiver {

	private Long id;
	private String vorname;
	private String nachname;
	private String email;
	private String titel;
	private String anrede;
	private String verteilername;
	private Long personid;
	private Long semester;

	public String toShortString() {

		return email + "<" + (titel + " ").trim() + vorname + " " + nachname + ">";
	}
}
