package de.tuhh.tusp.app.vorabcheck.pojo;

import java.util.List;

import lombok.Data;

@Data
public class EctsForm {

	private Long codenummer;
	private String anmerk;
	private Long stgId;
	private String zusatzErbracht;
	private String zusatzAusstehend;

	private List<Ects> ects;

	private List<Extra> extra;
}