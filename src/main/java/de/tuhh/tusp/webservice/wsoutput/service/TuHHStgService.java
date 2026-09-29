package de.tuhh.tusp.webservice.wsoutput.service;

public interface TuHHStgService {

	/**
	 * Holt den Stg-Wert eines Studiengangs anhand der Uni-Astat-ID
	 *
	 * @param astatUni
	 * @return Stg oder null
	 */
	String getStgByAstatUni(String astatUni);

}
