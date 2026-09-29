package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;

@Data
public class AbstractHioPojo implements BasePojo {

	protected Long id;
	protected String uniquename;
	protected String shorttextDe;
	protected String shorttextEn;
	protected String defaulttextDe;
	protected String defaulttextEn;
	protected String longtextDe;
	protected String longtextEn;
	protected String sortorder;
	protected Boolean valid;

	@Override
	public Boolean isValid() {

		return valid;
	}
}
