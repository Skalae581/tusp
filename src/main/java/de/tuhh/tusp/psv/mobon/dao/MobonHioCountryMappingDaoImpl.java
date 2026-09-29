package de.tuhh.tusp.psv.mobon.dao;

//import de.tuhh.tusp.psv.mobon.pojo.MobonHioCountry;
//import de.tuhh.tusp.utils.BaseDaoImpl;

//import static org.jooq.generated.Tables.*;
//
//import org.springframework.stereotype.Component;
//
//import de.tuhh.tusp.psv.mobon.pojo.MobonHioCountry;
//import de.tuhh.tusp.utils.BaseDaoImpl;

//@Component
public class MobonHioCountryMappingDaoImpl /*
											 * extends BaseDaoImpl<MobonHioCountry> implements MobonHioCountryMappingDao
											 */ {
//
//	/**
//	 * Construct
//	 */
//	public MobonHioCountryMappingDaoImpl() {
//
//		super(MobonHioCountry.class, MOBON_HIO_COUNTRY_MAPPING, MOBON_HIO_COUNTRY_MAPPING.ID, false);
//	}
//
//	@Override
//	public Long fetchHioIdByMobonId(Long mobonId) {
//
//		Long id = context	.select(VALUESERVICE_COUNTRIES.ID).from(VALUESERVICE_COUNTRIES)
//							.leftJoin(MOBON_HIO_COUNTRY_MAPPING)
//							.on(VALUESERVICE_COUNTRIES.ISO3166_1_ALPHA.eq(MOBON_HIO_COUNTRY_MAPPING.HIS_ISO))
//							.where(MOBON_HIO_COUNTRY_MAPPING.MOBON_ID.eq(mobonId)).fetchAny().value1();
//
//		if (id == null) {
//			throw new CountryNotFoundException(mobonId + "");
//		}
//
//		return id;
//	}

}
