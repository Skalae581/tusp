package de.tuhh.tusp.exa.pvsb.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Pruefervertreter {

	private Long id;
	private Timestamp timestamp_confirm;
	private Boolean complete;
	private String examiner_name;
	private String examiner_email;
	private String examiner_account;
	private String rep_surname;
	private String rep_firstname;
	private String rep_dateofbirth;
	private String rep_email;
	private String rep_account;
	private String institute;
	private String applicant;
	private Timestamp timestamp_process;
	private String processor;
	private Boolean mailsent;

	// Neu:
	// private Long id;
	// private Long pruefer_id;
	// private String name;
	// private String email;
	// private String account;
	// private String institute;
	// private Timestamp timestamp_confirm;
	// private String applicant;
	// private Boolean complete;
	// private Timestamp timestamp_process;
	// private String processor;
	// private Boolean mailsent;
}
