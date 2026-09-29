package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Addresstag;

@Component
public class AddresstagDaoImpl extends BaseDaoImpl<Addresstag> implements AddresstagDao {

	/**
	 * Instanziert das Dao
	 */
	public AddresstagDaoImpl() {
		super(Addresstag.class, VALUESERVICE_ADDRESSTAG, VALUESERVICE_ADDRESSTAG.ID, false);
	}

	@Override
	public Addresstag fetchByUniquename(String uniqueName) {

		return context	.select().from(VALUESERVICE_ADDRESSTAG).where(VALUESERVICE_ADDRESSTAG.UNIQUENAME.eq(uniqueName))
						.fetchAnyInto(typeParameterClass);
	}
}
