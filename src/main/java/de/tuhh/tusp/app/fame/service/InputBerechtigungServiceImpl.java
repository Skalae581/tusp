package de.tuhh.tusp.app.fame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;

@Component
public class InputBerechtigungServiceImpl implements InputBerechtigungService {

	@Autowired
	InputBerechtigungDao dao;

	@Override
	public void delete(Long id) {

		dao.delete(id);
	}

	@Override
	public List<InputBerechtigung> findByWorkflow(Long id) {

		return dao.findByWorkflow(id);
	}

	@Override
	public Long insert(InputBerechtigung s) {

		return dao.insert(s);
	}

	@Override
	public void update(InputBerechtigung s) {

		dao.update(s);
	}

}
