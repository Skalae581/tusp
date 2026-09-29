package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CourseOfStudy implements BasePojo {

	private Long id;
	private Long lid;
	private Long degreeid;
	private String longtextDe;
	private String longtextEn;
	private String shorttextDe;
	private String shorttextEn;
	private String defaulttextDe;
	private String defaulttextEn;
	private Boolean valid;
	private String uniquename;

	@Override
	public Boolean isValid() {

		return valid;
	}

}
