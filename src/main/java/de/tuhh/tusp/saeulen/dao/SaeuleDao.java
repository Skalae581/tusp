package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.Saeule;
import de.tuhh.tusp.utils.BaseDao;

public interface SaeuleDao extends BaseDao<Saeule> {

	List<Saeule> findByBezeichnung(String name);

}
