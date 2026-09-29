package de.tuhh.tusp.global.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.dao.IdentityDao;
import de.tuhh.tusp.global.pojo.Identity;

@Component
public class IdentityServiceImpl implements IdentityService {

	@Autowired
	private IdentityDao dao;

	@Override
	public int delete(Long id) {

		return dao.delete(id);
	}

	@Override
	public Identity fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public List<Identity> findAll() {

		return dao.findAll();
	}

	@Override
	public List<Identity> findValid() {

		return dao.findValid();
	}

	@Override
	public Long insert(Identity object) {

		return dao.insert(object);
	}

	@Override
	public void update(Identity object) {

		dao.update(object);
	}

}
