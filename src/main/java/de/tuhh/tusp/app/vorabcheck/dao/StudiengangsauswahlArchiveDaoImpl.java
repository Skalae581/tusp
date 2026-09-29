package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.VORABCHECK_STUDIENGANG_ARCHIV;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StudiengangsauswahlArchiveDaoImpl extends BaseDaoImpl<StudiengangsauswahlArchive>
		implements StudiengangsauswahlArchiveDao {

	/**
	 * Super-Konstruktor
	 */
	public StudiengangsauswahlArchiveDaoImpl() {
		super(StudiengangsauswahlArchive.class, VORABCHECK_STUDIENGANG_ARCHIV, VORABCHECK_STUDIENGANG_ARCHIV.ID, true);
	}

	@Override
	public StudiengangsauswahlArchive fetchByOriginId(Long originId) {

		return context.select().from(table).where(VORABCHECK_STUDIENGANG_ARCHIV.ORIGIN_ID.eq(originId))
				.fetchAnyInto(typeParameterClass);

	}

	@Override
	public List<Map<String, Object>> findAllByOriginId(Long stgId) {
		return context.select(VORABCHECK_STUDIENGANG_ARCHIV.fields()).from(table)
				.where(VORABCHECK_STUDIENGANG_ARCHIV.ORIGIN_ID.eq(stgId)).fetchMaps();
	}

	@Override
	public List<Map<String, Object>> findArchiveOriginIds() {
		return context.select(VORABCHECK_STUDIENGANG_ARCHIV.ORIGIN_ID).from(table).fetchMaps();
	}

	@Override
	public StudiengangsauswahlArchive getNewData(Long newStgId) {
		return context.select(VORABCHECK_STUDIENGANG_ARCHIV.SESSION_ID).from(table)
				.where(VORABCHECK_STUDIENGANG_ARCHIV.ID.equal(newStgId)).fetchAnyInto(typeParameterClass);
	}
}
