package de.tuhh.tusp.autovl.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.dao.AutoDao;
import de.tuhh.tusp.autovl.pojo.Auto;

@Component
public class AutoServiceImpl implements AutoService {

	private static Logger logger = LogManager.getLogger(AutoServiceImpl.class);

	@Autowired
	private AutoDao AutoDao;

	@Override
	public void delete(Long id) {

		AutoDao.delete(id);
	}

	@Override
	public Auto fetchById(Long id) {

		return AutoDao.fetchById(id);
	}

	@Override
	public List<Auto> findAll() {

		return AutoDao.findAll();
	}

	@Override
	public int getCount() {

		return AutoDao.getCount();
	}

	@Override
	public void insert(Auto Auto) {

		AutoDao.insert(Auto);

	}

	@Override
	public void update(Auto Auto) {

		AutoDao.update(Auto);
	}
}