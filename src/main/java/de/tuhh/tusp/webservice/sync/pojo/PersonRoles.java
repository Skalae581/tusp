package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PersonRoles implements BasePojo {

	private Long id;
	private Long orgunitLid;
	private Long personId;
	private Long roleId;
	private Date validFrom;
	private Date validTo;
	private Boolean valid;

	@Override
	public Boolean isValid() {

		return valid;
	}

}
