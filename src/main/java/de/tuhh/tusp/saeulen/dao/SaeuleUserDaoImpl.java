package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.SaeuleUser;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class SaeuleUserDaoImpl extends BaseDaoImpl<SaeuleUser> implements SaeuleUserDao {

	public SaeuleUserDaoImpl() {
		super(SaeuleUser.class, Tables.SAEULE_USER, Tables.SAEULE_USER.ID);
	}

	@Override
	public List<SaeuleUser> findEmailFromId(Long id) {

		return context.select(Tables.SAEULE_USER.EMAIL).from(Tables.SAEULE_USER).where(Tables.SAEULE_USER.ID.eq(id))
				.fetchInto(SaeuleUser.class);

	}

	@Override
	public List<SaeuleUser> findIdByName(String name) {

		return context.select(Tables.SAEULE_USER.ID).from(Tables.SAEULE_USER)
				.where(Tables.SAEULE_USER.BENUTZERNAME.eq(name)).fetchInto(SaeuleUser.class);

	}

	@Override
	public List<SaeuleUser> getUsersForQuelle(String quelleId) {

		return context.select(Tables.SAEULE_USER.fields()).from(Tables.SAEULE_USER)
				.where(Tables.SAEULE_USER.SAEULE_ID.eq(Integer.parseInt(quelleId))).fetchInto(SaeuleUser.class);

	}

}
