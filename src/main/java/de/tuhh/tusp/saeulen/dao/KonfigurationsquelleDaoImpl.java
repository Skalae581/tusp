package de.tuhh.tusp.saeulen.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Konfigurationsquelle;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class KonfigurationsquelleDaoImpl extends BaseDaoImpl<Konfigurationsquelle> implements KonfigurationsquelleDao {

	public KonfigurationsquelleDaoImpl() {
		super(Konfigurationsquelle.class, Tables.KONFIGURATIONSQUELLE, Tables.KONFIGURATIONSQUELLE.ID);
	}
}
