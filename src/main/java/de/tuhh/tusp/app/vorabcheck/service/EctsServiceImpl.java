package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.EctsDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;

@Component
public class EctsServiceImpl implements EctsService {

	@Autowired
	private EctsDao ectsDao;

	@Override
	public int delete(Long id) {

		return ectsDao.delete(id);
	}

	@Override
	public List<Ects> fetchByBewId(Long bewId) {

		return ectsDao.fetchByBewId(bewId);
	}

	@Override
	public Ects fetchById(Long id) {

		return ectsDao.fetchById(id);
	}

	@Override
	public List<Ects> findAll() {

		return ectsDao.findAll();
	}

	@Override
	public Long insert(Ects e) {

		return ectsDao.insert(e);
	}

	@Override
	public void update(Ects object) {

		ectsDao.update(object);
	}

}
