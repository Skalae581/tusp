package de.tuhh.tusp.exa.pvsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.pvsb.dao.PruefervertreterDao;
import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;

@Component
public class PruefervertreterServiceImpl implements PruefervertreterService {

	@Autowired
	private PruefervertreterDao pruefervertreterDao;

	@Override
	public int delete(Long id) {

		return pruefervertreterDao.delete(id);
	}

	@Override
	public Pruefervertreter fetchById(Long id) {

		return pruefervertreterDao.fetchById(id);
	}

	@Override
	public List<Pruefervertreter> findAll() {

		return pruefervertreterDao.findAll();
	}

	@Override
	public List<Pruefervertreter> findAllRequests() {

		return pruefervertreterDao.findAllRequests();
	}

	// @Override
	// public void sendMail(String to, String from, String subject, String body)
	// {
	//
	// try {
	// mailer.sendHTMLMail(to, from, subject, body);
	// }
	// catch (AddressException e) {
	// logger.error("Konnte Mail nicht senden, Adresse falsch - " + e);
	// }
	// catch (MessagingException e) {
	// logger.error("Konnte Mail nicht senden - " + e);
	// }
	// }

	@Override
	public Long insert(Pruefervertreter pruefervertreter) {

		return pruefervertreterDao.insert(pruefervertreter);
	}

	@Override
	public void update(Pruefervertreter pruefervertreter) {

		pruefervertreterDao.update(pruefervertreter);

	}

}
