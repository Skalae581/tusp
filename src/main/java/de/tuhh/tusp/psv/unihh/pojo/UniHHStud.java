package de.tuhh.tusp.psv.unihh.pojo;

import lombok.Data;

@Data
public class UniHHStud {

	private Long uhhMtknr;
	private Integer semester;
	private Integer stgnr;
	private Integer abschl;
	private String stgUnihhAstat;
	private String kzfa;
	private Integer stgsem; // DegreeProgram
	private String status; // status
	private String beugrund;
	private String hrst;
	private String stuart;
	private String stutyp;
	private Integer stufrm;
}
