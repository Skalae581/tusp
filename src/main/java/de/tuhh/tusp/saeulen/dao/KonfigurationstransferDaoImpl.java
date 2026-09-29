package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Konfigurationstransfer;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class KonfigurationstransferDaoImpl extends BaseDaoImpl<Konfigurationstransfer>
		implements KonfigurationstransferDao {

	public KonfigurationstransferDaoImpl() {
		super(Konfigurationstransfer.class, Tables.KONFIGURATIONSTRANSFER, Tables.KONFIGURATIONSTRANSFER.ID);
	}

	@Override
	public List<Konfigurationstransfer> findByUID(Long UID) {

		return context.select(Tables.KONFIGURATIONSTRANSFER.fields()).from(Tables.KONFIGURATIONSTRANSFER)
				.where(Tables.KONFIGURATIONSTRANSFER.UID.eq(UID)).fetchInto(Konfigurationstransfer.class);

	}

	@Override
	public List<Konfigurationstransfer> findeKonfigurationstransferFuerDieQuelle(int saueleId) {

		return context.select(Tables.KONFIGURATIONSTRANSFER.fields()).from(Tables.KONFIGURATIONSTRANSFER)
				.where(Tables.KONFIGURATIONSTRANSFER.SAEULE_ID.eq(saueleId)).fetchInto(Konfigurationstransfer.class);

	}

}
