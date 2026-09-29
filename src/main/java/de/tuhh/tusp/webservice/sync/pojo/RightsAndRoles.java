package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RightsAndRoles implements BasePojo {

	private Long roleId;
	private String shorttext;
	private String defaulttext;
	private String longtext;
	private String uniquename;
	private Boolean valid;

	@Override
	public Long getId() {

		return roleId;
	}

	@Override
	public Boolean isValid() {

		return valid;
	}

}
