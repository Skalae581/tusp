package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class BewerberDaoImpl extends BaseDaoImpl<Bewerber> implements BewerberDao {

	/**
	 * Super-Konstruktor
	 */
	public BewerberDaoImpl() {
		super(Bewerber.class, VORABCHECK_BEWERBER, VORABCHECK_BEWERBER.ID, true);
	}

	@Override
	public List<String> fetchAllCodenummer() {

		return context.select().from(table).fetch().getValues(VORABCHECK_BEWERBER.CODENUMMER, String.class);
	}

	@Override
	public Bewerber fetchByCode(String bewHash) {

		return context	.select().from(table).where(VORABCHECK_BEWERBER.CODENUMMER.eq(bewHash))
						.fetchAnyInto(typeParameterClass);
	}

	@Override
	public Object findStgNameByCode(String bewHash) {

		return context	.select(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY)
						.join(VORABCHECK_STUDIENGANG)
						.on(VORABCHECK_STUDIENGANG.COURSEOFSTUDY_ID.eq(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID))
						.join(VORABCHECK_BEWERBER).on(VORABCHECK_BEWERBER.STG_ID.eq(VORABCHECK_STUDIENGANG.ID))
						.where(VORABCHECK_BEWERBER.CODENUMMER.eq(bewHash))
						.fetchOne(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE);
	}
}
