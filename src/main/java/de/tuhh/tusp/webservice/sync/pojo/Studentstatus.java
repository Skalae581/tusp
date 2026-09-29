package de.tuhh.tusp.webservice.sync.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Studentstatus extends AbstractHioPojo implements BasePojo {

	private String astat;
	private Long priority;
	private Date validFrom;
	private Date validTo;
}
