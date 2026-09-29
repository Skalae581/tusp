package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class Subject implements BasePojo {

	private Long id;
	private Long lid;
	private String uniquename;
	private String shorttextDe;
	private String shorttextEn;
	private String defaulttextDe;
	private String defaulttextEn;
	private String longtextDe;
	private String longtextEn;
	private String sortorder;
	private Date validFrom;
	private Date validTo;
	private String astatBund;
	private String astatLand;
	private Boolean valid;

	@Override
	public Boolean isValid() {

		return valid;
	}
}
