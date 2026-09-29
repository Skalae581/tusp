package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.FAME_ROLLEN;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class RollenDaoImpl extends BaseDaoImpl<Rollen> implements RollenDao {

	public RollenDaoImpl() {
		super(Rollen.class, FAME_ROLLEN, FAME_ROLLEN.ID);
	}
}
