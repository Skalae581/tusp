package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.District;

@Component
public class DistrictDaoImpl extends BaseDaoImpl<District> implements DistrictDao {

	/**
	 * Instanziert das Dao
	 */
	public DistrictDaoImpl() {
		super(District.class, VALUESERVICE_DISTRICTVALUE, VALUESERVICE_DISTRICTVALUE.ID, false);
	}

	@Override
	public District fetchByUniquename(String uniquename) {

		return context	.select().from(VALUESERVICE_DISTRICTVALUE)
						.where(VALUESERVICE_DISTRICTVALUE.UNIQUENAME.eq(uniquename)).fetchAnyInto(typeParameterClass);
	}
}
