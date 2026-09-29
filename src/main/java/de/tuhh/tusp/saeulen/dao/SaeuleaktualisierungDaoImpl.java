package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Saeuleaktualisierung;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class SaeuleaktualisierungDaoImpl extends BaseDaoImpl<Saeuleaktualisierung> implements SaeuleaktualisierungDao {

	public SaeuleaktualisierungDaoImpl() {
		super(Saeuleaktualisierung.class, Tables.SAEULEAKTUALISIERUNG, Tables.SAEULEAKTUALISIERUNG.ID);
	}

	@Override
	public List<Saeuleaktualisierung> findByUID(Long UID) {

		return context.select(Tables.SAEULEAKTUALISIERUNG.fields()).from(Tables.SAEULEAKTUALISIERUNG)
				.where(Tables.SAEULEAKTUALISIERUNG.UID.eq(UID)).fetchInto(Saeuleaktualisierung.class);

	}

	@Override
	public List<Saeuleaktualisierung> findeAktualisierungenFuerDieQuelle(Long zielQuelleId) {

		return context.select(Tables.SAEULEAKTUALISIERUNG.fields()).from(Tables.SAEULEAKTUALISIERUNG)
				.where(Tables.SAEULEAKTUALISIERUNG.SAEULE_VON_ID.eq(zielQuelleId))
				.fetchInto(Saeuleaktualisierung.class);

	}

}
