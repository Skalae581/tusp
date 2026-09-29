package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Eaddresstype extends AbstractHioPojo implements BasePojo {

	private String addresstype;
}
