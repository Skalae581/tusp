package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY;
import static org.jooq.generated.Tables.VORABCHECK_BEREICH;
import static org.jooq.generated.Tables.VORABCHECK_FACH;
import static org.jooq.generated.Tables.VORABCHECK_STUDIENGANG;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StudiengangsauswahlDaoImpl extends BaseDaoImpl<Studiengangsauswahl> implements StudiengangsauswahlDao {

	private static final int aktiv_vervollst = 1;

	/**
	 * Super-Konstruktor
	 */
	public StudiengangsauswahlDaoImpl() {

		super(Studiengangsauswahl.class, VORABCHECK_STUDIENGANG, VORABCHECK_STUDIENGANG.ID, true);
	}

	@Override
	public List<Map<String, Object>> findAllActiveVervollstWithoutStandardkz() {

		return context	.select(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).select(VORABCHECK_STUDIENGANG.ID)
						.select(VORABCHECK_STUDIENGANG.AKTIV_VERVOLLST).from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY)
						.join(table)
						.on(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(VORABCHECK_STUDIENGANG.COURSEOFSTUDY_ID))
						.where(VORABCHECK_STUDIENGANG.AKTIV_VERVOLLST.eq(aktiv_vervollst))
						.and(VORABCHECK_STUDIENGANG.STANDARDKZ.isNull())
						.orderBy(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findAllBereichById(Long stgId) {

		return context	.select(VORABCHECK_BEREICH.fields()).from(VORABCHECK_BEREICH)
						.where(VORABCHECK_BEREICH.STG_ID.eq(stgId)).orderBy(VORABCHECK_BEREICH.ID).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findAllFachByIds(List<Long> bereichIds) {

		return context.select().from(VORABCHECK_FACH).where(VORABCHECK_FACH.BEREICH_ID.in(bereichIds)).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findAllStg() {

		return context	.select(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).select(VORABCHECK_STUDIENGANG.fields())
						.from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY).join(table)
						.on(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(VORABCHECK_STUDIENGANG.COURSEOFSTUDY_ID))
						.orderBy(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findAllStgAOS() {

		return context	.select(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).select(VORABCHECK_STUDIENGANG.fields())
						.from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY).join(table)
						.on(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(VORABCHECK_STUDIENGANG.COURSEOFSTUDY_ID))
						.where(VORABCHECK_STUDIENGANG.AOS.eq(true))
						.orderBy(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findAllStgMD() {

		return context	.select(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).select(VORABCHECK_STUDIENGANG.fields())
						.from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY).join(table)
						.on(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(VORABCHECK_STUDIENGANG.COURSEOFSTUDY_ID))
						.where(VORABCHECK_STUDIENGANG.AOS.eq(false))
						.orderBy(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findBereichStgId() {

		return context.select(VORABCHECK_BEREICH.STG_ID).from(VORABCHECK_BEREICH).fetchMaps();
	}

	@Override
	public String getShorttextDe(Long courseofstudyId) {

		return context	.select().from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY)
						.where(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(courseofstudyId))
						.fetchOne(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.SHORTTEXT_DE);
	}
}
