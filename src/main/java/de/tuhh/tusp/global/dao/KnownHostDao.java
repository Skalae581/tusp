package de.tuhh.tusp.global.dao;

import java.util.List;

import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.utils.BaseDao;

public interface KnownHostDao extends BaseDao<KnownHost> {

	/**
	 * Holt alle gültigen KnownHosts
	 *
	 * @return Liste der KnownHosts
	 */
	List<KnownHost> findValid();

}
