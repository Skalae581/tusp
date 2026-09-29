package de.tuhh.tusp.psv.unihh.pojo;

import lombok.Data;

@Data
public class UniHHPerson {

	private Long uhhMtknr;
	private Long semester;
	private String status;
	private String nachname;
	private String vorname;
	private String gebname;
	private String gebort;
	private String gebdat;
	private String geschl;
	private String staat;
	private String zweitstaat;
	private String hmkfzkz;
	private String hmkfz;
	private Long poplz;
	private String poort;
	private String postrasse;
	private String pozusatz;
	private String potel;
	private String immdat;
	private String hzbart;
	private String hzbjahr;
	private String hzbkfzkz;
	private String hzbkfz;
	private String hzbland;
	private String erhskfz;
	private String erhsart;
	private Long erhssembrd;
	private Long hssem; // uni semester
	private Long urlsem;
	private Long kolsem;
	private String unimail;

	public String toShortString() {

		return "UniHHPerson(uhhMtknr=" + uhhMtknr + ", vorname=" + vorname + ", nachname=" + nachname + ")";
	}

}
