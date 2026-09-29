package de.tuhh.tusp.app.fame.enums;

import java.util.Arrays;
import java.util.List;

public enum InputRechteEnum {
	NONE("N"), READ("R"), SET("S"), WRITE("W");

	public static List<InputRechteEnum> getAll() {

		return Arrays.asList(InputRechteEnum.values());
	}

	private String value;

	InputRechteEnum(String value) {
		this.value = value;
	}

	public String getName() {

		return this.name();
	}

	public String getValue() {

		return value;
	}

	public String valueOf() {

		return value;

	}
}
