package de.tuhh.tusp.app.precheck.dao;

import static org.jooq.generated.Tables.PRECHECK_NOTE;

import org.jooq.Record1;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.pojo.CountryGrades;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class GradeDaoImpl extends BaseDaoImpl<CountryGrades> implements GradeDao {

	/**
	 * Construct
	 */
	public GradeDaoImpl() {
		super(CountryGrades.class, PRECHECK_NOTE, PRECHECK_NOTE.ID);
	}

	@Override
	public Float getGradeForCountry(Long id) {

		if (id == null) {
			return null;
		}

		Record1<Float> result = context.selectDistinct(PRECHECK_NOTE.NOTE)

				.from(PRECHECK_NOTE)

				.where(PRECHECK_NOTE.COUNTRIES.eq(id)).fetchAny();

		if (result == null) {
			return null;
		}
		return result.value1();
	}
}
