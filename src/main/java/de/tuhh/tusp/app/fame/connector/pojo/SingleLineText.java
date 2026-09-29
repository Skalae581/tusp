package de.tuhh.tusp.app.fame.connector.pojo;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.service.ValueService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleLineText implements ConnectorPojo {

	private String value;
	private ValueService valueService;

	@Override
	public void save(ExtendedInput input) throws ImportException, PflichtfeldException, RegexException {

		if (!InputTypeEnum.CONNECTOR_TEXT.equals(input.getInputTypeUniqName())) {
			throw new ImportException("Input " + input.getName() + " muss vom Typ "
					+ InputTypeEnum.CONNECTOR_TEXT.name() + " sein.");
		}

		valueService.save(input, value, false);
	}

	@Override
	public String toString() {

		return "SingleLineText(" + value + ")";
	}
}
