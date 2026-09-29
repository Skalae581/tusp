package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Termtype;

@Component
public class TermtypeDaoImpl extends BaseDaoImpl<Termtype> implements TermtypeDao {

	/**
	 * Instanziert das Dao
	 */
	public TermtypeDaoImpl() {
		super(Termtype.class, Tables.VALUESERVICE_TERMTYPEVALUE, Tables.VALUESERVICE_TERMTYPEVALUE.ID, false);
	}
}
