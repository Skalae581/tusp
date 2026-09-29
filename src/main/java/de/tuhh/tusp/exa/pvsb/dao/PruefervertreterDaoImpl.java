package de.tuhh.tusp.exa.pvsb.dao;

import static org.jooq.generated.Tables.PVSB_ANTRAG;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class PruefervertreterDaoImpl extends BaseDaoImpl<Pruefervertreter> implements PruefervertreterDao {

	/**
	 * Super-Konstruktor
	 */
	public PruefervertreterDaoImpl() {

		super(Pruefervertreter.class, PVSB_ANTRAG, PVSB_ANTRAG.ID, true);
	}

	@Override
	public List<Pruefervertreter> findAllRequests() {

		return context	.selectFrom(table).orderBy(PVSB_ANTRAG.COMPLETE, PVSB_ANTRAG.TIMESTAMP_CONFIRM)
						.fetchInto(typeParameterClass);
	}

}
