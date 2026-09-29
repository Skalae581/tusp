package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;

@Component
public class CourseOfStudyDaoImpl extends BaseDaoImpl<CourseOfStudy> implements CourseOfStudyDao {

	/**
	 * Instanziert das Dao
	 */
	public CourseOfStudyDaoImpl() {

		super(CourseOfStudy.class, COURSEOFSTUDYSERVICE_COURSEOFSTUDY, COURSEOFSTUDYSERVICE_COURSEOFSTUDY.ID, false);
	}

	@Override
	public List<CourseOfStudy> findByStgUndAbschl(String stg, String abschl) {

		return context	.select().from(COURSEOFSTUDYSERVICE_COURSEOFSTUDY).where(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.VALID)
						.and(COURSEOFSTUDYSERVICE_COURSEOFSTUDY.UNIQUENAME.like(abschl + "|" + stg + "|%"))
						.fetchInto(typeParameterClass);
	}

}
