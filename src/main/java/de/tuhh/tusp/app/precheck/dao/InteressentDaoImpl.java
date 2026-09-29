package de.tuhh.tusp.app.precheck.dao;

import static org.jooq.generated.Tables.PRECHECK_INTERESSENT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.TableField;
import org.jooq.generated.Tables;
import org.jooq.generated.tables.PrecheckStatus;
import org.jooq.generated.tables.PrecheckStudiengang;
import org.jooq.generated.tables.ValueserviceCountries;
import org.jooq.generated.tables.records.PrecheckInteressentRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.pojo.Interessent;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class InteressentDaoImpl extends BaseDaoImpl<Interessent> implements InteressentDao {

	public InteressentDaoImpl() {
		super(Interessent.class, PRECHECK_INTERESSENT, PRECHECK_INTERESSENT.ID);
	}

	public List<ExtendedInteressent> filterSelect(String countryId, String statusId, String stgId, String done,
			String zwilinge, String search, int elementList, int currentPage,
			TableField<PrecheckInteressentRecord, ?> order, List<Long> ids) {

		ValueserviceCountries study = Tables.VALUESERVICE_COUNTRIES.as("STUDY");
		ValueserviceCountries birth = Tables.VALUESERVICE_COUNTRIES.as("BIRTH");
		PrecheckStudiengang stg_tuhh = Tables.PRECHECK_STUDIENGANG.as("stg_tuhh");
		PrecheckStatus status = Tables.PRECHECK_STATUS.as("statusName");

		return context.select(PRECHECK_INTERESSENT.fields())
				.select(Tables.VALUESERVICE_GENDER.DEFAULTTEXT_EN.as("gender_name"),

						study.DEFAULTTEXT_EN.as("study_country"), birth.DEFAULTTEXT_EN.as("birth_country"),
						stg_tuhh.SHORTTEXT.as("stg_tuhh"), status.NAME.as("statusName"))

				.from(PRECHECK_INTERESSENT, Tables.VALUESERVICE_GENDER, study, birth, stg_tuhh, status)

				.where(Tables.VALUESERVICE_GENDER.ID.eq(PRECHECK_INTERESSENT.GESCHLECHT)
						.and(PRECHECK_INTERESSENT.STUDIENLAND.eq(study.ID))

						.and(PRECHECK_INTERESSENT.GEBURTSLAND.eq(birth.ID))
						.and(PRECHECK_INTERESSENT.STG_TUHH.eq(stg_tuhh.COURSEOFSTUDY_ID)
								.and(PRECHECK_INTERESSENT.STATUS.eq(status.ID))
								.and(!countryId.equals("0")
										? PRECHECK_INTERESSENT.STUDIENLAND.eq(Long.parseLong(countryId))
										: DSL.trueCondition())
								.and(!stgId.equals("0") ? PRECHECK_INTERESSENT.STG_TUHH.eq(Long.parseLong(stgId))
										: DSL.trueCondition())
								.and(!statusId.equals("0") ? PRECHECK_INTERESSENT.STATUS.eq(Long.parseLong(statusId))
										: DSL.trueCondition()))

						.and(search != null && !search.isEmpty()
								? PRECHECK_INTERESSENT.NACHNAME.likeIgnoreCase("%" + search + "%")
										.or(PRECHECK_INTERESSENT.VORNAME.likeIgnoreCase("%" + search + "%"))
										.or(PRECHECK_INTERESSENT.EMAIL.likeIgnoreCase("%" + search + "%"))
								: DSL.trueCondition())

						.and(!done.equals("b") ? PRECHECK_INTERESSENT.BEARBEITET.eq(done) : DSL.trueCondition()))
				.and(ids == null ? DSL.trueCondition() : PRECHECK_INTERESSENT.ID.in(ids)).orderBy(order.asc())
				.limit(elementList).offset(elementList * (currentPage - 1)).fetchInto(ExtendedInteressent.class);

	}

	@Override
	public List<ExtendedInteressent> findAllFilteredExtended(String countryId, String statusId, String stgId,
			String done, String zwilinge, String search, int elementList, int currentPage,
			TableField<PrecheckInteressentRecord, ?> order, boolean doubles, boolean doubles_mail) {

		if (elementList == 0) {
			elementList = 10000;
		}

		Result<Record> records = null;

		if (doubles_mail) {

			records = context.fetch("select id from precheck_interessent p where (select count(*) "
					+ "from precheck_interessent inr where inr.email = p.email) > 1");

			List<Long> duplicates = getResult(records);

			return filterSelect(countryId, statusId, stgId, done, zwilinge, search, elementList, currentPage, order,
					duplicates);

		} else if (doubles) {

			records = context.fetch("select id from precheck_interessent p where "
					+ "(select count(*) from precheck_interessent inr " + "where inr.vorname = p.vorname"
					+ " and inr.nachname = p.nachname " + "and inr.geburtsdatum = p.geburtsdatum) > 1");

			List<Long> duplicates = getResult(records);

			return filterSelect(countryId, statusId, stgId, done, zwilinge, search, elementList, currentPage, order,
					duplicates);
		} else {

			return filterSelect(countryId, statusId, stgId, done, zwilinge, search, elementList, currentPage, order,
					null);
		}

	}

	@Override
	public List<Map<String, Object>> findAllStatuses() {

		return context.select(PRECHECK_INTERESSENT.STATUS)

				.from(PRECHECK_INTERESSENT).fetchMaps();

	}

	@Override
	public Record1<String> findCountryNameById(Long id) {

		return context.selectDistinct(Tables.VALUESERVICE_COUNTRIES.DEFAULTTEXT_EN)

				.from(Tables.VALUESERVICE_COUNTRIES)

				.where(Tables.VALUESERVICE_COUNTRIES.ID.eq(id)).fetchAny();

	}

	@Override
	public Record1<String> findGenderNameById(Integer id) {

		return context.selectDistinct(Tables.VALUESERVICE_GENDER.DEFAULTTEXT_EN)

				.from(Tables.VALUESERVICE_GENDER)

				.where(Tables.VALUESERVICE_GENDER.ID.eq(id)).fetchAny();

	}

	@Override
	public List<Map<String, Object>> findGenderNames() {

		return context.select(Tables.VALUESERVICE_GENDER.ID, Tables.VALUESERVICE_GENDER.DEFAULTTEXT_DE)

				.from(Tables.VALUESERVICE_GENDER, PRECHECK_INTERESSENT)

				.where(Tables.VALUESERVICE_GENDER.ID.eq(PRECHECK_INTERESSENT.GESCHLECHT)).fetchMaps();

	}

	@Override
	public boolean getCodeIfExists(String code) {

		List<ExtendedInteressent> codeList = context

				.select(PRECHECK_INTERESSENT.VERIFIER_CODE)

				.from(PRECHECK_INTERESSENT)

				.where(PRECHECK_INTERESSENT.VERIFIER_CODE.eq(code))

				.fetchInto(ExtendedInteressent.class);

		return codeList != null && codeList.size() > 0;
	}

	private List<Long> getResult(Result<Record> records) {

		List<Long> result = new ArrayList<>();

		for (int i = 0; i < records.size(); i++) {

			Record r = records.get(i);
			result.add(Long.parseLong(r.get("id").toString()));
		}

		return result;

	}

}
