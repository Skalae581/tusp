package de.tuhh.tusp.webservice.sync.service;

import java.util.List;

import de.tuhh.tusp.webservice.sync.pojo.Degree;

public interface DegreeService extends WebserviceSync {

	/**
	 * Liefert alle Degrees
	 *
	 * @return Liste der Degrees
	 */
	List<Degree> findAll();
}
