package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.FachDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;

@Component
public class FachServiceImpl implements FachService {

	@Autowired
	private FachDao fachDao;

	@Override
	public int delete(Long id) {

		return fachDao.delete(id);
	}

	@Override
	public Fach fetchById(Long id) {

		return fachDao.fetchById(id);
	}

	@Override
	public List<Fach> findAll() {

		return fachDao.findAll();
	}

	@Override
	public List<Fach> findAllByBereichId(Long bereichId) {

		return fachDao.findAllByBereichId(bereichId);
	}

	@Override
	public List<Fach> findAllByBereichIds(List<Long> bereichIds) {

		return fachDao.findAllByBereichIds(bereichIds);
	}

	// @Override
	// public ResultSet getById(Long id) {
	//
	// return fachDao.getById(id);
	// }

	@Override
	public Long insert(Fach b) {

		return fachDao.insert(b);
	}

	@Override
	public void update(Fach object) {

		fachDao.update(object);

	}

}
