package de.tuhh.tusp.app.vorabcheck.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "extra")
@Data
public class Extra {

	private Long id;
	private Long stgId;
	private String titel;
	private String name;
	private String zusatzErbracht;
	private String zusatzAusstehend;
}