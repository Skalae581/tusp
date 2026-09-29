package de.tuhh.tusp.app.fame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.InputTypeDao;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.InputType;

@Component
public class InputTypeServiceImpl implements InputTypeService {

	@Autowired
	InputTypeDao dao;

	@Override
	public InputType fetchByName(String string) {

		return dao.fetchByName(string);
	}

	@Override
	public InputType fetchByUniqueName(InputTypeEnum name) {

		return dao.fetchByUniqueName(name);
	}

	@Override
	public List<InputType> findAll() {

		return dao.findAll();
	}

}
