package de.tuhh.tusp.global.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class IdentityDaoImpl extends BaseDaoImpl<Identity> implements IdentityDao {

	/**
	 * Erstellt eien Instanz
	 */
	public IdentityDaoImpl() {
		super(Identity.class, GLOBAL_IDENTITY, GLOBAL_IDENTITY.ID);
	}

	@Override
	public List<Identity> findValid() {

		return context.select().from(table).where(GLOBAL_IDENTITY.VALID.equal(true)).fetchInto(Identity.class);
	}

}
