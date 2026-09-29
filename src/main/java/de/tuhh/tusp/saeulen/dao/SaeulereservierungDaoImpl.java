package de.tuhh.tusp.saeulen.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.saeulen.pojo.Saeulenreservierung;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class SaeulereservierungDaoImpl extends BaseDaoImpl<Saeulenreservierung> implements SaeulereservierungDao {

	public SaeulereservierungDaoImpl() {
		super(Saeulenreservierung.class, Tables.SAEULENRESERVIERUNG, Tables.SAEULENRESERVIERUNG.ID);
	}

}
