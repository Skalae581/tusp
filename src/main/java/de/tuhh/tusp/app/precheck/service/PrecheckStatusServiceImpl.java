package de.tuhh.tusp.app.precheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.dao.PrecheckStatusDao;
import de.tuhh.tusp.app.precheck.pojo.Status;

@Component
public class PrecheckStatusServiceImpl implements PrecheckStatusService {

	@Autowired
	private PrecheckStatusDao statusDao;

	@Override
	public void delete(Long id) {
		statusDao.delete(id);
	}

	@Override
	public Status fetchById(Long id) {

		return statusDao.fetchById(id);
	}

	@Override
	public List<Status> findAll() {

		return statusDao.findAll();
	}

	@Override
	public void insert(Status status) {

		statusDao.insert(status);
	}

	@Override
	public void update(Status status) {

		statusDao.update(status);
	}

}
