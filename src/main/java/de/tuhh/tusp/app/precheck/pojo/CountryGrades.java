package de.tuhh.tusp.app.precheck.pojo;

import lombok.Data;

@Data
public class CountryGrades {

	private Long id;
	private Double grade;
	private Long countryId;

}
