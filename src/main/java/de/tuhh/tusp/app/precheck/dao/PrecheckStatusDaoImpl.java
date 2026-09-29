package de.tuhh.tusp.app.precheck.dao;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.pojo.Status;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class PrecheckStatusDaoImpl extends BaseDaoImpl<Status> implements PrecheckStatusDao {

	public PrecheckStatusDaoImpl() {
		super(Status.class, org.jooq.generated.Tables.PRECHECK_STATUS, org.jooq.generated.Tables.PRECHECK_STATUS.ID);
	}

}
