package de.tuhh.tusp.stu.timsl.dao;

import static org.jooq.generated.Tables.TIM_SONDERLIEFERUNG;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.utils.CalendarUtils;

@Component
public class TimSonderStudentDaoImpl extends BaseDaoImpl<TimSonderStudent> implements TimSonderStudentDao {

	public TimSonderStudentDaoImpl() {

		super(TimSonderStudent.class, TIM_SONDERLIEFERUNG, TIM_SONDERLIEFERUNG.ID);
	}

	@Override
	public List<TimSonderStudent> findValid() {

		LocalDate now = CalendarUtils.toLocalDate(new java.util.Date());

		return context	.select().from(table).where(TIM_SONDERLIEFERUNG.STARTDATE.le(now))
						.and(TIM_SONDERLIEFERUNG.ENDDATE.ge(now)).fetchInto(TimSonderStudent.class);
	}

}
