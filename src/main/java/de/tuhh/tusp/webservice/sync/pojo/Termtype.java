package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Termtype extends AbstractHioPojo implements BasePojo {

	private Long termCategory;
	private Long termNumber;
}
