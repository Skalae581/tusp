package de.tuhh.tusp.exa.pvsb.dao;

import java.util.List;

import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;
import de.tuhh.tusp.utils.BaseDao;

public interface PruefervertreterDao extends BaseDao<Pruefervertreter> {

	List<Pruefervertreter> findAllRequests();

}
