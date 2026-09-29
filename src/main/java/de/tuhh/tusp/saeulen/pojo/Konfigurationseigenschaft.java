package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class Konfigurationseigenschaft {

	private Long id;
	private boolean brauchtquelle;
	private String bezeichnung;
	private boolean angekreuzt;

}
