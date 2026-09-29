package de.tuhh.tusp.psv.unihh.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class GewHistory {

	private Long id;
	private String filename;
	private String username;
	private Date zeit;
}
