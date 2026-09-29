package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.VALUESERVICE_ACADEMICDEGREE;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;

@Component
public class AcademicdegreeDaoImpl extends BaseDaoImpl<Academicdegree> implements AcademicdegreeDao {

	/**
	 * Instanziert das Dao
	 */
	public AcademicdegreeDaoImpl() {
		super(Academicdegree.class, VALUESERVICE_ACADEMICDEGREE, VALUESERVICE_ACADEMICDEGREE.ID, false);
	}

}
