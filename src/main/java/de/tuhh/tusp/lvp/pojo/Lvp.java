package de.tuhh.tusp.lvp.pojo;

import lombok.Data;

@Data
public class Lvp {

	private Long lv_nr_s; // Veranstaltungsnummer
	private String titel; // Bezeichnung der Veranstaltung
	private String nutzer; // Dozent oder Raumbucher
	private String lv_typ; // Typ der Veranstaltung
	private String bereich; // Organisationseinheit
}
