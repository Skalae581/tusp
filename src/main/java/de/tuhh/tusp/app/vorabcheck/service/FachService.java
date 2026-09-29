package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import de.tuhh.tusp.app.vorabcheck.dao.FachDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.utils.CrudService;

public interface FachService extends CrudService<Fach> {
	/**
	 * @see FachDao
	 *
	 * @param bereichId
	 * @return
	 */
	List<Fach> findAllByBereichId(Long bereichId);

	/**
	 * @see FachDao
	 *
	 * @param bereichIds
	 * @return
	 */
	List<Fach> findAllByBereichIds(List<Long> bereichIds);

}
