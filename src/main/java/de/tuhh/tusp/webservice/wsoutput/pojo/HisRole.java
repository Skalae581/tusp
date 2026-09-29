package de.tuhh.tusp.webservice.wsoutput.pojo;

import javax.xml.datatype.XMLGregorianCalendar;

import lombok.Data;

@Data
public class HisRole {

	private String ouUniquename;
	private String rollenUniquename;
	private XMLGregorianCalendar validFrom;
	private XMLGregorianCalendar validTo;

}
