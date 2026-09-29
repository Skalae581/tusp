package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.dao.MailErrorsDao;
import de.tuhh.tusp.exa.massmails.pojo.MailErrors;

@Component
public class MailErrorsServiceImpl implements MailErrorsService {

	@Autowired
	private MailErrorsDao mailErrorsDao;

	@Override
	public List<MailErrors> findAll() {

		return mailErrorsDao.findAll();
	}

	@Override
	public List<MailErrors> findForUid(String uid) {
		return mailErrorsDao.findForUid(uid);
	}

	@Override
	public Long insert(MailErrors mailErrors) {

		return mailErrorsDao.insert(mailErrors);
	}

}
