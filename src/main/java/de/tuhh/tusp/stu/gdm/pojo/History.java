package de.tuhh.tusp.stu.gdm.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class History {

	private Long id;
	private String filename;
	private String username;
	private Date zeit;
}
