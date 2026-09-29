package de.tuhh.tusp.app.vorabcheck.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "ects")
@Data
public class Ects {

	private Long id;
	private Long bewId;
	private Long bereichId;
	private Long fachId;
	private Double punkte;
	private Double bereichPunkte;
	private Double gesamtPunkte;
	private String erwerb;
	private String modul;
	private String nachweis;
	private String ergebnis;

	@XmlElement
	public Double getPunkte() {

		return punkte;
	}

	public void setPunkte(Double punkte) {

		this.punkte = punkte;
	}
}