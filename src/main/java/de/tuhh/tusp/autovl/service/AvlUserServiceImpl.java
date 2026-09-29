package de.tuhh.tusp.autovl.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.dao.AvlUserDao;
import de.tuhh.tusp.autovl.pojo.AvlUser;

@Component
public class AvlUserServiceImpl implements AvlUserService {

	private static Logger logger = LogManager.getLogger(AvlUserServiceImpl.class);

	@Autowired
	private AvlUserDao AvlUserDao;

	@Override
	public void delete(Long id) {

		AvlUserDao.delete(id);
	}

	@Override
	public AvlUser fetchById(Long id) {

		return AvlUserDao.fetchById(id);
	}

	@Override
	public List<AvlUser> findAll() {

		return AvlUserDao.findAll();
	}

	@Override
	public int getCount() {

		return AvlUserDao.getCount();
	}

	@Override
	public void insert(AvlUser AvlUser) {

		AvlUserDao.insert(AvlUser);

	}

	@Override
	public void update(AvlUser AvlUser) {

		AvlUserDao.update(AvlUser);
	}
}