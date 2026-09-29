package de.tuhh.tusp.lvp.pojo;

import lombok.Data;

@Data
public class Termine {

	private Long lv_nr_s; // Veranstaltungsnummer
	private String gbd_s; // Gebäudekürzel
	private String raum_s; // Raumnummer
	private String dbeg; // Datum Beginn
	private String dend; // Datum Ende
	private String beginn; // Zeit Von
	private String ende; // Zeit Bis
}
