package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Saeule;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class SaeuleDaoImpl extends BaseDaoImpl<Saeule> implements SaeuleDao {

	public SaeuleDaoImpl() {
		super(Saeule.class, Tables.SAEULE, Tables.SAEULE.ID);
	}

	@Override
	public List<Saeule> findByBezeichnung(String name) {
		return context.selectDistinct(Tables.SAEULE.fields()).from(Tables.SAEULE)
				.where(Tables.SAEULE.BEZEICHNUNG.eq(name)).fetchInto(Saeule.class);

	}

}
