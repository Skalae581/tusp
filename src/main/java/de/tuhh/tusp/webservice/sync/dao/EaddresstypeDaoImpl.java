package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Eaddresstype;

@Component
public class EaddresstypeDaoImpl extends BaseDaoImpl<Eaddresstype> implements EaddresstypeDao {

	/**
	 * Instanziert das Dao
	 */
	public EaddresstypeDaoImpl() {
		super(Eaddresstype.class, VALUESERVICE_EADDRESSTYPE, VALUESERVICE_EADDRESSTYPE.ID, false);
	}

	@Override
	public Eaddresstype fetchByUniquename(String uniqueName) {

		return context	.select().from(VALUESERVICE_EADDRESSTYPE)
						.where(VALUESERVICE_EADDRESSTYPE.UNIQUENAME.eq(uniqueName)).fetchAnyInto(typeParameterClass);
	}

}
