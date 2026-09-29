package de.tuhh.tusp.exa.lvsb.dao;

import static org.jooq.generated.Tables.LVSB_ANTRAG;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class LehrvertreterDaoImpl extends BaseDaoImpl<Lehrvertreter> implements LehrvertreterDao {

	/**
	 * Super-Konstruktor
	 */
	public LehrvertreterDaoImpl() {

		super(Lehrvertreter.class, LVSB_ANTRAG, LVSB_ANTRAG.ID, true);
	}

	@Override
	public List<Lehrvertreter> findAllRequests() {

		return context	.selectFrom(table).orderBy(LVSB_ANTRAG.COMPLETE, LVSB_ANTRAG.TIMESTAMP_CONFIRM)
						.fetchInto(typeParameterClass);
	}

}
