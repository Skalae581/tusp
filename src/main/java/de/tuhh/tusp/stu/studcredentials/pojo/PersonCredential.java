package de.tuhh.tusp.stu.studcredentials.pojo;

import lombok.Data;

@Data
public class PersonCredential {

	private String username;
	private String initPassword;
	private Long personId;
	private Long accountId;

}
