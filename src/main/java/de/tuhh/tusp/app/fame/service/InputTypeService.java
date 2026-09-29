package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.InputType;

public interface InputTypeService {

	InputType fetchByName(String string);

	InputType fetchByUniqueName(InputTypeEnum name);

	List<InputType> findAll();

}
