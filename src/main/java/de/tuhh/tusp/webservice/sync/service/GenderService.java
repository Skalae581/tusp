package de.tuhh.tusp.webservice.sync.service;

import java.util.List;
import java.util.NoSuchElementException;

import de.tuhh.tusp.webservice.sync.pojo.Gender;

public interface GenderService extends WebserviceSync {

	/**
	 * Versucht anhand des String ein Gender zu laden.
	 *
	 * f, w, F, W und weiblich werden auf weiblich gematcht.
	 *
	 * m, M und männlich werden auf männlich gematcht
	 *
	 * u und U werden auf Undefined gematcht.
	 *
	 * null bleibt null
	 *
	 * @param string
	 * @return das Passende Gender-Objekt
	 * @throws NoSuchElementException
	 *             Wenn kein Gender ermittelt werden konnte
	 */
	Gender fetchByString(String string) throws NoSuchElementException;

	/**
	 * Holt alle Gender
	 *
	 * @return Liste der Gender
	 */
	List<Gender> findAll();

}
