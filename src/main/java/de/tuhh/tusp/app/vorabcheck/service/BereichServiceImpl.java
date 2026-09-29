package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.BereichDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;

@Component
public class BereichServiceImpl implements BereichService {

	@Autowired
	private BereichDao bereichDao;

	@Override
	public int delete(Long id) {

		return bereichDao.delete(id);
	}

	@Override
	public Bereich fetchById(Long id) {

		return bereichDao.fetchById(id);
	}

	@Override
	public List<Bereich> findAll() {

		return bereichDao.findAll();
	}

	@Override
	public List<Bereich> findAllByStgId(Long stgId) {

		return bereichDao.findAllByStgId(stgId);
	}

	@Override
	public List<Long> getBereichIdsById(Long stgId) {

		return bereichDao.getBereichIdsById(stgId);
	}

	@Override
	public Long insert(Bereich b) {

		return bereichDao.insert(b);
	}

	@Override
	public void update(Bereich object) {

		bereichDao.update(object);

	}

}
