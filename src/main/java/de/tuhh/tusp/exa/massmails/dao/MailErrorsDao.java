package de.tuhh.tusp.exa.massmails.dao;

import java.util.List;

import de.tuhh.tusp.exa.massmails.pojo.MailErrors;
import de.tuhh.tusp.utils.BaseDao;

public interface MailErrorsDao extends BaseDao<MailErrors> {

	List<MailErrors> findForUid(String uid);

}