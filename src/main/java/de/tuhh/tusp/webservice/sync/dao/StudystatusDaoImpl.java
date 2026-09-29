package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Studystatus;

@Component
public class StudystatusDaoImpl extends BaseDaoImpl<Studystatus> implements StudystatusDao {

	/**
	 * Instanziert das Dao
	 */
	public StudystatusDaoImpl() {
		super(	Studystatus.class, Tables.VALUESERVICE_STUDYSTATUSVALUE, Tables.VALUESERVICE_STUDYSTATUSVALUE.ID,
				false);
	}
}
