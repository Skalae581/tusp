package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class Saeuleaktualisierung {

	private Long id;
	private String datum;
	private Long saeule_user_id;
	private Long saeulenquelle_id;
	private String begruendung;
	private Long uid;
	private Long saeule_von_id;
	private boolean abgeschlossen;
}
