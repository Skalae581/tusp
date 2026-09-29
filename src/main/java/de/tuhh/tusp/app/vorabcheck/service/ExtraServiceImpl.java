package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.ExtraDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;

@Component
public class ExtraServiceImpl implements ExtraService {

	@Autowired
	private ExtraDao extraDao;

	@Override
	public int delete(Long id) {

		return extraDao.delete(id);
	}

	@Override
	public Extra fetchById(Long id) {

		return extraDao.fetchById(id);
	}

	@Override
	public List<Extra> findAll() {

		return extraDao.findAll();
	}

	@Override
	public Long insert(Extra object) {

		return extraDao.insert(object);
	}

	@Override
	public void update(Extra object) {

		extraDao.update(object);
	}
}
