package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data

public class Saeule {
	private Long id;
	private String bezeichnung;
	private String hisoneSuperxVersion;
	private String hisoneDatum;
	private String hisoneHotfixNr;
	private String hisoneHotfixDatum;
	private String hiodbQuelle;
	private String hiodbDatum;
	private String sosposVersion;
	private String sosposDatum;

}
