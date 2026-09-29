package de.tuhh.tusp.exa.pvsb.pojo;

import lombok.Data;

@Data
public class Mail {

	private String to;
	private String cc;
	private String subject;
	private String body;
}