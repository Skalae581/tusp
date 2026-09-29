package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.VORABCHECK_BEREICH;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class BereichDaoImpl extends BaseDaoImpl<Bereich> implements BereichDao {

	/**
	 * Super-Konstruktor
	 */
	public BereichDaoImpl() {
		super(Bereich.class, VORABCHECK_BEREICH, VORABCHECK_BEREICH.ID, true);
	}

	@Override
	public List<Bereich> findAll() {

		return context.selectFrom(table).orderBy(VORABCHECK_BEREICH.ID).fetchInto(Bereich.class);
	}

	@Override
	public List<Bereich> findAllByStgId(Long stgId) {

		return context.select().from(table).where(VORABCHECK_BEREICH.STG_ID.eq(stgId)).orderBy(VORABCHECK_BEREICH.ID)
				.fetchInto(typeParameterClass);
	}

	@Override
	public List<Long> getBereichIdsById(Long stgId) {

		return context.select(VORABCHECK_BEREICH.ID).from(table).where(VORABCHECK_BEREICH.STG_ID.eq(stgId)).fetch()
				.getValues(VORABCHECK_BEREICH.ID, Long.class);
	}

}
