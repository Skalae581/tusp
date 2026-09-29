package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class Konfigurationstransfer {

	private Long id;
	private Long saeule_id;
	private Long saeule_user_id;
	private String begruendung;
	private String datum;
	private Long konfigurationsquelle_id;
	private Long uid;
	private boolean abgeschlossen;
	private String konfigurationseingeschaften;
}
