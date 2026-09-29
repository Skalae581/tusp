package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.VORABCHECK_ECTS;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class EctsDaoImpl extends BaseDaoImpl<Ects> implements EctsDao {

	/**
	 * Super-Konstruktor
	 */
	public EctsDaoImpl() {

		super(Ects.class, VORABCHECK_ECTS, VORABCHECK_ECTS.ID, true);
	}

	@Override
	public List<Ects> fetchByBewId(Long bewId) {

		return context.select().from(table).where(VORABCHECK_ECTS.BEW_ID.eq(bewId)).fetchInto(typeParameterClass);
	}
}
