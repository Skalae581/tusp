package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Zustimmungen;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ZustimmungenDaoImpl extends BaseDaoImpl<Zustimmungen> implements ZustimmungenDao {

	public ZustimmungenDaoImpl() {
		super(Zustimmungen.class, Tables.ZUSTIMMUNGEN, Tables.ZUSTIMMUNGEN.ID);
	}

	@Override
	public List<Zustimmungen> findZustimmungenFromUID(Long UID) {

		return context.select(Tables.ZUSTIMMUNGEN.fields()).from(Tables.ZUSTIMMUNGEN)
				.where(Tables.ZUSTIMMUNGEN.UID.eq(UID)).fetchInto(Zustimmungen.class);

	}

}
