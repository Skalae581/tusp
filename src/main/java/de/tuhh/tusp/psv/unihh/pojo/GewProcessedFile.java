package de.tuhh.tusp.psv.unihh.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class GewProcessedFile {

	private Long id;
	private String inName;
	private String hash;
	private Date verarbeitet;
	private String outName;
}
