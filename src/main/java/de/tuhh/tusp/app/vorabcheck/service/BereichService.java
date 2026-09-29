package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.dao.BereichDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.utils.CrudService;

public interface BereichService extends CrudService<Bereich> {

	/**
	 * @see BereichDao
	 *
	 * @param stgId
	 * @return Bereiche
	 */
	List<Bereich> findAllByStgId(Long stgId);

	/**
	 * @see BereichDao
	 *
	 * @param stgId
	 * @return BereichsIDs
	 */
	List<Long> getBereichIdsById(Long stgId);
}
