package de.tuhh.tusp.exa.massmails.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Controller;

import de.tuhh.tusp.exa.massmails.pojo.MailErrors;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Controller
public class MailErrorsDaoImpl extends BaseDaoImpl<MailErrors> implements MailErrorsDao {

	public MailErrorsDaoImpl() {
		super(MailErrors.class, Tables.MAILERRORS, Tables.MAILERRORS.ID);
	}

	@Override
	public List<MailErrors> findForUid(String uid) {

		return context.select(Tables.MAILERRORS.fields()).from(Tables.MAILERRORS).where(Tables.MAILERRORS.UID.eq(uid))
				.fetchInto(MailErrors.class);
	}

}