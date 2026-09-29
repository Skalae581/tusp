package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrgUnit implements BasePojo {

	private Long id;
	private Long lid;
	private String uniquename;
	private String shorttextDe;
	private String displaytextDe;
	private String longtextDe;
	private Long parentId;
	private Long type;
	private Boolean valid;

	@Override
	public Boolean isValid() {

		return valid;
	}
}
