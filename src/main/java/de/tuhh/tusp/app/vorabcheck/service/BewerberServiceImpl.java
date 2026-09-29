package de.tuhh.tusp.app.vorabcheck.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.BewerberDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.utils.MD5;

@Component
public class BewerberServiceImpl implements BewerberService {

	@Autowired
	private BewerberDao bewerberDao;

	@Autowired
	private MD5 hashGenerator;

	@Override
	public int delete(Long id) {

		return bewerberDao.delete(id);
	}

	@Override
	public List<String> fetchAllCodenummer() {

		return bewerberDao.fetchAllCodenummer();
	}

	@Override
	public Bewerber fetchByCode(String bewHash) {

		return bewerberDao.fetchByCode(bewHash);
	}

	@Override
	public Bewerber fetchById(Long id) {

		return bewerberDao.fetchById(id);
	}

	@Override
	public List<Bewerber> findAll() {

		return bewerberDao.findAll();
	}

	@Override
	public Object findStgNameByCode(String bewHash) {

		return bewerberDao.findStgNameByCode(bewHash);
	}

	@Override
	public String hashCode(String stg, Date zeitstempel) {
		String combo = stg + zeitstempel.toString();
		return hashGenerator.md5(combo, 12);
	}

	@Override
	public String hashCode(String mailaddr, String stg, long zeitstempel) {

		String combo = mailaddr + stg + zeitstempel;
		return hashGenerator.md5(combo, 12);
	}

	@Override
	public Long insert(Bewerber object) {

		return bewerberDao.insert(object);
	}

	@Override
	public void update(Bewerber object) {

		bewerberDao.update(object);
	}

}
