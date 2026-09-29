package de.tuhh.tusp.exa.massmails.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.pojo.MailProtokoll;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class MailProtokollDaoImpl extends BaseDaoImpl<MailProtokoll> implements MailProtokollDao {

	public MailProtokollDaoImpl() {
		super(MailProtokoll.class, Tables.MAILPROTOKOLL, Tables.MAILPROTOKOLL.ID);
	}

}
