package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class District extends AbstractHioPojo implements BasePojo {

	private Long lid;
	private String astat;
	private Date validFrom;
	private Date validTo;
}
