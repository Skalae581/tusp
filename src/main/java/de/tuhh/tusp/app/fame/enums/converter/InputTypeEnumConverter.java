package de.tuhh.tusp.app.fame.enums.converter;

import org.jooq.impl.EnumConverter;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;

public class InputTypeEnumConverter extends EnumConverter<String, InputTypeEnum> {

	private static final long serialVersionUID = 6249828199161617838L;

	public InputTypeEnumConverter() {
		super(String.class, InputTypeEnum.class);
	}
}