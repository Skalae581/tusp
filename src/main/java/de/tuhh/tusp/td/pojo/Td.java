package de.tuhh.tusp.td.pojo;

import lombok.Data;

@Data
public class Td {

	private Long lv_nr_s; // Veranstaltungsnummer
	private String titel; // Veranstaltungs Name
	private String nutzer; // Dozent oder Raumbucher
	private String dbeg; // Datum Beginn
	private String beginn; // Zeit Von
	private String ende; // Zeit Bis
	private String day; // Tag name
	private String month; // Monat Name
	private String raum_s; // Raumnummer

}
