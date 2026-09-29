package de.tuhh.tusp.psv.mobon.pojo;

import lombok.Data;

@Data
public class SopApplicant {

	private long id;
	private String vorname;
	private String nachname;
	private String email;
	private String geschlecht;
	private String geburtstag;
	private String geburtsort;
	private Long geburtsland;
	private Long nationalitaet;
	private Long nationalitaet2;
	private String bewerbungsSemester;
	private String heimathochschulLand;
	private int anzahlAbsolSemester;
	private String hmkfz;

}
