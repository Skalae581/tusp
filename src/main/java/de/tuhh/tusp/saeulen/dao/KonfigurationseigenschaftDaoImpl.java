package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Konfigurationseigenschaft;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class KonfigurationseigenschaftDaoImpl extends BaseDaoImpl<Konfigurationseigenschaft>
		implements KonfigurationseigenschaftDao {

	public KonfigurationseigenschaftDaoImpl() {
		super(Konfigurationseigenschaft.class, Tables.KONFIGURATIONSEIGENSCHAFT, Tables.KONFIGURATIONSEIGENSCHAFT.ID);
	}

	@Override
	public List<Konfigurationseigenschaft> findeAlleAndere() {

		return context.select(Tables.KONFIGURATIONSEIGENSCHAFT.fields()).from(Tables.KONFIGURATIONSEIGENSCHAFT)
				.where(Tables.KONFIGURATIONSEIGENSCHAFT.BRAUCHTQUELLE.isFalse())
				.fetchInto(Konfigurationseigenschaft.class);
	}

	@Override
	public List<Konfigurationseigenschaft> findeAlleFuerDieQuelle() {

		return context.select(Tables.KONFIGURATIONSEIGENSCHAFT.fields()).from(Tables.KONFIGURATIONSEIGENSCHAFT)
				.where(Tables.KONFIGURATIONSEIGENSCHAFT.BRAUCHTQUELLE.isTrue())
				.fetchInto(Konfigurationseigenschaft.class);
	}

	@Override
	public String findNamebyId(Long id) {

		return context.select(Tables.KONFIGURATIONSEIGENSCHAFT.BEZEICHNUNG).from(Tables.KONFIGURATIONSEIGENSCHAFT)
				.where(Tables.KONFIGURATIONSEIGENSCHAFT.ID.eq(id)).fetchInto(Konfigurationseigenschaft.class).get(0)
				.getBezeichnung();

	}
}
