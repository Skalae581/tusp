package de.tuhh.tusp.stu.timsl.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class TimSonderStudent {

	private Long id;
	private Long mtknr;
	private Date startdate;
	private Date enddate;
}
