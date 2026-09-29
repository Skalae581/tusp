package de.tuhh.tusp.app.precheck.pojo;

import lombok.Data;

@Data
public class Studiengang {

	private Long id;
	private Long courseofstudyId;
	private Integer aktiv;
	private String shorttext;
}
