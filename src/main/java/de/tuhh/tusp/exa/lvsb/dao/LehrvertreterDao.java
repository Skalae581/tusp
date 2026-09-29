package de.tuhh.tusp.exa.lvsb.dao;

import java.util.List;

import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.utils.BaseDao;

public interface LehrvertreterDao extends BaseDao<Lehrvertreter> {

	List<Lehrvertreter> findAllRequests();

}
