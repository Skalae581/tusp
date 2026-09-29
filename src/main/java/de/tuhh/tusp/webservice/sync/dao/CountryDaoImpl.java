package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.VALUESERVICE_COUNTRIES;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Country;

@Component
public class CountryDaoImpl extends BaseDaoImpl<Country> implements CountryDao {

	/**
	 * Instanziert das Dao
	 */
	public CountryDaoImpl() {

		super(Country.class, VALUESERVICE_COUNTRIES, VALUESERVICE_COUNTRIES.ID, false);
	}

	@Override
	public Country fetchByDefaultnameDe(String defName) {

		Country c = context.select().from(VALUESERVICE_COUNTRIES)
				.where(VALUESERVICE_COUNTRIES.DEFAULTTEXT_DE.eq(defName)).fetchAnyInto(typeParameterClass);

		return c;

	}

	@Override
	public Country fetchByUniquename(String unName) {

		Country c = context.select().from(VALUESERVICE_COUNTRIES).where(VALUESERVICE_COUNTRIES.UNIQUENAME.eq(unName))
				.fetchAnyInto(typeParameterClass);

		return c;

	}

	@Override
	public Country fetchCountryById(Long id) {

		Country c = context.select().from(VALUESERVICE_COUNTRIES).where(VALUESERVICE_COUNTRIES.ID.eq(id))
				.fetchAnyInto(typeParameterClass);

		return c;

	}

	@Override
	public Country fetchCountryByLid(Long lid) {

		// FIXME Datentyp in der lid Spalte muss BIGINT werden!
		Country c = context.select().from(VALUESERVICE_COUNTRIES).where(VALUESERVICE_COUNTRIES.LID.eq(lid))
				.fetchAnyInto(typeParameterClass);

		return c;
	}

	@Override
	public List<Country> findAllAscending() {

		return context.select().from(VALUESERVICE_COUNTRIES).orderBy(VALUESERVICE_COUNTRIES.DEFAULTTEXT_DE)
				.fetchInto(typeParameterClass);
	}

}
