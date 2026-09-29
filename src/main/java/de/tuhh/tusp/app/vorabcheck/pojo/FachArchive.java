package de.tuhh.tusp.app.vorabcheck.pojo;

import lombok.Data;

@Data
public class FachArchive {

	private Long id;
	private Long sessionId;
	private Long bereichId;
	private String name;
	private Integer punkte;
	private String zusatz;
}
