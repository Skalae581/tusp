package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Enrollment;

@Component
public class EnrollmentDaoImpl extends BaseDaoImpl<Enrollment> implements EnrollmentDao {

	/**
	 * Instanziert das Dao
	 */
	public EnrollmentDaoImpl() {
		super(	Enrollment.class, Tables.VALUESERVICE_ENROLLMENTVALUE, Tables.VALUESERVICE_ENROLLMENTVALUE.ID,
				false);
	}
}
