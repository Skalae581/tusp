package de.tuhh.tusp.autovl.dao;

import static org.jooq.generated.Tables.AVL_BUCHUNG;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.pojo.Buchung;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class BuchungDaoImpl extends BaseDaoImpl<Buchung> implements BuchungDao {

	public BuchungDaoImpl() {
		super(Buchung.class, AVL_BUCHUNG, AVL_BUCHUNG.ID);
	}
}
