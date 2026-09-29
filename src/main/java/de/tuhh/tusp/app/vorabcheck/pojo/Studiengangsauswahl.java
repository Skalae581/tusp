package de.tuhh.tusp.app.vorabcheck.pojo;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Studiengangsauswahl {

	private Long id;
	private Long courseofstudyId;
	private Integer aktivVorab;
	private Integer aktivVervollst;
	private String de;
	private String name;
	private String shorttextDe;
	private Integer prozentsatz;
	private Boolean aos;

	@Length(max = 2)
	private String standardkz;

	private String extrakz;
	private String extratitel;

	public Studiengangsauswahl(	long id, long courseofstudyId, int aktivVorab, int aktivVervollst, int prozentsatz,
								String standardkz) {
		this.id = id;
		this.courseofstudyId = courseofstudyId;
		this.aktivVorab = aktivVorab;
		this.aktivVervollst = aktivVervollst;
		this.prozentsatz = prozentsatz;
		this.standardkz = standardkz;
	}
}
