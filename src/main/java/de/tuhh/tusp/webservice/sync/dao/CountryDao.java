package de.tuhh.tusp.webservice.sync.dao;

import java.util.List;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;

public interface CountryDao extends BaseDao<Country> {

	/**
	 * Liefert ein Country anhand des Namen
	 *
	 * @param uniqueName
	 * @return Country
	 */
	Country fetchByDefaultnameDe(String uniqueName);

	/**
	 *
	 * @param unName
	 * @return das Country
	 */
	Country fetchByUniquename(String unName);

	/**
	 * Liefert ein Country über ID
	 *
	 * @param id
	 * @return das Country
	 */
	Country fetchCountryById(Long id);

	/**
	 * Liefert ein Country über LID
	 *
	 * @param lid
	 * @return das Country
	 */
	Country fetchCountryByLid(Long lid);

	/**
	 * Liefert alle Countries in aufsdteigender Reihelfolge
	 *
	 * @return Liste der Countries
	 */
	List<Country> findAllAscending();
}
