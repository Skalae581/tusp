package de.tuhh.tusp.exa.massmails.pojo;

import lombok.Data;

@Data
public class MailErrors {

	private Long id;
	private String errorname;
	private String maildatum;
	private String vorname;
	private String nachname;
	private String message;
	private String uid;

	public MailErrors() {

	}

	public MailErrors(Long id, String errorname, String maildatum, String vorname, String nachname, String message,
			String uid) {
		this.id = id;
		this.errorname = errorname;
		this.maildatum = maildatum;
		this.vorname = vorname;
		this.nachname = nachname;
		this.message = message;
		this.uid = uid;

	}

	public MailErrors(String errorname, String maildatum, String vorname, String nachname, String message, String uid) {
		this.errorname = errorname;
		this.maildatum = maildatum;
		this.vorname = vorname;
		this.nachname = nachname;
		this.message = message;
		this.uid = uid;
	}

}
