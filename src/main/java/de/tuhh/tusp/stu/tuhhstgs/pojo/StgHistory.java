package de.tuhh.tusp.stu.tuhhstgs.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class StgHistory {

	private Long id;
	private String filename;
	private String username;
	private Date zeit;
}
