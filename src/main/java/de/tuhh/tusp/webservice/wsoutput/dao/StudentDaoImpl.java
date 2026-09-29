package de.tuhh.tusp.webservice.wsoutput.dao;

import org.jooq.Record1;
import org.jooq.generated.tables.PsvStudent;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.wsoutput.pojo.Student;

@Component
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	/**
	 * Instanziert das Dao
	 */
	public StudentDaoImpl() {

		super(Student.class, PsvStudent.PSV_STUDENT, PsvStudent.PSV_STUDENT.ID, false);

	}

	@Override
	public Long getPersonIdFromMtknrId(Long mtknr) {

		if (mtknr == null) {
			return null;
		}

		Record1<Long> result = context	.select(PsvStudent.PSV_STUDENT.PERSON_ID).from(table)
										.where(PsvStudent.PSV_STUDENT.MTKNR.eq(mtknr)).fetchAny();

		if (result == null) {
			return null;
		}

		return result.value1();

	}
}
