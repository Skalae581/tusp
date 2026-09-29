package de.tuhh.tusp.app.vorabcheck.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fach {

	Long id;
	Long bereichId;
	String name;
	Integer punkte;
	String zusatz;
}
