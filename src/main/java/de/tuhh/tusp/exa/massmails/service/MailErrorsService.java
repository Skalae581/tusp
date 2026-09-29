package de.tuhh.tusp.exa.massmails.service;

import java.util.List;

import de.tuhh.tusp.exa.massmails.pojo.MailErrors;

public interface MailErrorsService {

	List<MailErrors> findAll();

	List<MailErrors> findForUid(String uid);

	Long insert(MailErrors mailErrors);

}
