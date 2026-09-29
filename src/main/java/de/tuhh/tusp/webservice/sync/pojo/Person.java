package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Person implements BasePojo {

	private Long id;
	private String firstname;
	private String surname;
	private Date updatedAt;
	private Boolean valid;
	private Long academicdegreeId;
	private String academicdegreeSuffix;

	@Override
	public Boolean isValid() {

		return valid;
	}
}
