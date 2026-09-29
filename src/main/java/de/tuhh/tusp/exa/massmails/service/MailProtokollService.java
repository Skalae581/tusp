package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import de.tuhh.tusp.exa.massmails.pojo.MailProtokoll;

public interface MailProtokollService {

	int delete(Long id);

	MailProtokoll fetchById(Long id);

	List<MailProtokoll> findAll();

	Long insert(MailProtokoll mailProtokoll);

	void update(MailProtokoll mailProtokoll);
}
