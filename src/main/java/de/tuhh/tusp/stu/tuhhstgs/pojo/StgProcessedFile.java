package de.tuhh.tusp.stu.tuhhstgs.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class StgProcessedFile {

	private Long id;
	private String inName;
	private String hash;
	private Date verarbeitet;
	private String outName;
}
