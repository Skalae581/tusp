package de.tuhh.tusp.webservice.sync.service;

import java.util.List;

import de.tuhh.tusp.webservice.sync.pojo.Country;

public interface CountryService extends WebserviceSync {

	/**
	 * Holt alle Länder
	 *
	 * @return die Länder
	 */
	List<Country> findAll();

	/**
	 * Holt alle Länder ASC
	 *
	 * @return die Lander
	 */
	List<Country> findAllAscending();

}
