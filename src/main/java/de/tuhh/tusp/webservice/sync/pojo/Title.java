package de.tuhh.tusp.webservice.sync.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Title extends AbstractHioPojo implements BasePojo {

	private String shorttextFemaleDe;
	private String shorttextFemaleEn;
	private String defaulttextFemaleDe;
	private String defaulttextFemaleEn;
	private String longtextFemaleDe;
	private String longtextFemaleEn;
	private String lettersalutationDe;
	private String lettersalutationEn;
	private String lettersalutationFemaleDe;
	private String lettersalutationFemaleEn;
}
