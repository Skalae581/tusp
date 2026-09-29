package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Studystatus extends AbstractHioPojo implements BasePojo {

	private String astat;
	private Long priority;
}
