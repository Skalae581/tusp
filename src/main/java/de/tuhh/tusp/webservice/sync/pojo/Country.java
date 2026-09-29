package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends AbstractHioPojo implements BasePojo {

	private Long lid;
	private String iso3166_1_alpha;
	private Integer euMember;
	private Integer ewrMember;
}
