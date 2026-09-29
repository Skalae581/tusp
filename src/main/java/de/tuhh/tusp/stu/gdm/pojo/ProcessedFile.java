package de.tuhh.tusp.stu.gdm.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class ProcessedFile {

	private Long id;
	private String inName;
	private String hash;
	private Date verarbeitet;
	private String outName;
}
