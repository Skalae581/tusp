package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Subject;

@Component
public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao {

	/**
	 * Instanziert das Dao
	 */
	public SubjectDaoImpl() {
		super(Subject.class, VALUESERVICE_SUBJECT, VALUESERVICE_SUBJECT.ID, false);
	}

	@Override
	public Subject fetchByUniquename(String uniquename) {

		return context	.select().from(VALUESERVICE_SUBJECT).where(VALUESERVICE_SUBJECT.UNIQUENAME.eq(uniquename))
						.fetchAnyInto(typeParameterClass);
	}
}
