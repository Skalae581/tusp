package de.tuhh.tusp.exa.lvsb.pojo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Lehrvertreter {

	private Long id;
	private Timestamp timestamp_confirm;
	private Boolean complete;
	private String teacher_name;
	private String teacher_email;
	private String teacher_account;
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
	private Boolean employee;
	private String reason;
}
