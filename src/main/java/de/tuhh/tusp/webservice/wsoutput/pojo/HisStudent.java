package de.tuhh.tusp.webservice.wsoutput.pojo;

import lombok.Data;

@Data
public class HisStudent {

	private Long externeMtknr;
	private String unihhAstatStg;
	private String surname;
	private String firstname;
	private Long genderHiskey;
	private Boolean isCandidateStudent;
	private String courseOfStudyUniquename;
	private Long stgSemester;
	private String abschl;
	private String beugrund;
	private String hrst;
	private String kzfa;
	private String semester;
	private String status;
	private String stgnr;
	private String stgsem;
	private String stuart;
	private String stuform;
	private String stutyp;

	private String todo;
	private String details; // was muss gemacht werden bei ergänzen und
							// zurückmelden
}
