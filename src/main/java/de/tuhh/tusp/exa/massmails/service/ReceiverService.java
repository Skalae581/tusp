package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import de.tuhh.tusp.exa.massmails.dao.ReceiverDao.Verteiler;
import de.tuhh.tusp.exa.massmails.pojo.Receiver;

public interface ReceiverService {

	List<Receiver> findAll();

	List<Receiver> findAllByName(String name);

	List<Receiver> findAllByVerteilername(String name, Long semester);

	List<Verteiler> getVerteiler();
}
