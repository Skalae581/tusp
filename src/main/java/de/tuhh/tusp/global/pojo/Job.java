package de.tuhh.tusp.global.pojo;

import java.util.Date;

import de.tuhh.tusp.utils.schedule.Status;
import lombok.Data;

@Data
public class Job {

	private Long id;
	private String className;
	private String name;
	private Date start;
	private Date ende;
	private Status status;
	private String server;
	private String log;
}
