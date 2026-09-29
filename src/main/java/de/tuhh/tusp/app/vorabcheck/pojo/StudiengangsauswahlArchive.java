package de.tuhh.tusp.app.vorabcheck.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudiengangsauswahlArchive {

	private Long id;
	private Long originId;
	Long sessionId;
	private Long courseofstudyId;
	private Integer aktivVorab;
	private Integer aktivVervollst;
	private String name;
	private Integer prozentsatz;
	private String standardkz;
	private LocalDateTime zeitstempel;
	private String bearbeiter;
	private String grund;
	private String extrakz;
}
