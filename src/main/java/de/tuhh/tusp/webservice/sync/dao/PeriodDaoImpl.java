package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.PERIODSERVICE_PERIOD;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.pojo.Period;

@Component
public class PeriodDaoImpl extends BaseDaoImpl<Period> implements PeriodDao {

	/**
	 * Instanziert das Dao
	 */
	public PeriodDaoImpl() {

		super(Period.class, PERIODSERVICE_PERIOD, PERIODSERVICE_PERIOD.ID, false);
	}

	@Override
	// TODO Nicht über den UniqueName sondern den Typ filtern!
	public Period findSemesterzeitraumForDate(Date date) {

		LocalDate localDate = CalendarUtils.toLocalDate(date);

		return context	.select()

						.from(table)

						.where(PERIODSERVICE_PERIOD.STARTDATE.lessOrEqual(localDate))

						.and(PERIODSERVICE_PERIOD.ENDDATE.greaterOrEqual(localDate))

						.and(PERIODSERVICE_PERIOD.UNIQUENAME.like("%SZ%"))

						.fetchAnyInto(Period.class);
	}
}
