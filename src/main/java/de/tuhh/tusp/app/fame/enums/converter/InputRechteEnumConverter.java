package de.tuhh.tusp.app.fame.enums.converter;

import org.springframework.core.convert.converter.Converter;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;

public class InputRechteEnumConverter
		implements Converter<String, InputRechteEnum>, org.jooq.Converter<String, InputRechteEnum> {

	private static final long serialVersionUID = 6249828199161615838L;

	@Override
	public InputRechteEnum convert(String arg0) {

		if (arg0 == null) {
			return InputRechteEnum.NONE;
		}

		for (InputRechteEnum recht : InputRechteEnum.getAll()) {
			if (arg0.equals(recht.getValue())) {
				return recht;
			}
		}
		return InputRechteEnum.NONE;
	}

	@Override
	public InputRechteEnum from(String arg0) {

		return convert(arg0);
	}

	@Override
	public Class<String> fromType() {

		return String.class;
	}

	@Override
	public String to(InputRechteEnum arg0) {

		return arg0.getValue();
	}

	@Override
	public Class<InputRechteEnum> toType() {

		return InputRechteEnum.class;
	}
}