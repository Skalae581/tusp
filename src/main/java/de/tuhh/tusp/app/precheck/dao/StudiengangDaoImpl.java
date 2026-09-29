package de.tuhh.tusp.app.precheck.dao;

import java.util.List;
import java.util.Map;

import org.jooq.Record1;
import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StudiengangDaoImpl extends BaseDaoImpl<Studiengang> implements StudiengangDao {

	public StudiengangDaoImpl() {
		super(Studiengang.class, Tables.PRECHECK_STUDIENGANG, Tables.PRECHECK_STUDIENGANG.ID);
	}

	@Override
	public Studiengang fetchByCoursOfStudyId(Long id) {

		return context.select().from(table).where(Tables.PRECHECK_STUDIENGANG.COURSEOFSTUDY_ID.eq(id))
				.fetchAnyInto(typeParameterClass);
	}

	@Override
	public List<Studiengang> findByAktiv() {

		return context.select().from(table).where(Tables.PRECHECK_STUDIENGANG.AKTIV.eq(1))
				.fetchInto(typeParameterClass);
	}

	@Override
	public List<Map<String, Object>> findLabeldAll() {

		return context.select(Tables.PRECHECK_STUDIENGANG.AKTIV, Tables.PRECHECK_STUDIENGANG.ID)
				.select(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.DEFAULTTEXT_DE,
						Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.as("courseofstudy_id"))
				.from(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY)

				.leftJoin(table)
				.on(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(Tables.PRECHECK_STUDIENGANG.COURSEOFSTUDY_ID))

				.orderBy(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.DEFAULTTEXT_DE).fetchMaps();
	}

	@Override
	public Record1<String> findLabelNameById(Long id) {

		return context.selectDistinct(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.DEFAULTTEXT_EN)

				.from(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY)

				.where(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID.eq(id)).fetchAny();

	}

	@Override
	public List<Map<String, Object>> findLabelNames() {

		return context
				.select(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.DEFAULTTEXT_DE,
						Tables.PRECHECK_STUDIENGANG.COURSEOFSTUDY_ID, Tables.PRECHECK_STUDIENGANG.ID)

				.from(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY, Tables.PRECHECK_STUDIENGANG)
				.where(Tables.PRECHECK_STUDIENGANG.COURSEOFSTUDY_ID.eq(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID))
				.and(Tables.PRECHECK_STUDIENGANG.AKTIV.eq(1))

				.orderBy(Tables.COURSEOFSTUDYSERVICE_COURSEOFSTUDY.DEFAULTTEXT_DE).fetchMaps();
	}

}
