package de.tuhh.tusp.webservice.wsoutput.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Student {

	private Long id;
	private Long mtknr; // Von der UniHH
	private Long personId; // Aus TUNE
	private Long studentId; // Aus TUNE
	private Boolean valid;

}
