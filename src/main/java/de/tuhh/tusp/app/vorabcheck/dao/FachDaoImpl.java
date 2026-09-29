package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class FachDaoImpl extends BaseDaoImpl<Fach> implements FachDao {

	/**
	 * Super-Konstruktor
	 */
	public FachDaoImpl() {
		super(Fach.class, VORABCHECK_FACH, VORABCHECK_FACH.ID, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fach> findAll() {

		return context.select().from(table).orderBy(idColumn).fetchInto(typeParameterClass);
	}

	@Override
	public List<Fach> findAllByBereichId(Long bereichId) {

		return context	.select().from(table).where(VORABCHECK_FACH.BEREICH_ID.in(bereichId))
						.fetchInto(typeParameterClass);
	}

	@Override
	public List<Fach> findAllByBereichIds(List<Long> bereichIds) {

		return context	.select().from(table).where(VORABCHECK_FACH.BEREICH_ID.in(bereichIds))
						.fetchInto(typeParameterClass);
	}

}
