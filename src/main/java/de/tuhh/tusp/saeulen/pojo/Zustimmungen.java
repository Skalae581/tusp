package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class Zustimmungen {

	private Long id;
	private Long uid;

	private Long saeulenaktualisierungs_id;
	private Long konfigurationstransfer_id;

	private Long saeule_user_id;

	private boolean zugestimmt;
	private String zusgestimmtdatum;

	private boolean abgelehnt;
	private String ablehnungsgrund;
	private String ablehnungsdatum;

}
