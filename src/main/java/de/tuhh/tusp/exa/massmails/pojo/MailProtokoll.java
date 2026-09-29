package de.tuhh.tusp.exa.massmails.pojo;

import lombok.Data;

@Data
public class MailProtokoll {

	private Long id;
	private String receiver;
	private String sender;
	private String timestamp;
	private String subject;
	private String body;
	private String username;
	private String status;
	private String replyto;

}
