package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.COURSEOFSTUDYSERVICE_DEGREE;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@Component
public class DegreeDaoImpl extends BaseDaoImpl<Degree> implements DegreeDao {

	/**
	 * Instanziert das Dao
	 */
	public DegreeDaoImpl() {

		super(Degree.class, COURSEOFSTUDYSERVICE_DEGREE, COURSEOFSTUDYSERVICE_DEGREE.ID, false);
	}

	@Override
	public Degree fetchByUniquename(String uniquename) {

		return context	.select().from(COURSEOFSTUDYSERVICE_DEGREE)
						.where(COURSEOFSTUDYSERVICE_DEGREE.UNIQUENAME.eq(uniquename)).fetchAnyInto(typeParameterClass);
	}

	@Override
	public String fetchUniquenameById(Integer id) {

		return context	.select().from(COURSEOFSTUDYSERVICE_DEGREE).where(COURSEOFSTUDYSERVICE_DEGREE.ID.eq(id))
						.fetchAnyInto(typeParameterClass).getUniquename();
	}

}
