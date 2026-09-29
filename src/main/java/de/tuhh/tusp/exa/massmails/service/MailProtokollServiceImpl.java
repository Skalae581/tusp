package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.dao.MailProtokollDao;
import de.tuhh.tusp.exa.massmails.pojo.MailProtokoll;

@Component
public class MailProtokollServiceImpl implements MailProtokollService {

	@Autowired
	private MailProtokollDao mailProtokollDao;

	@Override
	public int delete(Long id) {

		return mailProtokollDao.delete(id);
	}

	@Override
	public MailProtokoll fetchById(Long id) {

		return mailProtokollDao.fetchById(id);
	}

	@Override
	public List<MailProtokoll> findAll() {

		return mailProtokollDao.findAll();
	}

	@Override
	public Long insert(MailProtokoll mailProtokoll) {

		return mailProtokollDao.insert(mailProtokoll);
	}

	@Override
	public void update(MailProtokoll mailProtokoll) {

		mailProtokollDao.update(mailProtokoll);

	}
}
