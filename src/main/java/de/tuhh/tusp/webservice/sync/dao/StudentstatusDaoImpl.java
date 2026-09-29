package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Studentstatus;

@Component
public class StudentstatusDaoImpl extends BaseDaoImpl<Studentstatus> implements StudentstatusDao {

	/**
	 * Instanziert das Dao
	 */
	public StudentstatusDaoImpl() {
		super(	Studentstatus.class, Tables.VALUESERVICE_STUDENTSTATUSVALUE,
				Tables.VALUESERVICE_STUDENTSTATUSVALUE.ID, false);
	}
}
