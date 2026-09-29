package de.tuhh.tusp.autovl.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.dao.BuchungDao;
import de.tuhh.tusp.autovl.pojo.Buchung;

@Component
public class BuchungServiceImpl implements BuchungService {

	private static Logger logger = LogManager.getLogger(BuchungServiceImpl.class);

	@Autowired
	private BuchungDao BuchungDao;

	@Override
	public void delete(Long id) {

		BuchungDao.delete(id);
	}

	@Override
	public Buchung fetchById(Long id) {

		return BuchungDao.fetchById(id);
	}

	@Override
	public List<Buchung> findAll() {

		return BuchungDao.findAll();
	}

	@Override
	public int getCount() {

		return BuchungDao.getCount();
	}

	@Override
	public void insert(Buchung Buchung) {

		BuchungDao.insert(Buchung);

	}

	@Override
	public void update(Buchung Buchung) {

		BuchungDao.update(Buchung);
	}
}