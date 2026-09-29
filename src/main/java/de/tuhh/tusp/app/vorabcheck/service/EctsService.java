package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.dao.EctsDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.utils.CrudService;

public interface EctsService extends CrudService<Ects> {

	/**
	 * @see EctsDao
	 *
	 * @param bewId
	 * @return
	 */
	List<Ects> fetchByBewId(Long bewId);

}
