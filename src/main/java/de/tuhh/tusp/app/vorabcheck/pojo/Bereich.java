package de.tuhh.tusp.app.vorabcheck.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bereich {

	private Long id;
	private Long stgId;
	private String name;
	private Integer punkte;
	private String zusatz;
}
