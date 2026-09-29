package de.tuhh.tusp.saeulen.pojo;

import lombok.Data;

@Data
public class SaeuleUser {

	private Long id;
	private String name;
	private String nachname;
	private String benutzername;
	private boolean vorgesetzter;
	private boolean abstimmberechtigt;
	private Long saeule_id;
	private String email;
}
