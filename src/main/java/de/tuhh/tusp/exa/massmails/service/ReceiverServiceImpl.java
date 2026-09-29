package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.dao.ReceiverDao;
import de.tuhh.tusp.exa.massmails.dao.ReceiverDao.Verteiler;
import de.tuhh.tusp.exa.massmails.pojo.Receiver;

@Component
public class ReceiverServiceImpl implements ReceiverService {

	@Autowired
	private ReceiverDao receiverDao;

	@Override
	public List<Receiver> findAll() {

		return receiverDao.findAll();
	}

	@Override
	public List<Receiver> findAllByName(String name) {

		return receiverDao.findAllByName(name);
	}

	@Override
	public List<Receiver> findAllByVerteilername(String name, Long semester) {

		return receiverDao.findAllByVerteilername(name, semester);
	}

	@Override
	public List<Verteiler> getVerteiler() {

		return receiverDao.getVerteiler();
	}

}
