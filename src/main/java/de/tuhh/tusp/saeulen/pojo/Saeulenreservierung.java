package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class Saeulenreservierung {

	private Long id;
	private Long saeule_user_id;
	private String von;
	private String bis;
	private String begruendung;
	private Long saeule_id;
}
