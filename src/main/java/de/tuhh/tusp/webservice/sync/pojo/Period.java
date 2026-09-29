package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Period implements BasePojo {

	private Long id;
	private String uniquename;
	private Date startdate;
	private Date enddate;
	private Boolean valid;

	@Override
	public Boolean isValid() {

		return valid;
	}
}
