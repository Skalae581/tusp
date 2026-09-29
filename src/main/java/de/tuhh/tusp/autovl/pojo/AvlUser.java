package de.tuhh.tusp.autovl.pojo;

import lombok.Data;

@Data
public class AvlUser {
	private Long id;
	private String vorname;
	private String nachname;
	private String tel;
	private String email;
	private String institut;
	private String fuererschein;
	private String gueltigkeit;
}
