package de.tuhh.tusp.exa.lvsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.lvsb.dao.LehrvertreterDao;
import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.utils.MailUtils;

@Component
public class LehrvertreterServiceImpl implements LehrvertreterService {

	@Autowired
	private LehrvertreterDao lehrvertreterDao;

	@Autowired
	private MailUtils mailer;

	@Override
	public int delete(Long id) {

		return lehrvertreterDao.delete(id);
	}

	@Override
	public Lehrvertreter fetchById(Long id) {

		return lehrvertreterDao.fetchById(id);
	}

	@Override
	public List<Lehrvertreter> findAll() {

		return lehrvertreterDao.findAll();
	}

	@Override
	public List<Lehrvertreter> findAllRequests() {

		return lehrvertreterDao.findAllRequests();
	}

	@Override
	public Long insert(Lehrvertreter lehrvertreter) {

		return lehrvertreterDao.insert(lehrvertreter);
	}

	@Override
	public void update(Lehrvertreter lehrvertreter) {

		lehrvertreterDao.update(lehrvertreter);

	}

}
