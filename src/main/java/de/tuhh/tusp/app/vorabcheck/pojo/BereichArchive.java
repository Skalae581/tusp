package de.tuhh.tusp.app.vorabcheck.pojo;

import lombok.Data;

@Data
public class BereichArchive {

	private Long id;
	private Long stgId;
	private String name;
	private Integer punkte;
	private String zusatz;
	private Long originId;
	private Long sessionId;
}
