package de.tuhh.tusp.app.fame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;

@Component
public class StatusBerechtigungServiceImpl implements StatusBerechtigungService {

	@Autowired
	private StatusBerechtigungDao dao;

	@Override
	public int delete(Long id) {

		return dao.delete(id);
	}

	@Override
	public StatusBerechtigung fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public List<StatusBerechtigung> findAll() {

		return dao.findAll();
	}

	@Override
	public List<StatusBerechtigung> findByWorkflow(Long id) {

		return dao.findByWorkflow(id);
	}

	@Override
	public Long insert(StatusBerechtigung statusBerechtigung) {

		return dao.insert(statusBerechtigung);
	}

	@Override
	public void update(StatusBerechtigung statusBerechtigung) {

		dao.update(statusBerechtigung);
	}

}
