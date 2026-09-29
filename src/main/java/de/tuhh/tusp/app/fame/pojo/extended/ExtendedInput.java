package de.tuhh.tusp.app.fame.pojo.extended;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedInput extends Input {

	private static final long serialVersionUID = -4567313105110296876L;

	private InputTypeEnum inputTypeUniqName;
	private String eingabe;
	private Long vorgangId;
	private Long valueId;
	private InputRechteEnum recht;
	private String mimeType;
}