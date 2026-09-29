package de.tuhh.tusp.autovl.pojo;

import lombok.Data;

@Data
public class Buchung {
	private Long id;
	private String von;
	private String bis;
	private Long avlUserId;
	private Long autoId;

}
