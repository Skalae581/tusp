package de.tuhh.tusp.global.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.dao.KnownHostDao;
import de.tuhh.tusp.global.pojo.KnownHost;

@Component
public class KnownHostServiceImpl implements KnownHostService {

	@Autowired
	private KnownHostDao dao;

	@Override
	public int delete(Long id) {

		return dao.delete(id);
	}

	@Override
	public KnownHost fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public List<KnownHost> findAll() {

		return dao.findAll();
	}

	@Override
	public List<KnownHost> findValid() {

		return dao.findValid();
	}

	@Override
	public Long insert(KnownHost object) {

		return dao.insert(object);
	}

	@Override
	public void update(KnownHost object) {

		dao.update(object);
	}

}
